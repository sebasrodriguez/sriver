package tests;

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
		jugadorRojo = new Player(0, "Jugador 1");
		System.out.println("Creando el jugador 1 con Id 1 y username Jugador 2....");
		jugadorAzul = new Player(1,"Jugador 2");
				
		System.out.println("JugadorRojo: " + jugadorRojo.getId() + " " + jugadorRojo.getUsername());
		System.out.println("JugadorAzul: " + jugadorAzul.getId() + " " + jugadorAzul.getUsername());
		
		System.out.println("-------------------------------------------------------");
		
		
		EndTurnAction endTurnActionPrueba = new EndTurnAction(0,jugadorRojo);
		
		System.out.println("Turn: " + endTurnActionPrueba.getGameId() + " " + endTurnActionPrueba.getPlayerTurn().getUsername());
		
		System.out.println("-------------------------------------------------------");
		
		System.out.println("Cambiando gameId y player");
		endTurnActionPrueba.setGameId(4);
		endTurnActionPrueba.setPlayerTurn(jugadorAzul);
		
		System.out.println("Turn: " + endTurnActionPrueba.getGameId() + " " + endTurnActionPrueba.getPlayerTurn().getUsername());
		
		
	}

}
