package  
{
	import Common.Coordinate;
	import flash.events.Event;
	
	/**
	 * ...
	 * @author pablo
	 */
	public class CellEvent extends Event 
	{
		public static const CLICK:String = "cellEventClick";
		public var coordinate:Coordinate;
		
		public function CellEvent(type:String="cellEventClick",bubbles:Boolean=false,cancelable:Boolean=false) 
		{
			super(type,bubbles,cancelable);			
		}
		public override  function clone():Event {
            return new CellEvent(type, bubbles, cancelable);
        }
        public override  function toString():String {
            return formatToString("cellEventClick","type","bubbles","cancelable");
        }
		
	}

}