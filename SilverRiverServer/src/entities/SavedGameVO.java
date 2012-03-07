package entities;

public class SavedGameVO {
	
	private String redPlayerUsername;
	private String bluePlayerUsername;
	
	
	
	/*
	 * Constructor
	 */
	public SavedGameVO(String redPlayerUsername, String bluePlayerUsername){
		this.redPlayerUsername = redPlayerUsername;
		this.bluePlayerUsername = bluePlayerUsername;			
	}
	
	/*
	 * Metodos publicos
	 */
	
	/*
	 * Devuelve el nombre de usuario del jugador rojo
	 */
	public String getRedPlayerUsername(){
		return this.redPlayerUsername;
	}
	
	/*
	 * Devuelve el nombre del usuario del jugador azul
	 */
	public String getBluePlayerUsername(){
		return this.bluePlayerUsername;
	}
}
