package pruebas;

import logica.Captain;
import logica.Shift;

public class pruebaShift {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Shift shift = null;
		Captain jugadorRojo = new Captain(0, "JugadorRojo");
		Captain jugadorAzul = new Captain(1,"JugadorAzul");
		
		System.out.println("Creando objeto Shift con jugadorRojo...");
		
		shift = new Shift(jugadorRojo, 5);
		
		System.out.println("Shift: " + shift.getActivePlayer().getUsername() + " " + shift.getMovesLeft());
		
		shift.setMovesLeft(2);
		
		System.out.println("Shift luego de cambiar los moves: " + shift.getActivePlayer().getUsername() + " " + shift.getMovesLeft());
		
		System.out.println("Cambiando jugador activo....");
		
		if(shift.getActivePlayer().equals(jugadorRojo)){
			shift.endShift(jugadorAzul);
		}else{
			shift.endShift(jugadorRojo);
		}
		
		System.out.println("Shift: " + shift.getActivePlayer().getUsername() + " " + shift.getMovesLeft());
		
		shift.setMovesLeft(2);
		
		System.out.println("Shift luego de cambiar los moves: " + shift.getActivePlayer().getUsername() + " " + shift.getMovesLeft());}

}
