package entities;

import java.io.Serializable;

/*
 * Contiene las coordenadas (ubicacion en el mapa) del barco
 */
public class Coordinate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int x;
	private int y;
	
	
	/*
	 * Constructores
	 */
	public Coordinate(){
		
	}
	public Coordinate(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	/*
	 * Devuelve la coodenada X
	 */
	public int getX(){
		return this.x;
	}
	
	/*
	 * Devulve la coordenada Y
	 */
	public int getY(){
		return this.y;
	}
	
	/*
	 * Setea la coordenada X
	 */
	public void setX(int x){
		this.x = x;
	}
	
	/*
	 * Setea la coordenada Y
	 */
	public void setY(int y){
		this.y = y;
	}
}
