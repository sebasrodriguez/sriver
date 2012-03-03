package common 
{
	import flash.display.DisplayObject;
	import flash.events.MouseEvent;
	import mx.containers.Canvas;
	/**
	 * ...
	 * @author pablo
	 */
	public class AutoScroll 
	{
		public static const GAME_SCROLL_SENSOR:Number = 50;
		public static const GAME_SCROLL_INCREMENT:Number = 10;
	
		private var _main:Main;
		private var _board:Canvas;
		
		public function AutoScroll(main:Main, scrolled:Canvas) 
		{
			_main = main;
			_board = scrolled;
			main.addEventListener(MouseEvent.MOUSE_MOVE, mouseMoveHandler);
		}
		
		private function mouseMoveHandler(e:MouseEvent):void
		{
			//TODO: HACER MAS LINDO EL MOVIMIENTO
			//para animacion
			
			if (e.stageX < GAME_SCROLL_SENSOR)
			{ //scrollear izquierda						
				if (_board.x < 0)
					_board.x += GAME_SCROLL_INCREMENT;
			}
			if (e.stageX > (_main.stage.stageWidth - GAME_SCROLL_SENSOR))
			{ //scrollear derecha	
				//trace("left myCanvasX " + myCanvas.x +" - myCanvasWidth - " + myCanvas.width + " - stagewidth - " + stage.stageWidth);	
				if (_board.x > _main.stage.stageWidth - _board.width)
					_board.x -= GAME_SCROLL_INCREMENT;
			}
			if (e.stageY < GAME_SCROLL_SENSOR)
			{ //scrollear arriba	
				if (_board != null)
					if (_board.y < 0)
						_board.y += GAME_SCROLL_INCREMENT;
			}
			if (e.stageY > (_main.stage.stageHeight - GAME_SCROLL_SENSOR))
			{ //scrollear abajo		
				if (_board.y > _main.stage.stageHeight - _board.height)
					_board.y -= GAME_SCROLL_INCREMENT;
			}
		}
		
		public function centerMapToXY(x:Number, y:Number):void {
			_board.x = -(x - _main.stage.stageWidth / 2 );
			_board.y = -(y - _main.stage.stageHeight / 2 );
		}
		
	}

}