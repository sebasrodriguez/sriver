package tests;

import entities.Coordinate;
import entities.Cardinal;
import entities.RedShipVO;
import entities.ShipVO;
import logic.actions.RotateAction;
import logic.ship.RedShip;
import logic.ship.Ship;

public class pruebaRotateAction {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("Se crea el RedShip con coordenada SW");
		
		Coordinate position = new Coordinate(20,70);
		//Cardinal orientation = Cardinal.SW;
		
		Cardinal orientation = new Cardinal(-135);
		
		ShipVO redShipVO = new RedShipVO(3,50,60,70,80,90,100,position,orientation);
		RotateAction rotateActionPrueba = new RotateAction(redShipVO, orientation);
		
		System.out.println("---------------------------------------------------");
		
		System.out.println("Cardinal: " + rotateActionPrueba.getCardinal().toString());
		System.out.println("RedShip: ");
		System.out.println("Id: "+ rotateActionPrueba.getShip().getId());
		System.out.println("Speed: " + rotateActionPrueba.getShip().getSpeed());
		System.out.println("Armor: " + rotateActionPrueba.getShip().getArmor());
		System.out.println("Ammo: " + rotateActionPrueba.getShip().getAmmo());
		System.out.println("Torpedo: " + rotateActionPrueba.getShip().getTorpedo());
		System.out.println("ViewRange: " + rotateActionPrueba.getShip().getViewRange());
		System.out.println("Size: " + rotateActionPrueba.getShip().getSize());
		System.out.println("Position: (" + rotateActionPrueba.getShip().getPosition().getX() + "," + rotateActionPrueba.getShip().getPosition().getY() + ")");
		System.out.println("Cardinal: " + rotateActionPrueba.getShip().getOrientation().toString());
		
		System.out.println("---------------------------------------------------");
		
		
		System.out.println("Se rota el barco a la posicion NE");
		orientation = new Cardinal(45);
		redShipVO = new RedShipVO(redShipVO.getId(),redShipVO.getSpeed(),redShipVO.getArmor(),redShipVO.getAmmo(),redShipVO.getTorpedo(),redShipVO.getViewRange(),redShipVO.getSize(),redShipVO.getPosition(),orientation);
		rotateActionPrueba = new RotateAction(redShipVO, orientation);		
		System.out.println("Cardinal: " + rotateActionPrueba.getCardinal().toString());
		System.out.println("RedShip: ");
		System.out.println("Id: "+ rotateActionPrueba.getShip().getId());
		System.out.println("Speed: " + rotateActionPrueba.getShip().getSpeed());
		System.out.println("Armor: " + rotateActionPrueba.getShip().getArmor());
		System.out.println("Ammo: " + rotateActionPrueba.getShip().getAmmo());
		System.out.println("Torpedo: " + rotateActionPrueba.getShip().getTorpedo());
		System.out.println("ViewRange: " + rotateActionPrueba.getShip().getViewRange());
		System.out.println("Size: " + rotateActionPrueba.getShip().getSize());
		System.out.println("Position: (" + rotateActionPrueba.getShip().getPosition().getX() + "," + rotateActionPrueba.getShip().getPosition().getY() + ")");
		System.out.println("Cardinal: " + rotateActionPrueba.getShip().getOrientation().toString());

	}

}
