package Common
{
	import flash.geom.Point;
	
	/**
	 * ...
	 * @author sebas
	 */
	public class Helper
	{
		
		public function Helper()
		{
		}
		
		public static function ConvertPointToCoordinate(point:Point, squareSize:int):Coordinate
		{
			var position:Coordinate = new Coordinate();
			
			position.X = Math.floor(point.x / squareSize);
			position.Y = Math.floor(point.y / squareSize);
			
			return position;
		}
	}

}