package logic.player;

import entities.PlayerVO;

public class Player
{
	
	private String username;
	
	public Player()
	{
	}
	
	public Player(String username)
	{		
		this.username = username;
	}
	
	/* Setters y Getters */
	
	
	public String getUsername()
	{
		return this.username;
	}
	
	public void setUsername(String user)
	{
		this.username = user;
	}
	
	public PlayerVO mapToValueObject(){
		PlayerVO playerVOToReturn = new PlayerVO(this.username);
		return playerVOToReturn;
				
	}
}