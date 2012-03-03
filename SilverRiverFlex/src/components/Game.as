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
		
		private var _main:Main;
		private var _board:Canvas;
		private var _menu:Menu;
		private var _mapComponent:Map;
		private var _iter:int = 0;
		private var _actions:ResultEvent;
		private var _actionQueue:ArrayList;
		private var _shouldDecreaseTime:Boolean;
		private var _gameMode:GameMode;
		private var _componentsToShow:ArrayList;
		// Controles UI
		private var _movesLeftLabel:Label;
		private var _timeLeftLabel:Label;
		private var _waitingPlayerLabel:Label;
		private var _activePlayerLabel:Label;
		private var _myUsernameLabel:Label;
		
		public var _gridComponent:GameGrid;
		public var _redShipComponent:RedShip;
		public var _blueShipComponent1:BlueShip;
		public var _blueShipComponent2:BlueShip;
		public var _blueShipComponent3:BlueShip;
		public var _shipList:Array;
		public var _selectedShip:Ship;
		public var _turn:Turn;
		public var _myUsername:String;
		public var _redPlayer:Player;
		public var _bluePlayer:Player;
		private var _gameId:int;
		
		public function Game(main:Main, username:String)
		{
			// initialize variables
			_main = main;
			_gameMode = new GameMode(GameMode.NEWGAME);
			_myUsername = username;
			_actionQueue = new ArrayList();
			_shouldDecreaseTime = false;
			
			_main.wsRequest.newGame(username);
		}
		
		public function newGameHandler(response:ResultEvent):void
		{
			var gameId:int = response.result as int;
			if (gameId >= 0)
			{
				_gameId = gameId;
				_gameMode.gameMode = GameMode.GETTING_GAME;
				startSyncronizing();
				_main.wsRequest.getGame(gameId);
			}
			else
			{
				_waitingPlayerLabel = new Label();
				_waitingPlayerLabel.text = "Esperando un segundo jugador...";
				_waitingPlayerLabel.x = _main.width / 3;
				_waitingPlayerLabel.y = _main.height / 3;
				_waitingPlayerLabel.setStyle("fontSize", 30);
				_waitingPlayerLabel.setStyle("color", 0x000000);
				_waitingPlayerLabel.setStyle("fontStyle", "bold");
				_main.addElement(_waitingPlayerLabel);
				_gameMode.gameMode = GameMode.WAITING_FOR_PLAYER;
				startSyncronizing();
			}
		}
		
		public function checkGameIdHandler(response:ResultEvent):void
		{
			var gameId:int = response.result as int;
			if (gameId >= 0)
			{
				_gameId = gameId;
				trace("getting new game");
				_main.wsRequest.getGame(gameId);
				_gameMode.gameMode = GameMode.GETTING_GAME;
			}
			else
			{
				trace("still waiting for another player");
			}
		}
		
		private function initializeGame(game:Object):void
		{
			var redShip:Object = game.ships[0];
			var blueShip1:Object = game.ships[1];
			var blueShip2:Object = game.ships[2];
			var blueShip3:Object = game.ships[3];
			var turn:Object = game.turn;
			_bluePlayer = new Player(game.bluePlayer.username);
			_redPlayer = new Player(game.redPlayer.username);
			trace(_redPlayer.username + " vs " + _bluePlayer.username);
			
			_redShipComponent = new RedShip(redShip.id, new Coordinate(redShip.position.y, redShip.position.x), new Cardinal(redShip.orientation.direction), redShip.speed, redShip.size);
			_blueShipComponent1 = new BlueShip(blueShip1.id, new Coordinate(blueShip1.position.y, blueShip1.position.x), new Cardinal(blueShip1.orientation.direction), blueShip1.speed, blueShip1.size);
			_blueShipComponent2 = new BlueShip(blueShip2.id, new Coordinate(blueShip2.position.y, blueShip2.position.x), new Cardinal(blueShip2.orientation.direction), blueShip2.speed, blueShip2.size);
			_blueShipComponent3 = new BlueShip(blueShip3.id, new Coordinate(blueShip3.position.y, blueShip3.position.x), new Cardinal(blueShip3.orientation.direction), blueShip3.speed, blueShip3.size);
			
			_shipList = new Array(_redShipComponent, _blueShipComponent1, _blueShipComponent2, _blueShipComponent3);
			_turn = new Turn(turn.movesLeft, _redPlayer, turn.timeLeft);
			if (isActivePlayer())
			{
				_gameMode.gameMode = GameMode.PLAYING;
			}
			else
			{
				_gameMode.gameMode = GameMode.WAITING_PLAYER_TURN;
			}
		}
		
		private function loadUserInterface():void
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
			_gridComponent.goalCells(_mapComponent.getGoalCoordinates());
			_gridComponent.portCells(_mapComponent.getPortCoordinates());
			
			// Agrega los barcos al mapa
			addShipsToUI();
			
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
			
			// Label de movimientos restantes
			_movesLeftLabel = new Label();
			_movesLeftLabel.text = "Movimientos restantes: " + _turn.movesLeft.toString();
			_movesLeftLabel.x = 10;
			_movesLeftLabel.y = 10;
			_movesLeftLabel.setStyle("fontSize", 20);
			_movesLeftLabel.setStyle("color", 0xFFCC33);
			_movesLeftLabel.setStyle("fontStyle", "bold");
			_main.addElement(_movesLeftLabel);
			
			// Label de tiempo restante
			_timeLeftLabel = new Label();
			_timeLeftLabel.text = "Tiempo restante: " + _turn.timeLeft.toString();
			_timeLeftLabel.x = 10;
			_timeLeftLabel.y = 30;
			_timeLeftLabel.setStyle("fontSize", 20);
			_timeLeftLabel.setStyle("color", 0xFFCC33);
			_timeLeftLabel.setStyle("fontStyle", "bold");
			_main.addElement(_timeLeftLabel);
			
			// Label de jugador
			_myUsernameLabel = new Label();
			_myUsernameLabel.text = "Jugador: " + _myUsername;
			_myUsernameLabel.x = 10;
			_myUsernameLabel.y = 50;
			_myUsernameLabel.setStyle("fontSize", 20);
			_myUsernameLabel.setStyle("color", 0xFFCC33);
			_myUsernameLabel.setStyle("fontStyle", "bold");
			_main.addElement(_myUsernameLabel);
			
			// Label de jugador activo
			_activePlayerLabel = new Label();
			_activePlayerLabel.text = "Jugador activo: " + _turn.activePlayer.username;
			_activePlayerLabel.x = 10;
			_activePlayerLabel.y = 70;
			_activePlayerLabel.setStyle("fontSize", 20);
			_activePlayerLabel.setStyle("color", 0xFFCC33);
			_activePlayerLabel.setStyle("fontStyle", "bold");
			_main.addElement(_activePlayerLabel);
			
			_menu = new Menu(Menu.MENU_POSITION_BOTTOM_LEFT);
			_menu.addEventListener(ActionEvent.MODE_CHANGED, function(event:ActionEvent):void
				{
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
		}
		
		public function getGameHandler(response:ResultEvent):void
		{
			initializeGame(response.result);
			loadUserInterface();
		}
		
		public function moveHandler(response:ResultEvent):void
		{
			trace("el server se actualizo con la nueva posicion del barco");
		}
		
		public function fireHandler(response:ResultEvent):void
		{
			trace("el disparo del server");
			var coordinate:Coordinate = new Coordinate(response.result.hitCoordinate.y, response.result.hitCoordinate.x);
			var affectedShip:Ship = null;
			if (response.result.affectedShip != null)
				affectedShip = getShipById(response.result.affectedShip.id);
			if (response.result.hit == true)
			{
				
			}
			else
			{
				
			}
			fireAction(_selectedShip, affectedShip, response.result.hit, coordinate, response.result.weaponType.weapon);
		}
		
		public function rotateHandler(response:ResultEvent):void
		{
			trace("el server se actualizo con la nueva direccion del barco");
		}
		
		public function endTurnHandler(response:ResultEvent):void
		{
			trace("nuevo turno obtenido del servidor");
			trace("usuario activo: " + response.result.playerTurn.username);
		}
		
		public function endTurnFaultHandler(response:FaultEvent):void
		{
			trace("error al invocar WS");
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
			if (_gameMode.gameMode == GameMode.WAITING_FOR_PLAYER)
			{
				_main.wsRequest.checkGameId();
			}
			else if (_gameMode.gameMode == GameMode.PLAYING)
			{
				// trace("Soy user " + _turn.activePlayer.username + " y estoy jugando");
			}
			else if (_gameMode.gameMode == GameMode.WAITING_PLAYER_TURN)
			{
				// trace("El user " + _turn.activePlayer.username + " esta jugando");
				_main.wsRequest.getActions(_gameId, _myUsername);
			}
			
			if (_gameMode.gameMode == GameMode.PLAYING || _gameMode.gameMode == GameMode.WAITING_PLAYER_TURN)
				updateTimeLeft();
		}
		
		private function updateTimeLeft():void
		{
			if (_shouldDecreaseTime)
			{
				_turn.timeLeft--;
				_timeLeftLabel.text = "Tiempo restante: " + _turn.timeLeft.toString();
				if (_turn.timeLeft <= 0 && isActivePlayer())
				{
					trace("CAMBIO DE TURNO");
					endTurnAction();
				}
			}
			_shouldDecreaseTime = !_shouldDecreaseTime;
		}
		
		public function consumeActionsHandler(response:ResultEvent):void
		{
			if (response.result != null)
			{
				trace("tengo una accion en la cola");
				// Agregamos las nuevas acciones a ejecutar a la cola de acciones
				_actionQueue.addItem(response.result);
				
				consumeNextAction();
			}
			else
			{
				trace("no hay nada en la cola para consumir");
			}
		}
		
		public function consumeActionsFaultHandler(response:FaultEvent):void
		{
			trace("fallo llamada a consumir acciones");
		}
		
		public function consumeNextAction():void
		{
			if (_actionQueue != null && _actionQueue.length > 0)
			{
				var action:Object = _actionQueue.source[0];
				var ship:Ship;
				if (action.actionType == "MoveAction")
				{
					ship = getShipById(action.ship.id);
					moveAction(ship, new Coordinate(action.position.y, action.position.x), consumeNextAction);
				}
				else if (action.actionType == "RotateAction")
				{
					ship = getShipById(action.ship.id);
					rotateAction(ship, new Cardinal(action.cardinal.direction), consumeNextAction);
				}
				else if (action.actionType == "FireAction")
				{
					ship = getShipById(action.ship.id);
					var affectedShip:Ship = null;
					if (action.hit && action.affectedShip != null)
						affectedShip = getShipById(action.affectedShip.id);
					
					fireAction(ship, affectedShip, action.hit, new Coordinate(action.position.y, action.position.x), action.weaponType.weapon, consumeNextAction);
				}
				else if (action.actionType == "EndTurnAction")
				{
					// TODO: solucionar problema con los turnos
					endTurnAction();
				}
				else if (action.actionType == "EnterPortAction")
				{
					trace("EnterPortAction");
				}
				else if (action.actionType == "LeavePortAction")
				{
					trace("LeavePortAction");
				}
				else if (action.actionType == "EndGameAction")
				{
					trace("EndGameAction");
				}
				_actionQueue.removeItemAt(0);
			}
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
					var coor:Object = new Object();
					coor.x = event.coordinate.c;
					coor.y = event.coordinate.r;
					// Llamamos al webservice con la accion de disparo
					_main.wsRequest.fire(_gameId, _selectedShip.shipId, coor, _menu.currentFireMode);
						// 	fireAction(_selectedShip, event.coordinate, _menu.currentFireMode);
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
		
		private function addShipsToUI():void
		{
			setShipCellStatus(_redShipComponent, true);
			setShipCellStatus(_blueShipComponent1, true);
			setShipCellStatus(_blueShipComponent2, true);
			setShipCellStatus(_blueShipComponent3, true);
			
			_redShipComponent.addEventListener(SelectedShipEvent.CLICK, selectedShipEvent);
			_blueShipComponent1.addEventListener(SelectedShipEvent.CLICK, selectedShipEvent);
			_blueShipComponent2.addEventListener(SelectedShipEvent.CLICK, selectedShipEvent);
			_blueShipComponent3.addEventListener(SelectedShipEvent.CLICK, selectedShipEvent);
		}
		
		// Evento disparado cuando se selecciona un barco
		public function selectedShipEvent(event:SelectedShipEvent):void
		{
			var selectedShip:Ship = event.selectedShip;
			// El usuario selecciono el barco rojo
			if ((selectedShip is RedShip && _redPlayer.username == _myUsername) || (selectedShip is BlueShip && _bluePlayer.username == _myUsername))
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
			_movesLeftLabel.text = "Movimientos restantes: " + _turn.movesLeft.toString();
		}
		
		// Mueve el barco a la posicion dada y actualiza el estado del juego
		private function moveAction(ship:Ship, coordinate:Coordinate, func:Function = null):void
		{
			if (isActivePlayer())
			{
				var coor:Object = new Object();
				coor.x = coordinate.c;
				coor.y = coordinate.r;
				// Llamamos al servidor para avisarle que el barco se movio
				_main.wsRequest.move(_gameId, ship.shipId, coor);
			}
			// Se deshabilita las celdas de movimiento previamente mostradas
			_gridComponent.disableCells();
			// Actualizo la cantidad de movimientos restantes del turno
			_turn.decreaseMovesLeft();
			// Desbloqueamos las celdas actuales del barco
			setShipCellStatus(ship, false);
			// Se mueve el barco
			ship.moveTo(coordinate, function():void
				{
					//TODO: se llamarian las funciones luego de terminada la animacion como por ejemplo el chequeo de puerto o ganar
					if (checkPort())
						trace("esta en puerto");
					if (checkGoal())
						trace("ganoooooooooo");
					// Si quedan movimientos y yo soy el usuario activo muestro las celdas de movimientos
					if (_turn.hasMovesLeft() && isActivePlayer())
					{
						// Se muestran nuevas celdas de movimiento basadas en la nueva posicion del barco
						drawMovements();
					}
					// Bloqueamos la nueva posicion del barco
					setShipCellStatus(ship, true);
					if (func != null)
						func.call();
				}, 10);
		}
		
		// Rota el barco a la direccion dada y actualiza el estado del juego
		private function rotateAction(ship:Ship, direction:Cardinal, func:Function = null):void
		{
			if(rotationEnabled(ship, direction)){
				if (isActivePlayer())
				{
					// Llamamos al web service para actualizar la direccion del barco				
					_main.wsRequest.rotate(_gameId, ship.shipId, direction.cardinal);
				}
				
				// Actualizamos las celdas bloqueadas por el barco
				setShipCellStatus(ship, false);
				// Rotamos el barco
				ship.rotateTo(direction.cardinal, function():void
					{
						//TODO: se llamarian las funciones luego de terminada la animacion como por ejemplo el chequeo de puerto o ganar
						if (checkPort())
							_activePlayerLabel.text = "en el puerto";
						if (checkGoal())
							_activePlayerLabel.text = "ganoo";
						if (func != null)
							func.call();
					});
				// Seteamos la nueva direccion del barco
				ship.direction = direction;
				// Actualizamos las celdas bloqueadas por el barco en su nueva posicion
				setShipCellStatus(ship, true);
				// Actualizamos los movimientos restantes
				updateMovesLeft();
			}
		}
		
		// Cambia el control del jugador actual al jugador que estaba esperando
		private function endTurnAction():void
		{
			// Si soy el usuario activo envio al servidor el cambio de turno
			if (isActivePlayer())
			{
				_main.wsRequest.endTurn(_gameId);
			}
			// Cambia el turno y reseteo contadores
			_turn.switchTurn(_redPlayer, _bluePlayer);
			// Actualizo el contador de tiempo/movimientos y jugador actual
			// _movesLeftLabel.text = "Movimientos restantes: " + _turn.movesLeft.toString();
			_timeLeftLabel.text = "Tiempo restante: " + _turn.timeLeft.toString();
			_activePlayerLabel.text = "Jugador activo: " + _turn.activePlayer.username;
			if (isActivePlayer())
			{
				_gameMode.gameMode = GameMode.PLAYING;
				_movesLeftLabel.text = "soy el activo";
			}
			else
			{
				_gameMode.gameMode = GameMode.WAITING_PLAYER_TURN;
				_movesLeftLabel.text = "el otro es activo";
			}
		}
		
		// Dispara un projectil desde un barco dado
		private function fireAction(firingShip:Ship, affectedShip:Ship, hit:Boolean, target:Coordinate, projectile:int, func:Function = null):void
		{
			if (projectile == Projectile.WEAPON_TYPE_BULLET)
			{
				firingShip.fireBullet(target, func);
			}
			else
			{
				firingShip.fireTorpedo(func);
			}
			if (hit && affectedShip != null)
			{
				
			}
			updateMovesLeft();
		}
		
		// Dado un id de un barco lo retorna/
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
		
		// Chequea si esta en puerto
		private function checkPort():Boolean
		{
			return _mapComponent.areSubCoordinates(_selectedShip.coordinates, _mapComponent.getPortCoordinates());
		}
		
		// Chequea si gano
		private function checkGoal():Boolean
		{
			var result:Boolean = false;
			
			if (_selectedShip == _redShipComponent)
				result = _mapComponent.areSubCoordinates(_selectedShip.coordinates, _mapComponent.getPortCoordinates());
			else
				result = false;
			return result;
		}
		
		//verifica si hay celdas bloqueadas que impidan la rotacion
		private function rotationEnabled(ship:Ship, cardinal:Cardinal):Boolean {
			var result:Boolean = true;
			var tope:int = Math.floor(ship.size / 2);
			var i:int = 0;
			var currentPos:Coordinate = ship.currentPos;
			while (result && i < tope ){
				var a:Coordinate = Helper.calculateNextCell(currentPos, cardinal);				
				result = !_gridComponent.getCell(a).blocked;	
				currentPos =  a;
				i ++;
			}
			currentPos = ship.currentPos;
			i = 0;
			while (result && i < tope ){
				var b:Coordinate = Helper.calculateNextCell(ship.currentPos, Helper.getOppositeDirection(cardinal));
				result = !_gridComponent.getCell(b).blocked;	
				currentPos =  b;
				i ++;
			}
			return result
		}
	}
}