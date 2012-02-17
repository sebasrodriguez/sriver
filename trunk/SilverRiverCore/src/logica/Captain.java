package logica;


/*
 * Como Player no me dejaba crearla, se renombro a Captain 
 */
public class Captain {
	
	private int id;
	private String username;
	
	/*
	 * Constructores
	 */
	
	Captain(){
		
	}
	
	Captain(int id, String username){
		this.id = id;
		this.username = username;
	}
	
	/*
	 * Setters
	 */
	public void setId(int id){
		this.id = id;
	}
	
	public void setUsername(String username){
		this.username = username;
	}
	
	/*
	 * Getters
	 */
	public int getId(){
		return this.id;
	}
	
	public String getUsername(){
		return this.username;
	}


}
