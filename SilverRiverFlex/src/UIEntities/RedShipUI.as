package UIEntities
{
	import flash.display.Sprite;
	
	/**
	 * ...
	 * @author sebas
	 */
	public class RedShipUI extends Sprite
	{		
		public function RedShipUI()
		{
			this.graphics.beginBitmapFill(Assets.BIGSHIP_DATA, null, false, false);
			this.graphics.drawRect(0, 0, Assets.BIGSHIP_DATA.rect.width, Assets.BIGSHIP_DATA.rect.height);
			this.graphics.endFill();
		}
	}
}