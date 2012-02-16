package logica;

public class Shift {

	private Captain activePlayer;
	private int movesLeft;
	
	
	public Shift(){
		
	}
	
	public Shift(Captain activePlayer, int movesLeft){
		this.activePlayer = activePlayer;
		this.movesLeft = movesLeft;
	}
	
	public void endShift(){
		
	}
	
	public void setPlayer(Captain activePlayer){
		this.activePlayer = activePlayer;
	}
	
	public void setMovesLeft(int movesLeft){
		this.movesLeft = movesLeft;
	}
	
	public Captain getActivePlayer(){
		return this.activePlayer;
	}
	
	public int getMovesLeft(){
		return this.movesLeft;
	}
}
