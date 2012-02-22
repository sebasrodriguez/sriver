package logica;

import entities.Cardinal;
import entities.ShipVO;

public class RotateAction extends ShipAction{

	private Cardinal cardinal;
	
	/*
	 * Constructores 
	 */
	public RotateAction(){
		
	}
	
	public RotateAction(ShipVO shipRotating, Cardinal cardinal){
		super(shipRotating);
		this.cardinal = cardinal;
	}
	
	/*
	 * Getters
	 */
	public Cardinal getCardinal(){
		return this.cardinal;
	}
	
	/*
	 * Setters
	 */
	public void setCardinal(Cardinal cardinal){
		this.cardinal = cardinal;
	}
}
