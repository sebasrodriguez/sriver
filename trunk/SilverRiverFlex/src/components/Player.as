package components
{
	
	/**
	 * ...
	 * @author sebas
	 */
	public class Player
	{
		private var _username:String;
		private var _shipCollection:Array;
		
		public function Player(username:String)
		{
			_username = username;
			_shipCollection = new Array();
		}
		
		public function get username():String
		{
			return _username;
		}
		
		public function addShip(ship:Ship):void {
			_shipCollection.push(ship);
		}
		
		public function isMyShip(ship:Ship):Boolean {
			var result:Boolean = false;
			var i:int = 0;
			while (!result && i < _shipCollection.length) {
				if (ship == _shipCollection[i])
					result = true;
				i ++;
			}
			return result;
		}
		
		public function getShip(index:int = 0):Ship {
			return _shipCollection[index];
		}
	}

}