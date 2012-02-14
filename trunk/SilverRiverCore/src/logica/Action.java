package logica;

public abstract class Action {

	private Ship ship;
	
	
	Action(){
		
	}
	
	Action(Ship ship){
		this.ship = ship;
	}
	
	public Ship getShip(){
		return this.ship;
	}
	
	public void setShip(Ship ship){
		this.ship = ship;
	}
	
}
