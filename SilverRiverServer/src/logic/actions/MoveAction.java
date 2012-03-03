package logic.actions;

import entities.Coordinate;
import entities.ShipVO;


public class MoveAction extends ShipAction{

	private Coordinate position;
	
	/*
	 * Constructor
	 */
	public MoveAction(ShipVO shipMoving, Coordinate position){
		super(shipMoving, "MoveAction");
		this.position = position;
	}

	/*
	 * Devuelve la posicion del barco
	 */
	public Coordinate getPosition(){
		return this.position;
	}
}

