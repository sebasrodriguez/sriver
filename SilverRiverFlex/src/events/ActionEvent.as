package events
{
	import common.*;
	import flash.events.Event;
	
	/**
	 * ...
	 * @author pablo
	 */
	public class ActionEvent extends Event 
	{
		public static const MODE_CHANGED:String = "actionEventModeChanged";
		public static const ROTATION_CLICKED:String = "actionEventRotationClicked";
		public var mode:int;
		public var rotation:int;
		
		public function ActionEvent(type:String="actionEventModeChanged",bubbles:Boolean=false,cancelable:Boolean=false) 
		{
			super(type,bubbles,cancelable);			
		}
		public override  function clone():Event {
            return new ActionEvent(type, bubbles, cancelable);
        }
        public override  function toString():String {//24094949 cot - 1975 copsa
            return formatToString("type","bubbles","cancelable");
        }
		
	}

}