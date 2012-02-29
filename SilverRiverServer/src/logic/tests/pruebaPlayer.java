package logic.tests;

import logic.player.Player;

public class pruebaPlayer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	Player jugadorRojo = null;
	Player jugadorAzul = null;
	
	
	System.out.println("Cargando los 2 jugadores....");
	jugadorRojo = new Player("Jugador 1");
	jugadorAzul = new Player("Jugador 2");
	
	System.out.println("JugadorRojo: " + jugadorRojo.getUsername());
	System.out.println("JugadorAzul: " + jugadorAzul.getUsername());
	
	System.out.println("Cambiando atributos jugadores...");
	
	
	jugadorRojo.setUsername("JugadorRojo Cambiado");
	
	
	jugadorAzul.setUsername("JugadorAzul Cambiado");
	
	
	System.out.println("JugadorRojo: " + jugadorRojo.getUsername());
	System.out.println("JugadorAzul: " + jugadorAzul.getUsername());
	
	}

}
