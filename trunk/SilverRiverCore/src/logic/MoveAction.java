package logic;

import entities.Coordinate;


public class MoveAction extends ShipAction{

	private Coordinate position;
	
	
	/*
	 * Constructores
	 */
	public MoveAction(){
		
	}
	
	public MoveAction(Ship shipMoving, Coordinate position){
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

