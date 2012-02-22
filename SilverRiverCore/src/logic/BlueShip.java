package logic;

import entities.Cardinal;
import entities.Coordinate;

public class BlueShip extends Ship {

	/*
	 * Constructores
	 */
	
	public BlueShip(){
		
	}
	
	public BlueShip(int id, int speed, int armor, int ammo, int torpedo, int viewRange, int size, Coordinate position, Cardinal orientation){
		super(id, speed, armor, ammo, torpedo, viewRange, size, position, orientation);
	}
	
	
}
