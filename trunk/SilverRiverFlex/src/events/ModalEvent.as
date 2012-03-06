package events
{
	import common.*;
	import flash.events.Event;
	
	/**
	 * ...
	 * @author pablo
	 */
	public class ModalEvent extends Event 
	{
		public static const GAME_SELECTED:String = "modalGameSelected";
		public static const ATTRIBUTE_SELECTED:String = "modalAttributeSelected";
		public var attribute:String;
		public var game:String;
		public var username:String;
		
		public function ModalEvent(type:String="modalGameSelected",bubbles:Boolean=false,cancelable:Boolean=false) 
		{
			super(type,bubbles,cancelable);
		}
		public override  function clone():Event {
            return new ActionEvent(type, bubbles, cancelable);
        }
        public override  function toString():String {
            return formatToString("type","bubbles","cancelable");
        }
		
	}

}