package logic.actions;

import entities.PlayerVO;;

public class NewGameAction extends GameAction {
	
	private PlayerVO redPlayer;
	private PlayerVO bluePlayer;
	
	public NewGameAction(){		
	}
	
	public NewGameAction(int gameId, PlayerVO redPlayer, PlayerVO bluePlayer){
		super(gameId);
		this.redPlayer = redPlayer;
		this.bluePlayer = bluePlayer;
	}
	
	public PlayerVO getRedPlayer(){
		return redPlayer;
	}

	public void setRedPlayer(PlayerVO redPlayer){
		this.redPlayer = redPlayer;
	}

	public PlayerVO getBluePlayer(){
		return bluePlayer;
	}

	public void setBluePlayer(PlayerVO bluePlayer){
		this.bluePlayer = bluePlayer;
	}
}