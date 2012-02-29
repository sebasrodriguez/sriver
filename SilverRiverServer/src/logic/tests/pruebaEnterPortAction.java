package logic.tests;

import entities.Cardinal;
import entities.Coordinate;
import entities.RedShipVO;
import entities.ShipVO;
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
		ShipVO redShipVO = new RedShipVO(3,50,60,70,80,90,100,position,orientation);
		
		EnterPortAction enterPortActionPrueba = new EnterPortAction(redShipVO,port);
		
		System.out.println("Entrando al puerto: " + enterPortActionPrueba.getPort());
		
		System.out.println("---------------------------------------------------");		
		
	}

}
