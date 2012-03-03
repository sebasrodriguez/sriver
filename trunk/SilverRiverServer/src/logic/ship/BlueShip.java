package logic.ship;

import java.io.Serializable;

import entities.BlueShipVO;
import entities.Cardinal;
import entities.Coordinate;

public class BlueShip extends Ship  implements Serializable {

	/*
	 * Constructores
	 */
	
	public BlueShip(){
		
	}
	
	public BlueShip(int id, int speed, int armor, int ammo, int torpedo, int viewRange, int size, Coordinate position, Cardinal orientation){
		super(id, speed, armor, ammo, torpedo, viewRange, size, position, orientation);
	}
	
	/*
	 * Devuelve al barco azul como ValueObject
	 */
	public BlueShipVO mapToValueObject(){		
		BlueShipVO blueShipVOToReturn = new BlueShipVO(this.getId(), this.getSpeed(), this.getArmor(), this.getAmmo(), this.getTorpedo(), this.getViewRange(), this.getSize(), this.getPosition(),this.getOrientation());
		return blueShipVOToReturn;
	}
	
	
}
