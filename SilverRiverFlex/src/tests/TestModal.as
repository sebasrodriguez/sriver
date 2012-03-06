package tests 
{
	import components.*;
	import common.*;
	import events.*;
	/**
	 * ...
	 * @author pablo
	 */
	public class TestModal 
	{
		
		public function TestModal(main:tests.MainTest) 
		{
			var modal:Modal = new GameModal(main);
			modal.addEventListener(ModalEvent.GAME_SELECTED, function(event:ModalEvent):void {
				trace("selecciono : " + event.game);
			} );
			
		}
		
	}

}