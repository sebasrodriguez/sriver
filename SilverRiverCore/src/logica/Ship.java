package logica;

import java.awt.Point;

public class Ship {

	
	private int id;
	private int speed;
	private int armor;
	private int ammo;
	private int torpedo;
	private int viewRange;
	private int size;
	private Point position;	
	private Cardinal orientation;	
	private int side;
	
	
	/*
	 * Constructors
	 */
	public Ship(){
		
	}
	
	public Ship(int id, int speed, int armor, int ammo, int torpedo, int viewRange, int size, Point position, Cardinal orientation, int side){
		this.id = id;
		this.speed = speed;
		this.armor = armor;
		this.ammo = ammo;
		this.torpedo = torpedo;
		this.viewRange = viewRange;
		this.size = size;
		this.position = position;
		this.orientation = orientation;
		this.side = side;
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

	public void setPosition(Point position){
		this.position = position;
	}
	
	public void setOrientation(Cardinal orientation){
		this.orientation = orientation;
	}
	
	public void setSide(int side){
		this.side = side;
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
	
	public Point getPosition(){
		return this.position;
	}
	
	public Cardinal getOrientation(){
		return this.orientation;
	}
	
	public int getSide(){
		return this.side;
	}
	
	/*
	 * Actions
	 */
	/*
	 * Como entrada tiene el id del barco y el punto de destino
	 * Entrada: punto de destino
	 * Salida: MoveAction
	 * Procedimiento: 
	 * 	Actualiza la posicion del barco a la nueva posicion del barco
	 * 	Envia a la cola del otro jugador la accion que acaba de realizar para que el otro jugador actualize
	 */
	public MoveAction Move(Point destination){
		
	
	}
	
	/*
	 *Entrada: cardinal de destino
	 *Salida: RotateAction
	 *Procedimiento:
	 *	Actualiza la rotacion del barco al nuevo punto cardinal
	 *	Envia a la cola del otro jugador la accion que acaba de realizar para que el otro jugador actualize
	 *
	 */
	public RotateAction Rotate(Cardinal destination){
		
	}
	
	/*
	 * Entrada: arma usuada y destino a donde hizo click el jugador
	 * Salida FireAction
	 * Procedimiento:
	 * 	Realizar el random para saber donde realmente cae la bala
	 * 	Calcular daño hecho en caso de haberlo
	 * 	Actualizar el daño hecho al otro barco
	 * 	Actualizar la nueva cantidad de balas
	 * 	Enviar a la cola del otro jugador la accion que acaba de realizar para que el otro jugador actualize
	 */
	public FireAction Fire(Weapon gun, Point destination){
		
		
		
	}
}
