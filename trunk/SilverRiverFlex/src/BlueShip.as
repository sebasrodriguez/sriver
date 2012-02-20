package  
{
	import Common.Coordinate;
	/**
	 * ...
	 * @author pablo
	 */
	public class BlueShip extends Ship 
	{
		
		public function BlueShip(c:Coordinate) 
		{
			super(c);
			this.setBitmap(Assets.BLUESHIP_DATA);
			fixCenter();
		}
		
	}

}