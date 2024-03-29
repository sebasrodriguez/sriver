package components
{
	import flash.display.ColorCorrection;
	import flash.display.Shader;
	import flash.display.Shape;
	import flash.events.EventDispatcher;
	import flash.events.MouseEvent;
	import flash.geom.ColorTransform;
	import mx.controls.Label;
	import mx.controls.Text;
	import mx.core.UIComponent;
	import common.*;
	
	/**
	 * ...
	 * @author pablo
	 */
	public class GameCell extends UIComponent
	{
		private static const CELL_COLOR_NORMAL:uint = 0x000000;
		private static const CELL_COLOR_BLOCKED:uint = 0xff0000;
		private static const CELL_COLOR_GOAL:uint = 0x00ffff;
		private static const CELL_COLOR_PORT:uint = 0xffff00;
		private static const CELL_COLOR_AVAILABLE:uint = 0x00CC00;
		private static const CELL_ALPHA_NORMAL:Number = 0.0;
		private static const CELL_ALPHA_BLOCKED:Number = 0.0;
		private static const CELL_ALPHA_GOAL:Number = 0.3;
		private static const CELL_ALPHA_PORT:Number = 0.3;
		private static const CELL_ALPHA_AVAILABLE:Number = 0.5;
		
		private var _coordinate:Coordinate;
		private var _shape:Shape;
		private var _size:int = Coordinate.POINT_SIZE;
		private var _fillColor:uint = 0x000000;
		private var _borderColor:uint = 0x000000;
		private var _available:Boolean = false;
		private var _blocked:Boolean = false;
		private var _goalCell:Boolean = false;
		private var _portCell:Boolean = false;
		
		public function GameCell(c:Coordinate)
		{
			_coordinate = c;
			_shape = new Shape();
			/*if(c.r % 2 == 0)*/
			_shape.graphics.beginFill(CELL_COLOR_NORMAL, 1);
			alpha = CELL_ALPHA_NORMAL;
			/*else
			 _shape.graphics.beginFill(0xff0000, 0.1);*/
			//_shape.graphics.lineStyle(1, _borderColor, 1);
			_shape.graphics.drawRect(0, 0, _size, _size);
			_shape.graphics.endFill();
			addChild(_shape);
			/*var t:Label = new Label();
			t.text = "(" + _coordinate.r + "," + _coordinate.c + ")";
			t.setStyle("color", 0x000000);
			t.x = 0;
			t.y = 0;
			t.width = 50;
			t.height = 50;
			t.visible = true;
			this.addChild(t);*/
		
		}
		
		public function get available():Boolean
		{
			return _available;
		}
		
		public function set available(value:Boolean):void
		{
			if (value)
				changeColor(CELL_COLOR_AVAILABLE, CELL_ALPHA_AVAILABLE);
			else{
				if(_portCell)
					changeColor(CELL_COLOR_PORT, CELL_ALPHA_PORT);
				else
					if(_goalCell)
						changeColor(CELL_COLOR_GOAL, CELL_ALPHA_GOAL);
					else
						changeColor(CELL_COLOR_NORMAL, CELL_ALPHA_NORMAL);
			}
			_available = value;
		}
		
		public function get blocked():Boolean
		{
			return _blocked;
		}
		
		public function set blocked(value:Boolean):void
		{
			//descomentar para debuggear
			/*if (value)
				changeColor(CELL_COLOR_BLOCKED, CELL_ALPHA_BLOCKED);
			else{
				if(_portCell)
					changeColor(CELL_COLOR_PORT, CELL_ALPHA_PORT);
				else
					if(_goalCell)
						changeColor(CELL_COLOR_GOAL, CELL_ALPHA_GOAL);
					else
						changeColor(CELL_COLOR_NORMAL, CELL_ALPHA_NORMAL);						
			}*/
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
		
		public function get goalCell():Boolean 
		{
			return _goalCell;
		}
		
		public function set goalCell(value:Boolean):void 
		{
			if (value)
				changeColor(CELL_COLOR_GOAL, CELL_ALPHA_GOAL);
			else
				changeColor(CELL_COLOR_NORMAL, CELL_ALPHA_NORMAL);
			_goalCell = value;
		}
		
		public function get portCell():Boolean 
		{
			return _portCell;
		}
		
		public function set portCell(value:Boolean):void 
		{if (value)
				changeColor(CELL_COLOR_PORT, CELL_ALPHA_PORT);
			else
				changeColor(CELL_COLOR_NORMAL, CELL_ALPHA_NORMAL);
			_portCell = value;
		}
		
		private function changeColor(c:uint, a:Number = 1):void
		{
			var ct:ColorTransform = new ColorTransform();
			ct.color = c;
			alpha = a;
			_shape.transform.colorTransform = ct;
		}
	
	}

}