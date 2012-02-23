package webservice;

import logic.Facade;
import logic.actions.MoveAction;
import entities.*;

public class SilverRiverService {	

	public MoveAction move(int gameId, int shipId, Coordinate destination){
		return Facade.getInstance().move(gameId, shipId, destination);	
	}
}
