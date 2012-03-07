package logic.tests;

import java.util.Iterator;

import entities.GameVO;

import logic.Facade;
import logic.game.Game;

public class pruebaFacadeLoad {

	
	private static Facade pruebaFacade;
	
	private static void printArray(){
		Iterator<String> itString = pruebaFacade.devolverItString();
		System.out.print("Array: ");
		while (itString.hasNext()){
			System.out.print(itString.next() + ", ");
		}
		System.out.println("");
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		pruebaFacade = Facade.getInstance();		
		GameVO aux = null;
		
		System.out.println("Probando con un usuario que no tiene partidas guardadas");
		System.out.println(pruebaFacade.loadGame("jugador"));
		printArray();
		System.out.println("------------------------------------------");
		System.out.println("Probando con Jugador Rojo 1");
		System.out.println(pruebaFacade.loadGame("Jugador Rojo 1"));
		printArray();
		System.out.println("spooleando Jugador Rojo 1: " + pruebaFacade.getGameIdLoading("Jugador Rojo 1"));
		if(pruebaFacade.getGameIdLoading("Jugador Rojo 1") != -1){
			aux = pruebaFacade.getGame(pruebaFacade.getGameIdLoading("Jugador Rojo 1"));
			System.out.println("game: " + aux.getId() + " redplayer: " + aux.getRedPlayer().getUsername() + "bluePlayer: " + aux.getBluePlayer().getUsername());			
		}
		System.out.println("------------------------------------------");
		System.out.println("Probando con Jugador Rojo 2");
		System.out.println(pruebaFacade.loadGame("Jugador Rojo 2"));
		printArray();
		System.out.println("spooleando Jugador Rojo 2: " + pruebaFacade.getGameIdLoading("Jugador Rojo 2"));
		if(pruebaFacade.getGameIdLoading("Jugador Rojo 1") != -1){
			aux = pruebaFacade.getGame(pruebaFacade.getGameIdLoading("Jugador Rojo 1"));
			System.out.println("game: " + aux.getId() + " redplayer: " + aux.getRedPlayer().getUsername() + "bluePlayer: " + aux.getBluePlayer().getUsername());			
		}
		System.out.println("------------------------------------------");
		System.out.println("Probando con Jugador Azul 2");
		System.out.println(pruebaFacade.loadGame("Jugador Azul 2"));
		printArray();
		System.out.println("spooleando Jugador Rojo 1: " + pruebaFacade.getGameIdLoading("Jugador Rojo 1"));
		if(pruebaFacade.getGameIdLoading("Jugador Rojo 1") != -1){
			aux = pruebaFacade.getGame(pruebaFacade.getGameIdLoading("Jugador Rojo 1"));
			System.out.println("game: " + aux.getId() + " redplayer: " + aux.getRedPlayer().getUsername() + "bluePlayer: " + aux.getBluePlayer().getUsername());			
		}
		System.out.println("------------------------------------------");
		System.out.println("Probando con Jugador Azul 1");
		System.out.println(pruebaFacade.loadGame("Jugador Azul 1"));
		printArray();
		System.out.println("spooleando Jugador Azul 1: " + pruebaFacade.getGameIdLoading("Jugador Azul 1"));
		if(pruebaFacade.getGameIdLoading("Jugador Rojo 1") != -1){
			aux = pruebaFacade.getGame(pruebaFacade.getGameIdLoading("Jugador Rojo 1"));
			System.out.println("game: " + aux.getId() + " redplayer: " + aux.getRedPlayer().getUsername()+ "bluePlayer: " + aux.getBluePlayer().getUsername());			
		}
		System.out.println("------------------------------------------");
	}
	
	
		
	
	
}


