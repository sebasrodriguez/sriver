package components
{
	import common.*;
	
	/**
	 * ...
	 * @author pablo
	 */
	public class BlueShip extends Ship
	{
		
		public function BlueShip(id:int, c:Coordinate, d:Cardinal, s:int, size:int, armor:int, ammo:int, torpedoes:int, viewRange:int)
		{
			super(id, c, d, s, size, armor, ammo, torpedoes, viewRange);
			this.setBitmap(Assets.BLUESHIP_DATA);
			fixCenter();
		}
	
	}

}