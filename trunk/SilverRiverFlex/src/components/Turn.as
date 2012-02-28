package components
{
	
	public class Turn
	{
		private var _timeLeft:int;
		private var _movesLeft:int;
		private var _activePlayer:Player;
		
		public function Turn(movesLeft:int, activePlayer:Player, timeLeft:int)
		{
			_movesLeft = movesLeft;
			_activePlayer = activePlayer;
			_timeLeft = timeLeft;
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
		
		public function get timeLeft():int
		{
			return _timeLeft;
		}
		
		public function set timeLeft(value:int):void
		{
			_timeLeft = value;
		}
	}

}