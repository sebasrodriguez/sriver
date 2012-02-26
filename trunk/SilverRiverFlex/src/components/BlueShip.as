package components
{
	import common.*;
	
	/**
	 * ...
	 * @author pablo
	 */
	public class BlueShip extends Ship
	{
		
		public function BlueShip(id:int, c:Coordinate, d:Cardinal, s:int, size:int)
		{
			super(id, c, d, s, size);
			this.setBitmap(Assets.BLUESHIP_DATA);
			fixCenter();
		}
	
	}

}