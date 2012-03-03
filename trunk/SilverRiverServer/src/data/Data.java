package data;


import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import logic.game.Game;

public class Data {
	
	public Data(){
		
	}
	
	public Game loadGame(String redPlayerUsername, String bluePlayerUsername){
		
		Object deSerializedGame = null;
		
		try {
			Properties prop = new Properties();
			String archiveName = "src/logic/config.property";
			prop.load(new FileInputStream(archiveName));
			
			String username = prop.getProperty("dbUsername");
			String password = prop.getProperty("dbPassword");
			String url = prop.getProperty("dbUrl");
			String driver = prop.getProperty("dbDriver");
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, username, password);
			Queries query = new Queries();
			
			String loadGame = query.loadGame();
			PreparedStatement pepe = con.prepareStatement(loadGame);
			pepe.setString(1, redPlayerUsername);
			pepe.setString(2, bluePlayerUsername);
			
			ResultSet rs = pepe.executeQuery();
			
			if(rs.next()){			
				byte[] buf = rs.getBytes(4);
				ObjectInputStream objectIn = null;
				
				if (buf != null){
					objectIn = new ObjectInputStream(new ByteArrayInputStream(buf));
				 
				    deSerializedGame = objectIn.readObject();
				}				 
		    rs.close();			    
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (Game) deSerializedGame;
	}
	
	
	public void saveGame(Game gameToSave){	
		
		 try {
			Properties prop = new Properties();
			String archiveName = "src/logic/config.property";
			prop.load(new FileInputStream(archiveName));
			
			String username = prop.getProperty("dbUsername");
			String password = prop.getProperty("dbPassword");
			String url = prop.getProperty("dbUrl");
			String driver = prop.getProperty("dbDriver");
			Class.forName(driver);
			
			Connection con = DriverManager.getConnection(url, username, password);
			Queries query = new Queries();
			String saveGame = query.saveGame();
			PreparedStatement pstmt = con.prepareStatement(saveGame);
			pstmt.setString(1, gameToSave.getRedPlayer().getUsername());
			pstmt.setString(2, gameToSave.getBluePlayer().getUsername());		
			pstmt.setObject(3, gameToSave);
			
		    pstmt.executeUpdate();					   
			pstmt.close();
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    
	    
	}
	
}
