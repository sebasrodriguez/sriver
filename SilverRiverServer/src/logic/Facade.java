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
	private ArrayList<String> loadingGamePlayers;
	private Game gameWithoutBluePlayer;
	private static Facade facade;
	private static final int maxAxisX = 64;
	private static final int maxAxisY = 32;
	private static final int minAxisX = 0;
	private static final int minAxisY = 0;
	private static final int loading = 0;
	private static final int playing = 1;

	
		
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
		this.loadingGamePlayers = new ArrayList<String>();
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
	 * 	actualiza los valores del barco da�ado
	 * actualiza los valores del barco que disparo
	 * devuelve el fireAction	 	
	*/
	public FireAction fireAmmo(int gameId, int shipFiringId, Coordinate firingPoint){
		
		Game activeGame = this.findGame(gameId);
		FireAction fireActionToReturn = null;
		int newAmunition = 0;
		boolean hit = false;
		Ship affectedShip = null; 
		Ship aux = null;
		ShipVO firedShipVO = null;
		ShipVO firingShipVO = null;
		
		Coordinate exactFiringPoint = this.calculateHitPoint(activeGame.getShip(shipFiringId), firingPoint);
	
		
		
		if(activeGame.getShipFiredId(exactFiringPoint) != -1){
			//acerto	
			hit = true;			
			affectedShip = activeGame.getShip(activeGame.getShipFiredId(exactFiringPoint));
			int newArmor = affectedShip.getArmor() - 1;
			affectedShip.setArmor(newArmor);
				
					
			//Comapro si el barco da�ado es rojo
			if(affectedShip.getId() == 0){
				firedShipVO = new RedShipVO(affectedShip.getId(), affectedShip.getSpeed(), affectedShip.getArmor(), affectedShip.getAmmo(), affectedShip.getTorpedo(), affectedShip.getViewRange(), affectedShip.getSize(), affectedShip.getPosition(), affectedShip.getOrientation());
			}else{
				firedShipVO = new BlueShipVO(affectedShip.getId(), affectedShip.getSpeed(), affectedShip.getArmor(), affectedShip.getAmmo(), affectedShip.getTorpedo(), affectedShip.getViewRange(), affectedShip.getSize(), affectedShip.getPosition(), affectedShip.getOrientation());
			}			
		}
		
		//Calculo la nueva cantidad de balas del barco
		aux = activeGame.getShip(shipFiringId);
		newAmunition = aux.getAmmo() - 1;
		aux.setAmmo(newAmunition);
		
		//Comparo si el barco que disparo es rojo
		if(aux.getId() == 0){
			firingShipVO = new RedShipVO(aux.getId(), aux.getSpeed(), aux.getArmor(), aux.getAmmo(), aux.getTorpedo(), aux.getViewRange(), aux.getSize(), aux.getPosition(), aux.getOrientation());
		}else{
			firingShipVO = new BlueShipVO(aux.getId(), aux.getSpeed(), aux.getArmor(), aux.getAmmo(), aux.getTorpedo(), aux.getViewRange(), aux.getSize(), aux.getPosition(), aux.getOrientation());
		}
		
		
		Weapon weapon = new Weapon(Weapon.MACHINEGUN);
		fireActionToReturn = new FireAction(firingShipVO, weapon, exactFiringPoint, hit, firedShipVO);
		
		
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
	 * Entrada: id del barco que disparo
	 * Salida: fireAction
	 * Procedimiento:
	 * Calculo todos las coordenadas de la recta en la cual es la direccion del torpedo
	 * Si existe algun barco en esa recta entonces 
	 * 		ese barco es da�ado
	 * sino
	 * 		no hay barco da�ados 
	 */
	public FireAction fireTorpedo(int gameId, int shipFiringId){
		
		Game activeGame = this.findGame(gameId);
		FireAction fireActionToReturn = null;		
		Ship shipFiring = activeGame.getShip(shipFiringId);
		ArrayList<Coordinate> coordinates = this.straightCoordinates(shipFiring.getPosition(), shipFiring.getOrientation());
		Iterator<Coordinate> coordinatesIt = coordinates.iterator();
		boolean hitted = false;
		Coordinate coordinateHitted = null;
		ShipVO firedShipVO = null;
		ShipVO firingShipVO = null;
		
		
		while(coordinatesIt.hasNext() && hitted == false){
			Coordinate coordinateAux = coordinatesIt.next();	
			System.out.println("Coord: (" + coordinateAux.getX() + "," + coordinateAux.getY() + ")" );
			if(activeGame.getShipFiredId(coordinateAux) != -1){
				//acerto	
				hitted = true;			
				coordinateHitted = coordinateAux;
			}
		}
		
		if(hitted){
			Ship affectedShip = activeGame.getShip(activeGame.getShipFiredId(coordinateHitted));
			int newArmor = affectedShip.getArmor() - 1;
			affectedShip.setArmor(newArmor);						
			//Comapro si el barco da�ado es rojo
			if(affectedShip.getId() == 0){
				firedShipVO = new RedShipVO(affectedShip.getId(), affectedShip.getSpeed(), affectedShip.getArmor(), affectedShip.getAmmo(), affectedShip.getTorpedo(), affectedShip.getViewRange(), affectedShip.getSize(), affectedShip.getPosition(), affectedShip.getOrientation());
			}else{
				firedShipVO = new BlueShipVO(affectedShip.getId(), affectedShip.getSpeed(), affectedShip.getArmor(), affectedShip.getAmmo(), affectedShip.getTorpedo(), affectedShip.getViewRange(), affectedShip.getSize(), affectedShip.getPosition(), affectedShip.getOrientation());
			}			
		}
			
		//Calculo la nueva cantidad de balas del barco	
		int	newAmunition = shipFiring.getTorpedo() - 1;
		shipFiring.setTorpedo(newAmunition);		
			
		//Comparo si el barco que disparo es rojo
		if(shipFiring.getId() == 0){
			firingShipVO = new RedShipVO(shipFiring.getId(), shipFiring.getSpeed(), shipFiring.getArmor(), shipFiring.getAmmo(), shipFiring.getTorpedo(), shipFiring.getViewRange(), shipFiring.getSize(), shipFiring.getPosition(), shipFiring.getOrientation());
		}else{
			firingShipVO = new BlueShipVO(shipFiring.getId(), shipFiring.getSpeed(), shipFiring.getArmor(), shipFiring.getAmmo(), shipFiring.getTorpedo(), shipFiring.getViewRange(), shipFiring.getSize(), shipFiring.getPosition(), shipFiring.getOrientation());
		}			
			
		Weapon weapon = new Weapon(Weapon.TORPEDO);
		fireActionToReturn = new FireAction(firingShipVO, weapon, coordinateHitted, hitted, firedShipVO);
			
			
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
		gameToSave.setStatus(loading);
		
		data.saveGame(gameToSave);		
	}
	
	
	
	/*
	 * Entrada: nombre del usuario que quiere cargar una partida
	 * Salida: el id de la partida o -1 en caso de no encotrarlo
	 * Procedimiento:
	 * 	Si la lista esta vacia entonces
	 * 		Agrego al jugador a la lista de espera
	 * 		retorno -1
	 *  sino
	 *  	Mientras hayan usuario en la lista de espera y el id a retornar sea -1
	 *  		si tiene partidas cargadas entonces
	 *  			cargo la partida en las partidas activas
	 *  			le cambio el estado a loading
	 *  			borro al jugador de la lista de espera
	 *  			devuelvo el id de la partida
	 *  	Si no se encontraro partidas entonces
	 *  		agrego al jugador a la lista de espera	 *   
	 */		
	public int loadGame(String username){
		Iterator<String> loadingGamesPlayersIt = this.loadingGamePlayers.iterator();
		Data data = new Data();	
		int gameIdToReturn = -1;		
		Game gameLoaded = null;
		
		if(this.loadingGamePlayers.isEmpty()){
			//esta vacia entonces agrego al usuario a la cola de espera
			this.loadingGamePlayers.add(username);			
		}else{
			//hay usuarios en la lista de espera
			while(loadingGamesPlayersIt.hasNext() && gameIdToReturn == -1){
				String playerWaiting = loadingGamesPlayersIt.next();
				if(data.loadGame(username, playerWaiting) != null){
					//encontre
					gameLoaded = data.loadGame(username, playerWaiting);
					gameIdToReturn = gameLoaded.getId();
					gameLoaded.setStatus(loading);
					gameLoaded.getTurn().setTimeLeft(60);
					this.activeGames.add(gameLoaded);
					this.loadingGamePlayers.remove(this.loadingGamePlayers.indexOf(username));
				}else{
					if(data.loadGame(playerWaiting,username) != null){
						//encontre
						gameLoaded = data.loadGame(playerWaiting, username);
						gameIdToReturn = gameLoaded.getId();
						gameLoaded.setStatus(loading);
						gameLoaded.getTurn().setTimeLeft(60);
						this.activeGames.add(gameLoaded);
						this.loadingGamePlayers.remove(this.loadingGamePlayers.indexOf(username));
					}
				}
			}			
			//si no encontre una partida con nadie, cargo el username a la lista de espera
			if(gameIdToReturn == -1){
				this.loadingGamePlayers.add(username);
			}			
		}
		
		return gameIdToReturn;
	}
	
	
	/*
	 * Entrada: nombre del usuario que esta esperando por contrincante en una partida cargada
	 * Salida: id de la partida si se logueo el contrincante o -1
	 * Procedimiento:
	 * 		Si su nombre de usuario existe en alguna partida en activeGames y ademas su satus es loading entonces
	 * 			colocar el status en playing
	 * 			retornar el id de la partida
	 *		sino
	 *			retornar -1
	 */
	public int getGameIdLoading(String usernameWaiting){
		Iterator<Game> gameIt = this.activeGames.iterator();
		Game auxGame = null;
		int gameIdToReturn = -1;
		
		while(gameIt.hasNext() && gameIdToReturn == -1){
			auxGame = gameIt.next();
			if(auxGame.getRedPlayer().getUsername() == usernameWaiting || auxGame.getBluePlayer().getUsername() == usernameWaiting){
				gameIdToReturn = auxGame.getId();
				auxGame.setStatus(playing);
				auxGame.getTurn().setTimeLeft(60);
			}			
		}
		return gameIdToReturn;		
	}
	
	
	/*
	 * Entrada: el nombre del jugador
	 * Salida: -1 si no existe la partida o el numero de la partida
	 * Procedimiento:
	 * 		Si existe una partida en status loading con mi nombre en uno de los jugadores entonces
	 * 			cambio el estado a playing,
	 * 			devuelvo el gameId
	 * 		sino
	 * 			retorno -1 		
	 
	public int checkLoadedGameId(String username){
		int gameId = -1;
		Iterator<Game> activeGameIt = this.activeGames.iterator();
		Game aux = null;
		
		while(activeGameIt.hasNext() && gameId == -1){
			aux = activeGameIt.next();
			if(aux.getStatus() == this.loading){
				if(aux.getRedPlayer().getUsername() == username || aux.getBluePlayer().getUsername() == username){
					gameId = aux.getId();
					this.findGame(gameId).setStatus(playing);
				}
			}
		}
		return gameId;		
	}*/
	
	
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
		
		double offset = (int)this.calculateDistance(firingShip, clickedCoordinate);
		
		offset = Math.floor(offset / 2.5);
		int max_x = (int)(clickedCoordinate.getX() + offset);
		int max_y = (int)(clickedCoordinate.getY() + offset);
		
		int min_x = (int)(max_x - (offset * 2));
		int min_y = (int)(max_y - (offset * 2));
		
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
		gameToInsert.setStatus(playing);
		
		gameToInsert.setBluePlayer(bluePlayer);		
		gameToInsert.setStatus(playing);
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
	
	/*
	 * Metodo que devuelve un ArrayList con todas las coordenadas que pertenecen a la recta en la cual va el torpedo
	 */
	private ArrayList<Coordinate> straightCoordinates(Coordinate origin, Cardinal orientation){
		ArrayList<Coordinate> coordinatesToReturn = new ArrayList<Coordinate>();
		int x = origin.getX();
		int y = origin.getY();
		Coordinate coordinateToAdd = null;
		
		switch (orientation.getDirection()){
		//Norte
		case Cardinal.N:
			//el x queda fijo, y - 1
			while(y >= minAxisY){
				y--;
				coordinateToAdd = new Coordinate(x,y);
				coordinatesToReturn.add(coordinateToAdd);
			}
			break;
		//Este
		case Cardinal.E:
			//el y queda fijo, x + 1
			while(x <= maxAxisX){
				x++;
				coordinateToAdd = new Coordinate(x,y);
				coordinatesToReturn.add(coordinateToAdd);
			}
			break;
		//Sur
		case Cardinal.S:
			//el x queda fijo, y + 1
			while(y <= maxAxisY){
				y++;
				coordinateToAdd = new Coordinate(x,y);
				coordinatesToReturn.add(coordinateToAdd);
			}
			break;
		//Oeste
		case Cardinal.W:
			//el y queda fijo, x - 1
			while(x >= minAxisX){
				x--;
				coordinateToAdd = new Coordinate(x,y);
				coordinatesToReturn.add(coordinateToAdd);
			}
			break;
		//Noreste
		case Cardinal.NE:
			//x + 1, y - 1
			while(x <= maxAxisX && y >= maxAxisY){
				y--;
				x++;
				coordinateToAdd = new Coordinate(x,y);
				coordinatesToReturn.add(coordinateToAdd);
			}
			break;
		//Noroeste
		case Cardinal.NW:
			//x - 1 y - 1
			while(x >= minAxisX && y >= minAxisY){
				y--;
				x--;
				coordinateToAdd = new Coordinate(x,y);
				coordinatesToReturn.add(coordinateToAdd);
			}
			break;
		//Sureste
		case Cardinal.SE:
			//x +1 y +1
			while(x <= maxAxisX && y <= maxAxisY){
				y++;
				x++;
				coordinateToAdd = new Coordinate(x,y);
				coordinatesToReturn.add(coordinateToAdd);
			}
			break;
		//Suroeste
		case Cardinal.SW:
			//x - 1 y +1
			while(x >= minAxisX && y <= maxAxisY){
				y++;
				x--;
				coordinateToAdd = new Coordinate(x,y);
				coordinatesToReturn.add(coordinateToAdd);
			}
			break;
		}
		return coordinatesToReturn;
	}
	
	
	/*
	 * Entrada: los nombres de los dos usuarios que conforman la partida a buscar
	 * Salida: Id de la partida o -1 en caso de no existir la misma en la BD
	 * Procedimiento:
	 * 	Si existe la partida entonces
	 * 		cambia el id de la partida
	 * 		La guarda en el ArrayList
	 * 		Devuelve el id 
	 * 	sino devuelve -1
	 
	private int loadGame(String redPlayerUsername, String bluePlayerUsername){
		
		Data data = new Data();
		
		Game gameLoaded = data.loadGame(redPlayerUsername, bluePlayerUsername);
		if(gameLoaded != null){
			gameLoaded.setId(this.nextFreeIndex());
			this.activeGames.add(gameLoaded);
			return gameLoaded.getId();
		}else{
			return -1;
		}	
	}
	*/
	//OJO QUE HAY QUE BORRARLOOOOO
	public Iterator<Game> devolverITerator(){
		return this.activeGames.iterator();
	}
}