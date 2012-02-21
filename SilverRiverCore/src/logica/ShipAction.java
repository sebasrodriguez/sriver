package logica;

public abstract class ShipAction extends Action {

	private Ship ship;
	
	/*
	 * Constructores
	 */
	public ShipAction(){
		
	}
	
	public ShipAction(Ship ship){
		this.ship = ship;
	}	
	
	/*
	 * Getters
	 */
	public Ship getShip(){
		return this.ship;
	}
}
