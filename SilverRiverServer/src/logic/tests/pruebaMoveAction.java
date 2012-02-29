package logic.tests;

import entities.Cardinal;
import entities.Coordinate;
import entities.RedShipVO;
import entities.ShipVO;
import logic.actions.MoveAction;
import logic.ship.RedShip;
import logic.ship.Ship;

public class pruebaMoveAction {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("Se crea el RedShip con posicion (20,70)");
		
		Coordinate position = new Coordinate(20,70);
		Cardinal orientation = new Cardinal(180);
		
		ShipVO redShipVO = new RedShipVO(3,50,60,70,80,90,100,position,orientation);
		MoveAction moveActionPrueba = new MoveAction(redShipVO, position);
		
		System.out.println("---------------------------------------------------");
		
		System.out.println("Coordenate: " + moveActionPrueba.getPosition().getX() + "," + moveActionPrueba.getPosition().getY());moveActionPrueba.getPosition();
		System.out.println("RedShip: ");
		System.out.println("Id: "+ moveActionPrueba.getShip().getId());
		System.out.println("Speed: " + moveActionPrueba.getShip().getSpeed());
		System.out.println("Armor: " + moveActionPrueba.getShip().getArmor());
		System.out.println("Ammo: " + moveActionPrueba.getShip().getAmmo());
		System.out.println("Torpedo: " + moveActionPrueba.getShip().getTorpedo());
		System.out.println("ViewRange: " + moveActionPrueba.getShip().getViewRange());
		System.out.println("Size: " + moveActionPrueba.getShip().getSize());
		System.out.println("Position: (" + moveActionPrueba.getShip().getPosition().getX() + "," + moveActionPrueba.getShip().getPosition().getY() + ")");
		System.out.println("Cardinal: " + moveActionPrueba.getShip().getOrientation().toString());
		
		System.out.println("---------------------------------------------------");
		
		System.out.println("Se mueve el barco a la posicion (50,90)");
		position.setX(50);
		position.setY(90);
		redShipVO = new RedShipVO(redShipVO.getId(),redShipVO.getSpeed(),redShipVO.getArmor(),redShipVO.getAmmo(),redShipVO.getTorpedo(),redShipVO.getViewRange(),redShipVO.getSize(),position,redShipVO.getOrientation());
		moveActionPrueba = new MoveAction(redShipVO, position);		
		System.out.println("Coordenate: " + moveActionPrueba.getPosition().getX() + "," + moveActionPrueba.getPosition().getY());moveActionPrueba.getPosition();
		System.out.println("RedShip: ");
		System.out.println("Id: "+ moveActionPrueba.getShip().getId());
		System.out.println("Speed: " + moveActionPrueba.getShip().getSpeed());
		System.out.println("Armor: " + moveActionPrueba.getShip().getArmor());
		System.out.println("Ammo: " + moveActionPrueba.getShip().getAmmo());
		System.out.println("Torpedo: " + moveActionPrueba.getShip().getTorpedo());
		System.out.println("ViewRange: " + moveActionPrueba.getShip().getViewRange());
		System.out.println("Size: " + moveActionPrueba.getShip().getSize());
		System.out.println("Position: (" + moveActionPrueba.getShip().getPosition().getX() + "," + moveActionPrueba.getShip().getPosition().getY() + ")");
		System.out.println("Cardinal: " + moveActionPrueba.getShip().getOrientation().toString());
			

	}

}
