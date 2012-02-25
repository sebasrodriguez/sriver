package logic;

import java.util.ArrayList;
import java.util.Iterator;

import logic.actions.EndTurnAction;
import logic.actions.FireAction;
import logic.actions.MoveAction;
import logic.actions.RotateAction;
import logic.game.Game;
import logic.player.Player;
import logic.ship.*;
import entities.Cardinal;
import entities.Coordinate;
import entities.Weapon;


/*
 * La clase Facade es singleton
 */
public class Facade {	
	
	private ArrayList<Game> activeGames;
	private Game gameWithoutBluePlayer;
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
	private Facade(){	
		
		this.activeGames = new ArrayList<Game>();
		this.gameWithoutBluePlayer = null;		
	}	
	

	
		
	/*
	 * Metodos publicos
	 */		
	
	
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
		activeGame.getTurn().consumeMovement();
		this.checkTurn(gameId);
		return moveActionToReturn;	
	}
	
		
	/*
	 * Entrada: Id de la partida, id del barco que dispara, punto la que dispara
	 * Salida: FireAction
	 * Procedimiento:
	 * Calcula el punto exacto del disparo
	 * Verifica que haya o no acertado
	 * Si acerto
	 * 	actualiza los valores del barco da�ado
	 * actualiza los valores del barco que disparo
	 * devuelve el fireAction	 	
	*/
	public FireAction fire(int gameId, int shipFiringId, Coordinate firingPoint, Weapon weaponType){
		
		Game activeGame = this.findGame(gameId);
		FireAction fireActionToReturn = null;
		int newAmunition = 0;
		boolean hit = false;
		Ship affectedShip = null; 
		Coordinate exactFiringPoint = this.calculateHitPoint(activeGame.getShip(shipFiringId), firingPoint);
		
		if(activeGame.getShipFiredId(exactFiringPoint) != -1){
			//acerto	
			hit = true;
			//OJOOOOOOOOO con Armor - 1
			int newArmor = activeGame.getShip(activeGame.getShipFiredId(exactFiringPoint)).getArmor() - 1;
			affectedShip = activeGame.getShip(activeGame.getShipFiredId(exactFiringPoint));
			affectedShip.setArmor(newArmor);	
			if(affectedShip.getArmor() <= 0){
				activeGame.destroyedShip(affectedShip.getId());
			}			
		}
		
		//comparo si es torpedo
		if(weaponType.getWeapon() == 0){
			newAmunition = activeGame.getShip(shipFiringId).getTorpedo() - 1;
			activeGame.getShip(shipFiringId).setTorpedo(newAmunition);			
		}else{
			newAmunition = activeGame.getShip(shipFiringId).getAmmo() - 1;
			activeGame.getShip(shipFiringId).setAmmo(newAmunition);
		}
		
		fireActionToReturn = new FireAction(activeGame.getShip(shipFiringId), weaponType, exactFiringPoint, hit, affectedShip);
		//Comparo si es igual al jugador ROJO
		if(activeGame.getTurn().getActivePlayer().equals(activeGame.getRedPlayer())){
			activeGame.getBlueActionQueue().add(fireActionToReturn);
		}else{
			activeGame.getRedActionQueue().add(fireActionToReturn);
		}
		
		activeGame.getTurn().consumeMovement();
		this.checkTurn(gameId);
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
		activeGame.getTurn().consumeMovement();
		this.checkTurn(gameId);
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
	 * Entrada: Id del game a eliminar de la memoria}
	 * Salida: void
	 * Procedimiento:
	 * 	Busca el index del game por su id
	 * 	Elimina el Game en ese index del ArrayList
	 */
	public void endGame(int gameId){
		Game gameToRemove = this.findGame(gameId);
		int indexToRemove = activeGames.indexOf(gameToRemove);
		this.activeGames.remove(indexToRemove);		
	}
	
	/*
	 *Entrada: String con el username del jugador que decidio hacer new game
	 *Salida: Devuelve el numero de la partida (gameId) o -1 en caso de quedar esperando un segundo jugador
	 *Procedimiento:
	 *	Si gameWithoutBluePlayer = null entonces crea el game y retorna -1 para que el UI sepa que no es una partida activa
	 *	sino llama al metodo addBluePlayerToGame con el jugador y retorna el id de la partida
	 */
	public int newGame(String username){
		
		if(this.gameWithoutBluePlayer == null){
			int nextIdToUse = this.nextFreeIndex();			
			Player redPlayer = new Player(username);			
			this.gameWithoutBluePlayer = new Game(nextIdToUse, redPlayer, null);
			return -1;
		}else{
			return this.addBluePlayerToGame(username);
			
		}				
	}
	
	
	/*
	 * Entrada: void 
	 * Salida: -1 si gameWithoutBluePlayer != null o last GameId in activeGames
	 * Procedimiento:
	 * 	Si gameWithoutBluePlayer != null devuelve -1
	 * 	sino devuelve el ultimo GameId
	 */
	public int checkGameId(){
		if(gameWithoutBluePlayer != null){
			return -1;
		}else{
			return this.nextFreeIndex() - 1;
		}		
	}
		
	
	
	
	
	/*
	 * Metodos privados
	 */
	
	/*
	 * Calcula la distacia entre el barco que dispara y el punto donde el jugador hizo click
	 * Esta distacia se v� a usar para variar que tanto se puede desviar el disparo.
	 * A m�s distacia m�s se va a desviar el disparo.
	*/
	private double calculateDistance(Ship firingShip , Coordinate clickedCoordinate){
		
		int x1 = firingShip.getPosition().getX();
		int y1 = firingShip.getPosition().getY();
		int x2 = clickedCoordinate.getX();
		int y2 = clickedCoordinate.getY();
		
		return  Math.hypot(x2 - x1, y2 - y1);
	}
	
	
	/*
	 *Calcula donde pega el disparo dependiendo de la distancia entre
	 *el barco que dispara y el objetivo.
	 *El punto final se calcula con un n�mero aleatorio entre x - offset e x + offset, lo mismo para las y 
	 */
	private Coordinate calculateHitPoint(Ship firingShip, Coordinate clickedCoordinate){
		
		Coordinate coord = new Coordinate();
		//int offset = 50;
		
		int offset = (int)this.calculateDistance(firingShip, clickedCoordinate);
		int max_x = clickedCoordinate.getX() + offset;
		int max_y = clickedCoordinate.getY() + offset;
		
		int min_x = max_x - (offset * 2);
		int min_y = max_y - (offset * 2);
		
		int x = (int)(Math.random() * (max_x - min_x)) + min_x;
		int y = (int)(Math.random() * (max_y - min_y)) + min_y;
		
		coord.setX(x);
		coord.setY(y);
		
		return coord;
	}
	
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
	 * Entrada: Nombre del segundo jugador
	 * Salida: el id de la partida 
	 * Procedimiento:
	 * 	Crea el objeto Player
	 * 	Agrega el segundo jugador a la partida 
	 * 	La coloca en el ArrayList de partidas activas 
	 */
	private int addBluePlayerToGame(String username){
		Player bluePlayer = new Player(username);		
		Game gameToInsert = gameWithoutBluePlayer.clone();
		gameWithoutBluePlayer = null;
		
		gameToInsert.setBluePlayer(bluePlayer);
		//this.activeGames.add(gameToInsert.getId(), gameToInsert);
		this.activeGames.add(gameToInsert);
		
		return gameToInsert.getId();		
	}
	
	/*
	 * Entrada: Id del game
	 * Salida: void
	 * Procedimiento:
	 * 	Si al jugador se le acabaron todos los movimientos entonces invoca al endTurn()
	 */
	private void checkTurn(int gameId){
		Game activeGame = this.findGame(gameId);
		
		if(activeGame.getTurn().getMovesLeft() <= 0){
			this.endTurn(gameId);
		}		
	}
	
	/*
	 * Entrada:void
	 * Salida: el proximo index libre para asignarle a la partida
	 * Procedimiento:
	 * 	Busca el ultimo index y le suma 1
	 */
	private int nextFreeIndex(){
		Iterator<Game> gameIt = this.activeGames.iterator();
		Game aux = null;
		int indexToReturn = 0;
		while(gameIt.hasNext()){
			aux = gameIt.next();
			indexToReturn = aux.getId() + 1;
		}
		return indexToReturn;
	}
	
	
	//OJO QUE HAY QUE BORRARLOOOOO
	public Iterator<Game> devolverITerator(){
		return this.activeGames.iterator();
	}
	
	
}
