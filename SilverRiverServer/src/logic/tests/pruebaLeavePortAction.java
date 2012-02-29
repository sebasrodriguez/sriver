package logic.tests;

import logic.actions.LeavePortAction;
import logic.entities.Cardinal;
import logic.entities.Coordinate;
import logic.entities.RedShipVO;
import logic.entities.ShipVO;
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
		ShipVO redShipVO = new RedShipVO(3,50,60,70,80,90,100,position,orientation);
		
		LeavePortAction leavePortActionPrueba = new LeavePortAction(redShipVO,port);
		
		System.out.println("Saliendo del puerto: " + leavePortActionPrueba.getPort());
		
		System.out.println("---------------------------------------------------");
		
				
	}

}
