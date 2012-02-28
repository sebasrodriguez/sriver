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
		this.redActionQueue = this.convertToArray(redActionQueue);
		this.blueActionQueue = this.convertToArray(blueActionQueue);
		this.ships = this.convertToShipArray(ships);
		this.turn = turn;
	}
	
	private Action[] convertToArray(ArrayList<Action>actions){
		Action[] actionsArr = new Action[actions.size()];
		
		for(int i=0; i < actions.size(); i++){			
			actionsArr[i] = actions.get(i);
		}
		
		return actionsArr;		
	}
	
	private ShipVO[] convertToShipArray(ArrayList<ShipVO>ships){
		ShipVO[] shipsArr = new ShipVO[ships.size()];
		
		for(int i=0; i < ships.size(); i++){			
			shipsArr[i] = ships.get(i);
		}
		
		return shipsArr;		
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
