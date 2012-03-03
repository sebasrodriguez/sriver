package logic.player;

import java.io.Serializable;

import entities.PlayerVO;

public class Player implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	
	/*
	 * Constructor
	 */
	public Player()
	{
	}
	
	public Player(String username)
	{		
		this.username = username;
	}
	
	/*
	 * Devuelve el nombre del usuario
	 */	
	public String getUsername()
	{
		return this.username;
	}
	
	/*
	 * Setea el nombre del usuario 
	 */
	public void setUsername(String user)
	{
		this.username = user;
	}
	
	/*
	 * Devuelve al jugador en ValueObject
	 */
	public PlayerVO mapToValueObject(){
		PlayerVO playerVOToReturn = new PlayerVO(this.username);
		return playerVOToReturn;
				
	}
}