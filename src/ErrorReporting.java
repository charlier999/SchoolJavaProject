/**
 * 
 */


public class ErrorReporting 
{
	private static String debugAll;
	private static boolean masterPrint = true;
	
	/**
	 * Displays all of the debug statements
	 */
	public static void displaydebugAll()
	{
		System.out.println(debugAll);
	}
	
	/**
	 * Sets the setting to whether the the error reporting 
	 * prints per use.
	 * @param input : boolean
	 */
	public static void setMasterPrint(boolean input)
	{
		masterPrint = input;
	}
	
	/**
	 * Prints out to console a boolean debug message
	 * @param file : the file that the command is in
	 * @param ln : line number
	 * @param input : the boolean to make into debug message
	 */
	public static void debug(String file, int ln, boolean input, boolean print)
	{
		String a = ("[File: " + file + "\t" + "Line: " + ln + "\t" + "Output Boolean: " + input + "]");
		if(masterPrint) if(print) System.out.println(a);
		debugAll += (a + "\n");
	}
	/**
	 * Prints out to console a int debug message
	 * @param file : the file that the command is in
	 * @param ln : line number
	 * @param input : the integer to make into debug message
	 */
	public static void debug(String file, int ln, int input, boolean print)
	{
		String a = ("[File: " + file + "\t" + "Line: " + ln + "\t" + "Output Integer: " + input + "]");
		if(masterPrint) if(print) System.out.println(a);
		debugAll += (a + "\n");
	}
	/**
	 * Prints out to console a double debug message
	 * @param file : the file that the command is in
	 * @param ln : line number
	 * @param input : the double to make into debug message
	 */
	public static void debug(String file, int ln, double input, boolean print)
	{
		String a = ("[File: " + file + "\t" + "Line: " + ln + "\t" + "Output Double: " + input + "]");
		if(masterPrint) if(print) System.out.println(a);
		debugAll += (a + "\n");
	}
	/**
	 * Prints out to console a String debug message
	 * @param file : the file that the command is in
	 * @param ln : line number
	 * @param input : the string to make into debug message
	 */
	public static void debug(String file, int ln, String input, boolean print)
	{
		String a = ("[File: " + file + "\t" + "Line: " + ln + "\t" + "Output String: " + input + "]");
		if(masterPrint) if(print) System.out.println(a);
		debugAll += (a + "\n");
	}
	/**
	 * Prints out to console a float debug message
	 * @param file : the file that the command is in
	 * @param ln : line number
	 * @param input : the float to make into debug message
	 */
	public static void debug(String file, int ln, float input, boolean print)
	{
		String a = ("[File: " + file + "\t" + "Line: " + ln + "\t" + "Output Float: " + input + "]");
		if(masterPrint) if(print) System.out.println(a);
		debugAll += (a + "\n");
	}
	/**
	 * Prints out to console a char debug message
	 * @param file : the file that the command is in
	 * @param ln : line number
	 * @param input : the cahr to make into debug message
	 */
	public static void debug(String file, int ln, char input, boolean print)
	{
		String a = ("[File: " + file + "\t" + "Line: " + ln + "\t" + "Output Char: " + input + "]");
		if(masterPrint) if(print) System.out.println(a);
		debugAll += (a + "\n");
	}
	/**
	 * Prints out to console a byte debug message
	 * @param file : the file that the command is in
	 * @param ln : line number
	 * @param input : the byte to make into debug message
	 */
	public static void debug(String file, int ln, byte input, boolean print)
	{
		String a = ("[File: " + file + "\t" + "Line: " + ln + "\t" + "Output Byte: " + input + "]");
		if(masterPrint) if(print) System.out.println(a);
		debugAll += (a + "\n");
	}
	/**
	 * Prints out to console a short debug message
	 * @param file : the file that the command is in
	 * @param ln : line number
	 * @param input : the short to make into debug message
	 */
	public static void debug(String file, int ln, short input, boolean print)
	{
		String a = ("[File: " + file + "\t" + "Line: " + ln + "\t" + "Output Short: " + input + "]");
		if(masterPrint) if(print) System.out.println(a);
		debugAll += (a + "\n");
	}
	/**
	 * Prints out to console a long debug message
	 * @param file : the file that the command is in
	 * @param ln : line number
	 * @param input : the long to make into debug message
	 */
	public static void debug(String file, int ln, long input, boolean print)
	{
		String a = ("[File: " + file + "\t" + "Line: " + ln + "\t" + "Output Long: " + input + "]");
		if(masterPrint) if(print) System.out.println(a);
		debugAll += (a + "\n");
	}
}
