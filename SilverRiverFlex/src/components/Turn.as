package components
{
	
	public class Turn
	{
		private var _totalTime:int;
		private var _totalMoves:int;
		private var _timeLeft:int;
		private var _movesLeft:int;
		private var _activePlayer:Player;
		
		public function Turn(movesLeft:int, activePlayer:Player, timeLeft:int)
		{
			_movesLeft = movesLeft;
			_totalTime = timeLeft;
			_totalMoves = movesLeft;
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
		
		public function switchTurn(redPlayer:Player, bluePlayer:Player):void
		{
			_timeLeft = _totalTime;
			_movesLeft = _totalMoves;
			if (_activePlayer.username == redPlayer.username)
				_activePlayer = bluePlayer;
			else
				_activePlayer = redPlayer;
			trace("active player = " + _activePlayer.username);
		}
		
		public function decreaseMovesLeft():void
		{
			_movesLeft--;
		}
		
		public function hasMovesLeft():Boolean
		{
			return _movesLeft > 0;
		}
		
		public function decreaseTimeLeft():void
		{
			_timeLeft--;
		}
	}

}