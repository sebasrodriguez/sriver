package tests;

import entities.Coordinate;
import entities.Cardinal;
import logic.actions.LeavePortAction;
import logic.ship.RedShip;
import logic.ship.Ship;

public class pruebaLeavePortAction {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int port = 1;
		
		Coordinate position = new Coordinate(20,70);
		Cardinal orientation = new Cardinal(180);
		Ship redShip = new RedShip(3,50,60,70,80,90,100,position,orientation);
		
		LeavePortAction leavePortActionPrueba = new LeavePortAction(redShip,port);
		
		System.out.println("Saliendo del puerto: " + leavePortActionPrueba.getPort());
		
		System.out.println("---------------------------------------------------");
		
		System.out.println("Cambiando de puerto al 32");
		leavePortActionPrueba.setPort(32);
		System.out.println("---------------------------------------------------");
		System.out.println("Saliendo del puerto: " + leavePortActionPrueba.getPort());
		
		
		
		
	}

}
