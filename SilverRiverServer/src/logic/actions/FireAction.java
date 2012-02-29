package logic.actions;

import logic.entities.Coordinate;
import logic.entities.ShipVO;
import logic.entities.Weapon;

public class FireAction extends ShipAction {
	
	private Weapon weaponType;
	private Coordinate hitCoordenate;
	private boolean hit;
	private ShipVO affectedShip;
	
	
	
	public FireAction(ShipVO shipFiring, Weapon weaponType, Coordinate hitCoordenate, boolean hit, ShipVO affectedShip){
		super(shipFiring, "FireAction");
		this.weaponType = weaponType;
		this.hitCoordenate = hitCoordenate;
		this.hit = hit;
		this.affectedShip = affectedShip;
	}
	
	
	/*
	 * Getters
	 */
	public Weapon getWeaponType(){
		return this.weaponType;
	}
	
	public Coordinate getHitCoordenate(){
		return this.hitCoordenate;
	}
	
	public boolean getHit(){
		return this.hit;
	}
	
	public ShipVO getAffectedShip(){
		return this.affectedShip;
	}	
}
