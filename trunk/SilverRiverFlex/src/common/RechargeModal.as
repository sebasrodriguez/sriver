package common 
{
	import events.ModalEvent;
	import flash.display.DisplayObject;
	import flash.events.MouseEvent;
	import mx.accessibility.ButtonAccImpl;
	import mx.containers.HBox;
	import mx.controls.Button;
	import components.Ship;
	import mx.managers.PopUpManager;
	/**
	 * ...
	 * @author pablo
	 */
	public class RechargeModal extends Modal
	{
		
		public function RechargeModal(p:DisplayObject) 
		{
			super(p, "Elije un atributo a recargar", 340, 130);
			var container:HBox = new HBox();
			container.x = 10;
			container.y = 10;
			canvas.addChild(container);
			var button:Button;
			
			button = new Button();
			button.label = "Blindaje";
			button.setStyle("fontSize", 20);
			button.width = 100;
			button.height = 100;		
			button.addEventListener(MouseEvent.CLICK, function():void { dispatchAttributeSelectedEvent( Ship.VARIABLE_ATTRIBUTE_ARMOR); } );
			container.addChild(button);
			
			button = new Button();
			button.label = "Cañon";
			button.setStyle("fontSize", 20);
			button.width = 100;
			button.height = 100;	
			button.addEventListener(MouseEvent.CLICK, function():void { dispatchAttributeSelectedEvent( Ship.VARIABLE_ATTRIBUTE_AMMO); } );
			container.addChild(button);
			
			button = new Button();
			button.label = "Torpedo";
			button.setStyle("fontSize", 20);
			button.width = 100;
			button.height = 100;
			button.addEventListener(MouseEvent.CLICK, function():void { dispatchAttributeSelectedEvent( Ship.VARIABLE_ATTRIBUTE_TORPEDOES); } );
			container.addChild(button);
			
		}
		
		private function dispatchAttributeSelectedEvent(attribute:String):void {
			var attributeSelectedEvent:ModalEvent = new ModalEvent(ModalEvent.ATTRIBUTE_SELECTED);
			attributeSelectedEvent.attribute = attribute;
			dispatchEvent(attributeSelectedEvent);
			close();
		}
	}

}