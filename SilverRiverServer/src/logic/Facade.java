package logic;

import data.Data;

import java.util.ArrayList;
import java.util.Iterator;

import entities.BlueShipVO;
import entities.Cardinal;
import entities.Coordinate;
import entities.GameVO;
import entities.RedShipVO;
import entities.ShipVO;
import entities.Weapon;

import logic.actions.Action;
import logic.actions.EndTurnAction;
import logic.actions.FireAction;
import logic.actions.MoveAction;
import logic.actions.RotateAction;
import logic.game.Game;
import logic.player.Player;
import logic.ship.*;


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
		ShipVO shipVO = null;
		Ship aux = null;
		
		if(activeGame != null){
			aux = activeGame.getShip(shipId);
			aux.setPosition(destination);
			
			
			//Comparo si es id 0 entonces es barco rojo
			if(aux.getId() == 0){			
				shipVO = new RedShipVO(aux.getId(), aux.getSpeed(), aux.getArmor(), aux.getAmmo(), aux.getTorpedo(), aux.getViewRange(), aux.getSize(), aux.getPosition(), aux.getOrientation());				
			}else{
				shipVO = new BlueShipVO(aux.getId(), aux.getSpeed(), aux.getArmor(), aux.getAmmo(), aux.getTorpedo(), aux.getViewRange(), aux.getSize(), aux.getPosition(), aux.getOrientation());
			}
			
			moveActionToReturn = new MoveAction(shipVO,destination);
			
			
			//Comparo si es igual al jugador ROJO
			if(activeGame.getTurn().getActivePlayer().equals(activeGame.getRedPlayer())){
				activeGame.getBlueActionQueue().add(moveActionToReturn);
			}else{
				activeGame.getRedActionQueue().add(moveActionToReturn);
			}
		}		
		activeGame.getTurn().consumeMovement();		
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
		
		Game activeGame = this.findGame(gameId);
		FireAction fireActionToReturn = null;
		int newAmunition = 0;
		boolean hit = false;
		Ship affectedShip = null; 
		Coordinate exactFiringPoint = this.calculateHitPoint(activeGame.getShip(shipFiringId), firingPoint);
		Ship aux = null;
		ShipVO firedShipVO = null;
		ShipVO firingShipVO = null;
		
		
		if(activeGame.getShipFiredId(exactFiringPoint) != -1){
			//acerto	
			hit = true;
			//OJOOOOOOOOO con Armor - 1
			affectedShip = activeGame.getShip(activeGame.getShipFiredId(exactFiringPoint));
			int newArmor = affectedShip.getArmor() - 1;
			affectedShip.setArmor(newArmor);
				
			/*if(affectedShip.getArmor() <= 0){
				activeGame.destroyedShip(affectedShip.getId());
			}	*/
			
			
			//Comapro si el barco dañado es rojo
			if(affectedShip.getId() == 0){
				firedShipVO = new RedShipVO(affectedShip.getId(), affectedShip.getSpeed(), affectedShip.getArmor(), affectedShip.getAmmo(), affectedShip.getTorpedo(), affectedShip.getViewRange(), affectedShip.getSize(), affectedShip.getPosition(), affectedShip.getOrientation());
			}else{
				firedShipVO = new BlueShipVO(affectedShip.getId(), affectedShip.getSpeed(), affectedShip.getArmor(), affectedShip.getAmmo(), affectedShip.getTorpedo(), affectedShip.getViewRange(), affectedShip.getSize(), affectedShip.getPosition(), affectedShip.getOrientation());
			}			
		}
		
		//comparo si es torpedo
		if(weaponType.getWeapon() == 0){
			aux = activeGame.getShip(shipFiringId);
			newAmunition = aux.getTorpedo() - 1;
			aux.setTorpedo(newAmunition);						
		}else{
			aux = activeGame.getShip(shipFiringId);
			newAmunition = aux.getAmmo() - 1;
			aux.setAmmo(newAmunition);
		}
		
		
		
		
		//Comparo si el barco que disparo es rojo
		if(aux.getId() == 0){
			firingShipVO = new RedShipVO(aux.getId(), aux.getSpeed(), aux.getArmor(), aux.getAmmo(), aux.getTorpedo(), aux.getViewRange(), aux.getSize(), aux.getPosition(), aux.getOrientation());
		}else{
			firingShipVO = new BlueShipVO(aux.getId(), aux.getSpeed(), aux.getArmor(), aux.getAmmo(), aux.getTorpedo(), aux.getViewRange(), aux.getSize(), aux.getPosition(), aux.getOrientation());
		}
		
		
		
		fireActionToReturn = new FireAction(firingShipVO, weaponType, exactFiringPoint, hit, firedShipVO);
		
		
		//Comparo si es igual al jugador ROJO
		if(activeGame.getTurn().getActivePlayer().getUsername().compareTo(activeGame.getRedPlayer().getUsername()) == 0){
			activeGame.getBlueActionQueue().add(fireActionToReturn);
		}else{
			activeGame.getRedActionQueue().add(fireActionToReturn);
		}
		
		activeGame.getTurn().consumeMovement();
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
		ShipVO shipVO = null;
		Ship aux = null;
		
		if(activeGame != null){			
			aux = activeGame.getShip(shipId);
			aux.setOrientation(destination);
			
			//Comparo si es jugador rojo para saber el bando
			if(aux.getId() == 0){			
				shipVO = new RedShipVO(aux.getId(), aux.getSpeed(), aux.getArmor(), aux.getAmmo(), aux.getTorpedo(), aux.getViewRange(), aux.getSize(), aux.getPosition(), aux.getOrientation());				
			}else{
				shipVO = new BlueShipVO(aux.getId(), aux.getSpeed(), aux.getArmor(), aux.getAmmo(), aux.getTorpedo(), aux.getViewRange(), aux.getSize(), aux.getPosition(), aux.getOrientation());
			}			
			
			rotateActionToReturn = new RotateAction(shipVO, destination);
			
			//Comparo si es igual al jugador ROJO
			if(activeGame.getTurn().getActivePlayer().getUsername().compareTo(activeGame.getRedPlayer().getUsername()) == 0){
				activeGame.getBlueActionQueue().add(rotateActionToReturn);
			}else{
				activeGame.getRedActionQueue().add(rotateActionToReturn);
			}			
		}
		activeGame.getTurn().consumeMovement();		
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
		if(activeGame.getTurn().getActivePlayer().getUsername().compareTo(activeGame.getRedPlayer().getUsername()) == 0){
			activeGame.getTurn().endTurn(activeGame.getBluePlayer());
			endTurnActionToReturn = new EndTurnAction(gameId, activeGame.getBluePlayer());
			activeGame.insertActionBlueQueue(endTurnActionToReturn);
		}else{
			activeGame.getTurn().endTurn(activeGame.getRedPlayer());
			endTurnActionToReturn = new EndTurnAction(gameId, activeGame.getRedPlayer());
			activeGame.insertActionRedQueue(endTurnActionToReturn);
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
		
		Data data = new Data();
		Game gameToSave = this.findGame(gameId);		
		
		data.saveGame(gameToSave);		
	}
	
	/*
	 * Entrada: los nombres de los dos usuarios que conforman la partida a buscar
	 * Salida: Id de la partida o -1 en caso de no existir la misma en la BD
	 * Procedimiento:
	 * 	Si existe la partida, la guarda en el ArrayList y devuelve el id 
	 * 	sino devuelve -1
	 */
	public int loadGame(String redPlayerUsername, String bluePlayerUsername){
		
		Data data = new Data();
		
		Game gameLoaded = data.loadGame(redPlayerUsername, bluePlayerUsername);
		if(gameLoaded != null){
			this.activeGames.add(gameLoaded);
			return gameLoaded.getId();
		}else{
			return -1;
		}
		
	
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
	 * Entrada: gameId y username
	 * Salida: el action[] queue de ese jugador
	 * Procedimiento:
	 * 	Si ese jugador es redPlayer entonces retorno el redActionQueue y la vacia
	 * 	sino retonra el blueActionQueue y la vacia 	
	 */
	public Action[] getActions(int gameId, String username){
		
		Game activeGame = findGame(gameId);
		Action[] actionToReturn;
		
		//comparo si es redPlayer
		if(activeGame.getRedPlayer().getUsername().compareTo(username) == 0){
			actionToReturn = activeGame.redActionQueueMapToArray();
			activeGame.getRedActionQueue().clear();
		}else{
			actionToReturn = activeGame.blueActionQueueMapToArray();
			activeGame.getBlueActionQueue().clear();
		}
		
		return actionToReturn;		
	}
	
	
	
	/*
	 * Entrada: gameId
	 * Salida: retorna un gameVO
	 * Procedimiento:
	 * 	Busca el game que corresponde a ese gameId
	 * 	Retorna el gameVO de ese gameId
	 */
	public GameVO getGame(int gameId){
		Game aux = this.findGame(gameId);		
		
		return aux.mapToValueObject();		
		
	}
	
	
	/*
	 * Metodos privados
	 */
	
	/*
	 * Calcula la distacia entre el barco que dispara y el punto donde el jugador hizo click
	 * Esta distacia se vá a usar para variar que tanto se puede desviar el disparo.
	 * A más distacia más se va a desviar el disparo.
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
	 *El punto final se calcula con un número aleatorio entre x - offset e x + offset, lo mismo para las y 
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
	
	/*
	//PRUEBAS CON LA UI
	public Action[] pruebaActions(){
		
		Coordinate position = null;
		Cardinal orientation = null;
		ArrayList<Action> actions = new ArrayList<Action>();
		
		
		
		
		//Creando los barcos
		position = new Coordinate(12,10);
		orientation = new Cardinal(Cardinal.S);
		ShipVO redShip = new RedShipVO(1, 10, 10, 12, 3, 10, 3, position, orientation);
		
		position = new Coordinate(20,20);
		orientation = new Cardinal(90);
		ShipVO blueShip1 = new BlueShipVO(2, 5, 5, 6, 1, 5, 1, position, orientation);
		position = new Coordinate(22,20);
		orientation = new Cardinal(90);
		ShipVO blueShip2 = new BlueShipVO(3, 5, 5, 6, 1, 5, 1, position, orientation);
		position = new Coordinate(24,20);
		orientation = new Cardinal(90);
		ShipVO blueShip3 = new BlueShipVO(4, 5, 5, 6, 1, 5, 1, position, orientation);
	
		//MAPA (64,36)	
	
		//Creando las acciones		
		
		position = new Coordinate(12,13);				
		MoveAction action1 = new MoveAction(redShip, position);
		actions.add(action1);
		//acs[0] = action1;
		
		
		orientation = new Cardinal(Cardinal.N);				
		RotateAction action2 = new RotateAction(redShip, orientation);
		actions.add(action2);
		//acs[1] = action2;
		
		
		position = new Coordinate(12,9);
		MoveAction action3 = new MoveAction(redShip, position);
		actions.add(action3);
		//acs[2] = action3;
		
		
		Action[] acs = {action1, action2, action3};
				
		return acs;	
		
	}*/
	
	
	
}
