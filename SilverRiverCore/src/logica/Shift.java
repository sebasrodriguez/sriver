package logica;

/*
 * Como Turn no me dejaba crearla, se renombro a Shift.
 * Se usa para manejar el turno del jugador.
 */
public abstract class Shift {

	private Captain activePlayer;
	private int movesLeft;
	
	
	/*
	 * Constructores
	 */
	public Shift(){
		
	}
	
	public Shift(Captain activePlayer, int movesLeft){
		this.activePlayer = activePlayer;
		this.movesLeft = movesLeft;
	}
	
	/*
	 * Termina el turno y le da paso al otro jugador
	 * Recibe al siguiente jugador para colocarlo como activo
	 * Coloca la cantidad de movimientos en 5, "resetea" la cantidad  de movimientos restantes
	 */
	public void endShift(Captain nextPlayer){
		this.activePlayer = nextPlayer;
		this.movesLeft = 5; //OJO QUE SI SACAMOS ESTE NUMERO DE UNA PROPERTY, NO DEJARLO HARDCODE!!!		
	}
	
	/*
	 * Setters
	 */
	public void setPlayer(Captain activePlayer){
		this.activePlayer = activePlayer;
	}
	
	public void setMovesLeft(int movesLeft){
		this.movesLeft = movesLeft;
	}
	
	/*
	 * Getters
	 */
	public Captain getActivePlayer(){
		return this.activePlayer;
	}
	
	public int getMovesLeft(){
		return this.movesLeft;
	}
}
