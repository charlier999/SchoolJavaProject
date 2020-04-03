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
	Proccesses proc = new Proccesses();

	// Variables
	private int playerWinner;
	
	// -=- -=- -=- -=- -=- -=- -=- -=- Inital Game Play Start -=- -=- -=- -=- -=-
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
		p.getPlayer(4)
			.addCardToHand
			(deck.dealCard());
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

	// -=- -=- -=- -=- -=- -=- -=- -=- Game Play Loop -=- -=- -=- -=- -=- -=- -=-
	/**
	 * The actual game play
	 */
	private void gameStart()
	{
		while(!checkWinCondition())
		{
			playerTurn(t.getTurnNumber());
			t.nextTurn(p.getPlayerCount());
		}
		displayWinMessage();
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
		System.out.println("Play Pile Card: " + getTopPlayPileCard().translateCard());
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
			// Help
			{
				playerHelp();
			}
			else if(input.equals("pc"))
			// Play Cards
			{
				if(isAllCardsPlayable(player))
				{
					playCardtoPlayDeck(player, deck);
				}
				else
				{	
					System.out.println("You do not have any playable cards.\n"
									 + "*You get a card and end your turn*");
					p.getPlayer(player).addCardToHand(1, deck);
				}
				done = true;

			}
			else if(input.equals("dc"))
			// Draw Cards
			{
				done = true;
				p.getPlayer(player).addCardToHand(1, deck);
			}
			else if(input.equals("vc")) 
			// View Cards
			{
				System.out.println("Play Pile Card: " + getTopPlayPileCard().translateCard());
				System.out.println("-=- -=- -=- -=- -=- -=- -=- -=- -=- -=-");
				p.displayPlayerHand(player);
			}
			else if(input.equals("dd"))
			// Displays the debug info
			{
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
		System.out.println("--------------------Help--------------------\n"
						 + "help  |  displays all available commands 	\n"
						 + "pc    |  brings up the card play gui       	\n"
						 + "dc    |  Draws one card from the draw pile 	\n"
						 + "vc    |  Displays all held cards			\n"
						 + "dd    |  [debug] Displays all debug msgs      ");
	}
	/**
	 * Checks to see if any player has achived the win condition
	 * @return output : boolean
	 */
	private boolean checkWinCondition()
	{
		boolean output = false;
		for(int x = 0; x > p.getPlayerCount(); x++)
		{
			if(p.getPlayer(x).getCardCount() == 0)
			{
				output = true;
				playerWinner = x;
				break;
			}
		}
		return output;
	}
	/**
	 * Displays the win condition
	 */
	private void displayWinMessage()
	{
		System.out.println("");
	}
	/**
	 * Checks to see if a player has playable cards
	 * @param player : int
	 * @return output : boolean
	 */
	private boolean isAllCardsPlayable(int player)
	{
		boolean output = false;
		for(int x = 0; x > p.getPlayer(player).getCardCount(); x++)
		{
			Card card = p.getPlayer(player).getCard(x);
			if(getTopPlayPileCard().getColor() == card.getColor())
			{
				output = true;
				break;
			}
			else if(getTopPlayPileCard().getRank() == card.getRank())
			{
				output = true;
				break;
			}
			else if(card.getColor() == 'W')
			{	
				output = true;
				break;
			}
		}
		return output;
	}

	
	// -=- -=- -=- -=- -=- -=- -=- -=- Play Cards GUI -=- -=- -=- -=- -=- -=- -=- 
	/**
	 * Starts the play card system
	 * @param player : int
	 * @param deck : Deck
	 */
	private void playCardtoPlayDeck(int player, Deck deck)
	{
		if(proc.checkPlayerExist(player, p.getPlayerCount()))
		{
			p.displayPlayerHand(player);
			playerPCInput(player);
		}
	}
	/**
	 * Gets the players commands and does actions from 
	 * the inputed command
	 * @param player : int
	 * @return cancel : boolean
	 */
	private void playerPCInput(int player)
	{
		int intInput = 0; 
		boolean done = false;
		while(!done)
		{
			System.out.println("Enter the number of the card to play the card"
					+ "\n enter 'help' for help");
			String input = sc.nextLine();
			if(proc.isInteger(input)) 
			{
				intInput = Integer.parseInt(input);
				if(intInput > -1)
				// If intinput is not negative
				{
					if(intInput > p.getPlayer(player).getCardCount())
					{
						if(isCardPlayable( p.getPlayer(player).getCard(intInput) ) )
						{
							// Adds the players card to the play deck
							p.getPlayer(4).addCardToHand( p.getPlayer(player).getCard(intInput) );
							// Applies the card's effect to players
							cardEffect(p.getPlayer(player).getCard(intInput));
							// Removes the played card from the player's hand
							p.getPlayer(player).removeCardfromHand(intInput);
						}
						else 
							System.out.println(input + " is not a playable card. Try another card. ('help' for commands)");
					}
					else 
						System.out.println(input + " is not a valid card. ('help' for commands)");
				}
				else 
					System.out.println(input + " is not a valid card. ('help' for commands)");

			}
			else if(input.equals("help")) 
			{
				done = false;
				playerCardsHelp();
			}
			else if(input.equals("vc")) 
			{
				done = false;
				p.displayPlayerHand(player);
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
	 * Displays the help screen for playing cards
	 */
	private void playerCardsHelp()
	{
		System.out.println("----------------------Play Cards Help-------------------\n"
						 + "help  |  displays all available commands 				\n"
						 + "(int) |  plays the selected card          				\n"
						 + "vc    |  Displays all held cards						\n"
						 + "can   |  Cancels playing card and returns to base menu 	\n" 
						 + "dd    |  [debug] Displays all debug msgs       ");		
	}
	/**
	 * Checks to see if the card is Playable
	 * @param card : int
	 * @return output : boolean 
	 */
	private boolean isCardPlayable(Card card)
	{
		boolean output = false;
		if(getTopPlayPileCard().getColor() == card.getColor())
			output = true;
		else if(getTopPlayPileCard().getRank() == card.getRank())
			output = true;
		else if(card.getColor() == 'W')
			output = true;
		return output;
	}
	/**
	 * Applies the effect of the inputed card
	 * @param card : Card
	 */
	private void cardEffect(Card card)
	{
		String input = card.getRank();
		input = proc.removeDupPrevent(input); // removes the dup identifier from input
		if(input.equals("0"))			nothing();			// Zero
		else if(input.equals("D2"))		cardDrawCards(2);	// Draw Two
		else if(input.equals("S"))  	cardSkip();			// Skip
		else if(input.equals("1"))  	nothing();			// One
		else if(input.equals("2"))  	nothing();			// Two
		else if(input.equals("3"))  	nothing();			// Three
		else if(input.equals("4"))  	nothing();			// Four
		else if(input.equals("5"))  	nothing();			// Five
		else if(input.equals("6"))  	nothing();			// Six
		else if(input.equals("7"))  	nothing();			// Seven
		else if(input.equals("8"))  	nothing();			// Eight
		else if(input.equals("9"))  	nothing();			// Nine
		else if(input.equals("D4"))  	cardWildD4();		// Draw Four
		else if(input.equals("W"))  	cardWild();			// Wild
		else if(input.equals("R"))  	cardReverse();		// Reverse
		else System.out.println("ERROR! : " + input + " is not a valid card.");
	}
	/**
	 * Finds who is effected by the card
	 * @param turns : int
	 * @return tempTurnNumber : int
	 */
	private int playerEffectTurns(int turns)
	{
		int tempTurnNumber = t.getTurnNumber();
		for(int x = 0; x > turns; x++)
		{
			if(t.getTurnDirection())
			{
				// FORWARDS
				if(tempTurnNumber == t.getPlayerCount() - 1)
					tempTurnNumber = 0;
				else
					tempTurnNumber += 1;
			}
			else
			{
				// BACKWORDS
				if(tempTurnNumber == 0)
					tempTurnNumber = t.getPlayerCount() - 1;
				else
					tempTurnNumber -= 1;
			}
		}
		return tempTurnNumber;
	}
	/**
	 * Forces the next player to draw the inputed amount of cards. 
	 * Also skips the next person's turn.
	 * @param cards : int
	 */
	private void cardDrawCards(int cards)
	{
		System.out.println(p.getPlayer(playerEffectTurns(1)) 
							+ " gets " + cards + " cards from " 
							+ t.getTurnNumber() + "card effect.");
		p.getPlayer(playerEffectTurns(1)).addCardToHand(cards, deck);
		cardSkip();
	}
	/**
	 * Allows the player to change the color of play
	 */
	private void cardWild()
	{
		boolean done = false;
		while(!done)
		{
			System.out.println("What color do you wish to change to? "
							 + "/n r : Red | y : Yellow | g : Green | b : Blue");
			String input = sc.nextLine();
			if(input.equals("r"))			
			{
				p.getPlayer(5).addCardToHand(new Card('R', ""));
				done = true;
			}
			else if(input.equals("y"))		
			{
				p.getPlayer(5).addCardToHand(new Card('Y', ""));
				done = true;
			}
			else if(input.equals("g"))  	
			{
				p.getPlayer(5).addCardToHand(new Card('G', ""));
				done = true;
			}
			else if(input.equals("b"))  	
			{
				p.getPlayer(5).addCardToHand(new Card('B', ""));
				done = true;
			}
			else
				System.out.println(input + " is not a valid color.");
		}
	}
	/**
	 * Allows the player to change the color of play and
	 * force the next player to draw 4 cards and
	 * skip their turn.
	 */
	private void cardWildD4()
	{
		cardWild();
		cardDrawCards(4);
	}
	/**
	 * Forces the next player to skip thier turn
	 */
	private void cardSkip()
	{
		t.nextTurn(p.getPlayerCount());
	}
	/**
	 * Reverses the direction of play
	 */
	private void cardReverse()
	{
		t.reverseTurns();
	}

	
	 // -=- -=- -=- -=- -=- -=- -=- -=- Other Methods -=- -=- -=- -=- -=- -=- -=- -=- -=-
	/**
	 * Does nothing
	 */
	private void nothing(){}

	/**
	 * Gets the top card of the play deck
	 * @return output : Card
	 */
	private Card getTopPlayPileCard()
	{
		return p.getPlayer(4).getCard( p.getPlayer(4).getCardCount() );
	}
}
