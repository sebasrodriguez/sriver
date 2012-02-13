package{
	import flash.display.*;
	
	public final class Assets{
		// REDSHIP
        [Embed (source="../assets/brownplane.png")]
        public static const PLANE_BMP : Class;
        public static const PLANE_DATA : BitmapData = (new PLANE_BMP() as Bitmap).bitmapData;
		
		// BULLET
        [Embed (source="../assets/bullet1.png")]
        public static const BULLET1_BMP : Class;
        public static const BULLET1_DATA : BitmapData = (new BULLET1_BMP() as Bitmap).bitmapData;
	}
}