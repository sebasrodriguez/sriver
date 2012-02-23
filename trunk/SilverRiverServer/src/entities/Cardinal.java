package entities;

public class Cardinal{
	
	public static final int N = 0;
	public static final int NE = 45;
	public static final int E = 90;
	public static final int SE = 135;
	public static final int S = 180;
	public static final int SW = -135;
	public static final int W = -90;
	public static final int NW = -45;			

	private int direction;
	
	public Cardinal(){
		
	}

	public Cardinal(int orientation){
		this.direction = orientation;
	}
	
	public int getDirection(){
		return this.direction;
	}	
}	


