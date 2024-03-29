package common 
{
	import flash.display.DisplayObject;
	import flash.events.MouseEvent;
	import mx.containers.Canvas;
	import mx.effects.Tween;
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
			var xinit:Number = _board.x;
			var yinit:Number = _board.y;
			var xscroll:Number = -(x - _main.stage.stageWidth / 2 );
			var yscroll:Number = -(y - _main.stage.stageHeight / 2 );
			if (xscroll > 0) xscroll = 0;
			if (xscroll < _main.stage.stageWidth - _board.width) xscroll = _main.stage.stageWidth - _board.width;
			if (yscroll > 0) yscroll = 0;
			if (yscroll < _main.stage.stageHeight - _board.height) yscroll = _main.stage.stageHeight - _board.height;
			
			new Tween(this, xinit, xscroll, 1000, 20, function(newX:Number):void
			{
				_board.x = newX;
			}, function():void{});
			new Tween(this, yinit, yscroll, 1000, 20, function(newY:Number):void
			{
				_board.y = newY;
			}, function():void{});
		}
		
	}

}