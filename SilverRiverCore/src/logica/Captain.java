package logica;


/*
 * Como la clase Player no me deja crearla porque ya existe una similar, se renombro a Captain
 */
public class Captain {
	
	private int id;
	private String username;
	
	Captain(){
		
	}
	
	Captain(int id, String username){
		this.id = id;
		this.username = username;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public void setUsername(String username){
		this.username = username;
	}
	
	public int getId(){
		return this.id;
	}
	
	public String getUsername(){
		return this.username;
	}


}
