package components 
{
	import flash.display.Shape;
	import mx.accessibility.ButtonAccImpl;
	import mx.controls.Button;
	import mx.controls.Label;
	import mx.controls.Text;
	import mx.core.Container;
	import mx.core.UIComponent;
	import common.*;
	/**
	 * ...
	 * @author pablo
	 */
	public class Info extends UIComponent
	{
		public static const INFO_LABEL_WIDTH:int = 240;
		public static const INFO_LABEL_HEIGHT:int = 25;
		public static const INFO_LABEL_FONT_SIZE:int = 15;
		public static const INFO_LABEL_FONT_COLOR:uint = 0xFFCC33;
		public static const INFO_LABEL_FONT_STYLE_BOLD:String = "bold";		
		
		private var _shape:Shape;
		private var _movesLeftLabel:Label;
		private var _movesLeftText:String;
		private var _timeLeftLabel:Label;
		private var _timeLeftText:String;
		private var _myUsernameLabel:Label;
		private var _myUsernameText:String;
		private var _activePlayerLabel:Label;
		private var _activePlayerText:String;
		
		public function Info() 
		{
			_shape = new Shape();
			_shape.graphics.beginFill(0x000000, 0.6);
			_shape.graphics.drawRoundRect(0, 0, 240, 200, 10, 10);
			left = 10;
			top = 10;
			_shape.graphics.endFill();
			addChild(_shape);			
			createLabels();	
		}
		private function createLabels():void {
			//label movimientos restantes
			_movesLeftLabel = new Label();
			_movesLeftLabel.text = "Movimientos restantes: ";
			formatInfoLabels(_movesLeftLabel);
			//_movesLeftLabel.y = INFO_LABEL_HEIGHT * 0;
			addChild(_movesLeftLabel);
			//label tiempo restante
			_timeLeftLabel = new Label();	
			_timeLeftLabel.text = "Tiempo restantes: ";		
			formatInfoLabels(_timeLeftLabel);
			_movesLeftLabel.y = INFO_LABEL_HEIGHT * 1;
			addChild(_timeLeftLabel);
			// Label de jugador
			_myUsernameLabel = new Label();
			_myUsernameLabel.text = "Jugador: ";
			formatInfoLabels(_myUsernameLabel);
			_myUsernameLabel.y = INFO_LABEL_HEIGHT * 2;
			addChild(_myUsernameLabel);
			// Label de jugador activo
			_activePlayerLabel = new Label();	
			_activePlayerLabel.text = "Jugador activo: ";		
			formatInfoLabels(_activePlayerLabel);
			_activePlayerLabel.y = INFO_LABEL_HEIGHT * 3;
			addChild(_activePlayerLabel);
		}
		
		public function formatInfoLabels(l:Label):void {	
			l.width = INFO_LABEL_WIDTH;
			l.height = INFO_LABEL_HEIGHT;
			l.setStyle("fontSize", INFO_LABEL_FONT_SIZE);
			l.setStyle("color", INFO_LABEL_FONT_COLOR);
			l.setStyle("fontStyle", INFO_LABEL_FONT_STYLE_BOLD);
		}
		
		public function set movesLeftText(value:String):void 
		{			
			_movesLeftText = "Movimientos restantes: " + value;
			_movesLeftLabel.text = _movesLeftText;
		}
		
		public function set timeLeftText(value:String):void 
		{			
			_timeLeftText = "Tiempo restantes: " + value;
			_timeLeftLabel.text = _timeLeftText;
		}
		
		public function set myUsernameText(value:String):void 
		{
			_myUsernameText = "Jugador: " + value;
			_myUsernameLabel.text = _myUsernameText;
		}
		
		public function set activePlayerText(value:String):void 
		{			
			_activePlayerText = "Jugador activo: " + value;
			_activePlayerLabel.text = _activePlayerText;
		}
		
		
	}

}