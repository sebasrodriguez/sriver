package tests 
{
	import common.Coordinate;
	import components.Map;
	/**
	 * ...
	 * @author pablo
	 */
	public class TestMap 
	{
		
		public function TestMap() 
		{
			var map:Map = new Map();
			var coordinates:Array = new Array();
			coordinates.push(new Coordinate(0,0));
			coordinates.push(new Coordinate(0,1));
			coordinates.push(new Coordinate(0,2));
			coordinates.push(new Coordinate(1,0));
			coordinates.push(new Coordinate(1,1));
			coordinates.push(new Coordinate(1,2));
			coordinates.push(new Coordinate(2,0));
			coordinates.push(new Coordinate(2,1));
			coordinates.push(new Coordinate(2, 2));
			
			var subcord:Array = new Array();
			subcord.push(new Coordinate(0,0));
			subcord.push(new Coordinate(0,1));
			subcord.push(new Coordinate(0, 2));
			
			trace(map.areSubCoordinates(subcord, coordinates));
			
		}
		
	}

}