package logic.tests;

import logic.game.Game;
import logic.player.Player;

public class pruebaGame2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Player redPlayerPrueba = new Player("Jugador 1");
		Player bluePlayerPrueba = new Player("Jugador 2");
		
		Game pruebaGame = new Game(0, redPlayerPrueba, bluePlayerPrueba);

		
		System.out.println("RedShip: (" + pruebaGame.getShip(0).getPosition().getX() + "," + pruebaGame.getShip(0).getPosition().getY() + ")");
		System.out.println("BlueShip1: (" + pruebaGame.getShip(1).getPosition().getX() + "," + pruebaGame.getShip(1).getPosition().getY() + ")");
		System.out.println("BlueShip2: (" + pruebaGame.getShip(2).getPosition().getX() + "," + pruebaGame.getShip(2).getPosition().getY() + ")");
		System.out.println("BlueShip3: (" + pruebaGame.getShip(3).getPosition().getX() + "," + pruebaGame.getShip(3).getPosition().getY() + ")");
		
		
	}

}
