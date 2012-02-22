package  
{
	import Common.Coordinate;
	import flash.events.MouseEvent;
	import UI.*;
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