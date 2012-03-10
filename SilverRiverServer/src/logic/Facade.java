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
import logic.actions.EndGameAction;
import logic.actions.EndTurnAction;
import logic.actions.EnterPortAction;
import logic.actions.FireAction;
import logic.actions.MoveAction;
import logic.actions.RotateAction;
import logic.actions.SaveGameAction;
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
			if(activeGame.getTurn().getActivePlayer().getUsername().compareTo(activeGame.getRedPlayer().getUsername()) == 0){
				activeGame.getBlueActionQueue().add(moveActionToReturn);
			}else{
				activeGame.getRedActionQueue().add(moveActionToReturn);
			}
			activeGame.getTurn().consumeMovement();	
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
	
		
		if(activeGame != null){
			if(activeGame.getShipFiredId(exactFiringPoint) != -1){
				//acerto	
				hit = true;			
				affectedShip = activeGame.getShip(activeGame.getShipFiredId(exactFiringPoint));
				int newArmor = affectedShip.getArmor() - 1;
				affectedShip.setArmor(newArmor);
					
						
				//Comapro si el barco dañado es rojo
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
		}
		return fireActionToReturn;
	}
	
	/*
	 * Entrada: id del barco que disparo
	 * Salida: fireAction
	 * Procedimiento:
	 * Calculo todos las coordenadas de la recta en la cual es la direccion del torpedo
	 * Si existe algun barco en esa recta entonces 
	 * 		ese barco es dañado
	 * sino
	 * 		no hay barco dañados 
	 */
	public FireAction fireTorpedo(int gameId, int shipFiringId){
		
		Game activeGame = this.findGame(gameId);
		FireAction fireActionToReturn = null;		
		Ship shipFiring = null;
		ArrayList<Coordinate> coordinates = null;
		Iterator<Coordinate> coordinatesIt = null;
		boolean hitted = false;
		Coordinate coordinateHitted = null;
		ShipVO firedShipVO = null;
		ShipVO firingShipVO = null;
		
		if(activeGame != null){
			shipFiring =  activeGame.getShip(shipFiringId);
			coordinates = this.straightCoordinates(shipFiring.getPosition(), shipFiring.getOrientation());
			coordinatesIt = coordinates.iterator();
			
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
				//Comapro si el barco dañado es rojo
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
		}
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
			activeGame.getTurn().consumeMovement();	
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
		
		if(activeGame != null){
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
		}
		return endTurnActionToReturn;		
	}
	
	/*
	 * Entrada: Id de la partida
	 * Salida: void
	 * Procedimiento:
	 * Llama al metodo saveGame en la clase Game 
	 */
	public SaveGameAction saveGame(int gameId){
		
		SaveGameAction saveGameAction = null;
		Data data = new Data();
		Game gameToSave = this.findGame(gameId);
		
		if(gameToSave != null){
			gameToSave.setStatus(loading);		
			data.saveGame(gameToSave);
			saveGameAction = new SaveGameAction(gameId);
		
			//comparo si es redPlayer
			if(gameToSave.getTurn().getActivePlayer().getUsername().compareTo(gameToSave.getRedPlayer().getUsername()) == 0){
				gameToSave.insertActionBlueQueue(saveGameAction);
			}else{
				gameToSave.insertActionRedQueue(saveGameAction);
			}
		}		
		return saveGameAction;
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
		
		if(data.hasSavedGames(username)){		
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
						gameLoaded.setId(this.nextFreeIndex());
						gameIdToReturn = gameLoaded.getId();
						gameLoaded.setStatus(loading);
						gameLoaded.getTurn().setTimeLeft(60);
						this.activeGames.add(gameLoaded);
						this.removeFromLoadingGamePlayers(playerWaiting);
						//this.loadingGamePlayers.remove(this.loadingGamePlayers.indexOf(username));
					}else{
						if(data.loadGame(playerWaiting,username) != null){
							//encontre
							gameLoaded = data.loadGame(playerWaiting, username);
							gameLoaded.setId(this.nextFreeIndex());
							gameIdToReturn = gameLoaded.getId();
							gameLoaded.setStatus(loading);
							gameLoaded.getTurn().setTimeLeft(60);
							this.activeGames.add(gameLoaded);
							this.removeFromLoadingGamePlayers(playerWaiting);
							//this.loadingGamePlayers.remove(this.loadingGamePlayers.indexOf(username));
							//System.out.println("ACACACa: " + this.loadingGamePlayers.indexOf(username));
							//this.loadingGamePlayers.
						}
					}
				}			
				//si no encontre una partida con nadie, cargo el username a la lista de espera
				if(gameIdToReturn == -1){
					this.loadingGamePlayers.add(username);
				}			
			}
		}else{
			gameIdToReturn = -2;
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
			//if(auxGame.getRedPlayer().getUsername() == usernameWaiting || auxGame.getBluePlayer().getUsername() == usernameWaiting){			
			if(auxGame.getStatus() == this.loading){
				if(auxGame.getRedPlayer().getUsername().compareTo(usernameWaiting) == 0 || auxGame.getBluePlayer().getUsername().compareTo(usernameWaiting) == 0){		
					//encontre
					gameIdToReturn = auxGame.getId();
					auxGame.setStatus(playing);
					auxGame.getTurn().setTimeLeft(60);				
				}
			}
		}
		return gameIdToReturn;		
	}
	
	
		
	/*
	 * Entrada: Id del game a eliminar de la memoria}
	 * Salida: void
	 * Procedimiento:
	 * 	Busca el index del game por su id
	 * 	Elimina el Game en ese index del ArrayList
	 */
	public EndGameAction endGame(int gameId){	
		EndGameAction endGameActionToReturn = null;
		Game activeGame = this.findGame(gameId);
		
		if(activeGame != null){
			endGameActionToReturn = new EndGameAction(gameId); 
			//Comparo si es igual al jugador ROJO
			if(activeGame.getTurn().getActivePlayer().getUsername().compareTo(activeGame.getRedPlayer().getUsername()) == 0){
				activeGame.getBlueActionQueue().add(endGameActionToReturn);
			}else{
				activeGame.getRedActionQueue().add(endGameActionToReturn);
			}	
		}		
		return endGameActionToReturn;		
	}
	
	/*
	 *Entrada: String con el username del jugador que decidio hacer new game
	 *Salida: Devuelve el numero de la partida (gameId) o -1 en caso de quedar esperando un segundo jugador
	 *Procedimiento:
	 *	Si gameWithoutBluePlayer = null entonces crea el game y retorna -1 para que el UI sepa que no es una partida activa
	 *	sino llama al metodo addBluePlayerToGame con el jugador y retorna el id de la partida
	 *	retorna -2 en caso de que sea un usuario invalido
	 */
	public int newGame(String username){
		int gameIdToReturn = 0;
		if(this.gameWithoutBluePlayer == null){
			int nextIdToUse = this.nextFreeIndex();			
			Player redPlayer = new Player(username);			
			this.gameWithoutBluePlayer = new Game(nextIdToUse, redPlayer, null);
			gameIdToReturn =  -1;
		}else{
			if(this.gameWithoutBluePlayer.getRedPlayer().getUsername().compareTo(username) == 0){
				gameIdToReturn = -2;
			}else{
				gameIdToReturn = this.addBluePlayerToGame(username);
			}			
		}
		return gameIdToReturn;
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
		Action[] actionToReturn = null;
		boolean mustRemove = false;
		int i = 0;
		
		if(activeGame != null){
			//comparo si es redPlayer
			if(activeGame.getRedPlayer().getUsername().compareTo(username) == 0){
				actionToReturn = activeGame.redActionQueueMapToArray();
				activeGame.getRedActionQueue().clear();
			}else{
				actionToReturn = activeGame.blueActionQueueMapToArray();
				activeGame.getBlueActionQueue().clear();
			}
			
			
			while(i < actionToReturn.length && !mustRemove){
				if(actionToReturn[i].getActionType().compareTo("SaveGameAction") == 0 || actionToReturn[i].getActionType().compareTo("EndGameAction") == 0){
					mustRemove = true;
				}
				i++;
			}
			
			if(mustRemove){
				this.activeGames.remove(this.activeGames.indexOf(this.activeGames.get(gameId)));			
			}
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
		GameVO gameVOToReturn = null;
		
		if(aux != null){
			gameVOToReturn =  aux.mapToValueObject();
		}
		return gameVOToReturn;
	}
	
	
	/*
	 * Metodo que le suma un 50% a todos los atributos del barco
	 */
	public EnterPortAction enterPort1 (int gameId, int shipId){
		Game activeGame = this.findGame(gameId);
		Ship shipToRepair = null;
		EnterPortAction enterPortActionToReturn = null;
		ShipVO shipVO = null;
		int newAmmo = 0;
		int newArmor = 0;
		int newTorpedo = 0;
		
		if(activeGame != null){
			shipToRepair = activeGame.getShip(shipId);
			switch (shipToRepair.getId()){
				//barco rojo
				case 0:				
					newAmmo = shipToRepair.getAmmo() + 6;
					if(newAmmo > 12){
						newAmmo = 12;
					}
					newTorpedo = shipToRepair.getTorpedo() + 2;
					if(newTorpedo > 4){
						newTorpedo = 4;
					}
					newArmor = shipToRepair.getArmor() + 5;
					if(newArmor > 10){
						newArmor = 10;
					}
					shipVO = new RedShipVO(shipToRepair.getId(), shipToRepair.getSpeed(), newArmor, newAmmo, newTorpedo, shipToRepair.getViewRange(), shipToRepair.getSize(), shipToRepair.getPosition(), shipToRepair.getOrientation());				
				break;
				//barco azul 1
				case 1:				
					newAmmo = shipToRepair.getAmmo() + 6;
					if(newAmmo > 12){
						newAmmo = 12;
					}
					newTorpedo = shipToRepair.getTorpedo() + 2;
					if(newTorpedo > 4){
						newTorpedo = 4;
					}
					newArmor = shipToRepair.getArmor() + 3;
					if(newArmor > 5){
						newArmor = 5;
					}
					shipVO = new BlueShipVO(shipToRepair.getId(), shipToRepair.getSpeed(), newArmor, newAmmo, newTorpedo, shipToRepair.getViewRange(), shipToRepair.getSize(), shipToRepair.getPosition(), shipToRepair.getOrientation());	
				break;
				//barco azul 2
				case 2:
					newAmmo = shipToRepair.getAmmo() + 3;
					if(newAmmo > 6){
						newAmmo = 6;
					}
					newTorpedo = shipToRepair.getTorpedo() + 1;
					if(newTorpedo > 2){
						newTorpedo = 2;
					}
					newArmor = shipToRepair.getArmor() + 3;
					if(newArmor > 5){
						newArmor = 5;
					}
					shipVO = new BlueShipVO(shipToRepair.getId(), shipToRepair.getSpeed(), newArmor, newAmmo, newTorpedo, shipToRepair.getViewRange(), shipToRepair.getSize(), shipToRepair.getPosition(), shipToRepair.getOrientation());	
				break;
				//barco azul 3
				case 3:					
					newAmmo = shipToRepair.getAmmo() + 3;
					if(newAmmo > 6){
						newAmmo = 6;
					}
					newTorpedo = shipToRepair.getTorpedo() + 1;
					if(newTorpedo > 2){
						newTorpedo = 2;
					}
					newArmor = shipToRepair.getArmor() + 3;
					if(newArmor > 5){
						newArmor = 5;
					}
					shipVO = new BlueShipVO(shipToRepair.getId(), shipToRepair.getSpeed(), newArmor, newAmmo, newTorpedo, shipToRepair.getViewRange(), shipToRepair.getSize(), shipToRepair.getPosition(), shipToRepair.getOrientation());
				break;
			}		
			
			enterPortActionToReturn = new EnterPortAction(shipVO, 1);
		}
		return enterPortActionToReturn;
	}
	
	/*
	 * Metodo que le suma 100% solo a un atributo
	 */
	public EnterPortAction enterPort2 (int gameId, int shipId, String attribute){
		
		Game activeGame = this.findGame(gameId);
		Ship shipToRepair = null;
		EnterPortAction enterPortActionToReturn = null;
		ShipVO shipVO = null;
		int newAmmo = 0;
		int newArmor = 0;
		int newTorpedo = 0;
		
		if(activeGame != null){
			shipToRepair = activeGame.getShip(shipId);
			switch (shipToRepair.getId()){
			//barco rojo
			case 0:			
				if(attribute.compareTo("armor") == 0){
					newAmmo = shipToRepair.getAmmo();
					newArmor = 10;
					newTorpedo = shipToRepair.getTorpedo();
				}else{
					if(attribute.compareTo("ammo") == 0){
						newAmmo = 12;
						newArmor = shipToRepair.getArmor();
						newTorpedo = shipToRepair.getTorpedo();
						
					}else{
						if(attribute.compareTo("torpedo") == 0){
							newAmmo = shipToRepair.getAmmo();
							newArmor = shipToRepair.getArmor();
							newTorpedo = 4;						
						}
					}
				}			
				shipVO = new RedShipVO(shipToRepair.getId(), shipToRepair.getSpeed(), newArmor, newAmmo, newTorpedo, shipToRepair.getViewRange(), shipToRepair.getSize(), shipToRepair.getPosition(), shipToRepair.getOrientation());				
			break;
			//barco azul 1
			case 1:				
				if(attribute.compareTo("ARMOR") == 0){
					newAmmo = shipToRepair.getAmmo();
					newArmor = 5;
					newTorpedo = shipToRepair.getTorpedo();
				}else{
					if(attribute.compareTo("AMMO") == 0){
						newAmmo = 12;
						newArmor = shipToRepair.getArmor();
						newTorpedo = shipToRepair.getTorpedo();
						
					}else{
						if(attribute.compareTo("TORPEDO") == 0){
							newAmmo = shipToRepair.getAmmo();
							newArmor = shipToRepair.getArmor();
							newTorpedo = 4;						
						}
					}
				}			
				shipVO = new RedShipVO(shipToRepair.getId(), shipToRepair.getSpeed(), newArmor, newAmmo, newTorpedo, shipToRepair.getViewRange(), shipToRepair.getSize(), shipToRepair.getPosition(), shipToRepair.getOrientation());	
			break;
			//barco azul 2
			case 2:		
				if(attribute.compareTo("ARMOR") == 0){
					newAmmo = shipToRepair.getAmmo();
					newArmor = 5;
					newTorpedo = shipToRepair.getTorpedo();
				}else{
					if(attribute.compareTo("AMMO") == 0){
						newAmmo = 6;
						newArmor = shipToRepair.getArmor();
						newTorpedo = shipToRepair.getTorpedo();
						
					}else{
						if(attribute.compareTo("TORPEDO") == 0){
							newAmmo = shipToRepair.getAmmo();
							newArmor = shipToRepair.getArmor();
							newTorpedo = 2;						
						}
					}
				}			
				shipVO = new RedShipVO(shipToRepair.getId(), shipToRepair.getSpeed(), newArmor, newAmmo, newTorpedo, shipToRepair.getViewRange(), shipToRepair.getSize(), shipToRepair.getPosition(), shipToRepair.getOrientation());		
			break;
			//barco azul 3
			case 3:					
				if(attribute.compareTo("ARMOR") == 0){
					newAmmo = shipToRepair.getAmmo();
					newArmor = 5;
					newTorpedo = shipToRepair.getTorpedo();
				}else{
					if(attribute.compareTo("AMMO") == 0){
						newAmmo = 6;
						newArmor = shipToRepair.getArmor();
						newTorpedo = shipToRepair.getTorpedo();
						
					}else{
						if(attribute.compareTo("TORPEDO") == 0){
							newAmmo = shipToRepair.getAmmo();
							newArmor = shipToRepair.getArmor();
							newTorpedo = 2;						
						}
					}
				}			
				shipVO = new RedShipVO(shipToRepair.getId(), shipToRepair.getSpeed(), newArmor, newAmmo, newTorpedo, shipToRepair.getViewRange(), shipToRepair.getSize(), shipToRepair.getPosition(), shipToRepair.getOrientation());
			break;
			}	
			
			enterPortActionToReturn = new EnterPortAction(shipVO, 2);
		}
		return enterPortActionToReturn;
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
		Game aux = null;
		boolean found = false;
		
		while(gameIterator.hasNext() && !found){
			aux= gameIterator.next();
			if(aux.getId() == gameId ){
				found = true;				
			}			
		}
		if(found){
			gameToReturn = aux;
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
		//gameToInsert.setStatus(playing);
		
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
	 * Remueve al jugador de la cola de espera
	 */
	private void removeFromLoadingGamePlayers(String username){
		int indexToRemove = 0;
		boolean found = false;
		
		while(indexToRemove < this.loadingGamePlayers.size() && !found){
			if(this.loadingGamePlayers.get(indexToRemove) != username){
				indexToRemove++;
			}else{
				found = true;
			}
		}
		this.loadingGamePlayers.remove(indexToRemove);		
	}
	
	//OJO QUE HAY QUE BORRARLOOOOO
	public Iterator<Game> devolverITerator(){
		return this.activeGames.iterator();
	}
	
	//OJO QUE HAY QUE BORRARLOOOOOOO
	public Iterator<String> devolverItString(){
		return this.loadingGamePlayers.iterator();
	}
}