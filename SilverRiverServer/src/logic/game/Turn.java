package logic.game;

import entities.PlayerVO;
import entities.TurnVO;
import logic.player.Player;

public class Turn{
	
	private Player activePlayer;
	private int movesLeft;
	private int timeLeft;
	
	public Turn(){
		
	}
	
	//public Turn(Player activePlayer, int movesLeft){
	public Turn(Player activePlayer){	
		this.activePlayer = activePlayer;
		this.movesLeft = 5;
		this.timeLeft = 60;
	}

	public Player getActivePlayer(){
		return activePlayer;
	}

	public void setActivePlayer(Player activePlayer){
		this.activePlayer = activePlayer;
	}

	public int getMovesLeft(){
		return movesLeft;
	}

	public void setMovesLeft(int movesLeft){
		this.movesLeft = movesLeft;
	}
	
	/*
	 * Termina el turno y le da paso al otro jugador
	 * Recibe al siguiente jugador para colocarlo como activo
	 * Coloca la cantidad de movimientos en 5, "resetea" la cantidad  de movimientos restantes
	 */
	public void endTurn(Player nextPlayer){
		this.activePlayer = nextPlayer;
		this.movesLeft = 5;	
	}
	
	/*
	 * Decrementa en 1 la cantidad de movimientos restantes
	 */
	public void consumeMovement(){
		this.movesLeft = this.movesLeft - 1;
	}
	
	public TurnVO mapToValueObject(){
		PlayerVO playerVO = this.activePlayer.mapToValueObject();
		TurnVO turnVOToReturn = new TurnVO(playerVO,this.movesLeft,this.timeLeft);
		
		return turnVOToReturn;
	}
}
