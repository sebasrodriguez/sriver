package entities;

public class TurnVO {

	private PlayerVO activePlayer;
	private int movesLeft;
	
	public TurnVO()
	{		
	}
	
	public TurnVO(PlayerVO activePlayer, int movesLeft) 
	{		
		this.activePlayer = activePlayer;
		this.movesLeft = movesLeft;
	}

	public PlayerVO getActivePlayer()
	{
		return activePlayer;
	}

	public void setActivePlayer(PlayerVO activePlayer) 
	{
		this.activePlayer = activePlayer;
	}

	public int getMovesLeft() 
	{
		return movesLeft;
	}

	public void setMovesLeft(int movesLeft) 
	{
		this.movesLeft = movesLeft;
	}	
}
