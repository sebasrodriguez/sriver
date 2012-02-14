package{
	import flash.display.*;
	
	public final class Assets{
		// MAP
        [Embed (source="../assets/map.png")]
        public static const MAP_BMP : Class;
        public static const MAP_DATA : BitmapData = (new MAP_BMP() as Bitmap).bitmapData;
		// REDSHIP
        [Embed (source="../assets/redship.png")]
        public static const REDSHIP_BMP : Class;
        public static const REDSHIP_DATA : BitmapData = (new REDSHIP_BMP() as Bitmap).bitmapData;
		
		// BLUESHIP
        [Embed (source="../assets/blueship.png")]
        public static const BLUESHIP_BMP : Class;
        public static const BLUESHIP_DATA : BitmapData = (new BLUESHIP_BMP() as Bitmap).bitmapData;
		
		// BULLET
        /*[Embed (source="../assets/bullet1.png")]
        public static const BULLET_BMP : Class;
        public static const BULLET_DATA : BitmapData = (new BULLET_BMP() as Bitmap).bitmapData;*/
		
		// BIG SHIP
		[Embed (source = "../assets/big_ship.png")]
		public static const BIGSHIP_BMP : Class;
		public static const BIGSHIP_DATA : BitmapData = (new BIGSHIP_BMP() as Bitmap).bitmapData;
	}
}