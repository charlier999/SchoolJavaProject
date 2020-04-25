import java.util.Scanner;

/**
 * 
 */

/**
 * @author creep
 *
 */
public class players 
{
	Scanner sc = new Scanner(System.in);
	Proccesses p = new Proccesses();
	hands[] playerArray = new hands[5];
	
	/**
	 * The amount of players that are in the game.
	 */
	private int playerCount;
	/**
	 * Creates all four players
	 */
	public void createPlayers()
	{
		for(int x = 0; x < 4; x++)
		{
			playerArray[x] = new hands();
		}
		playerArray[4] = new hands();
	}
	/**
	 * Returns the player's hand from the inputed player number
	 * @param player : the player's number
	 * @return hands 
	 */
	public hands getPlayer(int player)
	{		
		ErrorReporting.debug("players.java", 38, player, true);
		ErrorReporting.debug("players.java", 39, getPlayerCount(), true);
		ErrorReporting.debug("players.java", 40, p.checkPlayerExist(player, getPlayerCount()), true);
		ErrorReporting.debug("players.java", 41, playerArray.length, true);
		if(player == 4)
		{
			return playerArray[player];
		}
		else if(p.checkPlayerExist(player, getPlayerCount()))
		{
			return playerArray[player];
		}
		else
		{
			return null;
		}
	}
	/**
	 * Asks the user for the amount of players that are in the game
	 */
	public void userSetPlayerCount()
	{
		boolean done = false;
		while(!done)
		{
			System.out.println("How many players are there? Max: 4 (int)");
			int output = sc.nextInt();
			sc.nextLine();
			
			if(p.checkNegitiveIntInput(output)) // if the number is negative
			{
				setPlayerCount(output);
				done = true;
			}
			else
			{
				System.out.println(output + " is not a valid input.");
				done = false;
			}
		} // END OF DONE WHILE LOOP
		
	}
	/**
	 * Sets the playerCount int value in players.java
	 * @param input : int 
	 */
	public void setPlayerCount(int input)
	{
		playerCount = input;
	}
	/**
	 * Gets the int player count from players.java
	 * @return playerCount : int
	 */
	public int getPlayerCount() { return playerCount; }
	/**
	 * Displays all of the players current hands
	 */
	public void displayAllPlayersHands()
	{
		ErrorReporting.debug("players.java", 93, getPlayerCount(), true);
		for(int x = 0; x < getPlayerCount(); x++)
		{
			ErrorReporting.debug("players.java", 96, x, true);
			displayPlayerHand(x);
		}
	}
	/**
	 * Displays the hand of the inputed player
	 * @param player : int
	 */
	public void displayPlayerHand(int player)
	{
		if(!p.checkPlayerExist(player, getPlayerCount()))
			System.out.println("ERROR! Player " + player + "does not exist.");
		else
		{
			System.out.println("Player" + player + "'s Cards: \n" +
								getPlayer(player).getHandCardsString());
		}
	}
}
