package logica;

public class LeavePortAction extends ShipAction{

	private int port;
	
	/*
	 * Constructores
	 */
	public LeavePortAction(){
		
	}
	
	public LeavePortAction(Ship ship, int port){
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
