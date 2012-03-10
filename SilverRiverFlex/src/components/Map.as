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
		//devuelve una arreglo de celdas bloqueadas por el mapa
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
		
		//devuelve las coordenadas en las que el barco tiene que estar para ganar
		public function getGoalCoordinates():Array {
			var arr:Array = new Array();
			var f:int, c:int;
			//zona sureste
			for (f = 33; f < 36; f ++) {
				for (c = 61; c < 64; c ++) {
					arr.push(new Coordinate(f, c));
				}
			}
			//zona suroeste
			for (f = 33; f < 36; f ++) {
				for (c = 15; c < 18; c ++) {
					arr.push(new Coordinate(f, c));
				}
			}
			return arr;
		}
		
		//puerto que recarga la mitad de todo
		public function getPortHalfCoordinates():Array {
			var arr:Array = new Array();
			var f:int, c:int;
			for (f = 7; f < 10; f ++) {
				for (c = 19; c < 23; c ++) {
					arr.push(new Coordinate(f, c));
				}
			}
			return arr;
		}
		//puerto que recarga el total de un solo atributo
		public function getPortOneCoordinates():Array {
			var arr:Array = new Array();
			var f:int, c:int;
			for (f = 13; f < 17; f ++) {
				for (c = 10; c < 13; c ++) {
					arr.push(new Coordinate(f, c));
				}
			}
			return arr;
		}
		
		public function areSubCoordinates(sub:Array, coord:Array):Boolean {
			var result:Boolean = false;
			var score:int = 0;
			var i:int = 0;
			var j:int = 0;
			while (!result && i < coord.length) {
				if (sub[j].equals(coord[i])) {
					j ++;
					if (j == sub.length) {
						result = true;
					}
					i = 0;
				}else {
					i ++;
				}
			}
			return result;
		}
		
	}

}