package logica;

import java.util.List;

public class Game 
{
	private int id;
	private Player redPlayer;
	private Player bluePlayer;
	private List<Action> redActionQueue;
	private List<Action> blueActionQueue;
	private Ship[] ships;
	private Turn turn;
	
	public Game()
	{
		
	}
	
	public Game(int id, Player redPlayer, Player bluePlayer,
			List<Action> redActionQueue, List<Action> blueActionQueue,
			Ship[] ships, Turn turn) 
	{
		super();
		this.id = id;
		this.redPlayer = redPlayer;
		this.bluePlayer = bluePlayer;
		this.redActionQueue = redActionQueue;
		this.blueActionQueue = blueActionQueue;
		this.ships = ships;
		this.turn = turn;
	}

	public int getId() 
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	public Player getRedPlayer() 
	{
		return redPlayer;
	}

	public void setRedPlayer(Player redPlayer) 
	{
		this.redPlayer = redPlayer;
	}

	public Player getBluePlayer() 
	{
		return bluePlayer;
	}

	public void setBluePlayer(Player bluePlayer) 
	{
		this.bluePlayer = bluePlayer;
	}

	public List<Action> getRedActionQueue() 
	{
		return redActionQueue;
	}

	public void setRedActionQueue(List<Action> redActionQueue) 
	{
		this.redActionQueue = redActionQueue;
	}

	public List<Action> getBlueActionQueue() 
	{
		return blueActionQueue;
	}

	public void setBlueActionQueue(List<Action> blueActionQueue) 
	{
		this.blueActionQueue = blueActionQueue;
	}

	public Ship[] getShips() 
	{
		return ships;
	}

	public void setShips(Ship[] ships) 
	{
		this.ships = ships;
	}

	public Turn getTurn() 
	{
		return turn;
	}

	public void setTurn(Turn turn) 
	{
		this.turn = turn;
	}
}
