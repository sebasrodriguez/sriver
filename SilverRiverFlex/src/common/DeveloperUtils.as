package common 
{
	
	import flash.external.ExternalInterface;
	/**
	 * ...
	 * @author pblt
	 */
	public class DeveloperUtils 
	{
		
		public function DeveloperUtils() 
		{
			
		}
		public static function tr(any:*):void {
			trace(any);
			if(ExternalInterface.available)
				ExternalInterface.call("console.log", any.toString());
		}
		public static function trObject(object:Object):void {
			DeveloperUtils.tr(object + " {");
			for (var i:String in object) {
				DeveloperUtils.tr("\t" + i + ":" + object[i]);
            }
			DeveloperUtils.tr("}");
		}
	}

}