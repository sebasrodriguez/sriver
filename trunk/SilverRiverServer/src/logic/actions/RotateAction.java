package logic.actions;

import java.io.Serializable;

import entities.Cardinal;
import entities.ShipVO;

public class RotateAction extends ShipAction implements Serializable{

	private Cardinal cardinal;
	

	/*
	 * Constructor
	 */
	public RotateAction(ShipVO shipRotating, Cardinal cardinal){
		super(shipRotating, "RotateAction");
		this.cardinal = cardinal;
	}
	
	/*
	 * Devuelve el cardinal
	 */
	public Cardinal getCardinal(){
		return this.cardinal;
	}
}
