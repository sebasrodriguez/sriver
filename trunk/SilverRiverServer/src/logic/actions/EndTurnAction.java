package logic.actions;

import logic.player.Player;

public class EndTurnAction extends GameAction {
	
	private Player playerTurn;
	

	public EndTurnAction(int gameId, Player playerTurn){
		super(gameId, "EndTurnAction");
		this.playerTurn = playerTurn;
	}
	
	/*
	 * Getters
	 */
	public Player getPlayerTurn(){
		return this.playerTurn;
	}
}
