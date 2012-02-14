package  
{
	import flash.display.Sprite;
	import Common.Constants;
	/**
	 * ...
	 * @author pablo
	 */
	public class Map extends Sprite 
	{
		
		public function Map() 
		{			
			this.graphics.beginBitmapFill(Assets.MAP_DATA, null, false, false);
			this.graphics.drawRect(0, 0, Assets.MAP_DATA.rect.width, Assets.MAP_DATA.rect.height);
			this.graphics.endFill();
			this.width = this.width*Constants.GAME_SCALE;
			this.height = this.height*Constants.GAME_SCALE;
		}
		
	}

}