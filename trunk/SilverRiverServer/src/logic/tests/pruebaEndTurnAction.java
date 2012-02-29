package logic.tests;

import logic.actions.EndTurnAction;
import logic.player.Player;

public class pruebaEndTurnAction {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Player jugadorRojo = null;
		Player jugadorAzul = null;		
		
		System.out.println("Creando el jugador 1 con Id 0 y username Jugador 1....");
		jugadorRojo = new Player("Jugador 1");
		System.out.println("Creando el jugador 1 con Id 1 y username Jugador 2....");
		jugadorAzul = new Player("Jugador 2");
				
		System.out.println("JugadorRojo: " + jugadorRojo.getUsername());
		System.out.println("JugadorAzul: " + jugadorAzul.getUsername());
		
		System.out.println("-------------------------------------------------------");
		
		
		EndTurnAction endTurnActionPrueba = new EndTurnAction(0,jugadorRojo);
		
		System.out.println("Turn: " + endTurnActionPrueba.getGameId() + " " + endTurnActionPrueba.getPlayerTurn().getUsername());
		
		System.out.println("-------------------------------------------------------");
		
		
		
	}

}
