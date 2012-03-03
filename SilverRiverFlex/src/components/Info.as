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
		private static const INFO_LABEL_WIDTH:int = 240;
		private static const INFO_LABEL_HEIGHT:int = 25;
		private static const INFO_LABEL_FONT_SIZE:int = 15;
		private static const INFO_LABEL_FONT_COLOR:uint = 0xFFCC33;
		private static const INFO_LABEL_FONT_STYLE_BOLD:String = "bold";
		
		private var _shape:Shape;
		
		private var _movesLeftLabel:Label;
		private var _movesLeftText:String;
		
		private var _timeLeftLabel:Label;
		private var _timeLeftText:String;
		
		private var _redPlayerLabel:Label;
		private var _bluePlayerLabel:Label;
		
		private var _redPlayerText:String;
		private var _bluePlayerText:String;
		
		private var _redPlayerUsername:String;
		private var _bluePlayerUsername:String;
		
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
		
		private function createLabels():void
		{
			//label movimientos restantes
			_movesLeftLabel = new Label();
			formatInfoLabels(_movesLeftLabel);
			addChild(_movesLeftLabel);
			
			//label tiempo restante
			_timeLeftLabel = new Label();
			formatInfoLabels(_timeLeftLabel);
			_movesLeftLabel.y = INFO_LABEL_HEIGHT * 1;
			addChild(_timeLeftLabel);
			
			// Label de jugador rojo
			_redPlayerLabel = new Label();
			formatInfoLabels(_redPlayerLabel);
			_redPlayerLabel.y = INFO_LABEL_HEIGHT * 2;
			addChild(_redPlayerLabel);
			
			// Label de jugador azul
			_bluePlayerLabel = new Label();
			formatInfoLabels(_bluePlayerLabel);
			_bluePlayerLabel.y = INFO_LABEL_HEIGHT * 3;
			addChild(_bluePlayerLabel);
		}
		
		public function formatInfoLabels(l:Label):void
		{
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
			_timeLeftText = "Tiempo restante: " + value;
			_timeLeftLabel.text = _timeLeftText;
		}
		
		public function set redPlayerUsername(value:String):void
		{
			_redPlayerUsername = value;
		}
		
		public function set bluePlayerUsername(value:String):void
		{
			_bluePlayerUsername = value;
		}
		
		public function setActivePlayer(isRedPlayerActive:Boolean):void
		{
			if (isRedPlayerActive)
			{
				_redPlayerLabel.text = "Jugador rojo:  " + _redPlayerUsername + " (*)";
				_bluePlayerLabel.text = "Jugador azul: " + _bluePlayerUsername;
			}
			else
			{
				_redPlayerLabel.text = "Jugador rojo:  " + _redPlayerUsername;
				_bluePlayerLabel.text = "Jugador azul: " + _bluePlayerUsername + " (*)";
			}
		}
	}
}