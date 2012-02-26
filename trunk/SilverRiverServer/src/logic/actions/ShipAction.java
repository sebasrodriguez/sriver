package logic.actions;

import entities.ShipVO;

public abstract class ShipAction extends Action {

	private ShipVO ship;
	
	
	public ShipAction(ShipVO ship2){
		this.ship = ship2;
	}	
	
	/*
	 * Getters
	 */
	public ShipVO getShip(){
		return this.ship;
	}
}
