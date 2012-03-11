package logic.ship;

import java.util.ArrayList;

import entities.Cardinal;
import entities.Coordinate;
import entities.RedShipVO;


/*
 * Esta clase queda vacia para implementar los opcionales
 * El mantener esta clase aunque este vacia nos sirve para saber de que bando es
 */
public class RedShip extends Ship{
	
	
	/*
	 * Constructores
	 */
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RedShip(){
		
	}
	
	public RedShip(int id, int speed, int armor, int ammo, int torpedo, int viewRange, int size, Coordinate position, Cardinal orientation){
		super(id, speed, armor, ammo, torpedo, viewRange, size, position, orientation);
	}	
	
	/*
	 * Devuelve al barco rojo como ValueObject
	 */
	public RedShipVO mapToValueObject(){		
		RedShipVO redShipVOToReturn = new RedShipVO(this.getId(), this.getSpeed(), this.getArmor(), this.getAmmo(), this.getTorpedo(), this.getViewRange(), this.getSize(), this.getPosition(),this.getOrientation());
		return redShipVOToReturn;
	}
	
	/*
	 * Metodo que coloca en un array las posiciones que ocupa el barco rojo
	 */
	public ArrayList<Coordinate> calculateRedShipSize(){
		
		int x = this.getPosition().getX();
		int y = this.getPosition().getY();
		ArrayList<Coordinate> sizeToReturn = new ArrayList<Coordinate>();
		Coordinate positionToAdd = new Coordinate(this.getPosition().getX(),this.getPosition().getY());
		
		//agrego la celda actual
		sizeToReturn.add(positionToAdd);
		
		//agrego la proa
		switch(this.getOrientation().getDirection()){		
			//Norte
			case Cardinal.N:
				//el x queda fijo, y - 1
				y--;				
			break;
			//Este
			case Cardinal.E:
				//el y queda fijo, x + 1
				x++;
			break;
			//Sur
			case Cardinal.S:
				//el x queda fijo, y + 1
				y++;
			break;
			//Oeste
			case Cardinal.W:
				//el y queda fijo, x - 1
				x--;
			break;
			//Noreste
			case Cardinal.NE:
				//x + 1, y - 1
				y--;
				x++;
			break;
			//Noroeste
			case Cardinal.NW:
				//x - 1 y - 1			
				y--;
				x--;
			break;
			//Sureste
			case Cardinal.SE:
				//x +1 y +1
				y++;
				x++;
			break;
			//Suroeste
			case Cardinal.SW:
				//x - 1 y +1			
				y++;
				x--;
			break;		
		}
		
		positionToAdd = new Coordinate(x,y);
		sizeToReturn.add(positionToAdd);
		
		//inicializo nuevamente la "x" e "y"
		x = this.getPosition().getX();
		y = this.getPosition().getY();
		
		//agrego la popa
		switch(this.getOrientation().getDirection()){		
			//Norte
			case Cardinal.N:
				//el x queda fijo, y + 1
				y++;				
			break;
			//Este
			case Cardinal.E:
				//el y queda fijo, x - 1
				x--;
			break;
			//Sur
			case Cardinal.S:
				//el x queda fijo, y - 1
				y--;
			break;
			//Oeste
			case Cardinal.W:
				//el y queda fijo, x + 1
				x++;
			break;
			//Noreste
			case Cardinal.NE:
				//x - 1, y + 1
				y++;
				x--;
			break;
			//Noroeste
			case Cardinal.NW:
				//x + 1 y + 1			
				y++;
				x++;
			break;
			//Sureste
			case Cardinal.SE:
				//x -1 y -1
				y--;
				x--;
			break;
			//Suroeste
			case Cardinal.SW:
				//x + 1 y -1			
				y--;
				x++;
			break;		
		}
		positionToAdd = new Coordinate(x,y);
		sizeToReturn.add(positionToAdd);
		
		return sizeToReturn;
	}
	
}
