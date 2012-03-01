package logic.game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import entities.Cardinal;
import entities.Coordinate;
import entities.GameVO;
import entities.PlayerVO;
import entities.ShipVO;
import entities.TurnVO;

import logic.actions.Action;
import logic.player.Player;
import logic.ship.BlueShip;
import logic.ship.RedShip;
import logic.ship.Ship;



public class Game implements Serializable
{
	private static final long serialVersionUID = 1L;
	private int id;
	private Player redPlayer;
	private Player bluePlayer;
	private ArrayList<Action> redActionQueue;
	private ArrayList<Action> blueActionQueue;
	private ArrayList<Ship> ships;
	private Turn turn;
	
	public Game(){
		
	}
	
	/*public Game(int id, Player redPlayer, Player bluePlayer, ArrayList<Action> redActionQueue, 
			ArrayList<Action> blueActionQueue,ArrayList<Ship> ships, Turn turn) {
	*/
	
	public Game(int id, Player redPlayer, Player bluePlayer){	
		super();
		this.id = id;
		this.redPlayer = redPlayer;
		this.bluePlayer = bluePlayer;
		this.redActionQueue = new ArrayList<Action>();
		this.blueActionQueue = new ArrayList<Action>();
		this.ships = this.createShipsArray();
		this.turn = new Turn(redPlayer);
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
	public void destroyedShip(int shipId){		
		Ship shipToRemove = this.getShip(shipId);
		int indexToRemove = this.ships.indexOf(shipToRemove);
		this.ships.remove(indexToRemove);

	}
	
	/*
	 * Inserta la accion en la cola del jugador rojo
	 */
	public void insertActionRedQueue(Action action){
		this.redActionQueue.add(action);
	}
	
	/*
	 * Inserta la accion en la cola del jugador azul
	 */
	public void insertActionBlueQueue(Action action){
		this.blueActionQueue.add(action);
	}
	
	/*
	 * Vacia las acciones de la cola del jugador rojo
	 * Este metodo se llama luego de consumir las acciones de dicha cola
	 */
	public void clearRedActionQueue(){
		this.redActionQueue.clear();
	}
	
	
	/*
	 * Vacia las acciones de la cola del jugador azul
	 * Este metodo se llama luego de consumir las acciones de dicha cola
	 */
	public void clearBlueActionQueue(){
		this.blueActionQueue.clear();
	}
	
	/*
	 * Devuelve una copia del game
	 */
	public Game clone(){
		Game gameToReturn = new Game();
		gameToReturn.id = this.id;
		gameToReturn.redPlayer = this.redPlayer;
		gameToReturn.bluePlayer = this.bluePlayer;
		gameToReturn.redActionQueue = this.redActionQueue;
		gameToReturn.blueActionQueue = this.blueActionQueue;
		gameToReturn.ships = this.ships;
		gameToReturn.turn = this.turn;
		
		return gameToReturn;
	}
	
	
	public GameVO mapToValueObject(){
		PlayerVO redPlayerVO = this.redPlayer.mapToValueObject();
		PlayerVO bluePlayerVO = this.bluePlayer.mapToValueObject();
		TurnVO turnVO = this.turn.mapToValueObject();
		ArrayList<ShipVO> shipsVO = new ArrayList<ShipVO>();
		
		shipsVO = this.convertShipsToShipsVO();
		
		GameVO gameVOToreturn = new GameVO(this.id, redPlayerVO, bluePlayerVO, redActionQueue, blueActionQueue, shipsVO, turnVO);
		
		return gameVOToreturn;
	}
	
	/*
	 * Metodo que retorna el redActionQueue como un Action[]
	 */
	public Action[] redActionQueueMapToArray(){		
		Iterator<Action> raqIt = this.redActionQueue.iterator();
		Action[] actionsToReturn = new Action[this.redActionQueue.size()];
		int i = 0;
		
		while(raqIt.hasNext()){
			actionsToReturn[i] = raqIt.next();
			i++;
		}
		
		return actionsToReturn;		
	}
	
	/*
	 *Metodo que retorna el blueActionQueue como un Action[]
	 */
	public Action[] blueActionQueueMapToArray(){		
		Iterator<Action> raqIt = this.blueActionQueue.iterator();
		Action[] actionsToReturn = new Action[this.blueActionQueue.size()];
		int i = 0;
		
		while(raqIt.hasNext()){
			actionsToReturn[i] = raqIt.next();
			i++;
		}
		
		return actionsToReturn;		
	}
	
	/*
	 * Metodo que retorna el ships como un Ship[]
	 */
	public Ship[] shipsMapToArray(){
		Iterator<Ship> shIt = this.ships.iterator();
		Ship[] shipsToReturn = new Ship[this.ships.size()];
		int i = 0;
		
		while(shIt.hasNext()){
			shipsToReturn[i] = shIt.next();
			i++;
		}
		return shipsToReturn;
	}
	
	/*
	 * Metodos privados
	 */
	
	/*
	 * Metodo que crea devuelve un ArrayList<Ship> de los barcos con los mismos ya creados e insertados
	 */
	private ArrayList<Ship> createShipsArray(){
		ArrayList<Ship> shipsToReturn = new ArrayList<Ship>();
		
		//barcoRojo
		Coordinate position = new Coordinate(10,10);
		Cardinal cardinal = new Cardinal(45);
		Ship redShip = new RedShip(0,10,10,12,3,10,3,position, cardinal);
		shipsToReturn.add(redShip);
		
		//barcoAzul1
		position = new Coordinate(20,20);
		cardinal = new Cardinal(90);
		Ship blueShip1 = new BlueShip(1,10,5,12,3,10,1,position, cardinal);
		shipsToReturn.add(blueShip1);
		
		//barcoAzul2
		position = new Coordinate(25,25);
		cardinal = new Cardinal(-45);
		Ship blueShip2 = new BlueShip(2,10,5,6,1,5,1,position, cardinal);
		shipsToReturn.add(blueShip2);
		
		//barcoAzul3
		position = new Coordinate(32,32);
		cardinal = new Cardinal(-90);
		Ship blueShip3 = new BlueShip(3,10,5,6,1,5,1,position, cardinal);		
		shipsToReturn.add(blueShip3);
		
		return shipsToReturn;		
	}
	
	/*
	 * Metodo que el ArrayList<Ship> lo transforma en un ArrayList<ShipVO>
	 */
	private ArrayList<ShipVO> convertShipsToShipsVO(){
		ArrayList<ShipVO> shipsVOToReturn = new ArrayList<ShipVO>();
		Iterator<Ship> shipsIt = this.ships.iterator();
		
		while(shipsIt.hasNext()){
			shipsVOToReturn.add(shipsIt.next().mapToValueObject());
		}
		
		return shipsVOToReturn;
	}	
}
