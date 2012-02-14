package Actions 
{
	import Entities.Ship;
	/**
	 * ...
	 * @author sebas
	 */
	public class Action 
	{
		
		private var _ship: Ship;
		
		public function Action(ship: Ship = null) {
			this._ship = ship;
		}		
		
		public function get ActionShip(): Ship {
			return _ship;
		}
		
		public function set ActionShip(ship:Ship): void {
			_ship = ship;
		}
	}
}