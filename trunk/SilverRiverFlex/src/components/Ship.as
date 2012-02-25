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
		
		public function Ship(c:Coordinate, d:Cardinal) {
			super(c);	
			_direction = d;
			_ship = this;
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
		
	}

}