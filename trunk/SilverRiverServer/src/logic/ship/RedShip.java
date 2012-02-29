package logic.ship;

import entities.Cardinal;
import entities.Coordinate;
import entities.RedShipVO;


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
	
	public RedShipVO mapToValueObject(){		
		RedShipVO redShipVOToReturn = new RedShipVO(this.getId(), this.getSpeed(), this.getArmor(), this.getAmmo(), this.getTorpedo(), this.getViewRange(), this.getSize(), this.getPosition(),this.getOrientation());
		return redShipVOToReturn;
	}
	
}
