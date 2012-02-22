package logic.actions;

import logic.ship.Ship;


public class EnterPortAction extends ShipAction {

	
	private int port;
	
	/*
	 * Constructores
	 */
	public EnterPortAction(){
		
	}
	
	public EnterPortAction(Ship ship, int port){
		super(ship);
		this.port = port;
	}
	
	/*
	 * Getters
	 */
	public int getPort(){
		return this.port;
	}
	
	/*
	 * Setters
	 */
	public void setPort(int port){
		this.port = port;
	}
	
}
