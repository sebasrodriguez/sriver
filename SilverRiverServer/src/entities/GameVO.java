package entities;

import logic.actions.Action;


public class GameVO {
	
	private int id;
	private PlayerVO redPlayer;
	private PlayerVO bluePlayer;
	private Action[] redActionQueue;
	private Action[] blueActionQueue;
	private ShipVO[] ships;
	private TurnVO turn;
	
	public GameVO(int id, PlayerVO redPlayer, PlayerVO bluePlayer,
			Action[] redActionQueue, Action[] blueActionQueue,
			ShipVO[] ships, TurnVO turn) 
	{		
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

	public PlayerVO getRedPlayer() 
	{
		return redPlayer;
	}

	public void setRedPlayer(PlayerVO redPlayer) 
	{
		this.redPlayer = redPlayer;
	}

	public PlayerVO getBluePlayer() 
	{
		return bluePlayer;
	}

	public void setBluePlayer(PlayerVO bluePlayer) 
	{
		this.bluePlayer = bluePlayer;
	}

	public Action[] getRedActionQueue() 
	{
		return redActionQueue;
	}

	public void setRedActionQueue(Action[] redActionQueue) 
	{
		this.redActionQueue = redActionQueue;
	}

	public Action[] getBlueActionQueue() 
	{
		return blueActionQueue;
	}

	public void setBlueActionQueue(Action[] blueActionQueue) 
	{
		this.blueActionQueue = blueActionQueue;
	}

	public ShipVO[] getShips() 
	{
		return ships;
	}

	public void setShips(ShipVO[] ships) 
	{
		this.ships = ships;
	}

	public TurnVO getTurn() 
	{
		return turn;
	}

	public void setTurn(TurnVO turn) 
	{
		this.turn = turn;
	}
}
