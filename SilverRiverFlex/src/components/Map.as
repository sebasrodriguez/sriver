package components
{
	import common.*;
	import flash.display.Sprite;
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
			var f:int, c:int;
			for (f = 0; f < Game.GAME_BOARD_ROWS; f ++) {
				for (c = 0; c < 7; c ++) {
					arr.push(new Coordinate(f, c));
				}
			}
			for (f = 0; f < 6; f++) {
				for (c = 6; c < 46; c ++) {
					arr.push(new Coordinate(f, c));
				}
			}			
			for (f = 0; f < 4; f++) {
				for (c = 46; c < Game.GAME_BOARD_COLS; c ++) {
					arr.push(new Coordinate(f, c));
				}
			}		
			for (f = 6; f < 7; f++) {
				for (c = 7; c < 31; c ++) {
					arr.push(new Coordinate(f, c));
				}
			}	
			for (f = 7; f < Game.GAME_BOARD_ROWS; f++) {
				for (c = 7; c < 10; c ++) {
					arr.push(new Coordinate(f, c));
				}
			}
			for (f = 20; f < Game.GAME_BOARD_ROWS; f++) {
				for (c = 10; c < 12; c ++) {
					arr.push(new Coordinate(f, c));
				}
			}
			for (f = 28; f < Game.GAME_BOARD_ROWS; f++) {
				for (c = 12; c < 13; c ++) {
					arr.push(new Coordinate(f, c));
				}
			}
			for (f = 31; f < Game.GAME_BOARD_ROWS; f++) {
				for (c = 13; c < 15; c ++) {
					arr.push(new Coordinate(f, c));
				}
			}
			return arr;
		}
		
	}

}