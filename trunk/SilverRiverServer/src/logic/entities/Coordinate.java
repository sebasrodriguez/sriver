package logic.entities;

/*
 * Contiene las coordenadas (ubicacion en el mapa) del barco
 */
public class Coordinate {

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
	 * Getters
	 */
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	/*
	 * Setters
	 */
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
}
