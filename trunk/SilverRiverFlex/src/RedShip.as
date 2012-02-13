package  
{
	/**
	 * ...
	 * @author pablo
	 */
	public class RedShip extends Ship 
	{
		
		public function RedShip() 
		{
			this.graphics.beginBitmapFill(Assets.PLANE_DATA, null, false, false);
			this.graphics.drawRect(0, 0, Assets.PLANE_DATA.rect.width, Assets.PLANE_DATA.rect.height);
			this.graphics.endFill();
		}
		
	}

}