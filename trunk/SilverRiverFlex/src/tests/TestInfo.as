package tests 
{
	import common.Toast;
	import common.ToastManager;
	import components.Info;
	import flash.events.TimerEvent;
	import flash.utils.Timer;
	import mx.containers.VBox;
	/**
	 * ...
	 * @author pablo
	 */
	public class TestInfo 
	{
		
		public function TestInfo(main:tests.MainTest) 
		{
			var info:Info = new Info();
			main.addElement(info);
			
			var tm:ToastManager = new ToastManager();
			main.addElement(tm);
			
			var t:Timer = new Timer(1000, 10);
			var i:int = 0;
			t.addEventListener(TimerEvent.TIMER, function(event:TimerEvent):void {
				tm.addToast("Hola peteco " + i);
				i ++;
			} );
			t.start();
		}
		
	}

}