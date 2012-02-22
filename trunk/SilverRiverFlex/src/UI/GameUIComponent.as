package UI
{
	import Actions.Action;
	import Common.Coordinate;
	import flash.display.BitmapData;
	import flash.display.Sprite;
	import mx.core.UIComponent;
	import mx.effects.Rotate;
	import mx.effects.Tween;
	import mx.messaging.errors.NoChannelAvailableError;
	
	/**
	 * ...
	 * @author pablo
	 */
	public class GameUIComponent extends UIComponent
	{
		private var _sprite:Sprite;
		private var _currentPos:Coordinate;
		
		public function GameUIComponent(c:Coordinate = null)
		{
			if (c != null)
				setPosition(c);
			_sprite = new Sprite();
		}
		
		public function setBitmap(data:BitmapData):void
		{
			_sprite.graphics.beginBitmapFill(data, null, false, false);
			_sprite.graphics.drawRect(0, 0, data.rect.width, data.rect.height);
			_sprite.graphics.endFill();
			_sprite.visible = false;
			this.addChild(_sprite);
		}
		
		public function fixCenter():void
		{
			_sprite.x = -(_sprite.width / 2);
			_sprite.y = -(_sprite.height / 2);
		}
		
		public function show():void
		{
			_sprite.visible = true;
		}
		
		public function hide():void
		{
			_sprite.visible = false;
		}
		
		public function setPosition(c:Coordinate):void
		{
			this.x = c.x;
			this.y = c.y;
		}
		
		// Mueve un objeto a las coordenadas dadas
		public function moveTo(c:Coordinate):void
		{
			var xdelta:Number = c.x - this.x;
			var ydelta:Number = c.y - this.y;
			var distance:Number = Math.sqrt(Math.pow(xdelta, 2) + Math.pow(ydelta, 2));
			
			var time:Number = distance / 50 * 700;
			var angle:Number = Math.tan(xdelta / ydelta);
			var ox:Number = this.x;
			var oy:Number = this.y;
			
			new Tween(this, 0, distance, time, 20, function(newX:Number):void
				{
					this.listener.x = ox + xdelta * (newX / distance);
					this.listener.y = oy + ydelta * (newX / distance);
				
				}, function():void
				{
				});
		}
		
		// Rota un objeto la cantidad de grados dada
		public function rotateTo(degrees:int):void
		{
			var rotate:Rotate = new Rotate(this);
			rotate.angleFrom = this.rotation;
			rotate.angleTo = degrees;
			rotate.duration = 500;
			rotate.play();
		}
		
		public function get currentPos():Coordinate
		{
			return _currentPos;
		}
		
		public function get sprite():Sprite
		{
			return _sprite;
		}
		
		public function set sprite(value:Sprite):void
		{
			_sprite = value;
		}
	
	}

}