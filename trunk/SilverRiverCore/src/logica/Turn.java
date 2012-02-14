package logica;

public class Turn 
{
	private Player activePlayer;
	private int movesLeft;
	
	public Turn()
	{
		
	}
	
	public Turn(Player activePlayer, int movesLeft) 
	{
		super();
		this.activePlayer = activePlayer;
		this.movesLeft = movesLeft;
	}

	public Player getActivePlayer()
	{
		return activePlayer;
	}

	public void setActivePlayer(Player activePlayer) 
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
