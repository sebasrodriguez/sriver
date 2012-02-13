package logica;

public class Cardinal {

	enum cardinal{
		N(0), NE(45), E(90), SE(135), S(180), SO(-135), O(-90), NO(-45);		
	
		private int direction;
	
		cardinal(int orientation){
			this.direction = orientation;
		}
		
		public int getDirection(){
			return this.direction;
		}
	}	
}
	

