package entities;


public abstract class ShipVO {
	
	private int id;
	private int speed;
	private int armor;
	private int ammo;
	private int torpedo;
	private int viewRange;
	private int size;
	private Coordinate position;	
	private Cardinal orientation;	
		
	

	public ShipVO(int id, int speed, int armor, int ammo, int torpedo, int viewRange, int size, Coordinate position, Cardinal orientation){
		this.id = id;
		this.speed = speed;
		this.armor = armor;
		this.ammo = ammo;
		this.torpedo = torpedo;
		this.viewRange = viewRange;
		this.size = size;
		this.position = position;
		this.orientation = orientation;
	}
	
	
	
	public int getId(){
		return this.id;		
	}
	
	public int getSpeed(){
		return this.speed;
	}
	
	public int getArmor(){
		return this.armor;
	}
	
	public int getAmmo(){
		return this.ammo;
	}
	
	public int getTorpedo(){
		return this.torpedo;
	}
	
	public int getViewRange(){
		return this.viewRange;
	}
	
	public int getSize(){
		return this.size;
	}
	
	public Coordinate getPosition(){
		return this.position;
	}
	
	public Cardinal getOrientation(){
		return this.orientation;
	}

}
