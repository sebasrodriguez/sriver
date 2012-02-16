package  
{
	import Common.Constants;
	/**
	 * ...
	 * @author pablo
	 */
	public class BlueShip extends Ship 
	{
		
		public function BlueShip(x:Number = 0, y:Number = 0) 
		{
			super(x,y);
			this.setBitmap(Assets.BLUESHIP_DATA);
			fixCenter();
		}
		
	}

}