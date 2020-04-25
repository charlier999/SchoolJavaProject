/**
 * 
 */

/**
 * @author Charles Davis
 * 
 *
 */
public class UNO_MAIN 
{

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		ErrorReporting.setMasterPrint(false);
		GamePlay gp = new GamePlay();
		gp.start();
	}
}
