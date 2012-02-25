package logic.player;

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
}