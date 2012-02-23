package tests;


import entities.Weapon;

public class pruebaWeapon {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		Weapon weapon = new Weapon(3);
		
		
		
		switch (weapon.getWeapon()){
			case 0:
				System.out.println("TORPEDO");
				break;
			case 1:
				System.out.println("MACHINEGUN");
				break;
			case 2:
				System.out.println("AIRCRAFT");
				break;
			case 3:
				System.out.println("ANTIAIRCRAFT");
				break;
		}
	}
}
