package logica;

public class EndTurnAction extends GameAction {
	
	private Captain playerTurn;
	
	/*
	 * Constructores
	 */
	EndTurnAction(){
		
	}
	
	EndTurnAction(int gameId, Captain playerTurn){
		super(gameId);
		this.playerTurn = playerTurn;
	}
	
	/*
	 * Getters
	 */
	public Captain getPlayerTurn(){
		return this.playerTurn;
	}
	
	/*
	 * Setters
	 */
	public void setPlayerTurn(Captain playerTurn){
		this.playerTurn = playerTurn;
	}
}
