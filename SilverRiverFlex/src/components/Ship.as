package components
{
	import common.*;
	import events.*;
	import flash.events.MouseEvent;
	/**
	 * ...
	 * @author pablo
	 */
	public class Ship extends GameUIComponent
	{		
		private var _ship:Ship;
		private var _direction:Cardinal;
		private var _speed:int;
		private var _size:int;
		
		public function Ship(c:Coordinate, d:Cardinal, s:int, size:int) {
			super(c);	
			_direction = d;
			_ship = this;
			_speed = s;
			_size = size;
			this.rotation = d.cardinal;
			this.addEventListener( MouseEvent.CLICK, function (e:MouseEvent):void {
				var selectedShipEvent:SelectedShipEvent = new SelectedShipEvent();
				selectedShipEvent.selectedShip = _ship;				
				dispatchEvent(selectedShipEvent);
			});
		}
		
		
		public function get direction():Cardinal
		{
			return _direction;
		}
		
		public function set direction(value:Cardinal):void
		{
			_direction = value;
		}
		
		public function get speed():int
		{
			return _speed;
		}
		
		public function set speed(value:int):void
		{
			_speed = value;
		}	
		
		public function get size():int
		{
			return _size;
		}
		
		public function set size(value:int):void
		{
			_size = value;
		}	
	}

}