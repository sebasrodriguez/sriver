package components 
{
	import events.ActionEvent;
	import flash.display.Shape;
	import flash.display.Sprite;
	import flash.events.MouseEvent;
	import mx.accessibility.ButtonAccImpl;
	import mx.controls.Button;
	import mx.controls.ToggleButtonBar;
	import mx.core.Container;
	import mx.core.UIComponent;
	import common.*;
	import flash.display.BitmapData;
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
		
		private var _shape:Shape;
		private var _moveButton:Button;
		private var _rotateButton:Button;
		private var _fireButton:Button;
		private var _moveContainer:Container;
		private var _rotateContainer:Container;
		private var _fireContainer:Container;
		private var _currenMode:int = -1;
		private var _rotateButtons:Array;
		
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
			
			createModeButtons();
			createContainers();
			createRotationButtons();
			
		}
		private function createContainers():void {
			_moveContainer = getContainer();
			_moveContainer.visible = false;
			addChild(_moveContainer);
			_rotateContainer = getContainer();
			_rotateContainer.visible = false;
			_rotateContainer.x = 60;
			addChild(_rotateContainer);
			_fireContainer = getContainer();
			_fireContainer.visible = false;
			addChild(_fireContainer);
		}
		
		private function createModeButtons():void {
			_moveButton = getButton("Mover", 0, 0);
			_moveButton.addEventListener(MouseEvent.CLICK, function ():void { 
				if (_currenMode != MENU_MODE_MOVE) {
					hideContainers();
					_moveContainer.visible = true;
					dispatchModeChanged(MENU_MODE_MOVE);
					_currenMode = MENU_MODE_MOVE;
				}
				
			});
			addChild(_moveButton);
			_rotateButton = getButton("Rotar", MENU_BIG_BUTTON_WIDTH * 1, 0);
			_rotateButton.addEventListener(MouseEvent.CLICK, function ():void {
				if(_currenMode != MENU_MODE_ROTATE){
					hideContainers();
					_rotateContainer.visible = true;
					dispatchModeChanged(MENU_MODE_ROTATE);
					_currenMode = MENU_MODE_ROTATE;
				}				
			});
			addChild(_rotateButton);
			_fireButton = getButton("Disparar", MENU_BIG_BUTTON_WIDTH * 2, 0);
			_fireButton.addEventListener(MouseEvent.CLICK, function ():void {
				if(_currenMode != MENU_MODE_FIRE){
					hideContainers();
					_fireContainer.visible = true;
					dispatchModeChanged(MENU_MODE_FIRE);
					_currenMode = MENU_MODE_FIRE;
				}
				
			});
			addChild(_fireButton);
		}
		
		private function createRotationButtons():void {
			var currentButton:Button;
			currentButton = getSmallButton("N", MENU_SMALL_BUTTON_WIDTH * 1, MENU_SMALL_BUTTON_HEIGHT * 0);
			currentButton.addEventListener(MouseEvent.CLICK, function ():void { dispatchRotate(Cardinal.N); } );
			_rotateContainer.addChild(currentButton);
			currentButton = getSmallButton("S", MENU_SMALL_BUTTON_WIDTH * 1, MENU_SMALL_BUTTON_HEIGHT * 2);
			currentButton.addEventListener(MouseEvent.CLICK, function ():void { dispatchRotate(Cardinal.S); } );
			_rotateContainer.addChild(currentButton);
			currentButton = getSmallButton("E", MENU_SMALL_BUTTON_WIDTH * 2, MENU_SMALL_BUTTON_HEIGHT * 1);
			currentButton.addEventListener(MouseEvent.CLICK, function ():void { dispatchRotate(Cardinal.E); } );
			_rotateContainer.addChild(currentButton);
			currentButton = getSmallButton("O", MENU_SMALL_BUTTON_WIDTH * 0, MENU_SMALL_BUTTON_HEIGHT * 1);
			currentButton.addEventListener(MouseEvent.CLICK, function ():void { dispatchRotate(Cardinal.W);});
			_rotateContainer.addChild(currentButton);
			currentButton = getSmallButton("NE", MENU_SMALL_BUTTON_WIDTH * 2, MENU_SMALL_BUTTON_HEIGHT * 0);
			currentButton.addEventListener(MouseEvent.CLICK, function ():void { dispatchRotate(Cardinal.NE);});
			_rotateContainer.addChild(currentButton);
			currentButton = getSmallButton("SE", MENU_SMALL_BUTTON_WIDTH * 2, MENU_SMALL_BUTTON_HEIGHT * 2);
			currentButton.addEventListener(MouseEvent.CLICK, function ():void { dispatchRotate(Cardinal.SE);});
			_rotateContainer.addChild(currentButton);
			currentButton = getSmallButton("SO", MENU_SMALL_BUTTON_WIDTH * 0, MENU_SMALL_BUTTON_HEIGHT * 2);
			currentButton.addEventListener(MouseEvent.CLICK, function ():void { dispatchRotate(Cardinal.SW);});
			_rotateContainer.addChild(currentButton);
			currentButton = getSmallButton("NO", MENU_SMALL_BUTTON_WIDTH * 0, MENU_SMALL_BUTTON_HEIGHT * 0);
			currentButton.addEventListener(MouseEvent.CLICK, function ():void { dispatchRotate(Cardinal.NW);});
			_rotateContainer.addChild(currentButton);
		}
		private function dispatchModeChanged(mode:int):void {
			var modeEvent:ActionEvent = new ActionEvent();
				modeEvent.mode = mode;
				dispatchEvent(modeEvent);
		}
		
		private function dispatchRotate(rotation:int):void {
			var rotateEvent:ActionEvent = new ActionEvent(ActionEvent.ROTATION_CLICKED);
			rotateEvent.mode = MENU_MODE_ROTATE;
			rotateEvent.rotation = rotation
			dispatchEvent(rotateEvent);
		}
		
		private function getContainer():Container {
			var c:Container = new Container();
			c.x = 0;
			c.y = 100;
			c.width = 240;
			c.height = 100;
			return c;
		}
		
		private function getButton(text:String, x:Number, y:Number):Button {
			var button:Button = new Button();
			button.label = text;
			button.width = MENU_BIG_BUTTON_WIDTH;
			button.height = MENU_BIG_BUTTON_HEIGHT;
			button.x = x;
			button.y = y;
			return button;
		}
		private function getSmallButton(text:String, x:Number, y:Number):Button {
			var button:Button = getButton(text, x, y);
			button.width = MENU_SMALL_BUTTON_WIDTH;
			button.height = MENU_SMALL_BUTTON_HEIGHT;
			return button;
		}
		
		private function hideContainers():void {
			_moveContainer.visible = false;
			_rotateContainer.visible = false;
			_fireContainer.visible = false;
		}
		
	}

}