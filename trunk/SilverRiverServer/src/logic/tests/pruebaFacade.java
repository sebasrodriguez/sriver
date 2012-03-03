package logic.tests;

import java.util.Iterator;

import entities.Cardinal;
import entities.Coordinate;
import entities.Weapon;

import logic.Facade;
import logic.actions.FireAction;
import logic.actions.MoveAction;
import logic.actions.RotateAction;
import logic.game.Game;
import logic.ship.Ship;

public class pruebaFacade { 
	
	
	private static Facade facadePrueba;

	private static void spooleando(){
		int i = 0;
		System.out.print("Chequeando segundo jugador:");
		while(i < 3){
			System.out.print(" " + facadePrueba.checkGameId());
			i++;
		}	
		System.out.println();
	}
	
	private static void printShips(int gameId){
		Game gm = null;		
		
		Iterator<Game> gmIt = facadePrueba.devolverITerator();
		while(gmIt.hasNext()){
			Game aux = gmIt.next();
			if(aux.getId() == gameId){
				gm = aux.clone();
			}
		}
		
		Iterator<Ship> shIt = gm.getShips().iterator();
		while(shIt.hasNext()){
			Ship aux = shIt.next();
			System.out.println("Ships: " + aux.getId() + " " + aux.getSpeed() + " " + aux.getArmor() + 
					" " + aux.getAmmo() + " " + aux.getTorpedo() + " " + aux.getViewRange() + " " + 
					aux.getSize() + " (" + aux.getPosition().getX() + "," + aux.getPosition().getY() + ")" +
					aux.getOrientation().getDirection());		
			
		}				
	}
	
	private static void printTurn(int gameId){
	Game gm = null;		
		
		Iterator<Game> gmIt = facadePrueba.devolverITerator();
		while(gmIt.hasNext()){
			Game aux = gmIt.next();
			if(aux.getId() == gameId){
				gm = aux.clone();
			}
		}		
		System.out.println("Turn: " + gm.getTurn().getActivePlayer().getUsername() + ", " + gm.getTurn().getMovesLeft());
	}
	
	private static void printMoveAction(MoveAction mvAc){
		System.out.println("MoveAction: " + mvAc.getShip().getId() + " (" + mvAc.getPosition().getX() + "," + mvAc.getPosition().getY() + ")");
	}
	
	
	private static void printRotateAction(RotateAction rtAc){
		System.out.println("RotateAction: " + rtAc.getShip().getId() + " " + rtAc.getCardinal().getDirection());
	}
	
	
	private static void printFireAction(FireAction frAc){
		System.out.print("FireAction: " + frAc.getShip().getId() + " (" + frAc.getHitCoordinate().getX() + "," +
				frAc.getHitCoordinate().getY() +") " + frAc.getHit() + 	" " + frAc.getWeaponType().getWeapon());
		if(frAc.getAffectedShip() != null){
			System.out.println(" " + frAc.getAffectedShip().getId());
		}else{
			System.out.println(" erraste el tiro");
		}
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		facadePrueba = Facade.getInstance();	
		Coordinate position = null;
		Cardinal orientation = null;
		Weapon weaponType = null;
		MoveAction moveAction = null;
		RotateAction rotateAction = null;
		FireAction fireAction = null;
		
		
		//Partida 0
		System.out.println("Cargando al jugador 1 en el juego");
		facadePrueba.newGame("Jugador 1");
		spooleando();
		if(facadePrueba.checkGameId() == -1){
			System.out.println("Esperando al segundo jugador....");
		}else{
			System.out.println("Ya encontre mi segundo jugador, id de la partida: " + facadePrueba.checkGameId());
		}
		System.out.println("Cargando al jugador 2 en el juego");
		facadePrueba.newGame("Jugador 2");
		spooleando();
		if(facadePrueba.checkGameId() == -1){
			System.out.println("Esperando al segundo jugador....");
		}else{
			System.out.println("Ya encontre mi segundo jugador, id de la partida: " + facadePrueba.checkGameId());
		}
		System.out.println("-------------------------------------------------------------");
		
		//Partida 1
		System.out.println("Cargando al jugador 3 en el juego");
		facadePrueba.newGame("Jugador 3");
		spooleando();
		if(facadePrueba.checkGameId() == -1){
			System.out.println("Esperando al segundo jugador....");
		}else{
			System.out.println("Ya encontre mi segundo jugador, id de la partida: " + facadePrueba.checkGameId());
		}
		System.out.println("Cargando al jugador 4 en el juego");
		facadePrueba.newGame("Jugador 4");
		spooleando();
		if(facadePrueba.checkGameId() == -1){
			System.out.println("Esperando al segundo jugador....");
		}else{
			System.out.println("Ya encontre mi segundo jugador, id de la partida: " + facadePrueba.checkGameId());
		}
		System.out.println("-------------------------------------------------------------");
		
		System.out.println("Turnos partida 0");
		printTurn(0);
		System.out.println("Turno partida 1");
		printTurn(1);
		System.out.println("-------------------------------------------------------------");
		
		//Partida 0
		System.out.println("---- PARTIDA 0 ----");
		
		System.out.println("Moviendo el barco rojo de la paritda 0 al (12,12)");
		position = new Coordinate(12,12);
		moveAction = facadePrueba.move(0, 0, position);
		printShips(0);
		printTurn(0);
		printMoveAction(moveAction);
		System.out.println("--------------------------------------------");	
		
		System.out.println("Rotando el barco rojo de la paritda 0 al -135");
		orientation = new Cardinal(-135);
		rotateAction = facadePrueba.rotate(0, 0, orientation);
		printShips(0);
		printTurn(0);
		printRotateAction(rotateAction);
		System.out.println("--------------------------------------------");	
		
		System.out.println("Disparando desde el barco con id 0 al punto (25,25)");
		weaponType = new Weapon(1);
		position = new Coordinate(25,25);
		fireAction = facadePrueba.fire(0, 0, position, weaponType);
		printShips(0);
		printTurn(0);
		printFireAction(fireAction);
		System.out.println("--------------------------------------------");	
		
		System.out.println("Jugador 1 termina su turno");
		facadePrueba.endTurn(0);
		printShips(0);
		printTurn(0);
		System.out.println("--------------------------------------------");
		
		
		System.out.println("Jugador 2 rota el barco con id 2 a 135");
		orientation = new Cardinal(135);
		rotateAction = facadePrueba.rotate(0, 2, orientation);
		printShips(0);
		printTurn(0);
		printRotateAction(rotateAction);
		System.out.println("--------------------------------------------");
		
		System.out.println("Disparando desde el barco con id 3 al punto (12,12)");
		weaponType = new Weapon(0);
		position = new Coordinate(12,12);
		fireAction = facadePrueba.fire(0, 3, position, weaponType);
		printShips(0);
		printTurn(0);
		printFireAction(fireAction);
		System.out.println("--------------------------------------------");	
		
		System.out.println("moviendo el barco con id 1 al (12,10)");
		position = new Coordinate(12,10);
		moveAction = facadePrueba.move(0, 1, position);
		printShips(0);
		printTurn(0);
		printMoveAction(moveAction);
		System.out.println("--------------------------------------------");
		
		System.out.println("moviendo el barco con id 3 al (12,14)");
		position = new Coordinate(12,14);
		moveAction = facadePrueba.move(0, 3, position);
		printShips(0);
		printTurn(0);
		printMoveAction(moveAction);
		System.out.println("--------------------------------------------");
		
		System.out.println("Disparando desde el barco con id 3 al punto (12,12)");
		weaponType = new Weapon(1);
		position = new Coordinate(12,12);
		fireAction = facadePrueba.fire(0, 3, position, weaponType);
		printShips(0);
		printTurn(0);
		printFireAction(fireAction);
		System.out.println("--------------------------------------------");	
		
		
		//Partida 1
		System.out.println("---- PARTIDA 1 ----");
		
		System.out.println("moviendo el barco con id 0 al (12,10)");
		position = new Coordinate(12,10);
		moveAction = facadePrueba.move(1, 0, position);
		printShips(1);
		printTurn(1);
		printMoveAction(moveAction);
		System.out.println("--------------------------------------------");
		
		System.out.println("Jugador 3 rota el barco con id 0 a 135");
		orientation = new Cardinal(135);
		rotateAction = facadePrueba.rotate(1, 0, orientation);
		printShips(1);
		printTurn(1);
		printRotateAction(rotateAction);
		System.out.println("--------------------------------------------");
		
		System.out.println("Disparando desde el barco con id 0 al punto (20,20)");
		weaponType = new Weapon(1);
		position = new Coordinate(20,20);
		fireAction = facadePrueba.fire(1, 0, position, weaponType);
		printShips(1);
		printTurn(1);
		printFireAction(fireAction);
		System.out.println("--------------------------------------------");	
		
		System.out.println("Jugador 3 termina su turno");
		facadePrueba.endTurn(1);
		printShips(1);
		printTurn(1);
		System.out.println("--------------------------------------------");
			
		System.out.println("Jugador 4 rota el barco con id 2 a 90");
		orientation = new Cardinal(90);
		rotateAction = facadePrueba.rotate(1, 2, orientation);
		printShips(1);
		printTurn(1);
		printRotateAction(rotateAction);
		System.out.println("--------------------------------------------");
		
		System.out.println("Disparando desde el barco con id 3 al punto (12,10)");
		weaponType = new Weapon(0);
		position = new Coordinate(12,10);
		fireAction = facadePrueba.fire(1,3, position, weaponType);
		printShips(1);
		printTurn(1);
		printFireAction(fireAction);
		System.out.println("--------------------------------------------");	
		
		System.out.println("moviendo el barco con id 2 al (12,12)");
		position = new Coordinate(12,12);
		moveAction = facadePrueba.move(1, 2, position);
		printShips(1);
		printTurn(1);
		printMoveAction(moveAction);
		System.out.println("--------------------------------------------");
		
		System.out.println("moviendo el barco con id 1 al (12,8)");
		position = new Coordinate(12,8);
		moveAction = facadePrueba.move(1,1, position);
		printShips(1);
		printTurn(1);
		printMoveAction(moveAction);
		System.out.println("--------------------------------------------");
		
		System.out.println("Disparando desde el barco con id 1 al punto (12,10)");
		weaponType = new Weapon(0);
		position = new Coordinate(12,10);
		fireAction = facadePrueba.fire(1,1, position, weaponType);
		printShips(1);
		printTurn(1);
		printFireAction(fireAction);
		System.out.println("--------------------------------------------");	
		
		System.out.println("Eliminando el Game con Id 0");
		facadePrueba.endGame(0);
		Iterator<Game> gameIt = facadePrueba.devolverITerator();
		int i = 0;
		while(gameIt.hasNext()){
			i++;
			gameIt.next();
		}
		System.out.println("ActiveGames size: " + i);
		System.out.println("-------------------------------------------------------------");
		
		//Partida 1
		System.out.println("Cargando al jugador 5 en el juego");
		facadePrueba.newGame("Jugador 5");
		spooleando();
		if(facadePrueba.checkGameId() == -1){
			System.out.println("Esperando al segundo jugador....");
		}else{
			System.out.println("Ya encontre mi segundo jugador, id de la partida: " + facadePrueba.checkGameId());
		}
		System.out.println("Cargando al jugador 6 en el juego");
		facadePrueba.newGame("Jugador 6");
		spooleando();
		if(facadePrueba.checkGameId() == -1){
			System.out.println("Esperando al segundo jugador....");
		}else{
			System.out.println("Ya encontre mi segundo jugador, id de la partida: " + facadePrueba.checkGameId());
		}
		System.out.println("-------------------------------------------------------------");
		
		System.out.println("Guardando la partida con id 1");
		facadePrueba.saveGame(1);
		System.out.println("Se guardo....");
		System.out.println("-------------------------------------------------------------");
		
	}

}
