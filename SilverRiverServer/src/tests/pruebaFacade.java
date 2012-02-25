package tests;

import java.util.Iterator;

import logic.Facade;
import logic.actions.NewGameAction;
import logic.game.Game;
import logic.player.Player;

public class pruebaFacade {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
				
		Facade facadePrueba = Facade.getInstance();
	
		
		
		System.out.println(facadePrueba.newGame("Jugador 1"));
		System.out.println(facadePrueba.newGame("Jugador 2"));
		System.out.println(facadePrueba.newGame("Jugador 3"));
		System.out.println(facadePrueba.newGame("Jugador 4"));
		System.out.println(facadePrueba.newGame("Jugador 5"));

		Iterator<Game> pepe = facadePrueba.devolverITerator();
		
		while (pepe.hasNext()){
			Game ppito = pepe.next();
			System.out.println(ppito.getId());
			System.out.println(ppito.getRedPlayer().getUsername());
			System.out.println(ppito.getBluePlayer().getUsername());			
		}
		
		
		
		
		/*
		facadePrueba.newGame();
		
		
		facadePrueba.newGame()
		
		facadePrueba.move(gameId, shipId, destination)
		facadePrueba.rotate(gameId, shipId, destination)
		facadePrueba.fire(gameId, shipFiringId, firingPoint, weaponType)
		facadePrueba.endTurn(gameId)
		
		facadePrueba.endGame(gameId)
		
		facadePrueba.saveGame(gameId)
		facadePrueba.loadGame(gameId)
		
		
		
		*/
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*
		Coordinate pos = new Coordinate(10,10);
		Cardinal orientation = new Cardinal(-45);
		Ship ship1 = new RedShip(0,10,10,10,10,10,10,pos, orientation);
		Ship cambio = ship1;
		cambio.setId(123);
		
		
		System.out.println("ship1: ");
		System.out.println("Id: "+ ship1.getId());
		System.out.println("Speed: " + ship1.getSpeed());
		System.out.println("Armor: " + ship1.getArmor());
		System.out.println("Ammo: " + ship1.getAmmo());
		System.out.println("Torpedo: " + ship1.getTorpedo());
		System.out.println("ViewRange: " + ship1.getViewRange());
		System.out.println("Size: " + ship1.getSize());
		System.out.println("Position: (" + ship1.getPosition().getX() + "," + ship1.getPosition().getY() + ")");
		System.out.println("Cardinal: " + ship1.getOrientation().toString());		
		System.out.println("---------------------------------------------------------------");
		
		System.out.println("Cambio: ");
		System.out.println("Id: "+ cambio.getId());
		System.out.println("Speed: " + cambio.getSpeed());
		System.out.println("Armor: " + cambio.getArmor());
		System.out.println("Ammo: " + cambio.getAmmo());
		System.out.println("Torpedo: " + cambio.getTorpedo());
		System.out.println("ViewRange: " + cambio.getViewRange());
		System.out.println("Size: " + cambio.getSize());
		System.out.println("Position: (" + cambio.getPosition().getX() + "," + cambio.getPosition().getY() + ")");
		System.out.println("Cardinal: " + cambio.getOrientation().toString());
		
		System.out.println("---------------------------------------------------------------");
		*/

	}

}
