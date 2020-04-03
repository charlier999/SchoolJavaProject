
import java.util.Random;

public class Deck 
{
    Random r = new Random();
	
    // Create possible card suites and values
    private static char[] color = {'R','Y','G','B'};
    private static String[] rank = 
    		{
    		"0A","0B",
    		"D2A","D2B",
    		"SA","SB",
    		"RA","RB",
    		"1A","1B","1C","1D",
    		"2A","2B","2C","2D",
    		"3A","3B","3C","3D",
    		"4A","4B","4C","4D",
    		"5A","5B","5C","5D",
    		"6A","6B","6C","6D",
    		"7A","7B","7C","7D",
    		"8A","8B","8C","8D",
    		"9A","9B","9C","9D",
    		};
    public Card[] deck = new Card[color.length*rank.length+4];
    
    private int position = 0;

    /**
     * Returns the position of the card
     * @return position : int 
     */
    public int getPosition() 
    {
        return position;
    }

    /**
     * Checks to see if the current position is less then the size of the deck
     * then adds 1 to position
     * if the position reaches the end of the deck length, it will not run
     */
    public void addPosition() 
    {
        if (position < deck.length)
            position++;
    }

    /**
     * Sets the value of position to the input integer
     * @param input : int
     */
    public void setPosition(int input)
    {
    	position = input;
    }
    
    public void start()
    {
    	buildDeck();
    	shuffle();
    }
    
    /**
     * Builds the deck based on the parameters in the Deck class
     */
    private void buildDeck() 
    {
        int counter = 0;
        for (int a = 0; a < color.length; a++) 
        {
            for (int b = 0; b < rank.length; b++) 
            {
                deck[counter++] = new Card(color[a], rank[b]);
            }
        }
        	deck[counter++] = new Card('W', "D4A");
        	deck[counter++] = new Card('W', "D4B");
        	deck[counter++] = new Card('W', "WA");
        	deck[counter++] = new Card('W', "WB");
     }

    /**
     * Shuffles the deck and puts the cards in the deck in random order
     */
    public void shuffle() 
    {
        for (int x = 0; x < deck.length; x++) 
        {
            int randIndex = r.nextInt(deck.length);
            Card temp = deck[randIndex];
            deck[randIndex] = deck[x];
            deck[x] = temp;
        }

    }
    
    /**
     * Returns a card to the player and changes the position of the deck
     * @return Card
     */
    public Card dealCard()
    {
    	Card output = deck[getPosition()];
    	addPosition();
    	return output;
    }
    
    public void displayAllCards()
    {
    	System.out.println(deck.length);
    	for(int x = 0; x < deck.length; x++)
    	{
    		System.out.println(x);
    		System.out.println(deck[x].translateCard());
    	}
    }
}
