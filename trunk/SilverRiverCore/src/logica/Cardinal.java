package logica;

/*
 * Enumerado para tener la direccion a la cual esta apuntando la proa el barco.
 */
public enum Cardinal{
	N(0), NE(45), E(90), SE(135), S(180), SW(-135), W(-90), NW(-45);		

	private final int direction;

	Cardinal(int orientation){
		this.direction = orientation;
	}
	
	public int getDirection(){
		return this.direction;
	}	
}	


