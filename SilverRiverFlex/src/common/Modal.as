package common 
{
	import flash.display.DisplayObject;
	import mx.containers.Canvas;
	import mx.containers.TitleWindow;
	import mx.controls.Text;
	import mx.managers.PopUpManager;
	/**
	 * ...
	 * @author pablo
	 */
	public class Modal extends TitleWindow 
	{
		private var _canvas:Canvas;
		
		public function Modal(p:DisplayObject, t:String, w:int, h:int, m:String = "") 
		{
			if(t != "")
				title = t;
			_canvas = new Canvas();
			_canvas.width = w;
			_canvas.height = h;
			addChild(_canvas);
			if (m != "") {
				var message:Text = new Text();
				message.text = m;
				_canvas.addChild(message);
			}
			PopUpManager.addPopUp(this, p, true);
			PopUpManager.centerPopUp(this);
		}
		
		public function close():void {
			PopUpManager.removePopUp(this);			
		}
		
		public function get canvas():Canvas 
		{
			return _canvas;
		}
		
		public function set canvas(value:Canvas):void 
		{
			_canvas = value;
		}
		
	}

}