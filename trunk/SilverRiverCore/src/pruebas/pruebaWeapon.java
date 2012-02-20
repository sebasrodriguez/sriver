package pruebas;

import logica.Weapon;

public class pruebaWeapon {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		Weapon weapon;
		
		weapon = Weapon.MACHINEGUN;
		
		switch (weapon){
			case TORPEDO:
				System.out.println("TORPEDO");
				break;
			case MACHINEGUN:
				System.out.println("MACHINEGUN");
				break;
			case AIRCRAFT:
				System.out.println("AIRCRAFT");
				break;
			case ANTIAIRCRAFT:
				System.out.println("ANTIAIRCRAFT");
				break;
		}
	}
}
