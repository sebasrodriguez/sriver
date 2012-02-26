package logic.actions;

import entities.Coordinate;
import entities.ShipVO;


public class MoveAction extends ShipAction{

	private Coordinate position;
	
	
	public MoveAction(ShipVO shipMoving, Coordinate position){
		super(shipMoving);
		this.position = position;
	}

	/*
	 * Getters
	 */
	public Coordinate getPosition(){
		return this.position;
	}
}

