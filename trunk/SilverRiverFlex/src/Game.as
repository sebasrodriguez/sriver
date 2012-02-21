package  
{
	import flash.events.MouseEvent;
	import flash.events.TimerEvent;
	import flash.utils.SetIntervalTimer;
	import flash.utils.Timer;
	import mx.containers.Canvas;
	import Common.*;
	import mx.core.UIComponent;
	import mx.rpc.AbstractOperation;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.soap.WebService;
	import mx.rpc.soap.Operation
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
		
		public function Game(main:Main) 
		{
			_main = main;
			main.addEventListener(MouseEvent.MOUSE_MOVE, mouseMoveHandler);
			main.wsRequest.getTest();
			//revisar en java el webservice
			//startSyncronizing();
			//se crea el canvas principal que contiene el mapa y barcos
			_board = new Canvas();
			_board.x = 0;
			_board.y = 0;
			_board.horizontalScrollPolicy = "off";
			_board.verticalScrollPolicy = "off";
			_main.addElement(_board);
			
			_mapComponent = new Map();
			_board.width = _mapComponent.sprite.width;
			_board.height = _mapComponent.sprite.height;
			
			
			_gridComponent = new GameGrid(_board, GAME_BOARD_ROWS, GAME_BOARD_COLS);	
			_gridComponent.addEventListener(CellEvent.CLICK, function (event:CellEvent):void {
				_blueShipComponent1.moveTo(event.coordinate);
			});
			
			_redShipComponent = new RedShip(new Coordinate(6,6));
			_blueShipComponent1 = new BlueShip(new Coordinate(0,0));
			_blueShipComponent2 = new BlueShip(new Coordinate(1,1));
			_blueShipComponent3 = new BlueShip(new Coordinate(2,2));
			
			_board.addChild(_mapComponent);	
			_board.addChild(_gridComponent);		
			_board.addChild(_redShipComponent);
			_board.addChild(_blueShipComponent1);
			_board.addChild(_blueShipComponent2);
			_board.addChild(_blueShipComponent3);
			
			_mapComponent.show();
			_redShipComponent.show();
			_blueShipComponent1.show();
			_blueShipComponent2.show();
			_blueShipComponent3.show();
		}
		/*
		 * inicia la ssincronizacion con el server, ejecuta un timer cada 500 milisegundos
		 * 
		 */
		public function startSyncronizing():void {
			var timer:Timer = new Timer(500, 0);
			timer.addEventListener(TimerEvent.TIMER, timerHandler);
			timer.start();
		}
		private function timerHandler(event:TimerEvent):void {
			_main.wsRequest.synchronize();
		}
		
		private function mouseMoveHandler(e:MouseEvent):void {
			//TODO: HACER MAS LINDO EL MOVIMIENTO
			//para animacion
		
			if (e.stageX < 50) { //scroll left						
				if(_board.x < 0)
					_board.x += 10;
			}
			if (e.stageX > (_main.stage.stageWidth - 50)) { //scroll right	
				//trace("left myCanvasX " + myCanvas.x +" - myCanvasWidth - " + myCanvas.width + " - stagewidth - " + stage.stageWidth);	
				if(_board.x > _main.stage.stageWidth - _board.width)
					_board.x -= 10;
			}
			if (e.stageY < 50) { //scroll top	
				if(_board.y < 0)
					_board.y += 10;
			}
			if (e.stageY > (_main.stage.stageHeight - 50)) { //scroll height		
				if(_board.y > _main.stage.stageHeight - _board.height)
					_board.y -= 10;
			}
		}
		
		//Webservices handlers, son publicos porque los llamo desde el Main
		public function wsSyncronizeHandler(event:ResultEvent):void {
			trace(event.result);
		}
		public function wsGetTestHandler(event:ResultEvent):void {
			trace(event.result);
		}
		public function wsGetTestFaultHandler(event:FaultEvent):void {
			trace(event.fault);
		}
	}

}