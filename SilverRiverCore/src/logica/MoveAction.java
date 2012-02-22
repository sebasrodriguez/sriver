package logica;

import entities.Coordinate;
import entities.ShipVO;

public class MoveAction extends ShipAction{

	private Coordinate position;
	
	
	/*
	 * Constructores
	 */
	public MoveAction(){
		
	}
	
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
	
	/*
	 * Setters
	 */
	public void setPosition(Coordinate position){
		this.position = position;
	}
}

