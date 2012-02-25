package components
{
	import common.*;
	/**
	 * ...
	 * @author pablo
	 */
	public class RedShip extends Ship 
	{
		
		public function RedShip(c:Coordinate, d:Cardinal) 
		{
			super(c, d);
			this.setBitmap(Assets.REDSHIP_DATA);
			fixCenter();
		}
		
	}

}