package components
{
	import common.*;
	
	/**
	 * ...
	 * @author pablo
	 */
	public class BlueShip extends Ship
	{
		
		public function BlueShip(c:Coordinate, d:Cardinal, s:int, size:int)
		{
			super(c, d, s, size);
			this.setBitmap(Assets.BLUESHIP_DATA);
			fixCenter();
		}
	
	}

}