package entities;


public abstract class ShipVO {
	
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
	 *Constructor
	 */
	public ShipVO(int id, int speed, int armor, int ammo, int torpedo, int viewRange, int size, Coordinate position, Cardinal orientation){
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
	 * Devuele el id del barco
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
	 * Devuelve el blindaje del barco
	 */
	public int getArmor(){
		return this.armor;
	}
	
	/*
	 * Devuelve la cantidad de balas de la metralleta
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
	 * Devuelve la coordenada del barco
	 */
	public Coordinate getPosition(){
		return this.position;
	}
	
	/*
	 * Devuelve la orientacion del barco
	 */
	public Cardinal getOrientation(){
		return this.orientation;
	}

}
