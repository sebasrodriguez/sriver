package logica;

public class RotateAction extends Action{

	private Cardinal cardinal;
	
	/*
	 * Constructors 
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
