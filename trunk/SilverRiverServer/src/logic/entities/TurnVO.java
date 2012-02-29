package logic.entities;

public class TurnVO {

	private PlayerVO activePlayer;
	private int movesLeft;
	private int timeLeft;
	
	
	public TurnVO(PlayerVO activePlayer, int movesLeft, int timeLeft){		
		this.activePlayer = activePlayer;
		this.movesLeft = movesLeft;
		this.timeLeft = timeLeft;
	}

	public PlayerVO getActivePlayer(){
		return this.activePlayer;
	}

	
	public int getMovesLeft(){
		return this.movesLeft;
	}
	
	public int getTimeLeft(){
		return this.timeLeft;
	}	
}
