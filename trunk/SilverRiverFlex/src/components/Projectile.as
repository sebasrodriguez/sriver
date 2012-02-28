package components
{
	import common.Coordinate;
	import flash.display.*;
	
	public class Projectile extends GameUIComponent
	{
		public static const WEAPON_TYPE_BULLET:int = 0;
		public static const WEAPON_TYPE_TORPEDO:int = 1;
		
		public function Projectile(c:Coordinate = null)
		{
			super(c);
		}
	}
}