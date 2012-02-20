package pruebas;

import logica.Captain;

public class pruebaCaptain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	Captain jugadorRojo = null;
	Captain jugadorAzul = null;
	
	
	System.out.println("Cargando los 2 jugadores....");
	jugadorRojo = new Captain(0, "Jugador 1");
	jugadorAzul = new Captain(1,"Jugador 2");
	
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
