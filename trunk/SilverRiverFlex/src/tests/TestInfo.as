package tests 
{
	import components.Info;
	/**
	 * ...
	 * @author pablo
	 */
	public class TestInfo 
	{
		
		public function TestInfo(main:tests.MainTest) 
		{
			var info:Info = new Info();
			main.addElement(info);
		}
		
	}

}