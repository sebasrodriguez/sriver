package components
{
	
	public class Turn
	{
		private var _movesLeft:int;
		private var _activePlayer:Player;
		
		public function Turn(movesLeft:int, activePlayer:Player)
		{
			_movesLeft = movesLeft;
			_activePlayer = activePlayer;
		}
		
		public function get movesLeft():int
		{
			return _movesLeft;
		}
		
		public function get activePlayer():Player
		{
			return _activePlayer;
		}
		
		public function set movesLeft(value:int):void
		{
			_movesLeft = value;
		}
	
	}

}