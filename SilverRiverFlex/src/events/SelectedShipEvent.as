package events
{
	import components.*;
	import flash.events.Event;
	
	/**
	 * ...
	 * @author sebas
	 */
	public class SelectedShipEvent extends Event 
	{
		public static const CLICK:String = "shipEventClick";
		public var selectedShip:Ship;
		
		public function SelectedShipEvent(type:String="shipEventClick", bubbles:Boolean=false, cancelable:Boolean=false) 
		{ 
			super(type, bubbles, cancelable);			
		} 
		
		public override function clone():Event 
		{ 
			return new SelectedShipEvent(type, bubbles, cancelable);
		} 
		
		public override function toString():String 
		{ 
			return formatToString("SelectedShipEvent", "type", "bubbles", "cancelable", "eventPhase"); 
		}
		
	}
	
}