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
		
		public function Ship(c:Coordinate) {
			super(c);			
			_ship = this;
			this.addEventListener( MouseEvent.CLICK, function (e:MouseEvent):void {
				var selectedShipEvent:SelectedShipEvent = new SelectedShipEvent();
				selectedShipEvent.selectedShip = _ship;				
				dispatchEvent(selectedShipEvent);
			});
		}
		
	}

}