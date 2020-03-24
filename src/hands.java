import java.util.ArrayList;
import java.util.List;

/**
 * 
 */

/**
 * @author creep
 *
 */
public class hands 
{
	hands(){}
	
	private ArrayList<Card> hand = new ArrayList<>();
	
	/**
	 * Adds the inputed cards input the hand from the inpued deck
	 * @param amount : int
	 * @param deck : Deck
	 */
	protected void addCardToHand(int amount, Deck deck)
	{
		ErrorReporting.debug("hands.java", 25, amount, true);
		for(int x = 0; x < amount; x++)
		{
			ErrorReporting.debug("hands.java", 28, x, true);
			hand.add(deck.dealCard());
		}
	}
	/**
	 * Returns the translated cards in hand 
	 * @return String : string
	 */
	protected String getHandCardsString()
	{
		String output = "";
		ErrorReporting.debug("hands.java", 39, hand.size(), true);
		ErrorReporting.debug("hands.java", 40, hand.isEmpty(), true);
		if(hand.isEmpty())
			output = "Hand is empty";
		else
		{
			for(int x = 0; x < hand.size(); x++)
			{
				output += hand.get(x).translateCard() + "\n";
			}
		}
		return output;
	}
}
