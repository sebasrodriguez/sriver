package logic.actions;

import entities.Coordinate;
import entities.ShipVO;
import entities.Weapon;

public class FireAction extends ShipAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Weapon weaponType;
	private Coordinate hitCoordinate;
	private boolean hit;
	private ShipVO affectedShip;
	
	
	/*
	 * Constructor
	 */
	public FireAction(ShipVO shipFiring, Weapon weaponType, Coordinate hitCoordinate, boolean hit, ShipVO affectedShip){
		super(shipFiring, "FireAction");
		this.weaponType = weaponType;
		this.hitCoordinate = hitCoordinate;
		this.hit = hit;
		this.affectedShip = affectedShip;
	}
	
	
	/*
	 * Devuelve el tipo del arma
	 */
	public Weapon getWeaponType(){
		return this.weaponType;
	}
	
	/*
	 * Devuelve el punto donde disparo
	 */
	public Coordinate getHitCoordinate(){
		return this.hitCoordinate;
	}
	
	/*
	 * Devuelve true si acerto
	 */
	public boolean getHit(){
		return this.hit;
	}
	
	/*
	 * En caso de haber acertado, devuelve el barco afectado en ValueObject
	 */
	public ShipVO getAffectedShip(){
		return this.affectedShip;
	}	
}
