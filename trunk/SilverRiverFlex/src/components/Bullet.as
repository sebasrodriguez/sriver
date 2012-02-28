package components
{
	import common.*;
	/**
	 * ...
	 * @author pablo
	 */
	public class Bullet extends Projectile 
	{
		
		public function Bullet(c:Coordinate = null) 
		{						
			super(c);
			this.setBitmap(Assets.BULLET_DATA);
			fixCenter();
		}
		
	}

}