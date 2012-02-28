package components
{
	import flash.display.InteractiveObject;
	import flash.events.MouseEvent;
	import flash.events.TimerEvent;
	import flash.filters.GlowFilter;
	import flash.utils.SetIntervalTimer;
	import flash.utils.Timer;
	import mx.collections.ArrayCollection;
	import mx.collections.ArrayList;
	import mx.containers.Canvas;
	import mx.controls.Label;
	import mx.core.UIComponent;
	import mx.rpc.AbstractOperation;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.Fault;
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
		public static const RED_SHIP_ID:int = 0;
		
		private var _main:Main;
		private var _board:Canvas;
		private var _ws:WebService;
		private var _menu:Menu;
		private var _mapComponent:Map;
		private var _iter:int = 0;
		private var _actions:ResultEvent;
		private var _actionQueue:ArrayList;
		private var _shouldDecreaseTime:Boolean;
		
		public var _gridComponent:GameGrid;
		public var _redShipComponent:RedShip;
		public var _blueShipComponent1:BlueShip;
		public var _blueShipComponent2:BlueShip;
		public var _blueShipComponent3:BlueShip;
		public var _shipList:Array;
		public var _selectedShip:Ship;
		public var _turn:Turn;
		public var _movesLeftLabel:Label;
		public var _timeLeftLabel:Label;
		public var _myUsername:String;
		public var _redPlayer:Player;
		public var _bluePlayer:Player;
		
		public function Game(main:Main)
		{
			_main = main;
			// TODO: esto se obtiene luego del login del usuario
			_myUsername = "sebas";
			_actionQueue = new ArrayList();
			_shouldDecreaseTime = false;
			newGame();
			// Label de movimientos restantes
			_movesLeftLabel = new Label();
			_movesLeftLabel.text = _turn.movesLeft.toString();
			_movesLeftLabel.x = 10;
			_movesLeftLabel.y = 10;
			_movesLeftLabel.setStyle("fontSize", 30);
			_movesLeftLabel.setStyle("color", 0xFFCC33);
			_movesLeftLabel.setStyle("fontStyle", "bold");

			_main.addElement(_movesLeftLabel);
			_timeLeftLabel = new Label();
			_timeLeftLabel.text = _turn.timeLeft.toString();
			_timeLeftLabel.x = 10;
			_timeLeftLabel.y = 40;
			_timeLeftLabel.setStyle("fontSize", 30);
			_timeLeftLabel.setStyle("color", 0xFFCC33);
			_timeLeftLabel.setStyle("fontStyle", "bold");
			_main.addElement(_timeLeftLabel);
			startSyncronizing();
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
			updateTimeLeft();
		}
		
		private function updateTimeLeft():void
		{
			if (_shouldDecreaseTime)
			{
				_turn.timeLeft--;
				if (_turn.timeLeft <= 0)
				{
					_timeLeftLabel.text = "llamar WS con EndTurnAction";
				}
				else
				{
					_timeLeftLabel.text = _turn.timeLeft.toString();
				}
			}
			_shouldDecreaseTime = !_shouldDecreaseTime;
		}
		
		public function consumeActions(response:ResultEvent):void
		{
			// Agregamos las nuevas acciones a ejecutar a la cola de acciones
			for (var i:int = 0; i < response.result.length; i++)
			{
				_actionQueue.addItem(response.result[i]);
			}
			consumeNextAction();
		}
		
		public function consumeNextAction():void
		{
			if (_actionQueue != null && _actionQueue.length > 0)
			{
				var ship:Ship;
				if (_actionQueue.source[0].actionType == "MoveAction")
				{
					ship = getShipById(_actionQueue.source[0].ship.id);
					moveAction(ship, new Coordinate(_actionQueue.source[0].position.y, _actionQueue.source[0].position.x), consumeNextAction);
				}
				else if (_actionQueue.source[0].actionType == "RotateAction")
				{
					ship = getShipById(_actionQueue.source[0].ship.id);
					rotateAction(getShipById(_actionQueue.source[0].ship.id), new Cardinal(_actionQueue.source[0].cardinal.direction), consumeNextAction);
				}
				else if (_actionQueue.source[0].actionType == "FireAction")
				{
					trace("FireAction");
				}
				else if (_actionQueue.source[0].actionType == "EndTurnAction")
				{
					trace("FireAction");
				}
				else if (_actionQueue.source[0].actionType == "EnterPortAction")
				{
					trace("EndTurnAction");
				}
				else if (_actionQueue.source[0].actionType == "LeavePortAction")
				{
					trace("LeavePortAction");
				}
				else if (_actionQueue.source[0].actionType == "EndGameAction")
				{
					trace("EndGameAction");
				}
				_actionQueue.removeItemAt(0);
			}
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
			
			// TODO: los players se crean segun lo que me retorna el WS
			// Creo los dos players
			_redPlayer = new Player("santi");
			_bluePlayer = new Player("sebas");
			
			// TODO: el turno me lo retorna el WS
			// Creo el turno
			_turn = new Turn(10, _bluePlayer, 60);
			
			_menu = new Menu(Menu.MENU_POSITION_BOTTOM_LEFT);
			_menu.addEventListener(ActionEvent.MODE_CHANGED, function(event:ActionEvent):void
				{
					trace("evento disparado");
					switch (event.mode)
					{
						case Menu.MENU_MODE_MOVE: 
							moveMode();
							break;
						case Menu.MENU_MODE_ROTATE: 
							_gridComponent.disableCells();
							break;
						case Menu.MENU_MODE_FIRE: 
							trace("Cambio a Fire");
							break;
					}
				});
			_menu.addEventListener(ActionEvent.ROTATION_CLICKED, function(event:ActionEvent):void
				{
					if (_selectedShip != null && _turn.movesLeft > 0)
					{
						rotateAction(_selectedShip, new Cardinal(event.rotation));
					}
				});
			_main.addElement(_menu);
		
			// consume actions
			// _main.wsRequest.pruebaActions();
		}
		
		// Evento disparado cuando se selecciona una celda de la grilla
		private function selectedCellEvent(event:CellEvent):void
		{
			if (_selectedShip != null)
			{
				// Si el modo del menu es mover se mueve el barco a la celda seleccionada
				if (_menu.currentMenuMode() == Menu.MENU_MODE_MOVE)
				{
					// Si la celda seleccionada esta habilitada muevo el barco
					if (_gridComponent.isCellEnabled(event.coordinate))
					{
						moveAction(_selectedShip, event.coordinate);
					}
				}
				else if (_menu.currentMenuMode() == Menu.MENU_MODE_FIRE)
				{
					//que el webservice devuelva el radom con la coordenada
					fireAction(_selectedShip, event.coordinate, _menu.currentFireMode);
				}
			}
		}
		
		// Modo de mover el barco
		private function moveMode():void
		{
			if (_selectedShip != null)
			{
				drawMovements();
			}
		}
		
		private function createAndLocateShips():void
		{
			var c1:Coordinate = new Coordinate(10, 12);
			var d1:Cardinal = new Cardinal(Cardinal.S);
			
			var c2:Coordinate = new Coordinate(18, 10);
			var d2:Cardinal = new Cardinal(Cardinal.N);
			
			var c3:Coordinate = new Coordinate(15, 15);
			var d3:Cardinal = new Cardinal(Cardinal.SE);
			
			var c4:Coordinate = new Coordinate(15, 18);
			var d4:Cardinal = new Cardinal(Cardinal.W);
			
			_redShipComponent = new RedShip(1, c1, d1, 4, 3);
			_blueShipComponent1 = new BlueShip(2, c2, d2, 4, 1);
			_blueShipComponent2 = new BlueShip(3, c3, d3, 4, 1);
			_blueShipComponent3 = new BlueShip(4, c4, d4, 4, 1);
			
			setShipCellStatus(_redShipComponent, true);
			setShipCellStatus(_blueShipComponent1, true);
			setShipCellStatus(_blueShipComponent2, true);
			setShipCellStatus(_blueShipComponent3, true);
			
			_redShipComponent.addEventListener(SelectedShipEvent.CLICK, selectedShipEvent);
			_blueShipComponent1.addEventListener(SelectedShipEvent.CLICK, selectedShipEvent);
			_blueShipComponent2.addEventListener(SelectedShipEvent.CLICK, selectedShipEvent);
			_blueShipComponent3.addEventListener(SelectedShipEvent.CLICK, selectedShipEvent);
			
			_shipList = new Array();
			_shipList.push(_redShipComponent);
			_shipList.push(_blueShipComponent1);
			_shipList.push(_blueShipComponent2);
			_shipList.push(_blueShipComponent3);
		}
		
		// Evento disparado cuando se selecciona un barco
		public function selectedShipEvent(event:SelectedShipEvent):void
		{
			var selectedShip:Ship = event.selectedShip;
			// El usuario selecciono el barco rojo
			if ((selectedShip.shipId == RED_SHIP_ID && _redPlayer.username == _myUsername) || (selectedShip.shipId > RED_SHIP_ID && _bluePlayer.username == _myUsername))
			{
				if (_selectedShip != null)
					_selectedShip.filters = null;
				// Si hay un barco seleccionado lo marcamos en la pantalla, en caso contrario desmarcamos
				// el que previamente estuviese marcado
				if (selectedShip != _selectedShip)
				{
					_selectedShip = selectedShip;
					var glow:GlowFilter = new GlowFilter(0xFF0000);
					_selectedShip.filters = [glow];
				}
				else
				{
					_gridComponent.disableCells();
					_selectedShip.filters = null;
					_selectedShip = null;
				}
			}
		}
		
		// Dibuja los movimientos posibles del barco
		private function drawMovements():void
		{
			var forwardCoordinate:Coordinate = new Coordinate(_selectedShip.currentPos.r, _selectedShip.currentPos.c);
			var backwardsCoordinate:Coordinate = new Coordinate(_selectedShip.currentPos.r, _selectedShip.currentPos.c);
			var backwardsDirection:Cardinal = Helper.getOppositeDirection(_selectedShip.direction);
			var cellEnabled:Boolean = true;
			
			if (_selectedShip.size > 1)
			{
				forwardCoordinate = Helper.calculateNextCell(_selectedShip.currentPos, _selectedShip.direction);
				backwardsCoordinate = Helper.calculateNextCell(_selectedShip.currentPos, backwardsDirection);
			}
			
			for (var i:int = 0; i < _selectedShip.speed; i++)
			{
				forwardCoordinate = Helper.calculateNextCell(forwardCoordinate, _selectedShip.direction);
				cellEnabled = _gridComponent.enableCell(forwardCoordinate);
				if (!cellEnabled)
					break;
			}
			for (var j:int = 0; j < _selectedShip.speed / 2; j++)
			{
				backwardsCoordinate = Helper.calculateNextCell(backwardsCoordinate, backwardsDirection);
				cellEnabled = _gridComponent.enableCell(backwardsCoordinate);
				if (!cellEnabled)
					break;
			}
		}
		
		// Bloquea/Desbloquea las celdas que el barco ocupa/ocupo
		private function setShipCellStatus(ship:Ship, enabled:Boolean):void
		{
			_gridComponent.setCellStatus(ship.currentPos, enabled);
			if (ship.size > 1)
			{
				_gridComponent.setCellStatus(Helper.calculateNextCell(ship.currentPos, ship.direction), enabled);
				_gridComponent.setCellStatus(Helper.calculateNextCell(ship.currentPos, Helper.getOppositeDirection(ship.direction)), enabled);
			}
		}
		
		// Actualiza los movimientos del turno y refleja en el UI la cantidad de movimientos restantes
		private function updateMovesLeft():void
		{
			_turn.movesLeft = _turn.movesLeft - 1;
			_movesLeftLabel.text = _turn.movesLeft.toString();
		}
		
		// Mueve el barco a la posicion dada y actualiza el estado del juego
		private function moveAction(ship:Ship, coordinate:Coordinate, func:Function = null):void
		{
			// Desbloqueamos las celdas actuales del barco
			setShipCellStatus(ship, false);
			// Se mueve el barco
			ship.moveTo(coordinate, func);
			// Se actualiza la posicion del barco
			ship.setPosition(coordinate);
			// Se deshabilita las celdas de movimiento previamente mostradas
			_gridComponent.disableCells();
			// Actualizo la cantidad de movimientos restantes del turno
			updateMovesLeft();
			// Si quedan movimientos y yo soy el usuario activo muestro las celdas de movimientos
			if (_turn.movesLeft > 0 && isActivePlayer())
			{
				// Se muestran nuevas celdas de movimiento basadas en la nueva posicion del barco
				drawMovements();
			}
			// Bloqueamos la nueva posicion del barco
			setShipCellStatus(ship, true);
		}
		
		// Rota el barco a la direccion dada y actualiza el estado del juego
		private function rotateAction(ship:Ship, direction:Cardinal, func:Function = null):void
		{
			// Actualizamos las celdas bloqueadas por el barco
			setShipCellStatus(ship, false);
			// Rotamos el barco
			ship.rotateTo(direction.cardinal, func);
			// Seteamos la nueva direccion del barco
			ship.direction = direction;
			// Actualizamos las celdas bloqueadas por el barco en su nueva posicion
			setShipCellStatus(ship, true);
			// Actualizamos los movimientos restantes
			updateMovesLeft();
		}
		
		// Dispadra un projectil desde un barco dado
		private function fireAction(ship:Ship, target:Coordinate, projectile:int, func:Function = null):void {
			if(projectile == Projectile.WEAPON_TYPE_BULLET){
				ship.fireBullet(target, func);
			}else {
				ship.fireTorpedo(func);
			}
			updateMovesLeft();
			
		}
		// Dado un id de un barco lo retorna
		private function getShipById(shipId:int):Ship
		{
			var ship:Ship = null;
			for (var i:int = 0; i < _shipList.length; i++)
			{
				if (_shipList[i].shipId == shipId)
					ship = _shipList[i];
			}
			return ship;
		}
		
		// Si el usuario activo soy yo mismo retorno true, de lo contrario retorno false
		private function isActivePlayer():Boolean
		{
			return _turn.activePlayer.username == _myUsername;
		}
	}
}