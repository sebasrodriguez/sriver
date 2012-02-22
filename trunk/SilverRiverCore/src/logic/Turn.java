package logic;

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
	
	/*
	 * Termina el turno y le da paso al otro jugador
	 * Recibe al siguiente jugador para colocarlo como activo
	 * Coloca la cantidad de movimientos en 5, "resetea" la cantidad  de movimientos restantes
	 */
	public void endTurn(Player nextPlayer){
		this.activePlayer = nextPlayer;
		this.movesLeft = 5; //OJO QUE SI SACAMOS ESTE NUMERO DE UNA PROPERTY, NO DEJARLO HARDCODE!!!		
	}
}
