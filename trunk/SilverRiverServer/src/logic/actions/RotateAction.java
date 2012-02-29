package logic.actions;

import logic.entities.Cardinal;
import logic.entities.ShipVO;

public class RotateAction extends ShipAction{

	private Cardinal cardinal;
	

	
	public RotateAction(ShipVO shipRotating, Cardinal cardinal){
		super(shipRotating, "RotateAction");
		this.cardinal = cardinal;
	}
	
	/*
	 * Getters
	 */
	public Cardinal getCardinal(){
		return this.cardinal;
	}
}
