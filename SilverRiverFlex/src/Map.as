package  
{
	import Common.Cardinal;
	import Common.Coordinate;
	import flash.display.Sprite;
	import UI.*;
	/**
	 * ...
	 * @author pablo
	 */
	public class Map extends GameUIComponent 
	{
		
		public function Map() 
		{	
			this.setBitmap(Assets.MAP_DATA);
			
		}
		
		public function getInitialBlockedCoordinates():Array {
			var arr:Array = new Array();
			for (var f:int = 0; f < Game.GAME_BOARD_ROWS; f ++) {
				for (var c:int = 0; c < 6; c ++) {
					arr.push(new Coordinate(f, c));
				}
			}
			return arr;
		}
		
	}

}