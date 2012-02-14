package Common 
{
	import flash.geom.Point;
	/**
	 * ...
	 * @author sebas
	 */
	public class Helper 
	{
		
		public function Helper() {			
		}
		
		public static function ConvertPointToCoordinate(point: Point, squaresize: int):Coordinate {
			var position: Coordinate = new Coordinate();
			
			position.X = Math.floor(point.x / squaresize);
			position.Y = Math.floor(point.y / squaresize);
			
			return position;
		}		
	}

}