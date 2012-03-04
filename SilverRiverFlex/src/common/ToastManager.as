package common 
{
	import mx.containers.VBox;
	/**
	 * ...
	 * @author pablo
	 */
	public class ToastManager extends VBox
	{
		
		public function ToastManager() 
		{
			y = 10;
			x = 250;
		}
		public function addToast(message:String, time:int = 3):void {
			addChildAt(new Toast(this, message, time), 0);
		}
		
	}

}