package  
{
	/**
	 * ...
	 * @author pablo
	 */
	public class Bullet extends Projectile 
	{
		
		public function Bullet() 
		{			
			this.graphics.beginBitmapFill(Assets.BULLET1_DATA, null, false, false);
			this.graphics.drawRect(0, 0, Assets.BULLET1_DATA.rect.width, Assets.BULLET1_DATA.rect.height);
			this.graphics.endFill();
		}
		
	}

}