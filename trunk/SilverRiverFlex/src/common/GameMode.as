package common
{
	
	/**
	 * ...
	 * @author sebas
	 */
	public class GameMode
	{
		public static const NEWGAME:int = 1;
		public static const WAITING_FOR_PLAYER:int = 2;			
		public static const WAITING_PLAYER_TURN:int = 3;
		public static const PLAYING:int = 4;
		public static const GETTING_GAME:int = 5;
		public static const WAITING_FOR_LOADING:int = 6;
		
		private var _gameMode:int;
		
		public function GameMode(gameMode:int = 1)
		{
			_gameMode = gameMode;
		}
		
		public function get gameMode():int
		{
			return _gameMode;
		}
		
		public function set gameMode(value:int):void
		{
			_gameMode = value;
		}
	}

}