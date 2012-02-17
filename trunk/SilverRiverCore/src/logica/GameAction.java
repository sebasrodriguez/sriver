package logica;

public class GameAction extends Action{

	private int gameId;
	
	/*
	 * Constructores
	 */
	GameAction(){
		
	}
	
	GameAction(int gameId){
		this.gameId = gameId;
	}
	
	/*
	 * Getters
	 */
	public int getGameId(){
		return this.gameId;
	}
	
	/*
	 * Setters
	 */
	public void setGameId(int gameId){
		this.gameId = gameId;
	}
}
