package Common 
{
	/**
	 * ...
	 * @author sebas
	 */
	public class Coordenate 
	{
		
		private var _x: int;
		private var _y: int;
		
		public function Coordenate() {			
		}
		
		public function Coordenate(x:int, y:int) {
			this._x = x;
			this._y = y;
		}
		
		public function get X(): int {
			return _x;
		}
		
		public function set X(x:int): void {
			_x = x;
		}
		
		public function get Y(): int {
			return _y;
		}
		
		public function set Y(y:int): void {
			_y = y;
		}		
	}

}