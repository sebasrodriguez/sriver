package logic.tests;

import entities.GameVO;
import entities.ShipVO;
import logic.Facade;

public class pruebaFacadeSave {

	private static Facade facadePrueba;
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub	
		
		facadePrueba = Facade.getInstance();
		
		GameVO gameToSave = null;
				
		System.out.println(facadePrueba.newGame("Jugador Rojo 1"));
		int gameId = facadePrueba.newGame("Jugador Azul 1");
		System.out.println(gameId);
		
		gameToSave = facadePrueba.getGame(gameId);
		System.out.println("---Mostrando game a salvar---");		
		System.out.println("GameId: " + gameToSave.getId());
		System.out.println("RedPlayer: " + gameToSave.getRedPlayer().getUsername());
		System.out.println("BluePlayer: " + gameToSave.getBluePlayer().getUsername());
		System.out.println("RedActionQueueSize: " + gameToSave.getRedActionQueue().length);
		System.out.println("BlueActionQueueSize: " + gameToSave.getBlueActionQueue().length);
		System.out.println("ShipsSize: " + gameToSave.getShips().length);
		System.out.println("ActivePlayer: " + gameToSave.getTurn().getActivePlayer().getUsername() + " MovesLeft: " + gameToSave.getTurn().getMovesLeft());
		
		
		ShipVO[] shipIt = gameToSave.getShips();
		
		for(int i=0;i<=3;i++){
			System.out.println(shipIt[i].getId() + " " + shipIt[i].getAmmo() + " " + shipIt[i].getArmor() + " " + shipIt[i].getSize() + " " + shipIt[i].getSpeed() + " " + shipIt[i].getTorpedo() + " " + shipIt[i].getViewRange() + " " + shipIt[i].getOrientation().getDirection() + " (" + shipIt[i].getPosition().getX() + "," + shipIt[i].getPosition().getY() + ")");
		}
		
		System.out.println("---------------------------------------------");
		
		System.out.println("--- Salvando ---");
		facadePrueba.saveGame(gameId);
		System.out.println("----------------------------------");
		
		
	}
}
