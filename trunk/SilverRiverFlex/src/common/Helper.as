package common
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
		
		/*public static function ConvertPointToCoordinate(point:Point, squareSize:int):Coordinate
		   {
		   var position:Coordinate = new Coordinate();
		
		   position.X = Math.floor(point.x / squareSize);
		   position.Y = Math.floor(point.y / squareSize);
		
		   return position;
		 }*/
		
		public static function getOppositeDirection(direction:Cardinal):Cardinal
		{
			var oppositeDirection:Cardinal;
			
			if (direction.cardinal >= 0)
				oppositeDirection = new Cardinal(direction.cardinal - 180);
			else
				oppositeDirection = new Cardinal(direction.cardinal + 180);
			
			return oppositeDirection;
		}
		
		public static function calculateNextCell(actualCoordinate:Coordinate, direction:Cardinal, distance:Number = 1):Coordinate
		{
			var newCoordinate:Coordinate = new Coordinate(actualCoordinate.r, actualCoordinate.c);
			// si la direccion es al este
			if (direction.cardinal == Cardinal.E)
			{
				newCoordinate.c = newCoordinate.c + distance;
			}
			// si la direccion es al oeste
			if (direction.cardinal == Cardinal.W)
			{
				newCoordinate.c = newCoordinate.c - distance;
			}
			// si la direccion es al norte
			if (direction.cardinal == Cardinal.N)
			{
				newCoordinate.r = newCoordinate.r - distance;
			}
			// si la direccion es al sur
			if (direction.cardinal == Cardinal.S)
			{
				newCoordinate.r = newCoordinate.r + distance;
			}
			if (direction.cardinal == Cardinal.SE)
			{
				newCoordinate.r = newCoordinate.r + distance;
				newCoordinate.c = newCoordinate.c + distance;
			}
			if (direction.cardinal == Cardinal.SW)
			{
				newCoordinate.r = newCoordinate.r + distance;
				newCoordinate.c = newCoordinate.c - distance;
			}
			if (direction.cardinal == Cardinal.NE)
			{
				newCoordinate.r = newCoordinate.r - distance;
				newCoordinate.c = newCoordinate.c + distance;
			}
			if (direction.cardinal == Cardinal.NW)
			{
				newCoordinate.r = newCoordinate.r - distance;
				newCoordinate.c = newCoordinate.c - distance;
			}
			
			return newCoordinate;
		}
		
		public static function getAngleToCoordinate(a:Coordinate, b:Coordinate):Number
		{
			var xdelta:Number = b.x - a.x;
			var ydelta:Number = b.y - a.y;
			var angle:Number = Math.atan2(ydelta, xdelta) / (Math.PI / 180);
			return angle;
		}
		
		public static function distanceBetweenCells(coorA:Coordinate, coorB:Coordinate):int
		{
			var xdelta:Number = coorA.c - coorB.c;
			var ydelta:Number = coorA.r - coorB.r;
			var distance:Number = Math.sqrt(Math.pow(xdelta, 2) + Math.pow(ydelta, 2));
			
			return Math.ceil(distance);
		}
	}

}