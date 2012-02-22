package tests;

import entities.Coordinate;
import entities.Cardinal;
import logic.actions.EnterPortAction;
import logic.ship.RedShip;
import logic.ship.Ship;

public class pruebaEnterPortAction {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int port = 1;
		
		Coordinate position = new Coordinate(20,970);
		Cardinal orientation = new Cardinal (180);
		Ship redShip = new RedShip(3,50,60,70,80,90,100,position,orientation);
		
		EnterPortAction enterPortActionPrueba = new EnterPortAction(redShip,port);
		
		System.out.println("Entrando al puerto: " + enterPortActionPrueba.getPort());
		
		System.out.println("---------------------------------------------------");
		
		System.out.println("Cambiando de puerto al 32");
		enterPortActionPrueba.setPort(32);
		System.out.println("---------------------------------------------------");
		System.out.println("Entrando al puerto: " + enterPortActionPrueba.getPort());
		
	}

}
