package logic.game;

import java.util.ArrayList;
import java.util.Iterator;

import logic.actions.Action;
import logic.player.Player;
import logic.ship.Ship;

import entities.Coordinate;


public class Game 
{
	private int id;
	private Player redPlayer;
	private Player bluePlayer;
	private ArrayList<Action> redActionQueue;
	private ArrayList<Action> blueActionQueue;
	private ArrayList<Ship> ships;
	private Turn turn;
	
	public Game(){
		
	}
	
	public Game(int id, Player redPlayer, Player bluePlayer, ArrayList<Action> redActionQueue, 
			ArrayList<Action> blueActionQueue,ArrayList<Ship> ships, Turn turn) {
		
		super();
		this.id = id;
		this.redPlayer = redPlayer;
		this.bluePlayer = bluePlayer;
		this.redActionQueue = redActionQueue;
		this.blueActionQueue = blueActionQueue;
		this.ships = ships;
		this.turn = turn;
	}

	public int getId(){
		return id;
	}

	public void setId(int id){
		this.id = id;
	}

	public Player getRedPlayer(){
		return redPlayer;
	}

	public void setRedPlayer(Player redPlayer){
		this.redPlayer = redPlayer;
	}

	public Player getBluePlayer(){
		return bluePlayer;
	}

	public void setBluePlayer(Player bluePlayer){
		this.bluePlayer = bluePlayer;
	}

	public ArrayList<Action> getRedActionQueue(){
		return redActionQueue;
	}

	public void setRedActionQueue(ArrayList<Action> redActionQueue){
		this.redActionQueue = redActionQueue;
	}

	public ArrayList<Action> getBlueActionQueue(){
		return blueActionQueue;
	}

	public void setBlueActionQueue(ArrayList<Action> blueActionQueue){
		this.blueActionQueue = blueActionQueue;
	}

	public ArrayList<Ship> getShips(){
		return ships;
	}

	public void setShips(ArrayList<Ship> ships){
		this.ships = ships;
	}

	public Turn getTurn(){
		return turn;
	}

	public void setTurn(Turn turn){
		this.turn = turn;
	}
	
	/*
	 * Devuelve el barco a partir de su id, en caso de no econtrarlo devuelve null
	 * Precondicion: el barco debe existir en el array
	 */
	public Ship getShip(int shipId){
		Ship shipToReturn = null;
		Iterator<Ship> shipIterator = this.ships.iterator();
		boolean found = false;		
		
		
		while (shipIterator.hasNext() && !found){
			Ship aux = shipIterator.next();
			if(aux.getId() == shipId){
				shipToReturn = aux;
				found = true;
			}			
		}
		return shipToReturn;		
	}
	
	/*
	 * Funcion que devuelve el id del barco disparado a partir de una posicion a la cual se disparo
	 * Devuelve el id del barco dañado o -1 en caso de que no haya barco en dicha posicion
	 */
	public int getShipFiredId(Coordinate position){
		int shipIdToReturn = -1;
		boolean found = false;
		Iterator<Ship> shipIterator = this.ships.iterator();
		
		while(shipIterator.hasNext() && !found){
			Ship aux = shipIterator.next();
			if(aux.getPosition().getX() == position.getX() && aux.getPosition().getY() == position.getY()){
				found = true;
				shipIdToReturn = aux.getId();
			}
		}		
		return shipIdToReturn;		
	}
	
	
	/*
	 * En caso de hundimiento de barco elimina el mismo del ArrayList
	 */
	public void destoyedShip(int shipId){		
		/*Iterator<Ship> shipIt = this.ships.iterator();
		Ship shipToRemove = this.getShip(shipId);
		this.ships.clear();
		
		while
		*/
		Ship shipToRemove = this.getShip(shipId);
		int indexToRemove = this.ships.indexOf(shipToRemove);
		System.out.println(indexToRemove);		
		this.ships.remove(indexToRemove);

	}
}
