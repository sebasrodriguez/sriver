package logic.tests;

import entities.Cardinal;
import entities.Coordinate;
import logic.Facade;

public class pruebaFacadeFireTorpedo {

	
	private static Facade facadePrueba;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		facadePrueba = Facade.getInstance();
		facadePrueba.newGame("jugador1");
		int gmid = facadePrueba.newGame("Jugador2");
		Coordinate pos = new Coordinate(5,32);
		facadePrueba.move(gmid, 0, pos);
		Cardinal card = new Cardinal(Cardinal.N);
		System.out.println(card.getDirection());
		facadePrueba.rotate(0, 0, card);
	
		pos = new Coordinate(5,5);
		facadePrueba.move(gmid, 3, pos);
		
		
		System.out.println(facadePrueba.fireTorpedo(0, 0).getHit());
	
	
	}

}
