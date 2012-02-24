package tests;

import entities.Cardinal;
import entities.Coordinate;
import logic.ship.RedShip;
import logic.ship.Ship;

public class pruebaFacade {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Coordinate pos = new Coordinate(10,10);
		Cardinal orientation = new Cardinal(-45);
		Ship ship1 = new RedShip(0,10,10,10,10,10,10,pos, orientation);
		Ship cambio = ship1;
		cambio.setId(123);
		
		
		System.out.println("ship1: ");
		System.out.println("Id: "+ ship1.getId());
		System.out.println("Speed: " + ship1.getSpeed());
		System.out.println("Armor: " + ship1.getArmor());
		System.out.println("Ammo: " + ship1.getAmmo());
		System.out.println("Torpedo: " + ship1.getTorpedo());
		System.out.println("ViewRange: " + ship1.getViewRange());
		System.out.println("Size: " + ship1.getSize());
		System.out.println("Position: (" + ship1.getPosition().getX() + "," + ship1.getPosition().getY() + ")");
		System.out.println("Cardinal: " + ship1.getOrientation().toString());		
		System.out.println("---------------------------------------------------------------");
		
		System.out.println("Cambio: ");
		System.out.println("Id: "+ cambio.getId());
		System.out.println("Speed: " + cambio.getSpeed());
		System.out.println("Armor: " + cambio.getArmor());
		System.out.println("Ammo: " + cambio.getAmmo());
		System.out.println("Torpedo: " + cambio.getTorpedo());
		System.out.println("ViewRange: " + cambio.getViewRange());
		System.out.println("Size: " + cambio.getSize());
		System.out.println("Position: (" + cambio.getPosition().getX() + "," + cambio.getPosition().getY() + ")");
		System.out.println("Cardinal: " + cambio.getOrientation().toString());
		
		System.out.println("---------------------------------------------------------------");
		

	}

}
