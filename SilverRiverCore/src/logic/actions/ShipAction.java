package logic.actions;

import logic.ship.Ship;

public abstract class ShipAction extends Action {

	private Ship ship;
	
	/*
	 * Constructores
	 */
	public ShipAction(){
		
	}
	
	public ShipAction(Ship ship2){
		this.ship = ship2;
	}	
	
	/*
	 * Getters
	 */
	public Ship getShip(){
		return this.ship;
	}
}
