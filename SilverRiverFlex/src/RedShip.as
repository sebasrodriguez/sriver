package  
{
	import Common.Constants;
	/**
	 * ...
	 * @author pablo
	 */
	public class RedShip extends Ship 
	{
		
		public function RedShip(x:Number = 0, y:Number = 0) 
		{
			super(x, y);
			this.setBitmap(Assets.REDSHIP_DATA);
			fixCenter();
		}
		
	}

}