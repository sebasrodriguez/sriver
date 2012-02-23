package
{
	import flash.events.MouseEvent;
	import flash.events.TimerEvent;
	import flash.filters.GlowFilter;
	import flash.utils.SetIntervalTimer;
	import flash.utils.Timer;
	import mx.containers.Canvas;
	import Common.*;
	import mx.core.UIComponent;
	import mx.rpc.AbstractOperation;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.soap.WebService;
	import mx.rpc.soap.Operation;
	import UI.*;
	
	/**
	 * ...
	 * @author pablo
	 */
	public class Game extends UIComponent
	{
		public static const GAME_BOARD_COLS:Number = 32;
		public static const GAME_BOARD_ROWS:Number = 18;
		public static const GAME_SCROLL_SENSOR:Number = 50;
		
		private var _main:Main;
		private var _board:Canvas;
		private var _ws:WebService;
		
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
			main.addEventListener(MouseEvent.MOUSE_MOVE, mouseMoveHandler);
			main.wsRequest.NewGame();			
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
		
		private function mouseMoveHandler(e:MouseEvent):void
		{
			//TODO: HACER MAS LINDO EL MOVIMIENTO
			//para animacion
			
			if (e.stageX < 50)
			{ //scroll left						
				if (_board.x < 0)
					_board.x += 10;
			}
			if (e.stageX > (_main.stage.stageWidth - 50))
			{ //scroll right	
				//trace("left myCanvasX " + myCanvas.x +" - myCanvasWidth - " + myCanvas.width + " - stagewidth - " + stage.stageWidth);	
				if (_board.x > _main.stage.stageWidth - _board.width)
					_board.x -= 10;
			}
			if (e.stageY < 50)
			{ //scroll top	
				if (_board != null)
					if (_board.y < 0)
						_board.y += 10;
			}
			if (e.stageY > (_main.stage.stageHeight - 50))
			{ //scroll height		
				if (_board.y > _main.stage.stageHeight - _board.height)
					_board.y -= 10;
			}
		}
		
		public function NewGameResult(event:ResultEvent):void
		{
			// Inicializa el Canvas que contiene los demas elementos
			_board = new Canvas();
			_board.x = 0;
			_board.y = 0;
			_board.horizontalScrollPolicy = "off";
			_board.verticalScrollPolicy = "off";
			_main.addElement(_board);
			
			// Inicializa el mapa
			_mapComponent = new Map();
			_board.width = _mapComponent.sprite.width;
			_board.height = _mapComponent.sprite.height;
			
			// Crea la grilla del mapa
			_gridComponent = new GameGrid(_board, GAME_BOARD_ROWS, GAME_BOARD_COLS);
			_gridComponent.addEventListener(CellEvent.CLICK, selectedCellEvent);
			
			// Crea y ubica los barcos en el mapa
			createAndLocateShips(event);
			
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
		}
		
		// Evento disparado cuando se selecciona una celda de la grilla
		private function selectedCellEvent(event:CellEvent):void
		{
			if (_selectedShip != null)
			{
				_selectedShip.moveTo(event.coordinate);				
			}
		}
		
		private function createAndLocateShips(event:ResultEvent):void
		{
			var c1:Coordinate = new Coordinate(event.result.Ships[0].Position.Y, event.result.Ships[0].Position.X);
			var c2:Coordinate = new Coordinate(event.result.Ships[1].Position.Y, event.result.Ships[1].Position.X);
			var c3:Coordinate = new Coordinate(event.result.Ships[2].Position.Y, event.result.Ships[2].Position.X);
			var c4:Coordinate = new Coordinate(event.result.Ships[3].Position.Y, event.result.Ships[3].Position.X);
			
			_redShipComponent = new RedShip(c1);
			_blueShipComponent1 = new BlueShip(c2);
			_blueShipComponent2 = new BlueShip(c3);
			_blueShipComponent3 = new BlueShip(c4);
			
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
		}
	}
}