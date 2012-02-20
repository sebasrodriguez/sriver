package logica;

import java.io.Serializable;
import java.util.ArrayList;

/*
 * IMPLEMENTARLA COMO SERIALIZE PARA QUE SE PUEDA GRABAR EN UNA BD
 * TENGO DUDAS DE LOS METODOS GET Y SET DE LAS QUEUE
 * FALTA IMPLEMENTAR SAVE Y LOAD GAME
 * 
 */
public class Board implements Serializable {

	private int id;
	private Captain redPlayer;
	private Captain bluePlayer;
	private ArrayList<Action> redActionQueue;
	private ArrayList<Action> blueActionQueue;
	private Ship ships[];
	private Shift shift;	
	
	
	Board(){
		
	}
	
	Board(Captain redPlayer, Captain bluePlayer, Ship ships[]){
		this.redPlayer = redPlayer;
		this.bluePlayer = bluePlayer;
		this.ships = ships;		
	}
	
	/*
	 * Getters
	 */
	public int getId(){
		return this.id;
	}
	
	public Captain getRedPlayer(){
		return this.redPlayer;
	}
	
	public Captain getBluePlayer(){
		return this.bluePlayer;
	}
	
	public ArrayList<Action> getRedActionQueue(){
		return this.redActionQueue;
	}
	
	public ArrayList<Action> getBlueActionQueue(){
		return this.blueActionQueue;
	}
	
	public Ship getShip(int shipId){
		Ship shipToReturn = null;
		boolean found = false;
		int i = 0;
		
		while(i < this.ships.length && !found){
			if(this.ships[i].getId() == shipId){
				shipToReturn = this.ships[i];
				found = true;
			}
			i++;
		}
		return shipToReturn;		
	}
	
	public int getShipFiredId(Coordenate position){
		int shipIdToReturn = -1;
		boolean found = false;
		int i = 0;
		
		while (i < this.ships.length && !found){
			if(this.ships[i].getPosition().equals(position)){
				found = true;
				shipIdToReturn = this.ships[i].getId();
			}
			i++;
		}
		return shipIdToReturn;		
	}
	
	public void destoyedShip(int shipId){
		boolean found = false;
		int i = 0;
		
		while(i < this.ships.length && !found){
			if(this.ships[i].getId() == shipId){				
				found = true;
				this.ships[i] = null;
			}
			i++;
		}
	}
	
	public Shift getShift(){
		return this.shift;
	}
	
	/*
	 * Setters
	 */
	
	public void setId(int id){
		this.id = id;
	}
	
	public void setRedPlayer(Captain redPlayer){
		this.redPlayer = bluePlayer;
	}
	
	public void setBluePlayer(Captain bluePlayer){
		this.bluePlayer = bluePlayer;
	}
	
	public void setRedActionQueue(ArrayList<Action> redActionQueue){
		this.redActionQueue = redActionQueue;
	}
	
	public void setBlueActionQueue(ArrayList<Action> blueActionQueue){
		this.blueActionQueue = blueActionQueue;
	}
	
	public void setShips(Ship ships[]){
		this.ships = ships;
	}
	
	public void setShift(Shift shift){
		this.shift = shift;
	}
	
	
	/*
	 * Acciones
	 */
	
	/*
	 * PARA MI ESTOS METODOS NO VAN ACA, PORQUE EL LOAD TIENE QUE CREAR EL OBJETO
	 * Y EL SAVE NO ME QUEDA 100% CLARO DE COMO SE HACE
	 */
	public void saveGame(){
		
	}
	
	public void loadGame(){
		
	}
	
	
	
}
