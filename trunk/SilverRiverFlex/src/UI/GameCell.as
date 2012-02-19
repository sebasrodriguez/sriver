package UI
{
	import flash.display.Shader;
	import flash.display.Shape;
	import Common.Coordinate;
	import flash.events.EventDispatcher;
	import flash.events.MouseEvent;
	import mx.core.UIComponent;
	
	/**
	 * ...
	 * @author pablo
	 */
	public class GameCell extends UIComponent
	{
		private var _coordinate:Coordinate;
		private var _shape:Shape;
		private var _size:int = Coordinate.POINT_SIZE;
		private var _fillColor:uint = 0x000000;
		private var _borderColor:uint = 0x000000;
		private var _available:Boolean = false;
		private var _blocked:Boolean = false;
		
		public function GameCell(c:Coordinate)
		{
			_coordinate = c;
			_shape = new Shape();
			_shape.graphics.beginFill(_fillColor, 0.5);
			_shape.graphics.lineStyle(1, _borderColor, 1);
			_shape.graphics.drawRect(0, 0, _size, _size);
			_shape.graphics.endFill();
			addChild(_shape);
		
		}
		
		public function get available():Boolean
		{
			return _available;
		}
		
		public function set available(value:Boolean):void
		{
			_available = value;
		}
		
		public function get blocked():Boolean
		{
			return _blocked;
		}
		
		public function set blocked(value:Boolean):void
		{
			graphics.beginFill(_fillColor, 1);
			graphics.endFill();
			_blocked = value;
		}
		
		public function get borderColor():uint
		{
			return _borderColor;
		}
		
		public function set borderColor(value:uint):void
		{
			_borderColor = value;
		}
		
		public function get fillColor():uint
		{
			return _fillColor;
		}
		
		public function set fillColor(value:uint):void
		{
			_fillColor = value;
		}
		
		public function get size():int 
		{
			return _size;
		}
		
		public function set size(value:int):void 
		{
			_size = value;
		}
		
		public function get coordinate():Coordinate 
		{
			return _coordinate;
		}
		
		public function set coordinate(value:Coordinate):void 
		{
			_coordinate = value;
		}
	
	}

}