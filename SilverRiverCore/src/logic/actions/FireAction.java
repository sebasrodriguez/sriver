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
	
	//Calcula la distacia entre el barco que dispara y el punto donde el jugador hizo click
	//Esta distacia se vá a usar para variar que tanto se puede desviar el disparo.
	//A más distacia más se va a desviar el disparo.
	public double caluclateDistance()
	{
		int x1 = this.getShip().getPosition().getX();
		int y1 = this.getShip().getPosition().getY();
		int x2 = this.getHitCoordenate().getX();
		int y2 = this.getHitCoordenate().getY();
		
		return  Math.hypot(x2 - x1, y2 - y1);
	}
	
	//Calcula donde pega el disparo dependiendo de la distancia entre
	//el barco que dispara y el objetivo.
	//El punto final se calcula con un número aleatorio entre x - offset e x + offset, lo mismo para las y
	public Coordinate calculateHitPoint()
	{
		Coordinate coord = new Coordinate();
		int offset = 50;
		
		//int offset = (int)this.caluclateDistance();
		int max_x = this.getHitCoordenate().getX() + offset;
		int max_y = this.getHitCoordenate().getY() + offset;
		
		int min_x = max_x - (offset * 2);
		int min_y = max_y - (offset * 2);
		
		int x = (int)(Math.random() * (max_x - min_x)) + min_x;
		int y = (int)(Math.random() * (max_y - min_y)) + min_y;
		
		coord.setX(x);
		coord.setY(y);
		
		return coord;
	}
}
