package webservice;

import logic.Facade;
import logic.actions.EndTurnAction;
import logic.actions.FireAction;
import logic.actions.MoveAction;
import logic.actions.RotateAction;
import entities.*;

public class SilverRiverService {	

	public MoveAction move(int gameId, int shipId, Coordinate destination){
		return Facade.getInstance().move(gameId, shipId, destination);	
	}
	
	public FireAction fire(int gameId, int shipFiringId, Coordinate firingPoint, Weapon weaponType){
		return Facade.getInstance().fire(gameId, shipFiringId, firingPoint, weaponType);
	}
	
	public RotateAction rotate(int gameId, int shipId, Cardinal destination){
		return Facade.getInstance().rotate(gameId, shipId, destination);
	}
	
	public EndTurnAction endTurn(int gameId){
		return Facade.getInstance().endTurn(gameId);
	}
	
	public void saveGame(int gameId){
		 Facade.getInstance().saveGame(gameId);
	}
	
	public void loadGame(int gameId){
		Facade.getInstance().loadGame(gameId);
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
	
	
	
}
