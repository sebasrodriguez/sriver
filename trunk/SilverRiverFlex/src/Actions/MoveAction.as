package Actions 
{
	import Entities.Ship;
	/**
	 * ...
	 * @author sebas
	 */
	public class MoveAction extends Action 
	{
		
		
		
		public function MoveAction() 
		{
			super();			
		}
		
		public function MoveAction(ship: Ship) {
			super(ship);
		}
	}

}