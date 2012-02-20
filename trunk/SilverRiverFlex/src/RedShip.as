package  
{
	import Common.Coordinate;
	/**
	 * ...
	 * @author pablo
	 */
	public class RedShip extends Ship 
	{
		
		public function RedShip(c:Coordinate) 
		{
			super(c);
			this.setBitmap(Assets.REDSHIP_DATA);
			fixCenter();
		}
		
	}

}