package components
{
	import common.*;
	import events.*;
	import flash.events.TimerEvent;
	import flash.utils.Timer;
	import mx.collections.ArrayCollection;
	import mx.collections.ArrayList;
	import mx.containers.Canvas;
	import mx.controls.Alert;
	import mx.controls.Label;
	import mx.core.UIComponent;
	import mx.rpc.events.ResultEvent;
	
	public class Game extends UIComponent
	{
		public static const GAME_BOARD_COLS:Number = 64;
		public static const GAME_BOARD_ROWS:Number = 36;
		public static const NEW_GAME:String = "new";
		public static const LOAD_GAME:String = "load";
		
		private var _main:Main;
		private var _board:Canvas;
		private var _menu:Menu;
		private var _mapComponent:Map;
		private var _iter:int = 0;
		private var _actions:ResultEvent;
		private var _actionQueue:ArrayList;
		private var _shouldDecreaseTime:Boolean;
		private var _gameMode:GameMode;
		private var _scrollControl:AutoScroll;
		private var _componentsToShow:ArrayList;
		private var _isAnimating:Boolean;
		// Controles UI		
		private var _waitingPlayerLabel:Label;
		private var _info:Info;
		private var _toastManager:ToastManager;
		private var timer:Timer;
		
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
		public var _me:Player;
		public var _messageModal:Modal;
		public var _rechargeModal:RechargeModal;
		private var _gameId:int;
		
		// Constructor de la clase Game
		public function Game(main:Main)
		{
			// Se inicializan las variables y se muestra el modal de nuevo juego o cargar juego
			_main = main;
			_gameMode = new GameMode(GameMode.NEWGAME);
			_actionQueue = new ArrayList();
			var modal:GameModal = new GameModal(_main);
			modal.addEventListener(ModalEvent.GAME_SELECTED, function(event:ModalEvent):void
				{
					_myUsername = event.username;
					switch (event.game)
					{
						case NEW_GAME: 
							_main.wsRequest.newGame(_myUsername);
							break;
						case LOAD_GAME: 
							_main.wsRequest.loadGame(_myUsername);
							break;
					}
				});
			// Se carga la interfaz grafica del juego
			loadUserInterface();
		}
		
		// Inicializa el juego en base a los datos que retorna el servidor
		private function initializeGame(game:Object):void
		{
			// Carga los objetos que retorna el web service
			var redShip:Object = game.ships[0];
			var blueShip1:Object = game.ships[1];
			var blueShip2:Object = game.ships[2];
			var blueShip3:Object = game.ships[3];
			var turn:Object = game.turn;
			
			_bluePlayer = new Player(game.bluePlayer.username);
			_redPlayer = new Player(game.redPlayer.username);
			if (_redPlayer.username == _myUsername)
				_me = _redPlayer;
			if (_bluePlayer.username == _myUsername)
				_me = _bluePlayer;
			
			// Crea los barcos del juego con los atributos y posiciones del servidor
			_redShipComponent = new RedShip(redShip.id, new Coordinate(redShip.position.y, redShip.position.x), new Cardinal(redShip.orientation.direction), redShip.speed, redShip.size, redShip.armor, redShip.ammo, redShip.torpedo, redShip.viewRange);
			_blueShipComponent1 = new BlueShip(blueShip1.id, new Coordinate(blueShip1.position.y, blueShip1.position.x), new Cardinal(blueShip1.orientation.direction), blueShip1.speed, blueShip1.size, blueShip1.armor, blueShip1.ammo, blueShip1.torpedo, blueShip1.viewRange);
			_blueShipComponent2 = new BlueShip(blueShip2.id, new Coordinate(blueShip2.position.y, blueShip2.position.x), new Cardinal(blueShip2.orientation.direction), blueShip2.speed, blueShip2.size, blueShip2.armor, blueShip2.ammo, blueShip2.torpedo, blueShip2.viewRange);
			_blueShipComponent3 = new BlueShip(blueShip3.id, new Coordinate(blueShip3.position.y, blueShip3.position.x), new Cardinal(blueShip3.orientation.direction), blueShip3.speed, blueShip3.size, blueShip3.armor, blueShip3.ammo, blueShip3.torpedo, blueShip3.viewRange);
			
			_shipList = new Array(_redShipComponent, _blueShipComponent1, _blueShipComponent2, _blueShipComponent3);
			_redPlayer.addShip(_redShipComponent);
			_bluePlayer.addShip(_blueShipComponent1);
			_bluePlayer.addShip(_blueShipComponent2);
			_bluePlayer.addShip(_blueShipComponent3);
			
			// Carga los datos del turno
			if (_bluePlayer.username == turn.activePlayer.username)
				_turn = new Turn(turn.movesLeft, _bluePlayer, turn.timeLeft);
			else
				_turn = new Turn(turn.movesLeft, _redPlayer, turn.timeLeft);
			
			// Si el usuario es el jugador activo se pone el estado del juego en jugando, de lo contrario queda
			// esperando los movimientos del oponente
			if (isActivePlayer())
				_gameMode.gameMode = GameMode.PLAYING;
			else
				_gameMode.gameMode = GameMode.WAITING_PLAYER_TURN;
			
			// Bloquea las celdas que ocupan los barcos
			_gridComponent.blockCells(_redShipComponent.coordinates);
			_gridComponent.blockCells(_blueShipComponent1.coordinates);
			_gridComponent.blockCells(_blueShipComponent2.coordinates);
			_gridComponent.blockCells(_blueShipComponent3.coordinates);
			
			// Si estamos cargando un juego hay posibilidad de que los barcos esten destruidos, en ese
			// caso actualizo si estado en la partida
			if (_redShipComponent.armor <= 0)
				destroyShip(_redShipComponent);
			if (_blueShipComponent1.armor <= 0)
				destroyShip(_blueShipComponent1);
			if (_blueShipComponent2.armor <= 0)
				destroyShip(_blueShipComponent2);
			if (_blueShipComponent3.armor <= 0)
				destroyShip(_blueShipComponent3);
			
			// Inicializo los eventos de click sobre los barcos
			_redShipComponent.addEventListener(SelectedShipEvent.CLICK, selectedShipEvent);
			_blueShipComponent1.addEventListener(SelectedShipEvent.CLICK, selectedShipEvent);
			_blueShipComponent2.addEventListener(SelectedShipEvent.CLICK, selectedShipEvent);
			_blueShipComponent3.addEventListener(SelectedShipEvent.CLICK, selectedShipEvent);
			
			// Agrego los barcos al mapa
			_board.addChild(_redShipComponent);
			_board.addChild(_blueShipComponent1);
			_board.addChild(_blueShipComponent2);
			_board.addChild(_blueShipComponent3);
			
			// Mostramos los barcos en el mapa
			_redShipComponent.show();
			_blueShipComponent1.show();
			_blueShipComponent2.show();
			_blueShipComponent3.show();
			
			// Seleccionamos el primer barco disponible del jugador
			selectShip(_me.getNextAliveShip());
			// Actualizamos la visibilidad de los barcos
			setShipsVisibility();
			
			// Cargamos informacion de usuarios y barcos
			_info.redPlayerUsername = _redPlayer.username;
			_info.bluePlayerUsername = _bluePlayer.username;
			_info.movesLeftText = _turn.movesLeft.toString();
			_info.timeLeftText = _turn.timeLeft.toString();
			_info.setActivePlayer(_turn.activePlayer == _redPlayer);
		
		}
		
		// Selecciona el barco recibido por pantalla en el mapa
		private function selectShip(ship:Ship):void
		{
			if (_selectedShip != null)
				_selectedShip.selected = false;
			_selectedShip = ship;
			_selectedShip.selected = true;
			centerOnShip(_selectedShip);
			_menu.updateShipInfo(_selectedShip);
			refreshMode();
		}
		
		// Inicializa todos los objetos graficos
		private function loadUserInterface():void
		{
			// Inicializa el Canvas que contiene los demas elementos
			_board = new Canvas();
			_board.x = 0;
			_board.y = 0;
			_board.horizontalScrollPolicy = "off";
			_board.verticalScrollPolicy = "off";
			_main.addElement(_board);
			_scrollControl = new AutoScroll(_main, _board);
			
			_isAnimating = false;
			
			// Inicializa el mapa
			_mapComponent = new Map();
			_board.width = _mapComponent.sprite.width;
			_board.height = _mapComponent.sprite.height;
			
			// Crea la grilla del mapa
			_gridComponent = new GameGrid(_board, GAME_BOARD_ROWS, GAME_BOARD_COLS);
			_gridComponent.addEventListener(CellEvent.CLICK, selectedCellEvent);
			_gridComponent.blockCells(_mapComponent.getInitialBlockedCoordinates());
			_gridComponent.goalCells(_mapComponent.getGoalCoordinates());
			_gridComponent.portCells(_mapComponent.getPortHalfCoordinates());
			_gridComponent.portCells(_mapComponent.getPortOneCoordinates());
			
			// Agrega los componentes al objeto Canvas
			_board.addChild(_mapComponent);
			_board.addChild(_gridComponent);
			
			// Muestra los componentes en pantalla
			_mapComponent.show();
			
			// Crea el menu y lo posiciona en la pantalla
			_menu = new Menu(Menu.MENU_POSITION_BOTTOM_LEFT);
			// Este evento es disparado cuando se cambia de opcion en el menu
			_menu.addEventListener(ActionEvent.MODE_CHANGED, function(event:ActionEvent):void
				{
					refreshMode();
				});
			// Este evento se dispara cuando se hace click sobre un punto cardinal de rotacion
			_menu.addEventListener(ActionEvent.ROTATION_CLICKED, function(event:ActionEvent):void
				{
					if (_selectedShip != null)
					{
						if (isActivePlayer() && !_isAnimating)
							rotateAction(_selectedShip, new Cardinal(event.rotation));
					}
				});
			// Este evento se dispara cuando cambia el modo de disparo
			_menu.addEventListener(ActionEvent.FIRE_MODE_CHANGED, function(event:ActionEvent):void
				{
					if (isActivePlayer() && !_isAnimating && _selectedShip != null && _menu.currentFireMode == Menu.MENU_FIRE_MODE_TORPEDO && _selectedShip.hasTorpedoes())
					{
						_main.wsRequest.fireTorpedo(_gameId, _selectedShip.shipId);
					}
					else if (isActivePlayer && _selectedShip != null && _menu.currentFireMode == Menu.MENU_FIRE_MODE_BULLET)
					{
						_toastManager.addToast("Haz click sobre un barco enemigo al cual dispararle");
					}
				});
			// Este evento se dispara cuando se hace click en el boton terminar turno
			_menu.addEventListener(ActionEvent.TURN_SKIP, function():void
				{
					if (isActivePlayer())
					{
						if (!_isAnimating)
							_main.wsRequest.endTurn(_gameId);
						else
							_toastManager.addToast("Debes esperar a que se termine la accion antes de poder terminar el turno");
					}
					else
						_toastManager.addToast("Para poder terminar el turno debes ser el jugador activo");
				});
			// Este evento se dispara cuando se hace click en el boton guardar y terminar
			_menu.addEventListener(ActionEvent.SAVE_GAME, function():void
				{
					if (isActivePlayer())
					{
						if (!_isAnimating)
							_main.wsRequest.saveGame(_gameId);
						else
							_toastManager.addToast("Debes esperar a que se termine la accion antes de guardar");
					}
					else
						_toastManager.addToast("Para poder guardar debes ser el jugador activo");
				});
			// Se agrega el menu a la pantalla
			_main.addElement(_menu);
			
			// Se agrega a la pantalla el panel de informacion sobre los barcos
			_info = new Info();
			_main.addElement(_info);
			
			// Se crea y se agrega a la pantalla el sistema de notificaciones
			_toastManager = new ToastManager();
			_main.addElement(_toastManager);
		}
		
		/**
		 * ************************************************
		 *
		 *  TIME HELPERS
		 *
		 * ************************************************
		 * */
		
		// Inicia el timer
		public function startSyncronizing():void
		{
			timer = new Timer(500, 0);
			timer.addEventListener(TimerEvent.TIMER, timerHandler);
			timer.start();
		}
		
		// Evento disparado cada 500 millisegundos
		private function timerHandler(event:TimerEvent):void
		{
			// Si el modo de juego es esperando jugador se chequea si hay una partida disponible
			if (_gameMode.gameMode == GameMode.WAITING_FOR_PLAYER)
			{
				_main.wsRequest.checkGameId();
			} // Si el modo de juego es esperando para cargar sigo preguntando al servidor si hay algun contrincante
			else if (_gameMode.gameMode == GameMode.WAITING_FOR_LOADING)
			{
				_main.wsRequest.getGameIdLoading(_myUsername);
			} // En este caso estoy jugando, solo se realizan acciones no se consumen
			else if (_gameMode.gameMode == GameMode.PLAYING)
			{
				if (_messageModal != null && _messageModal.isOpened)
					_messageModal.close();
			} // En este caso el jugador esta consumiendo las acciones del contrincante
			else if (_gameMode.gameMode == GameMode.WAITING_PLAYER_TURN)
			{
				if (_messageModal != null && _messageModal.isOpened)
					_messageModal.close();
				_main.wsRequest.getActions(_gameId, _myUsername);
			}
			// Si estoy jugando o estoy esperando por jugador actualizo el tiempo restante del turno
			if (_gameMode.gameMode == GameMode.PLAYING || _gameMode.gameMode == GameMode.WAITING_PLAYER_TURN)
				updateTimeLeft();
		}
		
		// Actualiza el tiempo restante del turno
		private function updateTimeLeft():void
		{
			if (_shouldDecreaseTime)
			{
				_turn.decreaseTimeLeft();
				_info.timeLeftText = _turn.timeLeft.toString();
			}
			if (_turn.timeLeft > 0)
				_shouldDecreaseTime = !_shouldDecreaseTime;
			else
				_shouldDecreaseTime = false;
			
			if (_turn.timeLeft <= 0 && isActivePlayer() && !_isAnimating)
			{
				if (_rechargeModal != null && _rechargeModal.isOpened)
					_rechargeModal.close();
				_main.wsRequest.endTurn(_gameId);
			}
		}
		
		/**
		 * ************************************************
		 *
		 *  HANDLERS
		 *
		 * ************************************************
		 * */
		
		// Evento disparado cuando el servidor retorna un id de juego, si el mismo es mayor que cero es que
		// hay una partida disponible, de lo contrario sigue sin haber un contrincante
		public function newGameHandler(response:ResultEvent):void
		{
			var gameId:int = response.result as int;
			if (gameId >= 0)
			{
				_gameId = gameId;
				_gameMode.gameMode = GameMode.GETTING_GAME;
				_main.wsRequest.getGame(gameId);
			}
			else
			{
				_messageModal = new Modal(_main, "Esperando a un segundo jugador...", 300, 130);
				_gameMode.gameMode = GameMode.WAITING_FOR_PLAYER;
			}
			startSyncronizing();
		}
		
		// Evento disparado cuando el servidor retorna un id de juego cargado, si el mismo es mayor que cero
		// se carga la partida si es -1 es que no hay ningun oponente todavia y si es -2 el usuario no tiene 
		// partidas para cargar
		public function loadGameHandler(response:ResultEvent):void
		{
			var gameId:int = response.result as int;
			if (gameId >= 0)
			{
				_gameId = gameId;
				_gameMode.gameMode = GameMode.GETTING_GAME;
				_main.wsRequest.getGame(gameId);
			}
			else
			{
				// Si el gameId es -1 es que tiene partidas para cargar en la base de datos
				if (gameId == -1)
				{
					_messageModal = new Modal(_main, "Esperando a un segundo jugador...", 300, 130);
					_gameMode.gameMode = GameMode.WAITING_FOR_LOADING;
				}
				else
				{
					showEndGameMessage("No existen partidas", "El usuario ingresado no posee partidas para cargar");
				}
			}
			// Solo inicializamos el timer si el usuario tiene posibles partidas para cargar
			if (gameId >= -1)
				startSyncronizing();
		}
		
		// Evento disparado cuando se guardo un juego en el servidor
		public function saveGameHandler(response:ResultEvent):void
		{
			saveGameAction();
		}
		
		// Evento disparado cuando se quiere cargar un juego del servidor
		public function checkGameIdHandler(response:ResultEvent):void
		{
			var gameId:int = response.result as int;
			if (gameId >= 0)
			{
				_gameId = gameId;
				_main.wsRequest.getGame(gameId);
				_gameMode.gameMode = GameMode.GETTING_GAME;
			}
		}
		
		// Evento disparado cuando el jugador esperando obtiene las acciones realizadas por el contrincante del servidor
		public function consumeActionsHandler(response:ResultEvent):void
		{
			// Si la respuesta es distinta de nula significa que el oponente realizo al menos una accion
			if (response.result != null)
			{
				// Si el resultado es un array significa que vinieron mas de una accion juntas, las agrego a la cola
				if (response.result is ArrayCollection)
				{
					for (var i:int = 0; i < response.result.length; i++)
					{
						_actionQueue.addItem(response.result[i]);
					}
				}
				else
				{
					// Agregamos las nuevas acciones a ejecutar a la cola de acciones
					_actionQueue.addItem(response.result);
				}
				// Llamamos a consumir la primera accion
				consumeNextAction();
			}
		}
		
		// Evento disparado cuando un jugador realiza un disparo
		public function fireHandler(response:ResultEvent):void
		{
			var coordinate:Coordinate = null;
			var affectedShip:Ship = null;
			var newArmor:int = -1;
			// Si la coordenada de disparo es null es porque el disparo es de tipo torpedo
			if (response.result.hitCoordinate != null)
			{
				coordinate = new Coordinate(response.result.hitCoordinate.y, response.result.hitCoordinate.x);
			}
			// Si hay un barco afectado cargo el nuevo armor del barco afectado
			if (response.result.affectedShip != null)
			{
				affectedShip = getShipById(response.result.affectedShip.id);
				newArmor = response.result.affectedShip.armor;
			}
			fireAction(_selectedShip, affectedShip, newArmor, response.result.hit, coordinate, response.result.weaponType.weapon);
		}
		
		// Evento disparado cuando se realiza una rotacion
		public function rotateHandler(response:ResultEvent):void
		{
		}
		
		// Evento disparado cuando el jugador finaliza el turno
		public function endTurnHandler(response:ResultEvent):void
		{
			endTurnAction();
		}
		
		// Evento disparado cuando se termina el juego
		public function endGameHandler(response:ResultEvent):void
		{
			endGameAction();
		}
		
		// Evento disparado cuando se obtiene un juego del servidor
		public function getGameHandler(response:ResultEvent):void
		{
			initializeGame(response.result);
		}
		
		// Evento disparado cuando se mueve un barco
		public function moveHandler(response:ResultEvent):void
		{
		}
		
		// Evento disparado cuando se entra a puerto
		public function enterPortHandler(response:ResultEvent):void
		{
			if (response.result.ship != null)
			{
				//DeveloperUtils.trObject(response.result.ship);
				var ship:Ship = getShipById(response.result.ship.id);
				enterPortAction(ship, response.result.ship.armor, response.result.ship.ammo, response.result.ship.torpedo);
			}
		}
		
		/**
		 * ************************************************
		 *
		 *  EVENTS
		 *
		 * ************************************************
		 * */
		
		// Evento disparado cuando se selecciona un barco
		public function selectedShipEvent(event:SelectedShipEvent):void
		{
			var ship:Ship = event.selectedShip;
			// El usuario selecciono su propio barco
			if (_me.isMyShip(ship))
			{
				//si el barco es distinto al seleccionado, desseleccionamos el anterior y seleccionamos el nuevo
				if (_selectedShip != ship)
					selectShip(ship);
			}
			else
			{
				// Si el modo de disparo es artillería y seleccione un barco enemigo, disparo hacia el mismo
				if (_menu.currentMode == Menu.MENU_MODE_FIRE)
				{
					// Si es el usuario activo disparo
					if (isActivePlayer() && !_isAnimating)
					{
						// Si quedan balas disparo
						if (_selectedShip.hasAmmo() && _selectedShip.isInFireRange(ship.currentPos))
						{
							var coor:Object = new Object();
							coor.x = ship.currentPos.c;
							coor.y = ship.currentPos.r;
							
							// Llamamos al webservice con la accion de disparo
							_main.wsRequest.fireAmmo(_gameId, _selectedShip.shipId, coor);
						}
						else
						{
							if (!_selectedShip.hasAmmo())
								_toastManager.addToast("El barco no tiene municiones");
							if (!_selectedShip.isInFireRange(ship.currentPos))
								_toastManager.addToast("El barco seleccionado esta fuera del rango de disparo");
						}
					}
				}
			}
		}
		
		// Evento disparado cuando se selecciona una celda de la grilla
		private function selectedCellEvent(event:CellEvent):void
		{
			if (_selectedShip != null && isActivePlayer() && _turn.hasMovesLeft() && !_isAnimating)
			{
				// Si el modo del menu es mover se mueve el barco a la celda seleccionada
				if (_menu.currentMode == Menu.MENU_MODE_MOVE)
				{
					// Si la celda seleccionada esta habilitada muevo el barco
					if (_gridComponent.isCellEnabled(event.coordinate))
					{
						moveAction(_selectedShip, event.coordinate);
					}
					else
					{
						_toastManager.addToast("La celda seleccionada no esta disponible para el movimiento");
					}
				}
			}
		}
		
		/**
		 * ************************************************
		 *
		 *  ACTIONS
		 *
		 * ************************************************
		 * */
		
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
			updateMovesLeft();
			// Desbloqueamos las celdas actuales del barco			
			_gridComponent.unblockCells(ship.coordinates);
			_isAnimating = true;
			// Se mueve el barco
			ship.moveTo(coordinate, function():void
				{
					//refrescamos para que habilite la accion mover nuevamente
					ship.setPosition(coordinate);
					_isAnimating = false;
					// Se muestran nuevas celdas de movimiento basadas en la nueva posicion del barco
					refreshMode();
					// Bloqueamos la nueva posicion del barco
					_gridComponent.blockCells(ship.coordinates);
					checkPort(ship);
					checkGoal(ship);
					// Actualizo la visibilidad de los barcos
					setShipsVisibility();
					endTurnIfNoMovesLeftAndActivePlayer();
					if (func != null)
						func.call();
				}, 10);
		}
		
		// Rota el barco a la direccion dada y actualiza el estado del juego
		private function rotateAction(ship:Ship, direction:Cardinal, func:Function = null):void
		{
			if (rotationEnabled(ship, direction))
			{
				if (isActivePlayer())
				{
					// Llamamos al web service para actualizar la direccion del barco				
					_main.wsRequest.rotate(_gameId, ship.shipId, direction.cardinal);
				}
				// Actualizamos los movimientos restantes
				updateMovesLeft();
				// Actualizamos las celdas bloqueadas por el barco
				_gridComponent.unblockCells(ship.coordinates);
				// Seteamos la nueva direccion del barco
				ship.direction = direction;
				_isAnimating = true;
				// Rotamos el barco
				ship.rotateTo(direction.cardinal, function():void
					{
						_isAnimating = false;
						endTurnIfNoMovesLeftAndActivePlayer()
						// Actualizamos las celdas bloqueadas por el barco en su nueva posicion
						_gridComponent.blockCells(ship.coordinates);
						checkPort(ship);
						checkGoal(ship);
						if (func != null)
							func.call();
					});
			}
		}
		
		// Cambia el control del jugador actual al jugador que estaba esperando
		private function endTurnAction():void
		{
			// Cambia el turno y reseteo contadores
			_turn.switchTurn(_redPlayer, _bluePlayer);
			// Cambia el modo
			refreshMode();
			// Actualizo el contador de tiempo/movimientos y jugador actual
			_info.movesLeftText = _turn.movesLeft.toString();
			_info.timeLeftText = _turn.timeLeft.toString();
			_info.setActivePlayer(_turn.activePlayer.username == _redPlayer.username);
			
			if (isActivePlayer())
			{
				_toastManager.addToast("Es tu turno");
				_gameMode.gameMode = GameMode.PLAYING;
			}
			else
			{
				_toastManager.addToast("Es el turno de tu oponente");
				_gameMode.gameMode = GameMode.WAITING_PLAYER_TURN;
			}
		}
		
		// Dispara un projectil desde un barco dado
		private function fireAction(firingShip:Ship, affectedShip:Ship, newArmor:int, hit:Boolean, target:Coordinate, projectile:int, func:Function = null):void
		{
			_isAnimating = true;
			updateMovesLeft();
			
			if (projectile == Projectile.WEAPON_TYPE_BULLET)
			{
				// Decremento la cantidad de balas
				firingShip.decreaseAmmo();
				// Ejecuto la accion de disparar
				firingShip.fireBullet(target, function():void
					{
						endTurnIfNoMovesLeftAndActivePlayer();
						// Actualizo el daño recibido en el barco
						if (hit && affectedShip != null)
						{
							checkFireHit(affectedShip, newArmor);
						}
						if (!hit)
							_toastManager.addToast("El disparo no impacto en el barco enemigo");
						_isAnimating = false;
						if (func != null)
							func.call();
					});
			}
			else
			{
				// Decremento la cantidad de torpedos
				firingShip.decreaseTorpedoes();
				var coordinate:Coordinate = null;
				if (affectedShip != null)
					coordinate = new Coordinate(target.r, target.c);
				else
				{
					var nextCell:Coordinate = new Coordinate(firingShip.currentPos.r, firingShip.currentPos.c);
					if (firingShip.size > 1)
						nextCell = Helper.calculateNextCell(nextCell, firingShip.direction);
					coordinate = calculateTorpedoCell(nextCell, firingShip.direction);
				}
				// Ejecuto la accion de disparar
				firingShip.fireTorpedo(coordinate, function():void
					{
						endTurnIfNoMovesLeftAndActivePlayer();
						// Actualizo el daño recibido en el barco
						if (hit && affectedShip != null)
						{
							checkFireHit(affectedShip, newArmor);
						}
						_isAnimating = false;
						if (func != null)
							func.call();
					});
			}
			if (isActivePlayer())
				_menu.updateShipInfo(firingShip);
		}
		
		// Realiza la entrada a puerto, dependiendo del puerto acualiza los atributos del barco
		private function enterPortAction(ship:Ship, newArmor:int, newAmmo:int, newTorpedoes:int):void
		{
			ship.armor = newArmor;
			ship.ammo = newAmmo;
			ship.torpedoes = newTorpedoes;
			if (isActivePlayer())
			{
				if (ship.selected)
					_menu.updateShipInfo(ship);
				_main.wsRequest.endTurn(_gameId);
			}
		
		}
		
		// Realiza la accion de terminar el juego y mostrar los correspondientes mensajes
		private function endGameAction():void
		{
			timer.stop();
			// Si el jugador rojo no tiene el barco vivo entonces gano el azul
			if (!_redPlayer.hasAliveShips())
				if (_me == _redPlayer)
					showEndGameMessage("Haz perdido", "El jugador azul ha ganado el juego");
				else
					showEndGameMessage("Ganador!!!", "Haz ganado el juego");
			// Si el jugador azul no tiene barcos vivos entonces gano el jugador rojo
			if (!_bluePlayer.hasAliveShips())
				if (_me == _bluePlayer)
					showEndGameMessage("Haz perdido", "El jugador rojo ha ganado el juego");
				else
					showEndGameMessage("Ganador!!!", "Haz ganado el juego");
			// Si el jugador rojo entro con el barco a una de las zonas de escape gano el juego
			var redShip:Ship = _redPlayer.getShip();
			if (_mapComponent.areSubCoordinates(redShip.coordinates, _mapComponent.getGoalCoordinates()))
			{
				if (_me == _redPlayer)
					showEndGameMessage("Ganador!!!", "Haz ganado el juego");
				else
					showEndGameMessage("Haz perdido", "El jugador rojo ha ganado el juego");
			}
		}
		
		// Finaliza el juego y muestra el mensaje de juego salvado
		private function saveGameAction():void
		{
			if (isActivePlayer())
				showEndGameMessage("Juego guardado", "El juego fue guardado");
			else
				showEndGameMessage("Juego guardado", "Tu contrincante guardo el juego");
			if (timer != null)
				timer.stop();
		}
		
		/**
		 * ************************************************
		 *
		 *  HELPERS
		 *
		 * ************************************************
		 * */
		
		// Chequea si un barco recibio daño y actualiza la armadura del mismo y muestra los correspondientes
		// mensajes en pantalla
		private function checkFireHit(affectedShip:Ship, newArmor:int):void
		{
			affectedShip.armor = newArmor;
			
			// Si la armadura del barco llego a cero el mismo fue hundido entonces lo ocultamos
			if (affectedShip.armor == 0)
			{
				// Muestro mensaje
				_toastManager.addToast("Barco hundido");
				checkGoal(null);
				destroyShip(affectedShip);
				// Si el barco eliminado es el que tengo seleccionado obtengo el siguiente barco
				if (affectedShip.shipId == _selectedShip.shipId && _me.hasAliveShips())
					selectShip(_me.getNextAliveShip());
			}
			else
			{
				if (!_me.isMyShip(affectedShip))
					_toastManager.addToast("El disparo impacto en el barco enemigo, su armadura es: " + newArmor);
				else
					_toastManager.addToast("El disparo impacto en tu barco, su armadura es: " + newArmor);
				if (affectedShip.shipId == _selectedShip.shipId)
					_menu.updateShipInfo(_selectedShip);
			}
		}
		
		// Destruye una nave ocultandola del mapa
		private function destroyShip(destroyedShip:Ship):void
		{
			// oculto el barco
			destroyedShip.visible = false;
			// dejo como disponibles las celdas que ocupaba el barco
			_gridComponent.unblockCells(destroyedShip.coordinates);
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
			return _turn.activePlayer.username == _me.username;
		}
		
		// Chequea si esta en puertos 
		private function checkPort(ship:Ship):void
		{
			if (isActivePlayer())
			{
				if (ship.port1Enabled && _mapComponent.areSubCoordinates(ship.coordinates, _mapComponent.getPortHalfCoordinates()))
				{
					_main.wsRequest.enterPort1(_gameId, ship.shipId);
					ship.port1Enabled = false;
					_toastManager.addToast("Haz entrado en puerto, se recargará la mitad de los atributos del barco");
				}
				if (ship.port2Enabled && _mapComponent.areSubCoordinates(ship.coordinates, _mapComponent.getPortOneCoordinates()))
				{
					_rechargeModal = new RechargeModal(_main);
					_rechargeModal.addEventListener(ModalEvent.ATTRIBUTE_SELECTED, function(event:ModalEvent):void
						{
							_main.wsRequest.enterPort2(_gameId, ship.shipId, event.attribute);
							ship.port2Enabled = false;
							_toastManager.addToast("Se recargará el total del atributo que haz seleccionado");
						});
				}
			}
		}
		
		// Chequea si gano
		private function checkGoal(ship:Ship):void
		{
			if (!_redPlayer.hasAliveShips())
				if (isActivePlayer())
					_main.wsRequest.endGame(_gameId);
			if (!_bluePlayer.hasAliveShips())
				if (isActivePlayer())
					_main.wsRequest.endGame(_gameId);
			if (_me == _redPlayer && _me.isMyShip(ship))
				if (_mapComponent.areSubCoordinates(ship.coordinates, _mapComponent.getGoalCoordinates()) && isActivePlayer())
					_main.wsRequest.endGame(_gameId);
		}
		
		//verifica si hay celdas bloqueadas que impidan la rotacion
		private function rotationEnabled(ship:Ship, cardinal:Cardinal):Boolean
		{
			var result:Boolean = true;
			var tope:int = Math.floor(ship.size / 2);
			var i:int = 0;
			var currentPos:Coordinate = ship.currentPos;
			
			while (result && i < tope)
			{
				currentPos = Helper.calculateNextCell(currentPos, cardinal);
				result = !_gridComponent.getCell(currentPos).blocked || ship.itsMe(currentPos);
				i++;
			}
			currentPos = ship.currentPos;
			i = 0;
			while (result && i < tope)
			{
				currentPos = Helper.calculateNextCell(currentPos, Helper.getOppositeDirection(cardinal));
				result = !_gridComponent.getCell(currentPos).blocked || ship.itsMe(currentPos);
				i++;
				
			}
			return result;
		}
		
		//centra la pantalla en el seleccionado
		public function centerOnShip(ship:Ship):void
		{
			_scrollControl.centerMapToXY(ship.currentPos.x, ship.currentPos.y);
		}
		
		// Actualiza los movimientos del turno y refleja en el UI la cantidad de movimientos restantes
		private function updateMovesLeft():void
		{
			_turn.decreaseMovesLeft();
			_info.movesLeftText = _turn.movesLeft.toString();
		}
		
		// Calcula la celda en la cual el torpedo debe morir ya que no impacto contra un barco enemigo
		public function calculateTorpedoCell(coordinate:Coordinate, cardinal:Cardinal):Coordinate
		{
			var distance:int = 0;
			var blocked:Boolean = false;
			var currentPos:Coordinate = new Coordinate(coordinate.r, coordinate.c);
			while (!blocked && distance < 10)
			{
				distance++;
				if (_gridComponent.getCell(Helper.calculateNextCell(currentPos, cardinal)).blocked)
				{
					blocked = true;
				}
				else
				{
					currentPos = Helper.calculateNextCell(currentPos, cardinal);
				}
			}
			return currentPos;
		}
		
		// Habilita las celdas disponibles de movimiento para el barco seleccionado
		public function enableMovement(ship:Ship):void
		{
			var nomore:Boolean = false;
			var offset:Number = Math.floor(ship.size / 2);
			var i:int = 0;
			var currentPos:Coordinate = ship.currentPos;
			var offsetPos:Coordinate;
			var realSpeed:Number = 2 / 5 * ship.speed;
			while (!nomore && i < realSpeed)
			{
				currentPos = Helper.calculateNextCell(currentPos, ship.direction);
				offsetPos = Helper.calculateNextCell(currentPos, ship.direction, offset);
				if (offsetPos.c < GAME_BOARD_COLS && offsetPos.r < GAME_BOARD_ROWS)
				{
					if (!_gridComponent.getCell(offsetPos).blocked || ship.itsMe(offsetPos))
					{
						_gridComponent.enableCell(currentPos);
					}
					else
						nomore = true;
				}
				else
					nomore = true;
				i++;
			}
			currentPos = ship.currentPos;
			i = 0;
			nomore = false;
			while (!nomore && i < realSpeed / 2)
			{
				currentPos = Helper.calculateNextCell(currentPos, Helper.getOppositeDirection(ship.direction));
				offsetPos = Helper.calculateNextCell(currentPos, Helper.getOppositeDirection(ship.direction), offset);
				if (offsetPos.c < GAME_BOARD_COLS && offsetPos.r < GAME_BOARD_ROWS)
				{
					if (!_gridComponent.getCell(offsetPos).blocked)
					{
						_gridComponent.enableCell(currentPos);
					}
					else
						nomore = true;
				}
				else
					nomore = true;
				i++;
			}
		}
		
		// Consume la primera accion guardada en la cola de acciones
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
					var coordinate:Coordinate = null;
					var affectedShip:Ship = null;
					var newArmor:int = -1;
					if (action.weaponType.weapon == Projectile.WEAPON_TYPE_BULLET)
						coordinate = new Coordinate(action.hitCoordinate.y, action.hitCoordinate.x);
					else if (action.hitCoordinate != null)
						coordinate = new Coordinate(action.hitCoordinate.y, action.hitCoordinate.x);
					
					if (action.hit && action.affectedShip != null)
					{
						affectedShip = getShipById(action.affectedShip.id);
						newArmor = action.affectedShip.armor;
					}
					
					fireAction(ship, affectedShip, newArmor, action.hit, coordinate, action.weaponType.weapon, consumeNextAction);
				}
				else if (action.actionType == "EndTurnAction")
				{
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
					endGameAction();
				}
				else if (action.actionType == "SaveGameAction")
				{
					saveGameAction();
				}
				_actionQueue.removeItemAt(0);
			}
		}
		
		// Actualiza el modo actual del barco seleccionado
		public function refreshMode():void
		{
			clearMode();
			if (_selectedShip != null && _turn.hasMovesLeft() && isActivePlayer())
			{
				switch (_menu.currentMode)
				{
					case Menu.MENU_MODE_MOVE: 
						enableMovement(_selectedShip);
						break;
					case Menu.MENU_MODE_ROTATE: 
						break;
					case Menu.MENU_MODE_FIRE: 
						break;
				}
			}
		}
		
		// Finaliza el turno si el usuario activo se quedo sin movimientos
		private function endTurnIfNoMovesLeftAndActivePlayer():void
		{
			// Si soy el usuario activo y no me quedan movimientos termino el turno
			if (isActivePlayer() && !_turn.hasMovesLeft())
				_main.wsRequest.endTurn(_gameId);
		}
		
		public function clearMode():void
		{
			_gridComponent.disableCells();
		}
		
		// Muestra el mensaje de fin de juego
		private function showEndGameMessage(title:String, message:String):void
		{
			Alert.show(message, title, Alert.OK, null, function():void
				{
					_main.reloadGame();
				});
		}
		
		// Actualiza la visibilidad de las naves dependiendo del rango de vision de las mismas
		private function setShipsVisibility():void
		{
			for (var i:int = 0; i < _shipList.length; i++)
			{
				// Si el barco fue destruido no lo muestro
				if (!_shipList[i].isAlive())
					_shipList[i].visible = false;
				else
				{
					// Si es mi barco lo muestro en pantalla
					if (_me.isMyShip(_shipList[i]))
						_shipList[i].visible = true;
					else
					{
						// Si no es mi barco detecto las posiciones de mis barcos, si hay alguno
						// dentro del rango de vision lo muestro
						var myShips:Array = _me.getAliveShips();
						var shouldShowShip:Boolean = false;
						var j:int = 0;
						while (!shouldShowShip && j < myShips.length)
						{
							var distance:int = Helper.distanceBetweenCells(myShips[j].currentPos, _shipList[i].currentPos);
							if (distance <= myShips[j].viewRange + 1)
								shouldShowShip = true;
							j++;
						}
						_shipList[i].visible = shouldShowShip;
					}
				}
			}
		}
	}
}