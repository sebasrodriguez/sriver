package logic;

import java.util.ArrayList;
import java.util.Iterator;

import logic.actions.Action;
import logic.actions.EndTurnAction;
import logic.actions.FireAction;
import logic.actions.MoveAction;
import logic.actions.RotateAction;
import logic.game.Game;
import logic.game.Turn;
import logic.player.Player;
import logic.ship.*;

import entities.Cardinal;
import entities.Coordinate;
import entities.PlayerVO;
import entities.Weapon;


/*
 * La clase Facade es singleton
 */
public class Facade {	
	
	private ArrayList<Game> activeGames;
	private static Facade facade;
	
	/*
	 * Devuelve la instancia del Facade (Singleton)
	 */
	public static Facade getInstance(){
		if(facade == null){
			facade = new Facade();
		}
		return facade;
	}
	
	/*
	 * Constructores
	 */
	public Facade(){	
		Game newGame = new Game();
		
		newGame.setBlueActionQueue(new ArrayList<Action>());
		newGame.setRedActionQueue(new ArrayList<Action>());
		newGame.setId(1);
		ArrayList<Ship> ships = new ArrayList<Ship>();		
		ships.add(new RedShip(1, 2, 3, 4, 5 ,6 ,7, new Coordinate(5, 5), new Cardinal(Cardinal.E)));
		ships.add(new BlueShip(2, 2, 3, 4, 5 ,6 ,7, new Coordinate(6, 6), new Cardinal(Cardinal.E)));
		ships.add(new BlueShip(3, 2, 3, 4, 5 ,6 ,7, new Coordinate(7, 7), new Cardinal(Cardinal.E)));
		ships.add(new BlueShip(4, 2, 3, 4, 5 ,6 ,7, new Coordinate(8, 8), new Cardinal(Cardinal.E)));
		newGame.setShips(ships);
		Player redPlayer = new Player(1, "sebas");
		Player bluePlayer = new Player(2, "pablo");
		newGame.setBluePlayer(bluePlayer);
		newGame.setRedPlayer(redPlayer);
		Turn turn = new Turn(redPlayer, 5);
		newGame.setTurn(turn);
		
		this.activeGames.add(newGame);
	}	
	
	/*
	Facade(ArrayList<Game> activeGames){
		this.activeGames = activeGames;
	}
	*/
	
	/*
	 * Getters
	 */
	public ArrayList<Game> getActiveGames(){
		return this.activeGames;
	}
	
	/*
	 * Setters
	 */
	public void setActiveGames(ArrayList<Game> activeGames){
		this.activeGames = activeGames;
	}
	
	/*
	 * Acciones
	 */	
	
	/*
	 * Metodo privado el cual encuentra la partida a la cual hago referencia en el array de partidas activas del Facade
	 */
	private Game findGame(int gameId){
		Iterator<Game> gameIterator = this.activeGames.iterator();
		Game gameToReturn = null;
		boolean found = false;
		
		while(gameIterator.hasNext() && !found){
			gameToReturn = gameIterator.next();
			if(gameToReturn.getId() == gameId ){
				found = true;				
			}			
		}
		return gameToReturn;
	}	
	
	/*
	 * Entrada: Id de la partida, Id del barco que se mueve, destino
	 * Salida: MoveAction
	 * Procedimiento: 
	 * Al barco que se mueve le actualiza el destino en la partida
	 * Coloca en la Queue del otro jugador esta accion
	 * Retorna MoveAction
	 */
	
	public MoveAction move(int gameId, int shipId, Coordinate destination){
		MoveAction moveActionToReturn = null;
		Game activeGame = findGame(gameId);
		
		if(activeGame != null){
			activeGame.getShip(shipId).setPosition(destination);
			moveActionToReturn = new MoveAction(activeGame.getShip(shipId),destination);
			
			
			//Comparo si es igual al jugador ROJO
			if(activeGame.getTurn().getActivePlayer().equals(activeGame.getRedPlayer())){
				activeGame.getBlueActionQueue().add(moveActionToReturn);
			}else{
				activeGame.getRedActionQueue().add(moveActionToReturn);
			}
		}		
		return moveActionToReturn;	
	}
	
		
	/*
	 * Entrada: Id de la partida, id del barco que dispara, punto la que dispara
	 * Salida: FireAction
	 * Procedimiento:
	 * Calcula el punto exacto del disparo
	 * Verifica que haya o no acertado
	 * Si acerto
	 * 	actualiza los valores del barco dañado
	 * actualiza los valores del barco que disparo
	 * devuelve el fireAction	 	
	*/
	public FireAction fire(int gameId, int shipFiringId, Coordinate firingPoint, Weapon weaponType){
		
		//NO SE COMO CALCULAR EL PUNTO EXACTO DE DISPARO!!
		//OJO, CAMBIAR EL FIRINGPOINT POR EL CALCULO DEL PUNTO EXACTO!!! INCLUSO EN EL FIREACTION A RETORNAR
		Game activeGame = this.findGame(gameId);
		FireAction fireActionToReturn = null;
		int newAmunition = 0;
		boolean hit = false;
		Ship affectedShip = null; 
		
		if(activeGame.getShipFiredId(firingPoint) != -1){
			//acerto	
			hit = true;
			int newArmor = activeGame.getShip(activeGame.getShipFiredId(firingPoint)).getArmor() - 20;
			affectedShip = activeGame.getShip(activeGame.getShipFiredId(firingPoint));
			affectedShip.setArmor(newArmor);	
			if(affectedShip.getArmor() <= 0){
				activeGame.destoyedShip(affectedShip.getId());
			}			
		}
		
		//comparo si es torpedo
		if(weaponType.equals("TORPEDO")){
			newAmunition = activeGame.getShip(shipFiringId).getTorpedo() - 1;
			activeGame.getShip(shipFiringId).setTorpedo(newAmunition);
			
		}else{
			newAmunition = activeGame.getShip(shipFiringId).getAmmo() - 1;
			activeGame.getShip(shipFiringId).setAmmo(newAmunition);
		}
		
		fireActionToReturn = new FireAction(activeGame.getShip(shipFiringId), weaponType, firingPoint, hit, affectedShip);
		return fireActionToReturn;
	}
	
	
	/*
	 * Entrada: Id de la partida, id del barco que rota y cardinal de destino
	 * Salida: RotateAction
	 * Procedimiento:
	 * Actualiza la posicion a la cual apunta el barco
	 * devuelve el rotateAction 
	 */
	public RotateAction rotate(int gameId, int shipId, Cardinal destination){
		
		RotateAction rotateActionToReturn = null;
		Game activeGame = this.findGame(gameId);
		
		if(activeGame != null){
			activeGame.getShip(shipId).setOrientation(destination);
			
			rotateActionToReturn = new RotateAction(activeGame.getShip(shipId), destination);
			
			//Comparo si es igual al jugador ROJO
			if(activeGame.getTurn().getActivePlayer().equals(activeGame.getRedPlayer())){
				activeGame.getBlueActionQueue().add(rotateActionToReturn);
			}else{
				activeGame.getRedActionQueue().add(rotateActionToReturn);
			}			
		}
		return rotateActionToReturn;		
	}
	
	
	/*
	 * Entrada: Id de la partida
	 * Salida: EndTurnAction
	 * Procedimiento: 
	 * Cambia al jugador activo
	 * carga la cantidad de movimientos disponibles 
	 * devuelve endTurnAction
	 */
	public EndTurnAction endTurn(int gameId){
		
		Game activeGame = this.findGame(gameId);
		EndTurnAction endTurnActionToReturn = null;
		
		//comparo si el jugador actual es el rojo
		if(activeGame.getTurn().getActivePlayer().equals(activeGame.getRedPlayer())){
			activeGame.getTurn().endTurn(activeGame.getBluePlayer());
			endTurnActionToReturn = new EndTurnAction(gameId, activeGame.getBluePlayer());
		}else{
			activeGame.getTurn().endTurn(activeGame.getRedPlayer());
			endTurnActionToReturn = new EndTurnAction(gameId, activeGame.getRedPlayer());
		}		
		return endTurnActionToReturn;		
	}
	
	/*
	 * Entrada: Id de la partida
	 * Salida: void
	 * Procedimiento:
	 * Llama al metodo saveGame en la clase Game 
	 */
	public void saveGame(int gameId){
		
	}
	
	/*
	 * TENGO DUDAS CON ESTE METODO DE COMO DEBERIA SER
	 * NO DEBERIA RECIBIR LA COMBINACION DE JUGADORES PARA SABER REALMENTE CUAL RECUPERAR?
	 */
	public void loadGame(int gameId){
		
	}
	
	/*
	 * QUE HARIA EL ENDGAME??????
	 */
	public void endGame(int gameId){
		
	}
	
	/*
	 * NO ENTIENDO EL PORQUE DE ESTE METODO
	 */
	public ArrayList<Action> listAction(int gameId){
		return null;
	}
}
