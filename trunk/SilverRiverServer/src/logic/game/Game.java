package logic.game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

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
	
	/*
	 * Constructores
	 */
	public Game(){
		
	}
	
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
	
	/*
	 * Devuelve el id del game
	 */
	public int getId(){
		return id;
	}

	/*
	 * Setea el id del game
	 */
	public void setId(int id){
		this.id = id;
	}

	/*
	 * Devuelve el jugador rojo
	 */
	public Player getRedPlayer(){
		return redPlayer;
	}

	/*
	 *Setea al jugador rojo
	 */
	public void setRedPlayer(Player redPlayer){
		this.redPlayer = redPlayer;
	}

	/*
	 * Devuelve al jugador azul
	 */
	public Player getBluePlayer(){
		return bluePlayer;
	}

	/*
	 * Setea al jugador azul
	 */
	public void setBluePlayer(Player bluePlayer){
		this.bluePlayer = bluePlayer;
	}

	/*
	 * Devuelve la cola de acciones del jugador rojo
	 */
	public ArrayList<Action> getRedActionQueue(){
		return redActionQueue;
	}

	/*
	 * Setea la cola de acciones del jugador rojo
	 */
	public void setRedActionQueue(ArrayList<Action> redActionQueue){
		this.redActionQueue = redActionQueue;
	}

	/*
	 * Devuelve la cola de acciones del jugador azul
	 */
	public ArrayList<Action> getBlueActionQueue(){
		return blueActionQueue;
	}

	/*
	 * Setea la cola de acciones del jugador azul
	 */
	public void setBlueActionQueue(ArrayList<Action> blueActionQueue){
		this.blueActionQueue = blueActionQueue;
	}

	/*
	 * Devuelve la lista de barcos del game
	 */
	public ArrayList<Ship> getShips(){
		return ships;
	}

	/*
	 * Setea la lista de barcos 
	 */
	public void setShips(ArrayList<Ship> ships){
		this.ships = ships;
	}

	/*
	 * Devuelve el turno
	 */
	public Turn getTurn(){
		return turn;
	}

	/*
	 * Setea el turno
	 */
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
	/*public void destroyedShip(int shipId){		
		Ship shipToRemove = this.getShip(shipId);
		int indexToRemove = this.ships.indexOf(shipToRemove);
		this.ships.remove(indexToRemove);

	}*/
	
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
	
	
	/*
	 * Devuelve el game en ValueObject
	 */
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
		int x = 0;
		int y = 0;
		
		//barcoRojo
		y = 10;
		x = this.randomX(15);
		Coordinate position = new Coordinate(x,y);
		Cardinal cardinal = this.randomCardinal();
		Ship redShip = new RedShip(0,10,10,12,3,10,3,position, cardinal);
		shipsToReturn.add(redShip);
		
		//barcoAzul1
		y = 30;
		x = this.randomX(15);
		position = new Coordinate(x,y);
		cardinal = this.randomCardinal();
		Ship blueShip1 = new BlueShip(1,10,5,12,3,10,1,position, cardinal);
		shipsToReturn.add(blueShip1);
		
		//barcoAzul2
		y = 31;
		x = this.randomX(15);
		position = new Coordinate(x,y);
		cardinal = this.randomCardinal();
		Ship blueShip2 = new BlueShip(2,10,5,6,1,5,1,position, cardinal);
		shipsToReturn.add(blueShip2);
		
		//barcoAzul3
		y = 32;
		x = this.randomX(15);
		position = new Coordinate(x,y);
		cardinal = this.randomCardinal();
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
	
	/*
	 * Metodo que genera el random entre un rango de valores, usado para variar el X de la posicion
	 */
	private int randomX(int minimum){
		Random randomX = new Random();
		int randomToReturn = randomX.nextInt(minimum) + minimum;
		
		return randomToReturn;		
	}
	
	/*
	 * Metodo que genera un random del cardinal
	 */
	private Cardinal randomCardinal(){
		int max = 360;
		Random random = new Random();
		Cardinal cardinalToReturn; 
		
		int randomPosition = random.nextInt(max);
		
		if(0 <= randomPosition  && randomPosition < 45){
			cardinalToReturn = new Cardinal(Cardinal.N);		
		}else{
			if(45 <= randomPosition && randomPosition < 90){
				cardinalToReturn = new Cardinal(Cardinal.NE);
			}else{
				if(90 <= randomPosition && randomPosition < 135){
					cardinalToReturn = new Cardinal(Cardinal.E);
				}else{
					if(135 <= randomPosition && randomPosition < 180){
						cardinalToReturn = new Cardinal(Cardinal.SE);
					}else{
						if(180 <= randomPosition && randomPosition < 225){
							cardinalToReturn = new Cardinal(Cardinal.S);
						}else{
							if(225 <= randomPosition && randomPosition < 270){
								cardinalToReturn = new Cardinal(Cardinal.SW);
							}else{
								if(270 <= randomPosition && randomPosition < 315){
									cardinalToReturn = new Cardinal(Cardinal.W);
								}else{
									cardinalToReturn = new Cardinal(Cardinal.NW);
								}
							}
						}
					}
				}
			}			
		}
		return cardinalToReturn;		
	}
	
}
