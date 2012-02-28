package components
{
	/**
	 * ...
	 * @author pablo
	 */
	import common.*;
	public class Torpedo extends Projectile 
	{
		
		public function Torpedo(c:Coordinate = null) 
		{
			super(c);
			this.setBitmap(Assets.TORPEDO_DATA);
			fixCenter();
			this.alpha = 0.6;
		}
		
	}

}