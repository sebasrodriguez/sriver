package entities;

import java.io.Serializable;

public class TurnVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PlayerVO activePlayer;
	private int movesLeft;
	private int timeLeft;
	
	/*
	 * Constructor
	 */
	public TurnVO(PlayerVO activePlayer, int movesLeft, int timeLeft){		
		this.activePlayer = activePlayer;
		this.movesLeft = movesLeft;
		this.timeLeft = timeLeft;
	}
	
	/*
	 * Devuelve el jugador activo en ValueObject
	 */
	public PlayerVO getActivePlayer(){
		return this.activePlayer;
	}

	
	/*
	 * Devuelve la cantiad de movimientos restantes
	 */
	public int getMovesLeft(){
		return this.movesLeft;
	}
	
	/*
	 * Devuelve la cantidad de tiempo restante
	 */
	public int getTimeLeft(){
		return this.timeLeft;
	}	
}
