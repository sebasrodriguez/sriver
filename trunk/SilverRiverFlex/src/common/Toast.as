package common 
{
	import components.Info;
	import flash.display.Shape;
	import flash.events.TimerEvent;
	import flash.utils.Timer;
	import mx.containers.Box;
	import mx.containers.VBox;
	import mx.controls.Text;
	import mx.core.Container;
	import mx.core.UIComponent;
	import mx.events.EffectEvent;
	import spark.effects.Fade;
	/**
	 * ...
	 * @author pablo
	 */
	public class Toast extends UIComponent
	{
		private var _parentContainer:VBox;
		
		public function Toast(pcontainer:VBox, message:String, time:int = 3) 
		{
			height = 40;
			width = 530;
			_parentContainer = pcontainer;
			var toastShape:Shape = new Shape();
			toastShape.graphics.beginFill(0x000000, 0.8);
			toastShape.graphics.drawRoundRect(0, 0, 530, 40, 10, 10);
			toastShape.graphics.endFill();
			toastShape.x = 5;
			addChild(toastShape);			
			var container:Box = new Box();
			container.width = 530;
			container.height = 40;
			container.setStyle("verticalAlign", "middle");
			container.setStyle("horizontalAlign", "center");
			addChild(container);
			var label:Text = new Text();		
			label.text = message;
			label.width = 510;
			label.setStyle("textAlign", "center");
			label.setStyle("fontSize", 14);
			label.setStyle("color", 0xffffff);
			label.setStyle("fontStyle", "bold");
			container.addChild(label);
			alpha = 0;
			show(time);
		}
		
		public function show(time:int):void {
			var fade:Fade = new Fade(this);
			fade.alphaFrom = 0;
			fade.alphaTo = 1;
			fade.duration = 1000;
			fade.play();		
			var timer:Timer = new Timer(time * 1000, 1);
			timer.addEventListener(TimerEvent.TIMER_COMPLETE, function(event:TimerEvent):void {
				hide();
			});			
			timer.start();
		}
		
		public function hide():void {
			var fade:Fade = new Fade(this);
			fade.alphaFrom = 1;
			fade.alphaTo = 0;
			fade.duration = 1000;
			var thistoast:Toast = this;
			fade.addEventListener(EffectEvent.EFFECT_END, function (event:EffectEvent):void {
				_parentContainer.removeChild(thistoast);
			});
			fade.play();	
		}
		
	}

}