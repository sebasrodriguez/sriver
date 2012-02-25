package components
{
	import common.*;
	
	/**
	 * ...
	 * @author pablo
	 */
	public class RedShip extends Ship
	{
		
		public function RedShip(c:Coordinate, d:Cardinal, s:int, size:int)
		{			
			super(c, d, s, size);
			this.setBitmap(Assets.REDSHIP_DATA);
			fixCenter();
		}
	
	}

}