package common
{
	
	/**
	 * ...
	 * @author sebas
	 */
	public class Cardinal
	{
		public static const N:int = -90;
		public static const S:int = 90;
		public static const E:int = 0;
		public static const W:int = -180;
		public static const NE:int = -45;
		public static const SE:int = 45;
		public static const SW:int = 135;
		public static const NW:int = -135;
		
		private var _cardinal:int;
		
		public function Cardinal(cardinal:int = 0)
		{
			_cardinal = cardinal;
		}
		
		public function get cardinal():int
		{
			return _cardinal;
		}
		
		public function set cardinal(value:int):void
		{
			_cardinal = value;
		}
	}

}