package logic.actions;


public class GameAction extends Action{

	private int gameId;
	
	/*
	 * Constructor
	 */
	public GameAction(int gameId, String type){
		super(type);
		this.gameId = gameId;
	}
	
	/*
	 * Devuelve el id de la partida
	 */
	public int getGameId(){
		return this.gameId;
	}
}
