package entities;

public class BlueShipVO extends ShipVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BlueShipVO(int id, int speed, int armor, int ammo, int torpedo, int viewRange, int size, Coordinate position, Cardinal orientation){
		super(id, speed, armor, ammo, torpedo, viewRange, size, position, orientation);
	}
}
