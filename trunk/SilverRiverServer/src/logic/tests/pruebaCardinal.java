package logic.tests;

import logic.entities.Cardinal;

public class pruebaCardinal {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Cardinal cardinal = new Cardinal(-135);
		
		
		switch (cardinal.getDirection()){
		case 0:
			System.out.println("Apuntando al North: " + cardinal.getDirection());
			break;
		case 90:
			System.out.println("Apuntando al East: " + cardinal.getDirection());
			break;
		case 180:
			System.out.println("Apuntando al South: " + cardinal.getDirection());
			break;
		case -90:
			System.out.println("Apuntando al West: " + cardinal.getDirection());
			break;
		case 45:
			System.out.println("Apuntando al NorthEast: " + cardinal.getDirection());
			break;
		case -45:
			System.out.println("Apuntando al NorthWest: " + cardinal.getDirection());
			break;
		case 135:
			System.out.println("Apuntando al SouthEast: " + cardinal.getDirection());
			break;
		case -135:
			System.out.println("Apuntando al SouthWest: " + cardinal.getDirection());
			break;
		}		
	}
}
