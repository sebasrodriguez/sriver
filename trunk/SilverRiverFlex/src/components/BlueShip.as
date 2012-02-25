package components
{
	import common.*;
	/**
	 * ...
	 * @author pablo
	 */
	public class BlueShip extends Ship 
	{
		
		public function BlueShip(c:Coordinate, d:Cardinal) 
		{
			super(c, d);
			this.setBitmap(Assets.BLUESHIP_DATA);
			fixCenter();
		}
		
	}

}