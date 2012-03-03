package logic.actions;

import entities.ShipVO;


public class LeavePortAction extends ShipAction{

	private int port;
	
	/*
	 * Constructor
	 */
	public LeavePortAction(ShipVO ship, int port){
		super(ship, "LeavePortAction");
		this.port = port;
	}
	
	/*
	 * Devuelve el puerto del cual salio
	 */
	public int getPort(){
		return this.port;
	}	
}
