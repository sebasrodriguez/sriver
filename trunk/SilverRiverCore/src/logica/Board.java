package logica;

import java.util.ArrayList;

public class Board {

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
	
	public Ship[] getShips(){
		return this.ships;
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
	
	public void saveGame(){
		
	}
	
	public void loadGame(){
		
	}
	
	
	
}
