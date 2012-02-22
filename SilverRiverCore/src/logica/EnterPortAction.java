package logica;

import entities.ShipVO;

public class EnterPortAction extends ShipAction {

	
	private int port;
	
	/*
	 * Constructores
	 */
	public EnterPortAction(){
		
	}
	
	public EnterPortAction(ShipVO ship, int port){
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
