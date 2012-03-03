package logic.actions;

import entities.ShipVO;

public abstract class ShipAction extends Action {

	private ShipVO ship;
	
	/*
	 * Constructor
	 */
	public ShipAction(ShipVO ship2, String type){
		super(type);		
		this.ship = ship2;
	}	
	
	/*
	 * Devuelve el barco en ValueObject
	 */
	public ShipVO getShip(){
		return this.ship;
	}
}
