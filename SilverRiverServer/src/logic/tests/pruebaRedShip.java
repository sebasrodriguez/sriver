package logic.tests;

import entities.Cardinal;
import entities.Coordinate;
import logic.ship.RedShip;
import logic.ship.Ship;

public class pruebaRedShip {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Coordinate position = new Coordinate(20,70);
		Cardinal orientation = new Cardinal(180);
		
		Ship redShipPrueba = new RedShip(3,50,60,70,80,90,100,position,orientation);
		
		
		System.out.println("Creando el RedShip con los valores: 3,50,60,70,80,90,100,(20,70), Cardinal.S");
		System.out.println("RedShip: ");
		System.out.println("Id: "+ redShipPrueba.getId());
		System.out.println("Speed: " + redShipPrueba.getSpeed());
		System.out.println("Armor: " + redShipPrueba.getArmor());
		System.out.println("Ammo: " + redShipPrueba.getAmmo());
		System.out.println("Torpedo: " + redShipPrueba.getTorpedo());
		System.out.println("ViewRange: " + redShipPrueba.getViewRange());
		System.out.println("Size: " + redShipPrueba.getSize());
		System.out.println("Position: (" + redShipPrueba.getPosition().getX() + "," + redShipPrueba.getPosition().getY() + ")");
		System.out.println("Cardinal: " + redShipPrueba.getOrientation().toString());
		
		System.out.println("---------------------------------------------------------------");
		
		
		System.out.println("Cambiando los valores a: 10,30,50,60,70,80,90, (10,1), NW");
		redShipPrueba.setId(10);
		redShipPrueba.setSpeed(30);
		redShipPrueba.setArmor(50);
		redShipPrueba.setAmmo(60);
		redShipPrueba.setTorpedo(70);
		redShipPrueba.setViewRange(80);
		redShipPrueba.setSize(90);
		position.setX(10);
		position.setY(1);
		redShipPrueba.setPosition(position);
		orientation = new Cardinal(-45);
		redShipPrueba.setOrientation(orientation);
		
		System.out.println("RedShip: ");
		System.out.println("Id: "+ redShipPrueba.getId());
		System.out.println("Speed: " + redShipPrueba.getSpeed());
		System.out.println("Armor: " + redShipPrueba.getArmor());
		System.out.println("Ammo: " + redShipPrueba.getAmmo());
		System.out.println("Torpedo: " + redShipPrueba.getTorpedo());
		System.out.println("ViewRange: " + redShipPrueba.getViewRange());
		System.out.println("Size: " + redShipPrueba.getSize());
		System.out.println("Position: (" + redShipPrueba.getPosition().getX() + "," + redShipPrueba.getPosition().getY() + ")");
		System.out.println("Cardinal: " + redShipPrueba.getOrientation().toString());
		
		System.out.println("---------------------------------------------------------------");
		
		
	}

}
