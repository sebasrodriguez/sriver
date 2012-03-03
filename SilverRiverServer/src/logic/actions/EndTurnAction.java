package logic.actions;

import logic.player.Player;

public class EndTurnAction extends GameAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Player playerTurn;
	
	/*
	 * Constructor
	 */
	public EndTurnAction(int gameId, Player playerTurn){
		super(gameId, "EndTurnAction");
		this.playerTurn = playerTurn;
	}
	
	/*
	 * Devuelve el jugador activo del turno
	 */
	public Player getPlayerTurn(){
		return this.playerTurn;
	}
}
