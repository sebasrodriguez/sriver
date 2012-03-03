package entities;

import java.io.Serializable;

public class PlayerVO  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
