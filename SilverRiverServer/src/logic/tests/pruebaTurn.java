package logic.tests;

import logic.game.Turn;
import logic.player.Player;



public class pruebaTurn {


		/**
		 * @param args
		 */
		public static void main(String[] args) {
			// TODO Auto-generated method stub

			Turn turn = null;
			Player jugadorRojo = new Player("JugadorRojo");
			Player jugadorAzul = new Player("JugadorAzul");
			
			System.out.println("Creando objeto Turn con jugadorRojo...");
			
			turn = new Turn(jugadorRojo);
			
			System.out.println("Turn: " + turn.getActivePlayer().getUsername() + " " + turn.getMovesLeft());
			
			turn.setMovesLeft(2);
			
			System.out.println("Turn luego de cambiar los moves: " + turn.getActivePlayer().getUsername() + " " + turn.getMovesLeft());
			
			System.out.println("Cambiando jugador activo....");
			
			if(turn.getActivePlayer().equals(jugadorRojo)){
				turn.endTurn(jugadorAzul);
			}else{
				turn.endTurn(jugadorRojo);
			}
			
			System.out.println("Turn: " + turn.getActivePlayer().getUsername() + " " + turn.getMovesLeft());
			
			turn.setMovesLeft(2);
			
			System.out.println("Turn luego de cambiar los moves: " + turn.getActivePlayer().getUsername() + " " + turn.getMovesLeft());}

	}



