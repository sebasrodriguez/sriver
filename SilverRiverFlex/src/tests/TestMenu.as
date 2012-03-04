package tests 
{
	import components.*;
	import common.*;
	/**
	 * ...
	 * @author pablo
	 */
	public class TestMenu 
	{
		
		public function TestMenu(main:tests.MainTest) 
		{
			var menu:Menu = new Menu(Menu.MENU_POSITION_BOTTOM_LEFT);
			main.addElement(menu);
			menu.updateShipInfo(new RedShip(0, new Coordinate(0, 0), new Cardinal(0), 10, 3, 10, 12, 3, 10)); 
			
		}
		
	}

}