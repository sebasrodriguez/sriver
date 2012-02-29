package data;

public class Queries {

	public String loadGame(){
		
		return "SELECT * FROM Games WHERE redPlayerUsername = ? AND bluePlayerUsername = ?";		
		
	}
	
	public String saveGame(){
		
		return "INSERT INTO Games (redPlayerUsername,bluePlayerUsername,game) VALUES(?,?,?)";
	}
	
	
}
