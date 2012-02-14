package  
{
	import Common.Constants;
	/**
	 * ...
	 * @author pablo
	 */
	public class BlueShip extends Ship 
	{
		
		public function BlueShip() 
		{
			
			this.graphics.beginBitmapFill(Assets.BLUESHIP_DATA, null, false, false);
			this.graphics.drawRect(0, 0, Assets.BLUESHIP_DATA.rect.width, Assets.BLUESHIP_DATA.rect.height);
			this.graphics.endFill();
			this.width = this.width * Constants.GAME_SCALE;
			this.height = this.height * Constants.GAME_SCALE;
		}
		
	}

}