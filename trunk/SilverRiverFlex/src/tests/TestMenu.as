package tests 
{
	import components.Menu;
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
			
		}
		
	}

}