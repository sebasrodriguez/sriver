package logic.actions;


public class GameAction extends Action{

	private int gameId;
	
	
	public GameAction(int gameId){
		this.gameId = gameId;
	}
	
	/*
	 * Getters
	 */
	public int getGameId(){
		return this.gameId;
	}
}
