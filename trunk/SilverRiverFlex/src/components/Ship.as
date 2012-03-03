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
		private var _ammo:int;
		private var _armor:int;
		private var _coordinates:Array;
		
		public function Ship(id:int, c:Coordinate, d:Cardinal, s:int, size:int)
		{
			super(c);
			_id = id;
			_direction = d;
			_ship = this;			
			_speed = (s * 4) / 10;			
			_size = size;
			this.rotation = d.cardinal;
			updateCoordinates();
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
		
		public function get coordinates():Array 
		{
			return _coordinates;
		}
		
		public function set coordinates(value:Array):void 
		{
			_coordinates = value;
		}
		
		public function updateCoordinates():void {
			var arr:Array = new Array();
			var midsize:Number = Math.floor(_size / 2);
			var offset:Coordinate = Cardinal.getOffsetCoodinate(_direction.cardinal);
			arr.push(this.currentPos);
			//TODO:RECHEQUEAR FUNCION
			for (var i:int = 1; i <= midsize; i ++) {
				arr.push(new Coordinate(this.currentPos.r + offset.r, this.currentPos.c + offset.c));
				arr.push(new Coordinate(this.currentPos.r - offset.r, this.currentPos.c - offset.c));
			}
			_coordinates = arr;
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
			torpedo.moveTo(new Coordinate(0,0), FunctionUtil.createDelegate(function (torpedo:Torpedo):void {
				torpedo.hide();
				torpedo = null;
				if( func != null)
					func.call();
			},torpedo), 10);
		}
		
		public override function moveTo(c:Coordinate, func:Function, speed:Number = 1):void{			
			super.moveTo(c, function():void {
				updateCoordinates();
				if(func != null)
					func.call();
			}, speed);
		}
		
		public override function rotateTo(degrees:int, func:Function):void{
			super.rotateTo(degrees, function():void {
				_direction = new Cardinal(degrees);
				updateCoordinates();
				if(func != null)
					func.call();
			});
		}
	}

}