package logic.actions;

import entities.ShipVO;


public class EnterPortAction extends ShipAction {

	
	private int port;
	

	public EnterPortAction(ShipVO ship, int port){
		super(ship, "EnterPortAction");
		this.port = port;
	}
	
	/*
	 * Getters
	 */
	public int getPort(){
		return this.port;
	}
}
