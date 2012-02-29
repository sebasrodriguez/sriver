package logic.actions;

import logic.entities.ShipVO;


public class LeavePortAction extends ShipAction{

	private int port;
	
	
	public LeavePortAction(ShipVO ship, int port){
		super(ship, "LeavePortAction");
		this.port = port;
	}
	
	/*
	 * Getters
	 */
	public int getPort(){
		return this.port;
	}	
}
