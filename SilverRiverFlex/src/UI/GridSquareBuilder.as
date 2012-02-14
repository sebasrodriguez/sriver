package UI
{
	import flash.display.Shape;
	import mx.core.UIComponent;
	
	/**
	 * ...
	 * @author sebas
	 */
	public class GridSquareBuilder
	{
		private var squareSize: int;
		private var squareFillColor: uint;
		private var squareBorderColor: uint;
		
		public function GridSquareBuilder(_squareSize:int = 40, _squareFillColor:uint=0x000000, _squareBorderColor: uint=0x000000)
		{
			squareSize = _squareSize;
			squareFillColor = _squareFillColor;
			squareBorderColor = _squareBorderColor;
		}
		
		public function DrawGridSquare(coordinate:Coordinate, uiComponent:UIComponent):void
		{
			var square:Shape = new Shape();
			square.graphics.beginFill(squareFillColor, 1);
			square.graphics.lineStyle(2, squareBorderColor, 1);
			square.graphics.drawRect(coordinate.X * squareSize, coordinate.Y * squareSize, squareSize, squareSize);
			square.graphics.endFill();
			
			uiComponent.addChild(square);
		}
	
	}

}