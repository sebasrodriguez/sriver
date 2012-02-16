package  
{
	import flash.display.BitmapData;
	import flash.display.Sprite;
	import mx.core.UIComponent;
	import mx.effects.Tween;
	import mx.messaging.errors.NoChannelAvailableError;
	/**
	 * ...
	 * @author pablo
	 */
	public class GameUIComponent extends UIComponent
	{
		private var sprite:Sprite;
		
		public function GameUIComponent(x:Number = 0, y:Number = 0) 
		{
			this.setPosition(x, y);
			this.sprite = new Sprite();
		}
		
		public function setBitmap(data:BitmapData):void {
			this.sprite.graphics.beginBitmapFill(data, null, false, false);
			this.sprite.graphics.drawRect(0, 0, data.rect.width, data.rect.height);
			this.sprite.graphics.endFill();
			this.sprite.visible = false;
			this.addChild(this.sprite);
		}
		
		public function fixCenter():void {
			this.sprite.x = -(this.sprite.width / 2);
			this.sprite.y = -(this.sprite.height / 2);
		}
		
		public function show():void {
			this.sprite.visible = true;
		}
		public function hide():void {
			this.sprite.visible = false;
		}
		
		public function setPosition(x:Number, y:Number):void {
			this.x = x;
			this.y = y;
		}
		
		public function getSprite():Sprite {
			return sprite;
		}
		
		public function moveTo(x:Number, y:Number):void {
			
			new Tween(this,this.x,x,5000, 20, this.moveToCallback, function():void{});
		}
		public function moveToCallback(newX:Number):void {
			trace(newX);
			this.x = newX;
		}
	}

}