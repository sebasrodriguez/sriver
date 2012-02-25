package components
{
	import flash.events.MouseEvent;
	import flash.events.TimerEvent;
	import flash.filters.GlowFilter;
	import flash.utils.SetIntervalTimer;
	import flash.utils.Timer;
	import mx.containers.Canvas;
	import mx.core.UIComponent;
	import mx.rpc.AbstractOperation;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.soap.WebService;
	import mx.rpc.soap.Operation;
	import common.*;
	import events.*;
	
	/**
	 * ...
	 * @author pablo
	 */
	public class Game extends UIComponent
	{
		public static const GAME_BOARD_COLS:Number = 64;
		public static const GAME_BOARD_ROWS:Number = 36;
		
		private var _main:Main;
		private var _board:Canvas;
		private var _ws:WebService;
		private var _menu:Menu;
		
		private var _mapComponent:Map;
		public var _gridComponent:GameGrid;
		public var _redShipComponent:RedShip;
		public var _blueShipComponent1:BlueShip;
		public var _blueShipComponent2:BlueShip;
		public var _blueShipComponent3:BlueShip;
		public var _selectedShip:Ship;
		
		public function Game(main:Main)
		{
			_main = main;
			newGame();
		}
		
		/*
		 * inicia la ssincronizacion con el server, ejecuta un timer cada 500 milisegundos
		 *
		 */
		public function startSyncronizing():void
		{
			var timer:Timer = new Timer(500, 0);
			timer.addEventListener(TimerEvent.TIMER, timerHandler);
			timer.start();
		}
		
		private function timerHandler(event:TimerEvent):void
		{
			// _main.wsRequest.synchronize();
		}		
		
		public function newGame():void
		{
			// Inicializa el Canvas que contiene los demas elementos
			_board = new Canvas();
			_board.x = 0;
			_board.y = 0;
			_board.horizontalScrollPolicy = "off";
			_board.verticalScrollPolicy = "off";
			_main.addElement(_board);
			new AutoScroll(_main, _board);
			
			// Inicializa el mapa
			_mapComponent = new Map();
			_board.width = _mapComponent.sprite.width;
			_board.height = _mapComponent.sprite.height;
			
			// Crea la grilla del mapa
			_gridComponent = new GameGrid(_board, GAME_BOARD_ROWS, GAME_BOARD_COLS);
			_gridComponent.addEventListener(CellEvent.CLICK, selectedCellEvent);
			_gridComponent.blockCells(_mapComponent.getInitialBlockedCoordinates());
			
			// Crea y ubica los barcos en el mapa
			createAndLocateShips();
			
			_selectedShip = null;
			
			// Agrega los componentes al objeto Canvas
			_board.addChild(_mapComponent);
			_board.addChild(_gridComponent);
			_board.addChild(_redShipComponent);
			_board.addChild(_blueShipComponent1);
			_board.addChild(_blueShipComponent2);
			_board.addChild(_blueShipComponent3);
			
			// Muestra los componentes en pantalla
			_mapComponent.show();
			_redShipComponent.show();
			_blueShipComponent1.show();
			_blueShipComponent2.show();
			_blueShipComponent3.show();
			
			_menu = new Menu(Menu.MENU_POSITION_BOTTOM_LEFT);
			_menu.addEventListener(ActionEvent.MODE_CHANGED, function (event:ActionEvent):void {
				switch(event.mode) {
					case Menu.MENU_MODE_MOVE:
						trace("Cambio a Move");
						break;
					case Menu.MENU_MODE_ROTATE:
						trace("Cambio a Rotate");
						break;
					case Menu.MENU_MODE_FIRE:
						trace("Cambio a Fire");
						break;
				}
			} );
			_menu.addEventListener(ActionEvent.ROTATION_CLICKED, function(event:ActionEvent):void {
				trace("Rotate :" + event.rotation);
			});
			_main.addElement(_menu);
			//this.addChild(_menu);
		}
		
		// Evento disparado cuando se selecciona una celda de la grilla
		private function selectedCellEvent(event:CellEvent):void
		{
			if (_selectedShip != null)
			{
				_selectedShip.moveTo(event.coordinate);
			}
		}
		
		private function createAndLocateShips():void
		{
			var c1:Coordinate = new Coordinate(10, 12);
			var d1:Cardinal = new Cardinal(Cardinal.S);
			
			var c2:Coordinate = new Coordinate(15, 10);
			var d2:Cardinal = new Cardinal(Cardinal.N);
			
			var c3:Coordinate = new Coordinate(15, 15);
			var d3:Cardinal = new Cardinal(Cardinal.S);
			
			var c4:Coordinate = new Coordinate(15, 18);
			var d4:Cardinal = new Cardinal(Cardinal.W);
			
			_redShipComponent = new RedShip(c1, d1, 4, 3);
			_blueShipComponent1 = new BlueShip(c2, d2, 4, 1);
			_blueShipComponent2 = new BlueShip(c3, d3, 4, 1);
			_blueShipComponent3 = new BlueShip(c4, d4, 4, 1);
			
			_redShipComponent.addEventListener(SelectedShipEvent.CLICK, selectedShipEvent);
			_blueShipComponent1.addEventListener(SelectedShipEvent.CLICK, selectedShipEvent);
			_blueShipComponent2.addEventListener(SelectedShipEvent.CLICK, selectedShipEvent);
			_blueShipComponent3.addEventListener(SelectedShipEvent.CLICK, selectedShipEvent);
		}
		
		public function selectedShipEvent(event:SelectedShipEvent):void
		{
			if (_selectedShip != null)
				_selectedShip.filters = null;
			if (event.selectedShip != _selectedShip)
			{
				_selectedShip = event.selectedShip;
				var glow:GlowFilter = new GlowFilter(0xFF0000);
				_selectedShip.filters = [glow];
			}
			else
			{
				_selectedShip.filters = null;
				_selectedShip = null;
			}
			if (_selectedShip != null)
			{
				drawMovements();
			}
		}
		
		private function drawMovements():void
		{			
			var forwardCoordinate:Coordinate = new Coordinate(_selectedShip.currentPos.r, _selectedShip.currentPos.c);
			var backwardsCoordinate:Coordinate = new Coordinate(_selectedShip.currentPos.r, _selectedShip.currentPos.c);
			var backwardsDirection:Cardinal = new Cardinal(_selectedShip.direction.cardinal - 180);
			
			if (_selectedShip.size == 3)
			{
				forwardCoordinate = calculateNextCell(_selectedShip.currentPos, _selectedShip.direction);
				backwardsCoordinate = calculateNextCell(_selectedShip.currentPos, backwardsDirection);
			}
			
			for (var i:int = 0; i < _selectedShip.speed; i++)
			{
				forwardCoordinate = calculateNextCell(forwardCoordinate, _selectedShip.direction);
				_gridComponent.enableCell(forwardCoordinate);
			}
			for (var j:int = 0; j < _selectedShip.speed / 2; j++) {
				backwardsCoordinate = calculateNextCell(backwardsCoordinate, backwardsDirection);
				_gridComponent.enableCell(backwardsCoordinate);
			}
		}
		
		private function calculateNextCell(actualCoordinate:Coordinate, direction:Cardinal):Coordinate
		{
			var newCoordinate:Coordinate = new Coordinate(actualCoordinate.r, actualCoordinate.c);
			// si la direccion es al este
			if (direction.cardinal == Cardinal.E)
			{
				newCoordinate.c = newCoordinate.c + 1;
			}
			// si la direccion es al oeste
			if (direction.cardinal == Cardinal.W)
			{
				newCoordinate.c = newCoordinate.c - 1;
			}
			// si la direccion es al norte
			if (direction.cardinal == Cardinal.N)
			{
				newCoordinate.r = newCoordinate.r - 1;
			}
			// si la direccion es al sur
			if (direction.cardinal == Cardinal.S)
			{
				newCoordinate.r = newCoordinate.r + 1;
			}
			if (direction.cardinal == Cardinal.SE)
			{
				newCoordinate.r = newCoordinate.r + 1;
				newCoordinate.c = newCoordinate.c + 1;
			}
			if (direction.cardinal == Cardinal.SW)
			{
				newCoordinate.r = newCoordinate.r + 1;
				newCoordinate.c = newCoordinate.c - 1;
			}
			if (direction.cardinal == Cardinal.NE)
			{
				newCoordinate.r = newCoordinate.r - 1;
				newCoordinate.c = newCoordinate.c + 1;
			}
			if (direction.cardinal == Cardinal.NW) {
				newCoordinate.r = newCoordinate.r - 1;
				newCoordinate.c = newCoordinate.c - 1;
			}
			
			return newCoordinate;
		}
	}
}