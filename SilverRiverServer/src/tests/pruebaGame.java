package tests;

import java.util.ArrayList;
import java.util.Iterator;

import entities.Coordinate;

import logic.actions.Action;
import logic.game.Game;
import logic.game.Turn;
import logic.player.Player;
import logic.ship.BlueShip;
import logic.ship.RedShip;
import logic.ship.Ship;
import entities.Cardinal;

public class pruebaGame {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		Game gamePrueba = null;
		Player redPlayerPrueba = null;
		Player bluePlayerPrueba = null;
		//ArrayList<Action> redActionQueuePrueba = new ArrayList<Action>();
		//ArrayList<Action> blueActionQueuePrueba = new ArrayList<Action>();
		ArrayList<Ship> shipsPrueba = new ArrayList<Ship>();
		Iterator <Action> raqIt = null;
		Iterator <Action> baqIt = null;
		Iterator <Ship> sIt = null;
		//Turn turnPrueba = null;
		Coordinate position = null;
		Cardinal orientation;
		
		
		System.out.println("Creando Jugador 1 con id 0");
		redPlayerPrueba = new Player(0, "Jugador 1");
		System.out.println("Creando Jugador 2 con id 1");
		bluePlayerPrueba = new Player(1,"Jugador 2");
		System.out.println("Creando el turno con el Jugador 1 y 5 movimientos pendientes");
		//turnPrueba = new Turn(redPlayerPrueba,5);
		System.out.println("-------------------------------------------------------");
		
		System.out.println("Creando los 4 barcos y colocandolos en el ArrayList");
		position = new Coordinate(10,10);
		orientation = new Cardinal(90);
		Ship redShipPrueba = new RedShip(0,100,100,100,100,100,3,position,orientation);
		shipsPrueba.add(redShipPrueba);
		
		
		position = new Coordinate(30,30);
		orientation = new Cardinal(180);
		Ship blueShipPrueba1 = new BlueShip(1, 75, 75, 75, 75, 75, 75, position, orientation);
		shipsPrueba.add(blueShipPrueba1);
		
		position = new Coordinate(50,50);
		orientation = new Cardinal(135);
		Ship blueShipPrueba2 = new BlueShip(2,50,50,50,50,50,50,position,orientation);
		shipsPrueba.add(blueShipPrueba2);
		
		position = new Coordinate(90,90);
		orientation = new Cardinal(-135);
		Ship blueShipPrueba3 = new BlueShip(3,25,25,25,25,25,25,position,orientation);
		shipsPrueba.add(blueShipPrueba3);		
		System.out.println("-------------------------------------------------------");		
	
	
		System.out.println("Creando el Game con id 0....");
		
		/*gamePrueba= new Game(0, redPlayerPrueba, bluePlayerPrueba, redActionQueuePrueba,
								blueActionQueuePrueba, shipsPrueba, turnPrueba);
		*/
		
		gamePrueba = new Game(0,redPlayerPrueba, bluePlayerPrueba);
		
		System.out.println("-------------------------------------------------------");
		
		System.out.println("Mostrando el Game como quedo......");
		System.out.println("Id: " + gamePrueba.getId());
		System.out.println("RedPlayer: " + gamePrueba.getRedPlayer().getUsername());
		System.out.println("BluePlayer: " + gamePrueba.getBluePlayer().getUsername());
		System.out.print("RedActionQueue:");
		raqIt = gamePrueba.getRedActionQueue().iterator();
		while(raqIt.hasNext()){
			System.out.print(" " + raqIt.next().toString());
		}
		System.out.println("");
		System.out.print("BlueActionQueue:");
		baqIt = gamePrueba.getBlueActionQueue().iterator();
		while(baqIt.hasNext()){
			System.out.print(" " + baqIt.next().toString());
		}
		System.out.println("");
		System.out.print("Ships:");
		sIt = gamePrueba.getShips().iterator();
		while(sIt.hasNext()){
			System.out.print(" " + sIt.next().getId());
		}
		System.out.println("");
		System.out.println("Turn: " + gamePrueba.getTurn().getActivePlayer().getUsername());	
		System.out.println("-------------------------------------------------------");
		
		/*
		System.out.println("Haciendolo pate probando los set");
		gamePrueba.setId(123);
		gamePrueba.setRedPlayer(bluePlayerPrueba);
		gamePrueba.setBluePlayer(redPlayerPrueba);
		gamePrueba.setRedActionQueue(blueActionQueuePrueba);
		gamePrueba.setBlueActionQueue(redActionQueuePrueba);
		shipsPrueba.removeAll(shipsPrueba);
		gamePrueba.setShips(shipsPrueba);
		gamePrueba.getTurn().setActivePlayer(bluePlayerPrueba);
		gamePrueba.getTurn().setMovesLeft(66);		
		System.out.println("-------------------------------------------------------");
		
		System.out.println("Mostrando el Game como quedo......");
		System.out.println("Id: " + gamePrueba.getId());
		System.out.println("RedPlayer: " + gamePrueba.getRedPlayer().getUsername());
		System.out.println("BluePlayer: " + gamePrueba.getBluePlayer().getUsername());
		System.out.print("RedActionQueue:");
		raqIt = gamePrueba.getRedActionQueue().iterator();
		while(raqIt.hasNext()){
			System.out.print(" " + raqIt.next().toString());
		}
		System.out.println("");
		System.out.print("BlueActionQueue:");
		baqIt = gamePrueba.getBlueActionQueue().iterator();
		while(baqIt.hasNext()){
			System.out.print(" " + baqIt.next().toString());
		}
		System.out.println("");
		System.out.print("Ships:");
		sIt = gamePrueba.getShips().iterator();
		while(sIt.hasNext()){
			System.out.print(" " + sIt.next().getId());
		}
		System.out.println("");
		System.out.println("Turn: " + gamePrueba.getTurn().getActivePlayer().getUsername());	
		System.out.println("-------------------------------------------------------");	
    	*/
		
		
		System.out.println("Probando el metodo Ship getShip(int ShipId) con id 0");
		System.out.println("Ship: " + gamePrueba.getShip(0).toString());
		System.out.println("Probando el metodo Ship getShip(int ShipId) con id 1");
		System.out.println("Ship: " + gamePrueba.getShip(1).toString());
		System.out.println("Probando el metodo Ship getShip(int ShipId) con id 2");
		System.out.println("Ship: " + gamePrueba.getShip(2).toString());
		System.out.println("Probando el metodo Ship getShip(int ShipId) con id 3");
		System.out.println("Ship: " + gamePrueba.getShip(3).toString());
		System.out.println("-------------------------------------------------------");
		
		
		System.out.println("Probando el metodo int getShipFiredId(Coordinate position) con (10,10)");
		position = new Coordinate(10,10);
		System.out.println("ShipFiredId: "+ gamePrueba.getShipFiredId(position));
		System.out.println("Probando el metodo int getShipFiredId(Coordinate position) con (30,30)");
		position = new Coordinate(30,30);
		System.out.println("ShipFiredId: "+ gamePrueba.getShipFiredId(position));
		System.out.println("Probando el metodo int getShipFiredId(Coordinate position) con (50,50)");
		position = new Coordinate(50,50);
		System.out.println("ShipFiredId: "+ gamePrueba.getShipFiredId(position));
		System.out.println("Probando el metodo int getShipFiredId(Coordinate position) con (90,90)");
		position = new Coordinate(90,90);
		System.out.println("ShipFiredId: "+ gamePrueba.getShipFiredId(position));
		System.out.println("Probando el metodo int getShipFiredId(Coordinate position) con (100,100)");
		position = new Coordinate(100,100);
		System.out.println("ShipFiredId: "+ gamePrueba.getShipFiredId(position));
		System.out.println("-------------------------------------------------------");
		
		System.out.println("Hundiendo barcos");
		gamePrueba.destroyedShip(0);
		
		if(gamePrueba.getShip(0) != null){
			System.out.println("Probando el metodo Ship getShip(int ShipId) con id 0");
			System.out.println("Ship: " + gamePrueba.getShip(0).toString());
		}
		if(gamePrueba.getShip(1) != null){
			System.out.println("Probando el metodo Ship getShip(int ShipId) con id 1");
			System.out.println("Ship: " + gamePrueba.getShip(1).toString());
		}
		if(gamePrueba.getShip(2) != null){
			System.out.println("Probando el metodo Ship getShip(int ShipId) con id 2");
			System.out.println("Ship: " + gamePrueba.getShip(2).toString());
		}
		if(gamePrueba.getShip(3) != null){
			System.out.println("Probando el metodo Ship getShip(int ShipId) con id 3");
			System.out.println("Ship: " + gamePrueba.getShip(3).toString());
		}
		System.out.println("-------------------------------------------------------");
		

	}

}
