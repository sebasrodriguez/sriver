package logic.actions;

import logic.entities.Coordinate;
import logic.entities.ShipVO;


public class MoveAction extends ShipAction{

	private Coordinate position;
	
	
	public MoveAction(ShipVO shipMoving, Coordinate position){
		super(shipMoving, "MoveAction");
		this.position = position;
	}

	/*
	 * Getters
	 */
	public Coordinate getPosition(){
		return this.position;
	}
}

