
public class FacadePublic {
	public String test = "hola 1 2 3";
	
	public void setTest(String value){
		this.test = value;
		System.out.println("Se cambio el valor para: " + value);
	}
	public String getTest(){
		System.out.println("Se pidio el valor: " + this.test);
		return this.test;
	}
}
