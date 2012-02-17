package logica;

public abstract class ShipAction extends Action {

	private Ship ship;
	
	/*
	 * Constructores
	 */
	ShipAction(){
		
	}
	
	ShipAction(Ship ship){
		this.ship = ship;
	}	
	
	/*
	 * Getters
	 */
	public Ship getShip(){
		return this.ship;
	}
}
