import java.util.Scanner;

/**
 * @author Charles Davis
 *
 */
public class GamePlay 
{
	Deck deck = new Deck();
	Scanner sc = new Scanner(System.in);
	turns t = new turns();
	players p = new players();

	// Variables
	private boolean endTurn;
	private boolean winConditionMet = false;
	
	/**
	 * The start of the game of uno
	 */
	public void start()
	{
		deck.start();
		//deck.displayAllCards();
		System.out.println("-=- -=- -=- PlayerCount ASK -=- -=- -=-");
		p.userSetPlayerCount();
		p.createPlayers();
		System.out.println("-=- -=- -=- DEAL CARDS TO Player -=- -=- -=- \n");
		dealPlayerCards();
		p.displayAllPlayersHands();
		t.start();
		t.setPlayerCount(p.getPlayerCount());
		System.out.println("-=- -=- -=- GAME START -=- -=- -=- \n");
		gameStart();
		
	}
	
	/**
	 * Deals the starting 7 cards to all players in the game
	 */
	private void dealPlayerCards()
	{
		ErrorReporting.debug("GamePlay.java", 27, p.getPlayerCount(), true);
		for(int x = 0; x < p.getPlayerCount(); x++)
		{
			ErrorReporting.debug("GamePlay.java", 30, x, true);
			p.getPlayer(x).addCardToHand(7, deck);
		}
	}

	/**
	 * The actual game play
	 */
	private void gameStart()
	{
		ErrorReporting.debug("GamePlay.java", 55, winConditionMet, true);
		while(!winConditionMet)
		{
			ErrorReporting.debug("GamePlay.java", 58, winConditionMet, true);
			playerTurn(t.getTurnNumber());
			t.nextTurn(p.getPlayerCount());
		}
		ErrorReporting.displaydebugAll();
		
	}
	/**
	 * Displays who's turn it is and that players cards.
	 * Asks for user commands.
	 * @param player : int
	 */
	private void playerTurn(int player)
	{
		ErrorReporting.debug("GamePlay.java", 72, player, true);
		System.out.println("-=- -=- Player" + player + "'s Turn -=- -=-");
		p.displayPlayerHand(player);
		playerInput(player);
	}
	/**
	 * Gets the players commands and does actions from 
	 * the inputed command
	 * @param player : int
	 */
	private void playerInput(int player)
	{
		boolean done = false;
		while(!done)
		{
			ErrorReporting.debug("GamePlay.java", 87, done, true);
			System.out.println("What do you wish to do? ('help' for commands)");
			String input = sc.nextLine();
			ErrorReporting.debug("GamePlay.java", 90, input, true);
			
			if(input.equals("help")) 
			{
				done = false;
				playerHelp();
			}
			else if(input.equals("pc")) 
			{
				done = true;
				playerPlayCard();
			}
			else if(input.equals("dc"))
			{
				done = true;
				p.getPlayer(player).addCardToHand(1, deck);
			}
			else if(input.equals("vc")) 
			{
				done = false;
				p.displayPlayerHand(player);
			}
			else if(input.equals("eg"))
			{
				done = true;
				winConditionMet = true;
			}
			else if(input.equals("dd"))
			{
				done = false;
				ErrorReporting.displaydebugAll();
			}
			
			else
			{
				System.out.println(input + " is not a valid command. ('help' for commands)");
			}
				
		}
	}
	/**
	 * Displays the command help message
	 */
	private void playerHelp()
	{
		System.out.println(" ------------ HELP ------------- \n"
							+ "help  |  displays all available commands 	\n"
							+ "pc    |  brings up the card play gui       	\n"
							+ "dc    |  Draws one card from the draw pile 	\n"
							+ "vc    |  Displays all held cards				\n"
							+ "eg    |  [debug] Ends the game				\n"
							+ "dd    |  [debug] Displays all debug msgs       ");
	}
	
	private void playerPlayCard()
	{
		
	}

}
