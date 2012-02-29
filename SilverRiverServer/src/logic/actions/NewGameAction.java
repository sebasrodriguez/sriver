package logic.actions;

import logic.entities.PlayerVO;

public class NewGameAction extends GameAction {
	
	private PlayerVO redPlayer;
	private PlayerVO bluePlayer;
	
	public NewGameAction(int gameId, PlayerVO redPlayer, PlayerVO bluePlayer){
		super(gameId);
		this.redPlayer = redPlayer;
		this.bluePlayer = bluePlayer;
	}
	
	public PlayerVO getRedPlayer(){
		return redPlayer;
	}

	public PlayerVO getBluePlayer(){
		return bluePlayer;
	}
}