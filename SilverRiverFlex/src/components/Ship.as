package components
{
	import common.*;
	import events.*;
	import flash.display.DisplayObject;
	import flash.display.DisplayObjectContainer;
	import flash.events.MouseEvent;
	import flash.filters.GlowFilter;
	import mx.containers.Canvas;
	
	/**
	 * ...
	 * @author pablo
	 */
	public class Ship extends GameUIComponent
	{
		public static const VARIABLE_ATTRIBUTE_ARMOR:String = "armor";
		public static const VARIABLE_ATTRIBUTE_AMMO:String = "ammo";
		public static const VARIABLE_ATTRIBUTE_TORPEDOES:String = "torpedoes";
		
		private var _id:int;
		private var _ship:Ship;
		private var _direction:Cardinal;
		private var _speed:int;
		private var _size:int;
		private var _ammo:int;
		private var _torpedoes:int;
		private var _armor:int;
		private var _viewRange:int;
		private var _coordinates:Array;
		private var _selected:Boolean;
		
		private var _maxAmmo:int;
		private var _maxTorpedoes:int;
		private var _maxArmor:int;
		private var _port1Enabled:Boolean;
		private var _port2Enabled:Boolean;
		
		public function Ship(id:int, c:Coordinate, d:Cardinal, s:int, size:int, armor:int, ammo:int, torpedoes:int, viewRange:int)
		{
			super(c);
			_id = id;
			_direction = d;
			_ship = this;
			_speed = s;
			_size = size;
			_armor = armor;
			_maxArmor = armor;
			_ammo = ammo;
			_maxAmmo = ammo;
			_torpedoes = torpedoes;
			_maxTorpedoes = torpedoes;
			_viewRange = viewRange;
			_port1Enabled = true;
			_port2Enabled = true;
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
		
		public function get armor():int
		{
			return _armor;
		}
		
		public function set armor(value:int):void
		{
			_armor = value;
		}
		
		public function get ammo():int
		{
			return _ammo;
		}
		
		public function set ammo(value:int):void
		{
			_ammo = value;
		}
		
		public function get torpedoes():int
		{
			return _torpedoes;
		}
		
		public function set torpedoes(value:int):void
		{
			_torpedoes = value;
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
		
		public function get selected():Boolean
		{
			return _selected;
		}
		
		public function hasAmmo():Boolean
		{
			return _ammo > 0;
		}
		
		public function hasTorpedoes():Boolean
		{
			return _torpedoes > 0;
		}
		
		public function decreaseAmmo():void
		{
			_ammo--;
		}
		
		public function decreaseTorpedoes():void
		{
			_torpedoes--;
		}
		
		public function set selected(value:Boolean):void
		{
			if (value)
			{
				var glow:GlowFilter = new GlowFilter(0xFF0000);
				filters = [glow];
			}
			else
			{
				filters = null;
			}
			_selected = value;
		}
		
		public function get viewRange():int
		{
			return _viewRange;
		}
		
		public function set viewRange(value:int):void
		{
			_viewRange = value;
		}		
		
		public function updateCoordinates():void
		{
			var arr:Array = new Array();
			var midsize:Number = Math.floor(_size / 2);
			var offset:Coordinate = Cardinal.getOffsetCoodinate(_direction.cardinal);
			arr.push(this.currentPos);
			//TODO:RECHEQUEAR FUNCION
			for (var i:int = 1; i <= midsize; i++)
			{
				arr.push(new Coordinate(this.currentPos.r + offset.r, this.currentPos.c + offset.c));
				arr.push(new Coordinate(this.currentPos.r - offset.r, this.currentPos.c - offset.c));
			}
			_coordinates = arr;
		}
		
		public function fireBullet(c:Coordinate, func:Function):void
		{
			var board:DisplayObjectContainer = this.parent;
			var bullet:Bullet = new Bullet(currentPos);
			board.addChild(bullet);
			bullet.rotation = Helper.getAngleToCoordinate(currentPos, c);
			bullet.show();
			bullet.moveTo(c, FunctionUtil.createDelegate(function(bullet:Bullet):void
				{
					bullet.hide();
					bullet = null;
					if (func != null)
						func.call();
				}, bullet), 10);
		}
		
		public function fireTorpedo(hitCoordinate:Coordinate, func:Function):void
		{
			var board:DisplayObjectContainer = this.parent;
			var torpedo:Torpedo = new Torpedo(currentPos);
			board.addChild(torpedo);
			torpedo.rotation = this.rotation;
			torpedo.show();
			torpedo.moveTo(hitCoordinate, FunctionUtil.createDelegate(function(torpedo:Torpedo):void
				{
					torpedo.hide();
					torpedo = null;
					if (func != null)
						func.call();
				}, torpedo), 10);
		}
		
		public override function moveTo(c:Coordinate, func:Function, speed:Number = 1):void
		{
			super.moveTo(c, function():void
				{
					updateCoordinates();
					if (func != null)
						func.call();
				}, speed);
		}
		
		public override function rotateTo(degrees:int, func:Function):void
		{
			super.rotateTo(degrees, function():void
				{
					_direction = new Cardinal(degrees);
					updateCoordinates();
					if (func != null)
						func.call();
				});
		}
		
		public function itsMe(coordinate:Coordinate):Boolean
		{
			var result:Boolean = false;
			var i:int = 0;
			while (!result && i < _coordinates.length)
			{
				if (coordinate.equals(_coordinates[i]))
					result = true;
				i++;
			}
			return result;
		}
		
		public function isInFireRange(coordinate:Coordinate):Boolean
		{
			var isInFireRange:Boolean = true;
			
			if (Helper.distanceBetweenCells(this.currentPos, coordinate) > _viewRange)
				isInFireRange = false;
			
			return isInFireRange;
		}
				
		public function isAlive():Boolean {
			return armor > 0;
		}
	}

}