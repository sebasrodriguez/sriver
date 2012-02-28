package entities;

import java.util.ArrayList;

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
			ArrayList<Action> redActionQueue, ArrayList<Action> blueActionQueue,
			ArrayList<ShipVO> ships, TurnVO turn) 
	{		
		this.id = id;
		this.redPlayer = redPlayer;
		this.bluePlayer = bluePlayer;
		this.redActionQueue = (Action[]) redActionQueue.toArray();
		this.blueActionQueue = (Action[]) blueActionQueue.toArray();
		this.ships = (ShipVO[]) ships.toArray();
		this.turn = turn;
	}

	public int getId() 
	{
		return id;
	}

	public PlayerVO getRedPlayer() 
	{
		return redPlayer;
	}
	
	public PlayerVO getBluePlayer() 
	{
		return bluePlayer;
	}
	
	public Action[] getBlueActionQueue() 
	{
		return blueActionQueue;
	}

	
	public Action[] getRedActionQueue() 
	{
		return redActionQueue;
	}

	
	public ShipVO[] getShips() 
	{
		return ships;
	}
	
	public TurnVO getTurn() 
	{
		return turn;
	}	
}
