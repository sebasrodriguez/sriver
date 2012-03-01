package logic.actions;


public class GameAction extends Action{

	private int gameId;
	
	
	public GameAction(int gameId, String type){
		super(type);
		this.gameId = gameId;
	}
	
	/*
	 * Getters
	 */
	public int getGameId(){
		return this.gameId;
	}
}
