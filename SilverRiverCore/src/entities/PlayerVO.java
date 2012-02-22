package entities;

public class PlayerVO {

	private int id;
	private String username;
	
	public PlayerVO()
	{
	}
	
	public PlayerVO(int id, String username)
	{
		this.id = id;
		this.username = username;
	}
	
	/* Setters y Getters */
	
	public int getId()
	{
		return this.id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public String getUsername()
	{
		return this.username;
	}
	
	public void setUsername(String user)
	{
		this.username = user;
	}
}
