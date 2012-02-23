package entities;

public class RedShipVO extends ShipVO {	

	public RedShipVO(){		
	}
	
	public RedShipVO(int id, int speed, int armor, int ammo, int torpedo, int viewRange, int size, Coordinate position, Cardinal orientation){
		super(id, speed, armor, ammo, torpedo, viewRange, size, position, orientation);
	}
}
