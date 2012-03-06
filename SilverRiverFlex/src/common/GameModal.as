package common 
{
	import events.ModalEvent;
	import flash.display.DisplayObject;
	import flash.events.FocusEvent;
	import flash.events.MouseEvent;
	import mx.accessibility.ButtonAccImpl;
	import mx.containers.HBox;
	import mx.containers.VBox;
	import mx.controls.Button;
	import components.Game;
	import mx.controls.Label;
	import mx.controls.TextInput;
	import mx.managers.PopUpManager;
	/**
	 * ...
	 * @author pablo
	 */
	public class GameModal extends Modal
	{
		private var _txtUsuario:TextInput;
		private var _lblError:Label;
		
		public function GameModal(p:DisplayObject) 
		{
			super(p, "Bienvenido a SilverRiver", 330, 130);
			var container:VBox = new VBox();
			container.x = 10;
			container.y = 10;
			canvas.addChild(container);
			
			var hContainer:HBox;
			
			hContainer = new HBox();
			container.addChild(hContainer);
			var lblUsuario:Label = new Label();
			lblUsuario.text = "Usuario: ";
			lblUsuario.setStyle("fontSize", 20);	
			hContainer.addChild(lblUsuario);
			_txtUsuario = new TextInput();
			_txtUsuario.setStyle("fontSize", 20);
			_txtUsuario.addEventListener(FocusEvent.FOCUS_IN, function():void { _lblError.text = ""; } );
			hContainer.addChild(_txtUsuario);			
			_lblError = new Label();
			_lblError.text = "";
			_lblError.setStyle("color", 0xFF0000);
			_lblError.setStyle("fontSize", 20);
			_lblError.setStyle("fontStyle", "bold");	
			hContainer.addChild(_lblError);
			
			hContainer = new HBox();
			container.addChild(hContainer);
			var button:Button;
			
			button = new Button();
			button.label = "Juego Nuevo";
			button.width = 150;
			button.height = 70;	
			button.setStyle("fontSize", 20);			
			button.addEventListener(MouseEvent.CLICK, function():void { dispatchGameSelectedEvent( Game.NEW_GAME);} );
			hContainer.addChild(button);
			
			button = new Button();
			button.label = "Cargar Juego";
			button.width = 150;
			button.height = 70;	
			button.setStyle("fontSize", 20);
			button.addEventListener(MouseEvent.CLICK, function():void { dispatchGameSelectedEvent(Game.LOAD_GAME);} );
			hContainer.addChild(button);
			
		}
		
		private function dispatchGameSelectedEvent(game:String):void {
			if(_txtUsuario.text != ""){
				var gameSelectedEvent:ModalEvent = new ModalEvent(ModalEvent.GAME_SELECTED);
				gameSelectedEvent.game = game;
				gameSelectedEvent.username = _txtUsuario.text;
				dispatchEvent(gameSelectedEvent);
				close();
			}else {
				_lblError.text = "Error";
			}
		}
	}

}