package logic.ship;

import logic.entities.Cardinal;
import logic.entities.Coordinate;
import logic.entities.ShipVO;
import logic.entities.Weapon;

public abstract class Ship {

	
	private int id;
	private int speed;
	private int armor;
	private int ammo;
	private int torpedo;
	private int viewRange;
	private int size;
	private Coordinate position;	
	private Cardinal orientation;	
		
	/*
	 * Constructores
	 */
	public Ship(){
		
	}
	
	public Ship(int id, int speed, int armor, int ammo, int torpedo, int viewRange, int size, Coordinate position, Cardinal orientation){
		this.id = id;
		this.speed = speed;
		this.armor = armor;
		this.ammo = ammo;
		this.torpedo = torpedo;
		this.viewRange = viewRange;
		this.size = size;
		this.position = position;
		this.orientation = orientation;
	}
	
	
	/*
	 * Setters
	 */
	public void setId(int id){
		this.id = id;
	}
	
	public void setSpeed(int speed){
		this.speed = speed;		
	}
	
	public void setArmor(int armor){
		this.armor = armor;
	}
	
	public void setAmmo(int ammo){
		this.ammo = ammo;
	}
	
	public void setTorpedo(int torpedo){
		this.torpedo = torpedo;
	}
	
	public void setViewRange(int viewRange){
		this.viewRange = viewRange;
	}
	
	public void setSize(int size){
		this.size = size;
	}

	public void setPosition(Coordinate position){
		this.position = position;
	}
	
	public void setOrientation(Cardinal orientation){
		this.orientation = orientation;
	}
	
	

	/*
	 * Getters
	 */
	public int getId(){
		return this.id;		
	}
	
	public int getSpeed(){
		return this.speed;
	}
	
	public int getArmor(){
		return this.armor;
	}
	
	public int getAmmo(){
		return this.ammo;
	}
	
	public int getTorpedo(){
		return this.torpedo;
	}
	
	public int getViewRange(){
		return this.viewRange;
	}
	
	public int getSize(){
		return this.size;
	}
	
	public Coordinate getPosition(){
		return this.position;
	}
	
	public Cardinal getOrientation(){
		return this.orientation;
	}	
	
	/*
	 * Metodo que actualiza la posicion del barco 
	 */
	public void move(Coordinate destination){
		this.position = destination;
	}
	
	/*
	 * Metodo que actualiza la orientacion del barco
	 */
	public void rotate(Cardinal orientation){
		this.orientation = orientation;
	}
	
	/*
	 * Metodo que actualiza la cantidad de balas del barco
	 */
	public void fire(Weapon weaponType){
		
		switch (weaponType.getWeapon()){
			case 0:
				this.torpedo = this.torpedo - 1;
				break;
			case 1:
				this.ammo = this.ammo -1;
				break;			
		}
	}
	
	/*
	 * Metodo que actualiza el daño causado por impacto
	 */
	public void damaged(int damage){
		this.armor = this.armor - damage;
	}
	
	/*
	 * Metodo que se retorna a si mismo como un ShipVO
	 */
	public abstract ShipVO mapToValueObject();
}
