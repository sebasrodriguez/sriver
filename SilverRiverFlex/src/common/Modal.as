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
		private var _isOpened:Boolean;
		
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
			_isOpened = true;
		}
		
		public function close():void {
			PopUpManager.removePopUp(this);
			_isOpened = false;
		}
		
		public function get canvas():Canvas 
		{
			return _canvas;
		}
		
		public function set canvas(value:Canvas):void 
		{
			_canvas = value;
		}
		
		public function get isOpened():Boolean 
		{
			return _isOpened;
		}
		
		public function set isOpened(value:Boolean):void 
		{
			_isOpened = value;
		}
		
	}

}