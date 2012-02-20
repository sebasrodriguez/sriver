package logica;

import java.util.ArrayList;
import java.util.Iterator;


/*
 * VA A SER SINGLETON?
 */
public class Facade {

	private ArrayList<Board> activeGames;
	
	
	/*
	 * Constructores
	 */
	Facade(){
		
	}
	
	Facade(ArrayList<Board> activeGames){
		this.activeGames = activeGames;
	}
	
	/*
	 * Getters
	 */
	public ArrayList<Board> getActiveGames(){
		return this.activeGames;
	}
	
	/*
	 * Setters
	 */
	public void setActiveGames(ArrayList<Board> activeGames){
		this.activeGames = activeGames;
	}
	
	/*
	 * Acciones
	 */	
	
	/*
	 * Metodo privado el cual encuentra la partida a la cual hago referencia en el array de partidas activas del Facade
	 */
	private Board findBoard(int gameId){
		Iterator<Board> boardIterator = this.activeGames.iterator();
		Board boardToReturn = null;
		boolean found = false;
		
		while(boardIterator.hasNext() && !found){
			boardToReturn = boardIterator.next();
			if(boardToReturn.getId() == gameId ){
				found = true;				
			}			
		}
		return boardToReturn;
	}
	
	/*
	 * Entrada: Id de la partida, Id del barco que se mueve, destino
	 * Salida: MoveAction
	 * Procedimiento: 
	 * Al barco que se mueve le actualiza el destino en la partida
	 * Coloca en la Queue del otro jugador esta accion
	 * Retorna MoveAction
	 */
	
	public MoveAction move(int gameId, int shipId, Coordenate destination){
		MoveAction moveActionToReturn = null;
		Board activeBoard = findBoard(gameId);
		
		if(activeBoard != null){
			activeBoard.getShip(shipId).setPosition(destination);
			moveActionToReturn = new MoveAction(activeBoard.getShip(shipId),destination);
			
			
			//Comparo si es igual al jugador ROJO
			if(activeBoard.getShift().getActivePlayer().equals(activeBoard.getRedPlayer())){
				activeBoard.getBlueActionQueue().add(moveActionToReturn);
			}else{
				activeBoard.getRedActionQueue().add(moveActionToReturn);
			}
		}		
		return moveActionToReturn;	
	}
	
		
	/*
	 public MoveAction move(int gameId, Ship shipMoving, Coordenate destination){
	 
		
		MoveAction moveAction = null;		
		Iterator<Board> boardIterator = this.activeGames.iterator();
		Board board = boardIterator.next();
		
		boolean found = false;
		
		while(boardIterator.hasNext() && !found){
			if(board.getId() == gameId){
				found = true;			
			}	
			board = boardIterator.next();
		}
		
		shipMoving.setPosition(destination);
		board.getShips(shipMoving.getId()).setPosition(destination);
		
		moveAction = new MoveAction(shipMoving, destination);
		return moveAction;		
	}
	*/
	
	
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
	public FireAction fire(int gameId, int shipFiringId, Coordenate firingPoint, Weapon weaponType){
		
		//NO SE COMO CALCULAR EL PUNTO EXACTO DE DISPARO!!
		//OJO, CAMBIAR EL FIRINGPOINT POR EL CALCULO DEL PUNTO EXACTO!!! INCLUSO EN EL FIREACTION A RETORNAR
		Board activeBoard = this.findBoard(gameId);
		FireAction fireActionToReturn = null;
		int newAmunition = 0;
		boolean hit = false;
		Ship affectedShip = null; 
		
		if(activeBoard.getShipFiredId(firingPoint) != -1){
			//acerto	
			hit = true;
			int newArmor = activeBoard.getShip(activeBoard.getShipFiredId(firingPoint)).getArmor() - 20;
			affectedShip = activeBoard.getShip(activeBoard.getShipFiredId(firingPoint));
			affectedShip.setArmor(newArmor);	
			if(affectedShip.getArmor() <= 0){
				activeBoard.destoyedShip(affectedShip.getId());
			}			
		}
		
		//comparo si es torpedo
		if(weaponType.equals("TORPEDO")){
			newAmunition = activeBoard.getShip(shipFiringId).getTorpedo() - 1;
			activeBoard.getShip(shipFiringId).setTorpedo(newAmunition);
			
		}else{
			newAmunition = activeBoard.getShip(shipFiringId).getAmmo() - 1;
			activeBoard.getShip(shipFiringId).setAmmo(newAmunition);
		}
		
		fireActionToReturn = new FireAction(activeBoard.getShip(shipFiringId), weaponType, firingPoint, hit, affectedShip);
		return fireActionToReturn;
	}
	
	
	/*
	 * Entrada: Id de la partida, barco que dispara, punto la que dispara
	 * Salida: FireAction
	 * Procedimiento:
	 * Calcula el punto exacto del disparo
	 * Verifica que haya o no acertado
	 * Si acerto
	 * 	actualiza los valores del barco dañado
	 * actualiza los valores del barco que disparo
	 * devuelve el fireAction	 	
	 
	public FireAction fire(int gameId, Ship shipFiring, Coordenate firingPoint){
		
		
		
	}
	*/
	
	/*
	 * Entrada: Id de la partida, id del barco que rota y cardinal de destino
	 * Salida: RotateAction
	 * Procedimiento:
	 * Actualiza la posicion a la cual apunta el barco
	 * devuelve el rotateAction 
	 */
	public RotateAction rotate(int gameId, int shipId, Cardinal destination){
		
		RotateAction rotateActionToReturn = null;
		Board activeBoard = this.findBoard(gameId);
		
		if(activeBoard != null){
			activeBoard.getShip(shipId).setOrientation(destination);
			
			rotateActionToReturn = new RotateAction(activeBoard.getShip(shipId), destination);
			
			//Comparo si es igual al jugador ROJO
			if(activeBoard.getShift().getActivePlayer().equals(activeBoard.getRedPlayer())){
				activeBoard.getBlueActionQueue().add(rotateActionToReturn);
			}else{
				activeBoard.getRedActionQueue().add(rotateActionToReturn);
			}			
		}
		return rotateActionToReturn;		
	}
	
	
	/*
	 * Entrada: Id de la partidam, barco que rota y cardianl de destino
	 * Salida: RotateAction
	 * Procedimiento:
	 * Actualiza la posicion a la cual apunta el barco
	 * devuelve el rotateAction 
	 */
	/*
	public RotateAction rotate(int gameId, ship shipRotating, Cardinal destination){	
	
	}
	*/
	
	
	/*
	 * Entrada: Id de la partida
	 * Salida: EndTurnAction
	 * Procedimiento: 
	 * Cambia al jugador activo
	 * carga la cantidad de movimientos disponibles 
	 * devuelve endTurnAction
	 */
	public EndTurnAction endTurn(int gameId){
		
	}
	
	/*
	 * Entrada: Id de la partida
	 * Salida: void
	 * Procedimiento:
	 * Llama al metodo saveGame en la clase Board 
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
		
	}
}
