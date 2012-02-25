package UI
{
	import Common.*;
	import flash.display.Shape;
	import flash.events.Event;
	import mx.containers.Canvas;
	import mx.core.UIComponent;
	import flash.events.MouseEvent;
	
	/**
	 * ...
	 * @author sebas
	 */
	public class GameGrid extends UIComponent
	{
		private var _container:Canvas;
		private var _rows:int;
		private var _cols:int;
		private var _gridMatrix:Array;
		private var _cellSize:Number;
		
		public function GameGrid(container:Canvas, rows:int, cols:int)
		{
			_container = container;
			_rows = rows;
			_cols = cols;
			_cellSize = Coordinate.POINT_SIZE;
			//build grid
			
			_gridMatrix = new Array();
			for (var r:int = 0; r < _rows; r++)
			{
				_gridMatrix[r] = new Array();
				for (var c:int = 0; c < _cols; c++)
				{
					_gridMatrix[r][c] = new GameCell(new Coordinate(r, c));
					this.addChild(_gridMatrix[r][c]);
					_gridMatrix[r][c].x = c * _cellSize;
					_gridMatrix[r][c].y = r * _cellSize;
					_gridMatrix[r][c].blocked = false;
					_gridMatrix[r][c].addEventListener(MouseEvent.CLICK, function(e:MouseEvent):void
						{
							var cellEvent:CellEvent = new CellEvent();
							cellEvent.coordinate = e.currentTarget.coordinate;
							dispatchEvent(cellEvent);
						});
				}
			}
		
		}
		
		public function blockCells(... coordinates):void
		{
			var cell:GameCell;
			var c:Coordinate;
			if (coordinates[0] is Array)
			{
				for each (c in coordinates[0])
				{
					cell = _gridMatrix[c.r][c.c];
					cell.blocked = true;
				}
			}
			else
			{
				for each (c in coordinates)
				{
					cell = _gridMatrix[c.r][c.c];
					cell.blocked = true;
				}
			}
		}
		
		public function enableCell(coordinate:Coordinate):Boolean
		{
			var enabled:Boolean = false;
			var cell:GameCell = _gridMatrix[coordinate.r][coordinate.c];
			if (!cell.blocked)
			{
				cell.available = true;
				enabled = true;
			}
			
			return enabled;
		}
	
	/*public function getSquareFromXY(x:Number, y:Number):Shape {
	   var r:int = Math.floor(y / this.squareSize);
	   var c:int = Math.floor(x / this.squareSize);
	   return _gridMatrix[r][c];
	   }
	
	   public function getCoordinateFromXY(x:Number, y:Number):Coordinate {
	   var square:Shape = this.getSquareFromXY(x, y);
	   var newX:Number = square.x + this.squareSize / 2;
	   var newY:Number = square.y + this.squareSize / 2;
	   return new Coordinate(newX, newY);
	   }
	
	   public function getCoordinateFromRC(r:Number, c:Number):Coordinate {
	   var square:Shape = _gridMatrix[r][c];
	   var newX:Number = square.x + this.squareSize / 2;
	   var newY:Number = square.y + this.squareSize / 2;
	   return new Coordinate(newX, newY);
	 }*/
	}

}