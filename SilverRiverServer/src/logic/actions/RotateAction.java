package logic.actions;

import entities.Cardinal;
import entities.ShipVO;

public class RotateAction extends ShipAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
