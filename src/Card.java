/**
 * A single card 
 * @author Charles Davis
 */
public class Card {

	private Proccesses p = new Proccesses();
	
    private char color; //Color GETS/SETS -=- -=-
    
    /**
	 * @return the color
	 */
	public char getColor() {return color;}
	
	/**
	 * @param color the color to set
	 */
	private void setColor(char color) {this.color = color;}
	
	private String rank; // Rank GET/SETS -=- -=-
	
	/**
	 * @return the rank
	 */
	public String getRank() {return rank;}
	
	/**
	 * @param rank the rank to set
	 */
	private void setRank(String rank) {this.rank = rank;}

    /**
     * The card itself
     * @param color : char 
     * @param rank  : String
     */
    Card(char color, String rank) 
    {
        this.color = color;
        this.rank = rank;
    }
    Card(){}

    /**
     * @return String : color and rank as "color - rank"
     */
    public String toString() 
    {
        return color + " - " + rank;
    }    
    
    /**
     * Translates the cards data to full title
     * @return String
     */
    public String translateCard()
    {
    	return p.getTranslatedColor(color) 
    		 + p.getTranslatedRank(rank);
    }
    
}