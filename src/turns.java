/**
 * @author Charles_Davis
 *
 */
public class turns 
{
	//  CONSTANTS -=- -=- -=- -=- -=- -=- -=- -=- -=- -=- -=- -=- -=- -=- -=- -=- -=- -=- -=- -=-
	/**
	 * What turn is the game is at
	 */
	private int turnNumber;
	/**
	 * The direction of play : True = Forwards | False = Backwords
	 */
	private boolean turnDirection;
	/**
	 * The player count for players in the game
	 */
	private int playerCount;

	// GET'S SET'S -=- -=- -=- -=- -=- -=- -=- -=- -=- -=- -=- -=- -=- -=- -=- -=- -=- -=- -=- -=-
	private void setTurnNumber(int turn){ turnNumber = turn; }
	public int getTurnNumber(){ return turnNumber; }
	
	private void setTurnDirection(boolean direction){ turnDirection = direction; }
	public boolean getTurnDirection(){ return turnDirection; }
	
	public void setPlayerCount(int count){ playerCount = count;}
	public int getPlayerCount(){return playerCount;}
	
	// METHODS -=- -=- -=- -=- -=- -=- -=- -=- -=- -=- -=- -=- -=- -=- -=- -=- -=- -=- -=- -=- -=-
	/**
	 * Start up the turns system
	 */
	public void start()
	{
		setTurnNumber(0);
		setTurnDirection(true);
	}
	/**
	 * Changes the turn to the next person
	 * @param playerCount : int
	 */
	public void nextTurn(int playerCount)
	{
		ErrorReporting.debug("turns.java", 46, getTurnDirection(), true);
		ErrorReporting.debug("turns.java", 47, getTurnNumber(), true);
		ErrorReporting.debug("turns.java", 48, getPlayerCount(), true);		
		ErrorReporting.debug("turns.java", 49, (getTurnNumber() == getPlayerCount()), true);
		ErrorReporting.debug("turns.java", 50, (getTurnNumber() == 0), true);
		if(getTurnDirection())
		{
			// FORWARDS
			if(getTurnNumber() == getPlayerCount()-1)
				setTurnNumber(0);
			else
				setTurnNumber(getTurnNumber() + 1);
		}
		else
		{
			// BACKWORDS
			if(getTurnNumber() == 0)
				setTurnNumber(getPlayerCount()-1);
			else
				setTurnNumber(getTurnNumber() - 1);
		}
	}
	/**
	 * Reverces the direction of play
	 */
	public void reverseTurns()
	{
		if(getTurnDirection())
			setTurnDirection(false);
		else
			setTurnDirection(true);
	}
}
