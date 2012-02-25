package entities;

public class Cardinal{
	
	public static final int N = -90;
	public static final int NE = -45;
	public static final int E = 0;
	public static final int SE = 45;
	public static final int S = 90;
	public static final int SW = 135;
	public static final int W = -180;
	public static final int NW = -135;			

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


