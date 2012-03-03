package entities;

public class PlayerVO {

	private String username;
	
	
	/*
	 * Constructor
	 */
	public PlayerVO(String username){		
		this.username = username;
	}
	
	/*
	 * Devuelve el nombre del jugador 
	 */
	public String getUsername(){
		return this.username;
	}	
}
