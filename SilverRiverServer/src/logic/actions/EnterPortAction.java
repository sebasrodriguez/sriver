package logic.actions;

import entities.ShipVO;


public class EnterPortAction extends ShipAction {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int port;
	
	/*
	 * Constructor
	 */
	public EnterPortAction(ShipVO ship, int port){
		super(ship, "EnterPortAction");
		this.port = port;
	}
	
	/*
	 * Devuelve el puerto al cual entro
	 */
	public int getPort(){
		return this.port;
	}
}
