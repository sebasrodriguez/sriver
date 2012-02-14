package Actions 
{
	import Common.Coordenate;
	import Entities.Ship;
	/**
	 * ...
	 * @author sebas
	 */
	public class MoveAction extends Action 
	{		
		private var _position: Coordenate;		
		
		public function MoveAction(ship: Ship=null, position: Coordenate=null) {
			super(ship);
			this._position = position;
		}
		
		public function get Position(): Coordenate {
			return _position;
		}
		
		public function set Position(position:Coordenate): void {
			this._position = position;
		}
	}
}