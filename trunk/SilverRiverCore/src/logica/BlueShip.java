package logica;

import entities.Cardinal;
import entities.Coordinate;
import entities.ShipVO;

public class BlueShip extends ShipVO {

	/*
	 * Constructores
	 */
	
	public BlueShip(){
		
	}
	
	public BlueShip(int id, int speed, int armor, int ammo, int torpedo, int viewRange, int size, Coordinate position, Cardinal orientation){
		super(id, speed, armor, ammo, torpedo, viewRange, size, position, orientation);
	}
	
	
}
