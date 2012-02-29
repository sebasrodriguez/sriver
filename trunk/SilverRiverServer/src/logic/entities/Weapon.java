package logic.entities;

public class Weapon{
	
	public static final int TORPEDO = 0;
	public static final int MACHINEGUN = 1;
	public static final int AIRCRAFT = 2;
	public static final int ANTIAIRCRAFT = 3;	
	
	private int weapon;

	public Weapon(int weapon){
		this.weapon = weapon;
	}
	
	public int getWeapon(){
		return this.weapon;
	}	
}
