package logic.ship;

import entities.Cardinal;
import entities.Coordinate;


/*
 * Esta clase queda vacia para implementar los opcionales
 * El mantener esta clase aunque este vacia nos sirve para saber de que bando es
 */
public class RedShip extends Ship {
	
	
	/*
	 * Constructores
	 */
	
	public RedShip(){
		
	}
	
	public RedShip(int id, int speed, int armor, int ammo, int torpedo, int viewRange, int size, Coordinate position, Cardinal orientation){
		super(id, speed, armor, ammo, torpedo, viewRange, size, position, orientation);
	}	
}
