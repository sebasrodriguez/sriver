package tests;

import entities.Cardinal;
import entities.Coordinate;
import logic.BlueShip;
import logic.Ship;

public class pruebaBlueShip {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Coordinate position = new Coordinate(20,70);
		Cardinal orientation = new Cardinal(180);
		
		Ship blueShipPrueba = new BlueShip(3,50,60,70,80,90,100,position,orientation);
		
		
		System.out.println("Creando el BlueShip con los valores: 3,50,60,70,80,90,100,(20,70), Cardinal.S");
		System.out.println("BlueShip: ");
		System.out.println("Id: "+ blueShipPrueba.getId());
		System.out.println("Speed: " + blueShipPrueba.getSpeed());
		System.out.println("Armor: " + blueShipPrueba.getArmor());
		System.out.println("Ammo: " + blueShipPrueba.getAmmo());
		System.out.println("Torpedo: " + blueShipPrueba.getTorpedo());
		System.out.println("ViewRange: " + blueShipPrueba.getViewRange());
		System.out.println("Size: " + blueShipPrueba.getSize());
		System.out.println("Position: (" + blueShipPrueba.getPosition().getX() + "," + blueShipPrueba.getPosition().getY() + ")");
		System.out.println("Cardinal: " + blueShipPrueba.getOrientation().toString());
		
		System.out.println("---------------------------------------------------------------");
		
		
		System.out.println("Cambiando los valores a: 10,30,50,60,70,80,90, (10,1), NW");
		blueShipPrueba.setId(10);
		blueShipPrueba.setSpeed(30);
		blueShipPrueba.setArmor(50);
		blueShipPrueba.setAmmo(60);
		blueShipPrueba.setTorpedo(70);
		blueShipPrueba.setViewRange(80);
		blueShipPrueba.setSize(90);
		position.setX(10);
		position.setY(1);
		blueShipPrueba.setPosition(position);
		orientation = new Cardinal(-45);
		blueShipPrueba.setOrientation(orientation);
		
		System.out.println("BlueShip: ");
		System.out.println("Id: "+ blueShipPrueba.getId());
		System.out.println("Speed: " + blueShipPrueba.getSpeed());
		System.out.println("Armor: " + blueShipPrueba.getArmor());
		System.out.println("Ammo: " + blueShipPrueba.getAmmo());
		System.out.println("Torpedo: " + blueShipPrueba.getTorpedo());
		System.out.println("ViewRange: " + blueShipPrueba.getViewRange());
		System.out.println("Size: " + blueShipPrueba.getSize());
		System.out.println("Position: (" + blueShipPrueba.getPosition().getX() + "," + blueShipPrueba.getPosition().getY() + ")");
		System.out.println("Cardinal: " + blueShipPrueba.getOrientation().toString());
		
		System.out.println("---------------------------------------------------------------");
		
		
		
	}

}
