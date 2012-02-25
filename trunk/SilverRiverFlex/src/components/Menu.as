package components 
{
	import events.ActionEvent;
	import flash.display.Shape;
	import flash.display.Sprite;
	import flash.events.MouseEvent;
	import mx.accessibility.ButtonAccImpl;
	import mx.controls.Button;
	import mx.controls.ToggleButtonBar;
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
		private var _currenMode:int = -1;
		private var _rotateButtons:Array;
		
		public function Menu(position:int) 
		{
			_shape = new Shape();
			_shape.graphics.beginFill(0x000000, 0.6);
			_shape.graphics.drawRoundRect(0, 0, 240, 200, 10, 10);
			if (position == MENU_POSITION_BOTTOM_LEFT) {
				left = 10;
				bottom = 110;
			}else if (position == MENU_POSITION_BOTTOM_RIGHT) {				
				right = 10;
				bottom = 110;
			}
			_shape.graphics.endFill();
			addChild(_shape);
			
			createModeButtons();
			createRotationButtons();
			
		}
		
		private function createModeButtons():void {
			_moveButton = getButton("Move", 0, 0);
			_moveButton.addEventListener(MouseEvent.CLICK, function ():void { 
				if(_currenMode != MENU_MODE_MOVE){
					var modeEvent:ActionEvent = new ActionEvent();
					modeEvent.mode = MENU_MODE_MOVE;
					dispatchEvent(modeEvent);
					_currenMode = MENU_MODE_MOVE;
				}
				
			});
			addChild(_moveButton);
			_rotateButton = getButton("Rotate", MENU_BIG_BUTTON_WIDTH * 1, 0);
			_rotateButton.addEventListener(MouseEvent.CLICK, function ():void {
				if(_currenMode != MENU_MODE_ROTATE){
					var modeEvent:ActionEvent = new ActionEvent();
					modeEvent.mode = MENU_MODE_ROTATE;
					dispatchEvent(modeEvent);
					_currenMode = MENU_MODE_ROTATE;
				}
				
			});
			addChild(_rotateButton);
			_fireButton = getButton("Fire", MENU_BIG_BUTTON_WIDTH * 2, 0);
			_fireButton.addEventListener(MouseEvent.CLICK, function ():void {
				if(_currenMode != MENU_MODE_FIRE){
					var modeEvent:ActionEvent = new ActionEvent();
					modeEvent.mode = MENU_MODE_FIRE;
					dispatchEvent(modeEvent);
					_currenMode = MENU_MODE_FIRE;
				}
				
			});
			addChild(_fireButton);
		}
		
		private function createRotationButtons():void {
			var currentButton:Button;
			currentButton = getSmallButton("N", MENU_SMALL_BUTTON_WIDTH * 0, 50);
			currentButton.addEventListener(MouseEvent.CLICK, function ():void { dispatchRotate(Cardinal.N); } );
			addChild(currentButton);
			currentButton = getSmallButton("S", MENU_SMALL_BUTTON_WIDTH * 1, 50);
			currentButton.addEventListener(MouseEvent.CLICK, function ():void { dispatchRotate(Cardinal.S); } );
			addChild(currentButton);
			currentButton = getSmallButton("E", MENU_SMALL_BUTTON_WIDTH * 2, 50);
			currentButton.addEventListener(MouseEvent.CLICK, function ():void { dispatchRotate(Cardinal.E); } );
			addChild(currentButton);
			currentButton = getSmallButton("W", MENU_SMALL_BUTTON_WIDTH * 3, 50);
			currentButton.addEventListener(MouseEvent.CLICK, function ():void { dispatchRotate(Cardinal.W);});
			addChild(currentButton);
			currentButton = getSmallButton("NE", MENU_SMALL_BUTTON_WIDTH * 0, 75);
			currentButton.addEventListener(MouseEvent.CLICK, function ():void { dispatchRotate(Cardinal.NE);});
			addChild(currentButton);
			currentButton = getSmallButton("SE", MENU_SMALL_BUTTON_WIDTH * 1, 75);
			currentButton.addEventListener(MouseEvent.CLICK, function ():void { dispatchRotate(Cardinal.SE);});
			addChild(currentButton);
			currentButton = getSmallButton("SW", MENU_SMALL_BUTTON_WIDTH * 2, 75);
			currentButton.addEventListener(MouseEvent.CLICK, function ():void { dispatchRotate(Cardinal.SW);});
			addChild(currentButton);
			currentButton = getSmallButton("NW", MENU_SMALL_BUTTON_WIDTH * 3, 75);
			currentButton.addEventListener(MouseEvent.CLICK, function ():void { dispatchRotate(Cardinal.NW);});
			addChild(currentButton);
		}
		
		private function dispatchRotate(rotation:int):void {
			var rotateEvent:ActionEvent = new ActionEvent(ActionEvent.ROTATION_CLICKED);
			rotateEvent.mode = MENU_MODE_ROTATE;
			rotateEvent.rotation = rotation
			dispatchEvent(rotateEvent);
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
		
	}

}