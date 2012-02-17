package logica;

public class RotateAction extends ShipAction{

	private Cardinal cardinal;
	
	/*
	 * Constructores 
	 */
	RotateAction(){
		
	}
	
	RotateAction(Ship shipRotating, Cardinal cardinal){
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
