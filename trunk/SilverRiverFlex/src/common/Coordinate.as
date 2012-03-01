package common 
{
	/**
	 * ...
	 * @author sebas
	 */
	public class Coordinate
	{
		public static const POINT_SIZE:Number = 50;
		
		private var _x: Number;
		private var _y: Number;
		private var _c:Number;
		private var _r:Number;
		
		public function Coordinate(r:Number = 0, c:Number = 0) {
			_r = r;
			_c = c;
			_x = POINT_SIZE * c + POINT_SIZE / 2;
			_y = POINT_SIZE * r + POINT_SIZE / 2;
		}
		
		public function get x(): Number {
			return _x;
		}
		
		public function set x(x:Number): void {
			_x = x;
		}
		
		public function get y(): Number {
			return _y;
		}
		
		public function set y(y:Number): void {
			_y = y;
		}	
		
		public function get c():Number 
		{
			return _c;
		}
		
		public function set c(value:Number):void 
		{
			_c = value;
		}
		
		public function get r():Number 
		{
			return _r;
		}
		
		public function set r(value:Number):void 
		{
			_r = value;
		}
		
		public function toString():String {
            return "Coordinate: ("+_r+","+_c+"), XY: ("+_x+","+_y+")";
        }
		
		public function equals(c:Coordinate):Boolean {
			return (this.c - c.c == 0 && this.r - c.r == 0);
		}
	}
}