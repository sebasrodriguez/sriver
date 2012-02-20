package pruebas;

import logica.Player;

public class pruebaPlayer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	Player jugadorRojo = null;
	Player jugadorAzul = null;
	
	
	System.out.println("Cargando los 2 jugadores....");
	jugadorRojo = new Player(0, "Jugador 1");
	jugadorAzul = new Player(1,"Jugador 2");
	
	System.out.println("JugadorRojo: " + jugadorRojo.getId() + " " + jugadorRojo.getUsername());
	System.out.println("JugadorAzul: " + jugadorAzul.getId() + " " + jugadorAzul.getUsername());
	
	System.out.println("Cambiando atributos jugadores...");
	
	jugadorRojo.setId(123);
	jugadorRojo.setUsername("JugadorRojo Cambiado");
	
	jugadorAzul.setId(234);
	jugadorAzul.setUsername("JugadorAzul Cambiado");
	
	
	System.out.println("JugadorRojo: " + jugadorRojo.getId() + " " + jugadorRojo.getUsername());
	System.out.println("JugadorAzul: " + jugadorAzul.getId() + " " + jugadorAzul.getUsername());
	
	}

}
