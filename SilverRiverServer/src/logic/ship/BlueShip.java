package logic.ship;

import logic.entities.BlueShipVO;
import logic.entities.Cardinal;
import logic.entities.Coordinate;

public class BlueShip extends Ship {

	/*
	 * Constructores
	 */
	
	public BlueShip(){
		
	}
	
	public BlueShip(int id, int speed, int armor, int ammo, int torpedo, int viewRange, int size, Coordinate position, Cardinal orientation){
		super(id, speed, armor, ammo, torpedo, viewRange, size, position, orientation);
	}
	
	public BlueShipVO mapToValueObject(){		
		BlueShipVO blueShipVOToReturn = new BlueShipVO(this.getId(), this.getSpeed(), this.getArmor(), this.getAmmo(), this.getTorpedo(), this.getViewRange(), this.getSize(), this.getPosition(),this.getOrientation());
		return blueShipVOToReturn;
	}
	
	
}
