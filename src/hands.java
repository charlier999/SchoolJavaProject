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
	private ArrayList<Card> hand = new ArrayList<>();

	/**
	 * Returns the size of the hand 
	 * @return hand size : int
	 */
	public int getCardCount()
	{
		return hand.size();
	}
	
	/**
	 * Adds the inputed cards input the hand from the inpued deck
	 * @param amount : int
	 * @param deck : Deck
	 */
	public void addCardToHand(int amount, Deck deck)
	{
		System.out.println("32 DEBUG");
		ErrorReporting.debug("hands.java", 25, amount, true);
		for(int x = 0; x < amount; x++)
		{
			ErrorReporting.debug("hands.java", 28, x, true);
			hand.add(deck.dealCard());
		}
	}
	/**
	 * Adds a specific card to the hand
	 * @param card
	 */
	public void addCardToHand(Card card)
	{
		System.out.println("46 DEBUG");
		hand.add(card);
	}	
	/**
	 * Returns the translated cards in hand 
	 * @return String : string
	 */
	public String getHandCardsString()
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
				output += x + ":\t" + hand.get(x).translateCard() + "\n";
			}
		}
		return output;
	}
	/**
	 * Removes the inputed card from the hand
	 * @param card : int
	 */
	public void removeCardfromHand(int card)
	{
		hand.remove(card);
	}
	/**
	 * Gets the card info from the selected card
	 * @param card : int
	 * @return output : Card 
	 */
	public Card getCard(int card)
	{
		return hand.get(card);
	}
}
