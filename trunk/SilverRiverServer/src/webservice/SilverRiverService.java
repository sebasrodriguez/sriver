package webservice;

import entities.*;
import logic.Facade;
import logic.actions.Action;
import logic.actions.EndTurnAction;
import logic.actions.EnterPortAction;
import logic.actions.FireAction;
import logic.actions.MoveAction;
import logic.actions.RotateAction;
import logic.actions.SaveGameAction;

public class SilverRiverService {	

	public MoveAction move(int gameId, int shipId, Coordinate destination){
		return Facade.getInstance().move(gameId, shipId, destination);	
	}
	
	public FireAction fireAmmo(int gameId, int shipFiringId, Coordinate firingPoint){
		return Facade.getInstance().fireAmmo(gameId, shipFiringId, firingPoint);
	}
	
	public FireAction fireTorpedo(int gameId, int shipFiringId){
		return Facade.getInstance().fireTorpedo(gameId, shipFiringId);
	}
	
	public RotateAction rotate(int gameId, int shipId, int degrees){
		Cardinal destination = new Cardinal(degrees);
		return Facade.getInstance().rotate(gameId, shipId, destination);
	}
	
	public EndTurnAction endTurn(int gameId){
		return Facade.getInstance().endTurn(gameId);
	}
	
	public SaveGameAction saveGame(int gameId){
		 return Facade.getInstance().saveGame(gameId);
	}	
	
	public int getGameIdLoading(String usernameWaiting){
		return Facade.getInstance().getGameIdLoading(usernameWaiting);
	}
	
	public int loadGame(String username){
		return Facade.getInstance().loadGame(username);
	}	
	
	public void endGame(int gameId){
		Facade.getInstance().endGame(gameId);
	}
	
	public int newGame(String username){
		return Facade.getInstance().newGame(username);
	}
	
	public int checkGameId(){
		return Facade.getInstance().checkGameId();
	}
	
	public GameVO getGame(int gameId){		
		return Facade.getInstance().getGame(gameId);
	}
	
	public Action[] getActions(int gameId, String username){		
		return Facade.getInstance().getActions(gameId, username);
	}	
	
	public EnterPortAction enterPort1 (int gameId, int shipId){
		return Facade.getInstance().enterPort1(gameId, shipId);
	}
	
	public EnterPortAction enterPort2 (int gameId, int shipId, String attribute){
		return Facade.getInstance().enterPort2(gameId, shipId, attribute);
	}
	
	/*public Action[] pruebaActions(){
		return Facade.getInstance().pruebaActions();
	}*/
	
	
}
