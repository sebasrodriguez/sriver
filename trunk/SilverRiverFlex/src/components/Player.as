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
		
		public function addShip(ship:Ship):void
		{
			_shipCollection.push(ship);
		}
		
		public function isMyShip(ship:Ship):Boolean
		{
			var result:Boolean = false;
			var i:int = 0;
			while (!result && i < _shipCollection.length)
			{
				if (ship == _shipCollection[i])
					result = true;
				i++;
			}
			return result;
		}
		
		public function getShip(index:int = 0):Ship
		{
			return _shipCollection[index];
		}
		
		public function hasAliveShips():Boolean
		{
			var result:Boolean = false;
			var i:int = 0;
			while (!result && i < _shipCollection.length)
			{
				result = _shipCollection[i].isAlive();
				i++;
			}
			return result;
		}
		
		public function getNextAliveShip():Ship
		{
			var ship:Ship = null;
			var i:int = 0;
			while (ship == null && i < _shipCollection.length)
			{
				if (_shipCollection[i].isAlive())
					ship = _shipCollection[i];
				i++;
			}
			return ship;
		}
		
		public function getAliveShips():Array
		{
			var aliveShips:Array = new Array();
			for (var i:int = 0; i < _shipCollection.length; i++)
			{
				if (_shipCollection[i].isAlive())
					aliveShips.push(_shipCollection[i]);
			}
			return aliveShips;
		}
	}

}