/**
 * @author creep
 *
 */
public class Proccesses 
{
	// -=- -=- -=- Card.java -=- -=- -=- 
	/**
	 * Takes the inputed char and determines the color of the card
	 * and returns it as a String
	 * @param input : char
	 * @return String : the translated result
	 */
	public String getTranslatedColor(char input)
	{
		if(input == 'R')		return "Red ";
    	else if(input == 'Y')	return "Yellow ";
    	else if(input == 'G')	return "Green ";
    	else if(input == 'B')  	return "Blue ";
    	else return "ERROR!";
	}
	/**
	 * Takes the inputed String and determins the value of the card
	 * and returns it as a String
	 * @param input : String
	 * @return String : the translated result
	 */
	public String getTranslatedRank(String input)
	{
		input = removeDupPrevent(input); // removes the dup identifier from input
		if(input.equals("0"))			return "Zero";
		else if(input.equals("D2"))  	return "Draw Two";
		else if(input.equals("S"))  	return "Skip";
		else if(input.equals("1"))  	return "One";
		else if(input.equals("2"))  	return "Two";
		else if(input.equals("3"))  	return "Three";
		else if(input.equals("4"))  	return "Four";
		else if(input.equals("5"))  	return "Five";
		else if(input.equals("6"))  	return "Six";
		else if(input.equals("7"))  	return "Seven";
		else if(input.equals("8"))  	return "Eight";
		else if(input.equals("9"))  	return "Nine";
		else if(input.equals("D4"))  	return "Draw Four";
		else if(input.equals("W"))  	return "Wild";
		else return "ERROR!"; // If the inputed value is none of the above,
							  // method will return an Error String
	}
	/**
	 * Removes the dup identifier from the end of the card string
	 * @param input : String
	 * @return String : the shortened result
	 */
	private String removeDupPrevent(String input)
	{
		if(input.length() == 2)
			return input.substring(0, 1); // returns the first character
		else if(input.length() == 3)
			return input.substring(0, 2); // returns the first 2 characters
		else return "ERROR!"; // if the string is longer then 3, returns
							  // an error string
	}
	
	//  -=- -=- -=- players.java -=- -=- -=- 
	/**
	 * Checks the int input to see if it is not negative
	 * @param input : Int
	 * @return boolean : true = positive || false = negative
	 */
	public boolean checkNegitiveIntInput(int input)
	{
		if(input > 0) 
		{
			if (input <= 4)
			{
				return true; // if greater then 0, return true
			}
			else return false;
		}
		else return false;
	}

	public boolean checkPlayerExist(int player, int playerCount)
	{
		if(player > playerCount)return false;
		else if(player <= 0) return false;
		else return true;
	}

}
