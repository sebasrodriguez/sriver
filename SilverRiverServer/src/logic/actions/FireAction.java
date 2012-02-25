package logic.actions;

import logic.ship.Ship;
import entities.Coordinate;
import entities.Weapon;

public class FireAction extends ShipAction {
	
	private Weapon weaponType;
	private Coordinate hitCoordenate;
	private boolean hit;
	private Ship affectedShip;
	
	
	
	/*
	 * Constructores
	 */
	public FireAction(){
		
	}
	
	public FireAction(Ship shipFiring, Weapon weaponType, Coordinate hitCoordenate, boolean hit, Ship affectedShip){
		super(shipFiring);
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
	
	public Ship getAffectedShip(){
		return this.affectedShip;
	}
	
	
	/*
	 * Setters
	 */
	public void setWeaponType(Weapon weaponType){
		this.weaponType = weaponType;
	}
	
	public void setHitCoordenate(Coordinate hitCoordenate){
		this.hitCoordenate = hitCoordenate;
	}
	
	public void setHit(boolean hit){
		this.hit = hit;
	}
	
	public void setAffectedShip(Ship affectedShip){
		this.affectedShip = affectedShip;
	}	
}
