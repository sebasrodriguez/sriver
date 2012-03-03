package entities;

import java.io.Serializable;
import java.util.ArrayList;

import logic.actions.Action;


public class GameVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private PlayerVO redPlayer;
	private PlayerVO bluePlayer;
	private Action[] redActionQueue;
	private Action[] blueActionQueue;
	private ShipVO[] ships;
	private TurnVO turn;
	
	
	/*
	 * Constructor
	 */
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
	
	
	/*
	 * Metodos publicos
	 */	
	
	/*
	 * Devuelve el id del game
	 */
	public int getId() 
	{
		return id;
	}

	/*
	 * Devuelve el jugador rojo en ValueObject
	 */
	public PlayerVO getRedPlayer() 
	{
		return redPlayer;
	}
	
	/*
	 * Devuelve el jugador azul en ValueObject
	 */
	public PlayerVO getBluePlayer() 
	{
		return bluePlayer;
	}
	
	/*
	 * Devuelve la cola de acciones del juador azul en un Array
	 */
	public Action[] getBlueActionQueue() 
	{
		return blueActionQueue;
	}

	/*
	 * Devuelve la cola de acciones del jugador rojo en un array
	 */
	public Action[] getRedActionQueue() 
	{
		return redActionQueue;
	}

	/*
	 * Devuelve un array de los barcos en ValueObject
	 */
	public ShipVO[] getShips() 
	{
		return ships;
	}
	
	/*
	 * Devuelve el turno en forma de ValueObject
	 */
	public TurnVO getTurn() 
	{
		return turn;
	}	
	
	/*
	 * Metodos privados
	 */
	
	/*
	 * Convierte el ArrayList<Action> en un array simple: Action[]
	 */
	private Action[] convertToArray(ArrayList<Action>actions){
		Action[] actionsArr = new Action[actions.size()];
		
		for(int i=0; i < actions.size(); i++){			
			actionsArr[i] = actions.get(i);
		}
		
		return actionsArr;		
	}
	
	/*
	 * Convierte el ArrayList<ShipVO> en un array simple ShipVO[]	
	 */
	private ShipVO[] convertToShipArray(ArrayList<ShipVO>ships){
		ShipVO[] shipsArr = new ShipVO[ships.size()];
		
		for(int i=0; i < ships.size(); i++){			
			shipsArr[i] = ships.get(i);
		}
		
		return shipsArr;		
	}
}
