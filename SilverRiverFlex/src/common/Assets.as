package common
{
	import flash.display.*;
	
	public final class Assets
	{
		// MAP
		[Embed(source="../../assets/map.jpg")]
		public static const MAP_BMP:Class;
		public static const MAP_DATA:BitmapData = (new MAP_BMP() as Bitmap).bitmapData;
		// REDSHIP
		[Embed(source="../../assets/redship.png")]
		public static const REDSHIP_BMP:Class;
		public static const REDSHIP_DATA:BitmapData = (new REDSHIP_BMP() as Bitmap).bitmapData;
		
		// BLUESHIP
		[Embed(source="../../assets/blueship.png")]
		public static const BLUESHIP_BMP:Class;
		public static const BLUESHIP_DATA:BitmapData = (new BLUESHIP_BMP() as Bitmap).bitmapData;
		
		// BULLET
		[Embed (source="../../assets/bullet.png")]
		public static const BULLET_BMP : Class;
		public static const BULLET_DATA : BitmapData = (new BULLET_BMP() as Bitmap).bitmapData;
		
	}
}