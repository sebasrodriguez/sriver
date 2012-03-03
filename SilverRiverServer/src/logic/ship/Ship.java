package logic.ship;

import java.io.Serializable;

import entities.Cardinal;
import entities.Coordinate;
import entities.ShipVO;
import entities.Weapon;

public abstract class Ship  implements Serializable{

	
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
	 * Setea el id del barco
	 */
	public void setId(int id){
		this.id = id;
	}
	
	/*
	 * Setea la velocidad del barco
	 */
	public void setSpeed(int speed){
		this.speed = speed;		
	}
	
	/*
	 * Setea el blindaje del barco
	 */
	public void setArmor(int armor){
		this.armor = armor;
	}
	
	/*
	 * Setea la cantidad de balas de la metralleta
	 */
	public void setAmmo(int ammo){
		this.ammo = ammo;
	}
	
	/*
	 * Setea la cantidad de torpedos
	 */
	public void setTorpedo(int torpedo){
		this.torpedo = torpedo;
	}
	
	/*
	 * Setea el rango de visibilidad
	 */
	public void setViewRange(int viewRange){
		this.viewRange = viewRange;
	}
	
	/*
	 * Setea el tamaño
	 */
	public void setSize(int size){
		this.size = size;
	}

	/*
	 *Setea la posicion del barco
	 */
	public void setPosition(Coordinate position){
		this.position = position;
	}
	
	/*
	 * Setea la orientacion del barco
	 */
	public void setOrientation(Cardinal orientation){
		this.orientation = orientation;
	}
	
	

	/*
	 * Devuelve el id del barco
	 */
	public int getId(){
		return this.id;		
	}
	
	/*
	 * Devuelve la velocidad del barco
	 */
	public int getSpeed(){
		return this.speed;
	}
	
	/*
	 * Devuelve la cantidad de blindaje
	 */
	public int getArmor(){
		return this.armor;
	}
	
	/*
	 * Devuelve la cantidad de balas
	 */
	public int getAmmo(){
		return this.ammo;
	}
	
	/*
	 * Devuelve la cantidad de torpedos
	 */
	public int getTorpedo(){
		return this.torpedo;
	}
	
	/*
	 * Devuelve el rango de visibilidad
	 */
	public int getViewRange(){
		return this.viewRange;
	}
	
	/*
	 * Devuelve el tamaño del barco
	 */
	public int getSize(){
		return this.size;
	}
	
	/*
	 * Devuelve la posicion del barco
	 */
	public Coordinate getPosition(){
		return this.position;
	}
	
	/*
	 * Devuelve la orientacion
	 */
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
	 * Metodo abstracto que se retorna a si mismo como un ShipVO
	 */
	public abstract ShipVO mapToValueObject();
}
