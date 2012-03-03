package components 
{
	import events.ActionEvent;
	import flash.display.Shape;
	import flash.display.Sprite;
	import flash.events.Event;
	import flash.events.MouseEvent;
	import mx.accessibility.ButtonAccImpl;
	import mx.containers.Grid;
	import mx.containers.GridItem;
	import mx.containers.GridRow;
	import mx.containers.HBox;
	import mx.containers.TabNavigator;
	import mx.containers.VBox;
	import mx.controls.Button;
	import mx.controls.Label;
	import mx.controls.tabBarClasses.Tab;
	import mx.controls.Text;
	import mx.controls.ToggleButtonBar;
	import mx.core.Container;
	import mx.core.UIComponent;
	import common.*;
	import flash.display.BitmapData;
	import mx.events.IndexChangedEvent;
	import mx.states.State;
	import spark.components.ToggleButton;
	/**
	 * ...
	 * @author pablo
	 */
	public class Menu extends UIComponent
	{
		public static const MENU_MODE_MOVE:int = 0;
		public static const MENU_MODE_ROTATE:int = 1;
		public static const MENU_MODE_FIRE:int = 2;
		public static const MENU_POSITION_BOTTOM_LEFT:int = 0;
		public static const MENU_POSITION_BOTTOM_RIGHT:int = 1;
		public static const MENU_BIG_BUTTON_WIDTH:Number = 80;
		public static const MENU_BIG_BUTTON_HEIGHT:Number = 25;
		public static const MENU_SMALL_BUTTON_WIDTH:Number = 40;
		public static const MENU_SMALL_BUTTON_HEIGHT:Number = 25;
		
		public static const MENU_FIRE_MODE_BULLET:int = 1;
		public static const MENU_FIRE_MODE_TORPEDO:int = 0;
		
		private var _shape:Shape;
		private var _tabNavigator:TabNavigator;
		
		private var _currentMode:int = MENU_MODE_MOVE;
		private var _rotateButtons:Array;
		private var _currentFireMode:int = MENU_FIRE_MODE_BULLET;
		
		public function Menu(position:int) 
		{
			_shape = new Shape();
			_shape.graphics.beginFill(0x000000, 0.6);
			_shape.graphics.drawRoundRect(0, 0, 240, 200, 10, 10);
			if (position == MENU_POSITION_BOTTOM_LEFT) {
				left = 10;
				bottom = 230;
			}else if (position == MENU_POSITION_BOTTOM_RIGHT) {				
				right = 10;
				bottom = 230;
			}
			_shape.graphics.endFill();
			addChild(_shape);
			alpha = 0.8;
			
			createTabNavigator();
			createMoveTab();
			createRotateTab();
			createFireTab();
			dispatchModeChanged(MENU_MODE_MOVE);
			/*createModeButtons();
			createContainers();
			createRotationButtons();
			createFireButtons();*/
			
		}
		private function createTabNavigator():void {
			_tabNavigator = new TabNavigator();
			_tabNavigator.addEventListener(IndexChangedEvent.CHANGE, function (event:IndexChangedEvent):void {
				_currentMode = event.newIndex;
				dispatchModeChanged(event.newIndex);
			});
			_tabNavigator.x = 10;
			_tabNavigator.y = 10;
			_tabNavigator.width = 220;
			_tabNavigator.height = 180;
			addChild(_tabNavigator);
		}
		
		private function createMoveTab():void {
			var box:VBox = new VBox();
			box.label = "Mover";
			var text:Text = new Text();
			text.width = 210;
			text.text = "Selecciona uno de tus barcos para moverlo";
			box.addChild(text);
			_tabNavigator.addChild(box);	
		}
		
		private function createRotateTab():void {
			var box:VBox = new VBox();
			box.label = "Rotar";			
			var text:Text = new Text();
			text.width = 210;
			text.text = "Selecciona uno de tus barcos y presiona un boton para rotarlo";
			box.addChild(text);
			var grid:Grid = new Grid();
			grid.x = 30;
			var row:GridRow;
			var item:GridItem;
			var button:Button;
			
			//primera fila de botones
			row = new GridRow();
			item = new GridItem();			
			button = getButton("NO");
			button.addEventListener(MouseEvent.CLICK, function ():void { dispatchRotate(Cardinal.NW); } );
			item.addChild(button);
			row.addChild(item);			
			button = getButton("N");
			button.addEventListener(MouseEvent.CLICK, function ():void { dispatchRotate(Cardinal.N); } );
			item.addChild(button);
			row.addChild(item);			
			button = getButton("NE");
			button.addEventListener(MouseEvent.CLICK, function ():void { dispatchRotate(Cardinal.NE); } );
			item.addChild(button);
			row.addChild(item);			
			grid.addChild(row);
			//segunda fila de botones
			row = new GridRow();
			item = new GridItem();			
			button = getButton("O");
			button.addEventListener(MouseEvent.CLICK, function ():void { dispatchRotate(Cardinal.W); } );
			item.addChild(button);
			row.addChild(item);				
			button = getButton(" ");
			item.addChild(button);
			row.addChild(item);				
			button = getButton("E");
			button.addEventListener(MouseEvent.CLICK, function ():void { dispatchRotate(Cardinal.E); } );
			item.addChild(button);
			row.addChild(item);			
			grid.addChild(row);
			
			//tercer fila de botones
			row = new GridRow();
			item = new GridItem();			
			button = getButton("S0");
			button.addEventListener(MouseEvent.CLICK, function ():void { dispatchRotate(Cardinal.SW); } );
			item.addChild(button);
			row.addChild(item);			
			button = getButton("S");
			button.addEventListener(MouseEvent.CLICK, function ():void { dispatchRotate(Cardinal.S); } );
			item.addChild(button);
			row.addChild(item);			
			button = getButton("SE");
			button.addEventListener(MouseEvent.CLICK, function ():void { dispatchRotate(Cardinal.SE); } );
			item.addChild(button);
			row.addChild(item);			
			grid.addChild(row);
			
			box.addChild(grid);			
			
			_tabNavigator.addChild(box);	
		}
		
		private function createFireTab():void {
			var box:VBox = new VBox();
			box.label = "Disparar";		
			var text:Text = new Text();
			text.width = 210;
			text.text = "Selecciona uno de tus barcos para dispara, luego haz click en el objetivo";
			box.addChild(text);
			
			var hbox:HBox = new HBox();
			var button1:Button;
			var button2:Button;
			button1 = getButton("Cañon", MENU_BIG_BUTTON_WIDTH);
			button1.toggle = true;
			button1.addEventListener(MouseEvent.CLICK, function ():void {
				button2.selected = false;
				_currentFireMode = MENU_FIRE_MODE_BULLET;
				dispatchFireMode(MENU_FIRE_MODE_BULLET); 
			} );
			hbox.addChild(button1);			
			button2 = getButton("Torpedo", MENU_BIG_BUTTON_WIDTH);
			button2.toggle = true;
			button2.addEventListener(MouseEvent.CLICK, function ():void {
				button1.selected = false;
				_currentFireMode = MENU_FIRE_MODE_TORPEDO;
				dispatchFireMode(MENU_FIRE_MODE_TORPEDO); 
			} );
			hbox.addChild(button2);
			box.addChild(hbox);
			_tabNavigator.addChild(box);	
		}
		
		
		private function dispatchRotate(rotation:int):void {
			var rotateEvent:ActionEvent = new ActionEvent(ActionEvent.ROTATION_CLICKED);
			rotateEvent.mode = MENU_MODE_ROTATE;
			rotateEvent.rotation = rotation
			dispatchEvent(rotateEvent);
		}
		
		private function dispatchFireMode(fireMode:int):void {
			var fireModeEvent:ActionEvent = new ActionEvent(ActionEvent.FIRE_MODE_CHANGED);
			fireModeEvent.mode = MENU_MODE_ROTATE;
			fireModeEvent.fireMode = fireMode
			dispatchEvent(fireModeEvent);
		}
		
		private function dispatchModeChanged(mode:int):void {
			var modeEvent:ActionEvent = new ActionEvent();
			modeEvent.mode = mode;
			dispatchEvent(modeEvent);
		}
		
		
		private function getButton(text:String, width:Number = MENU_SMALL_BUTTON_WIDTH):Button {
			var button:Button = new Button();
			button.label = text;
			button.width = width;
			return button;
		}
		
		
		public function get currentFireMode():int 
		{
			return _currentFireMode;
		}
		
		public function set currentFireMode(value:int):void 
		{
			_currentFireMode = value;
		}
		
		public function get currentMode():int 
		{
			return _currentMode;
		}
		
		public function set currentMode(value:int):void 
		{
			_currentMode = value;
		}
	}

}