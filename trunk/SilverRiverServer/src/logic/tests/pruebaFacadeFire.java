package logic.tests;

import entities.Coordinate;
import entities.Weapon;
import logic.Facade;
import logic.actions.FireAction;

public class pruebaFacadeFire {
	
	private static Facade facadePrueba;
	
	public static void main(String[] args) 
	{
		facadePrueba = Facade.getInstance();
		facadePrueba.newGame("jugador1");
		int gmid = facadePrueba.newGame("Jugador2");
		Coordinate pos = new Coordinate(5,5);
		facadePrueba.move(gmid, 0, pos);
	
	
		
		Coordinate cord1 = new Coordinate(10,10);
		facadePrueba.move(gmid, 1, cord1);
		
		for(int j=0;j<50;j++){
		FireAction fac = facadePrueba.fireAmmo(gmid, 0, cord1);
		
		int i = 0;
		while(!fac.getHit()){
			fac = facadePrueba.fireAmmo(gmid, 0, cord1);
			i++;
		}
		System.out.println(i);
		}
	}

}
