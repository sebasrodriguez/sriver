package logica;

import java.util.ArrayList;


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
	 * Entrada: Id de la partida, barco que se mueve, destino
	 * Salida: MoveAction
	 * Procedimiento: 
	 * Al barco que se mueve le actualiza el destino en la partida
	 * Coloca en la Queue del otro jugador esta accion
	 */
	public MoveAction move(int gameId, Ship shipMoving, Coordenate destination){
		
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
	 */
	public FireAction fire(int gameId, Ship sihpFiring, Coordenate firingPoint){
		
	}
	
	/*
	 * Entrada: Id de la partidam, barco que rota y cardianl de destino
	 * Salida: RotateAction
	 * Procedimiento:
	 * Actualiza la posicion a la cual apunta el barco
	 * devuelve el rotateAction 
	 */
	public RotateAction rotate(int gameId, ship shipRotating, Cardinal destination){
		
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
