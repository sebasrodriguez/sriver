package components
{
	import common.*;
	import events.*;
	import flash.display.DisplayObject;
	import flash.display.DisplayObjectContainer;
	import flash.events.MouseEvent;
	import mx.containers.Canvas;
	
	/**
	 * ...
	 * @author pablo
	 */
	public class Ship extends GameUIComponent
	{
		private var _id:int;
		private var _ship:Ship;
		private var _direction:Cardinal;
		private var _speed:int;
		private var _size:int;
		
		public function Ship(id:int, c:Coordinate, d:Cardinal, s:int, size:int)
		{
			super(c);
			_id = id;
			_direction = d;
			_ship = this;
			_speed = s;
			_size = size;
			this.rotation = d.cardinal;
			this.addEventListener(MouseEvent.CLICK, function(e:MouseEvent):void
				{
					var selectedShipEvent:SelectedShipEvent = new SelectedShipEvent();
					selectedShipEvent.selectedShip = _ship;
					dispatchEvent(selectedShipEvent);
				});
		}
		
		public function get shipId():int
		{
			return _id;
		}
		
		public function get direction():Cardinal
		{
			return _direction;
		}
		
		public function set direction(value:Cardinal):void
		{
			_direction = value;
		}
		
		public function get speed():int
		{
			return _speed;
		}
		
		public function set speed(value:int):void
		{
			_speed = value;
		}
		
		public function get size():int
		{
			return _size;
		}
		
		public function set size(value:int):void
		{
			_size = value;
		}
		
		public function fireBullet(c:Coordinate, func:Function):void {
			var board:DisplayObjectContainer = this.parent;
			var bullet:Bullet = new Bullet(currentPos);
			board.addChild(bullet);
			bullet.rotation = Helper.getAngleToCoordinate(currentPos, c);
			bullet.show();
			bullet.moveTo(c, FunctionUtil.createDelegate(function (bullet:Bullet):void {
				bullet.hide();
				bullet = null;
				if( func != null)
					func.call();
			},bullet), 10);
		}
		
		public function fireTorpedo(func:Function):void {
			var board:DisplayObjectContainer = this.parent;
			var torpedo:Torpedo = new Torpedo(currentPos);
			board.addChild(torpedo);
			torpedo.rotation = this.rotation;
			torpedo.show();
			trace(currentPos.c - currentPos.r);
			torpedo.moveTo(new Coordinate(0,0), FunctionUtil.createDelegate(function (torpedo:Torpedo):void {
				torpedo.hide();
				torpedo = null;
				if( func != null)
					func.call();
			},torpedo), 10);
		}
	}

}