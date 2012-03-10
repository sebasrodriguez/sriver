package logic.game;

import java.io.Serializable;

import entities.PlayerVO;
import entities.TurnVO;
import logic.player.Player;

public class Turn implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Player activePlayer;
	private int movesLeft;
	private int timeLeft;
	private int initialMoves;
	
	public Turn(){
		
	}
	
	/*
	 * Constructor
	 */
	public Turn(Player activePlayer){	
		this.activePlayer = activePlayer;
		this.initialMoves = 5;
		this.timeLeft = 60;		
		this.movesLeft = this.initialMoves;
	}

	/*
	 * Devuelve el jugador activo
	 */
	public Player getActivePlayer(){
		return activePlayer;
	}

	/*
	 * Setea al jugador activo
	 */
	public void setActivePlayer(Player activePlayer){
		this.activePlayer = activePlayer;
	}

	/*
	 * Devuelve la cantidad de movimientos restantes
	 */
	public int getMovesLeft(){
		return movesLeft;
	}

	/*
	 * Setea la cantidad de movimientos restantes
	 */
	public void setMovesLeft(int movesLeft){
		this.movesLeft = movesLeft;
	}
	
	/*
	 * Devuelve el tiempo del turno
	 */
	public int getTimeLeft(){
		return this.timeLeft;
	}
	
	/*
	 * Setea el tiempo restante
	 */
	public void setTimeLeft(int time){
		this.timeLeft = time;
	}
	
	/*
	 * Devuelve la cantidad de movimientos inicial
	 */
	public int getInitialMoves(){
		return this.initialMoves;
	}
	
	/*
	 * Setea la cantidad de movimientos inicial
	 */
	public void setInitialMoves(int initialMoves){
		this.initialMoves = initialMoves;
	}
	
	/*
	 * Termina el turno y le da paso al otro jugador
	 * Recibe al siguiente jugador para colocarlo como activo
	 * Coloca la cantidad de movimientos en 5, "resetea" la cantidad  de movimientos restantes
	 */
	public void endTurn(Player nextPlayer){
		this.activePlayer = nextPlayer;
		this.movesLeft = 5;	
		this.timeLeft = 60;
	}
	
	/*
	 * Decrementa en 1 la cantidad de movimientos restantes
	 */
	public void consumeMovement(){
		this.movesLeft = this.movesLeft - 1;
	}
	
	/*
	 * Devuelve el turno en ValueObject
	 */
	public TurnVO mapToValueObject(){
		PlayerVO playerVO = this.activePlayer.mapToValueObject();
		TurnVO turnVOToReturn = new TurnVO(playerVO,this.movesLeft,this.timeLeft, this.initialMoves);
		
		return turnVOToReturn;
	}
}
