package logic.tests;

import java.util.Iterator;

import entities.GameVO;

import logic.Facade;

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
		GameVO aux1 = null;
		GameVO aux2 = null;
		GameVO aux3 = null;
		
		
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
			aux1 = pruebaFacade.getGame(pruebaFacade.getGameIdLoading("Jugador Rojo 1"));
			System.out.println("game: " + aux1.getId() + " redplayer: " + aux1.getRedPlayer().getUsername() + "bluePlayer: " + aux1.getBluePlayer().getUsername());			
		}
		System.out.println("------------------------------------------");
		System.out.println("Probando con Jugador Azul 2");
		System.out.println(pruebaFacade.loadGame("Jugador Azul 2"));
		printArray();
		System.out.println("spooleando Jugador Rojo 1: " + pruebaFacade.getGameIdLoading("Jugador Rojo 1"));
		if(pruebaFacade.getGameIdLoading("Jugador Rojo 1") != -1){
			aux2 = pruebaFacade.getGame(pruebaFacade.getGameIdLoading("Jugador Rojo 1"));
			System.out.println("game: " + aux2.getId() + " redplayer: " + aux2.getRedPlayer().getUsername() + "bluePlayer: " + aux2.getBluePlayer().getUsername());			
		}
		System.out.println("------------------------------------------");
		System.out.println("Probando con Jugador Azul 1");
		System.out.println(pruebaFacade.loadGame("Jugador Azul 1"));
		printArray();
		System.out.println("spooleando Jugador Rojo 2: " + pruebaFacade.getGameIdLoading("Jugador Rojo 2"));
		if(pruebaFacade.getGameIdLoading("Jugador Rojo 2") != -1){
			aux3 = pruebaFacade.getGame(pruebaFacade.getGameIdLoading("Jugador Rojo 2"));
			System.out.println("game: " + aux3.getId() + " redplayer: " + aux3.getRedPlayer().getUsername()+ "bluePlayer: " + aux3.getBluePlayer().getUsername());			
		}
		System.out.println("------------------------------------------");
	}
	
	
		
	
	
}


