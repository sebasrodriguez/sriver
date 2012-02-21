
public class FacadePublic {
	public String test = "hola 1 2 3";
	
	
	public FacadePublic(){
		System.out.println("Hola new Facade");
	}
	
	
	public void setTest(String value){
		this.test = value;
		System.out.println("Se cambio el valor para: " + value);
	}
	public String getTest(){
		System.out.println("Se pidio el valor: " + this.test);
		return this.test;
	}
	
	/*
	 * retorna una nueva partida
	 */
	public String newGame(){
		
		
		String result = "{" +
				"'player':'1'" +
				"'ships':{" +
					"'redShip':[{'x':6,'y':6}]" +
					"" +
					"}" +
				"}";
		
		return result;
	}
	
	/*
	 * retorna nuevas acciones del contrincante
	 */
	public String synchronize(){
		String result = "{" +
				"" +
				"" +
				"}";
		return result;
	}
}
