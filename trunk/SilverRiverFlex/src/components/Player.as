package components
{
	
	/**
	 * ...
	 * @author sebas
	 */
	public class Player
	{
		private var _player:Player;
		private var _username:String;
		
		public function Player(username:String)
		{
			_username = username;
		}
		
		public function get username():String
		{
			return _username;
		}
	}

}