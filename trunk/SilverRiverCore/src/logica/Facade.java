package logica;

import java.util.ArrayList;


/*
 * VA A SER SINGLETON?
 */
public class Facade {

	private ArrayList<Board> activeGames;
	
	
	//NO HAGO EL CONSTRUCTOR HASTA DEFINIR SI QUEDA COMO SINGLETON
	
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
	
	//FALTAAAAAA
}
