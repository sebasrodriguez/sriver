package Common 
{
	/**
	 * ...
	 * @author sebas
	 */
	public class Coordinate 
	{
		
		private var _x: int;
		private var _y: int;		
		
		public function Coordinate(x:int=0, y:int=0) {
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