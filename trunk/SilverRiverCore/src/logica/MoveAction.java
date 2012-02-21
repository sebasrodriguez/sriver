package logica;

public class MoveAction extends ShipAction{

	private Coordenate position;
	
	
	/*
	 * Constructores
	 */
	public MoveAction(){
		
	}
	
	public MoveAction(Ship shipMoving, Coordenate position){
		super(shipMoving);
		this.position = position;
	}

	/*
	 * Getters
	 */
	public Coordenate getPosition(){
		return this.position;
	}
	
	/*
	 * Setters
	 */
	public void setPosition(Coordenate position){
		this.position = position;
	}
}

