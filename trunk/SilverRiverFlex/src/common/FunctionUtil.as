package common 
{
	
	/**
	 * ...
	 * @author Pablo Penén
	 */
	public class FunctionUtil {
		
		public function FunctionUtil() {
		}
		
		public static function createDelegate(handler:Function, ...args):Function {
			return function(...innerArgs):void {
				handler.apply(this, innerArgs.concat(args));
			}
		}
	}
	
}