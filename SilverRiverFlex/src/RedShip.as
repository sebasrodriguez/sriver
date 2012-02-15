package  
{
	import Common.Constants;
	/**
	 * ...
	 * @author pablo
	 */
	public class RedShip extends Ship 
	{
		
		public function RedShip() 
		{
			this.graphics.beginBitmapFill(Assets.REDSHIP_DATA, null, false, false);
			this.graphics.drawRect(0, 0, Assets.REDSHIP_DATA.rect.width, Assets.REDSHIP_DATA.rect.height);
			this.graphics.endFill();
			this.width = this.width*Constants.GAME_SCALE*1/2;
			this.height = this.height * Constants.GAME_SCALE*1/2;
		}
		
	}

}