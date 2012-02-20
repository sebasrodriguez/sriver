package pruebas;

import logica.Coordenate;

public class pruebaCoordenate {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		Coordenate coordenate = new Coordenate(10,50);
		
		System.out.println("Creando objeto coordenate con x= 10 e y=50...");
		
		System.out.println("Coordenate: " + coordenate.getX() + "," + coordenate.getY());
		
		coordenate.setY(100);
		System.out.println("Coordenate: " + coordenate.getX() + "," + coordenate.getY());
		
		coordenate.setX(20);
		System.out.println("Coordenate: " + coordenate.getX() + "," + coordenate.getY());
		
	}

}
