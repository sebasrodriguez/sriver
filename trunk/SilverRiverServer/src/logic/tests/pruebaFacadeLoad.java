package logic.tests;

import logic.Facade;
import entities.GameVO;

public class pruebaFacadeLoad {

	
	private static Facade facadePrueba;
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		GameVO gameLoaded = null;
		facadePrueba = Facade.getInstance();
		
		System.out.println("---- Cargando partida guardada---");
		int gameIdloaded = facadePrueba.loadGame("Jugador Rojo 1", "Jugador Azul 1");
		if(gameIdloaded == -1){
			System.out.println("NO CARGO LA PARTIDA");
		}else{
			gameLoaded = facadePrueba.getGame(gameIdloaded);
			System.out.println("---Mostrando game a salvar---");		
			System.out.println("GameId: " + gameLoaded.getId());
			System.out.println("RedPlayer: " + gameLoaded.getRedPlayer().getUsername());
			System.out.println("BluePlayer: " + gameLoaded.getBluePlayer().getUsername());
			System.out.println("RedActionQueueSize: " + gameLoaded.getRedActionQueue().length);
			System.out.println("BlueActionQueueSize: " + gameLoaded.getBlueActionQueue().length);
			System.out.println("ShipsSize: " + gameLoaded.getShips().length);
			System.out.println("ActivePlayer: " + gameLoaded.getTurn().getActivePlayer().getUsername() + " MovesLeft: " + gameLoaded.getTurn().getMovesLeft());		
		}
		System.out.println("---------------------------------------------");
	
		
		


	}

}
