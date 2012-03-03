package entities;

import java.io.Serializable;

public class Weapon implements Serializable{
	
	public static final int TORPEDO = 0;
	public static final int MACHINEGUN = 1;
	public static final int AIRCRAFT = 2;
	public static final int ANTIAIRCRAFT = 3;	
	
	private int weapon;

	/*
	 * Constructor
	 */
	public Weapon(int weapon){
		this.weapon = weapon;
	}
	
	/*
	 * Devuelve el tipo del arma
	 */
	public int getWeapon(){
		return this.weapon;
	}	
}
