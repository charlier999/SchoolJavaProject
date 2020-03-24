/**
 * @author Charles Davis
 *
 */
public class GamePlay extends players
{
	Deck deck = new Deck();

	/**
	 * The start of the game of uno
	 */
	public void start()
	{
		deck.start();
		deck.displayAllCards();
		System.out.println("-=- -=- -=- PlayerCount ASK -=- -=- -=-");
		userSetPlayerCount();
		createPlayers();
		System.out.println("-=- -=- -=- DEAL CARDS TO Player -=- -=- -=- \n");
		dealPlayerCards();
		displayAllPlayersHands();
	}
	/**
	 * Deals the starting 7 cards to all players in the game
	 */
	private void dealPlayerCards()
	{
		ErrorReporting.debug("GamePlay.java", 27, getPlayerCount(), true);
		for(int x = 0; x < getPlayerCount(); x++)
		{
			ErrorReporting.debug("GamePlay.java", 30, x, true);
			playerArray[x].addCardToHand(7, deck);
		}
	}
}
