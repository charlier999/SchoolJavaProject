import java.util.Random;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * @author Charles Davis
 *
 */
public class GamePlay 
{
	Scanner sc = new Scanner(System.in);
	Random rnd = new Random();
	
	Deck deck = new Deck();
	turns t = new turns();
	players p = new players();
	Proccesses proc = new Proccesses();

	// Variables
	private int winner;
	private boolean forceWin;
	private boolean autoOrPlayer;
	
	// -=- -=- -=- -=- -=- -=- -=- -=- Inital Game Play Start -=- -=- -=- -=- -=-
	/**
	 * The start of the game of uno
	 */
	public void start()
	{
		deck.start();
		//deck.displayAllCards();
		p.userSetPlayerCount();
		p.createPlayers();
		autoPlayUserSelect();
		dealPlayerCards();
		System.out.println("Wild Cards have been removed due to a crash exploit.");
		//p.displayAllPlayersHands();
		dealPlayDeckCard();
		t.start();
		t.setPlayerCount(p.getPlayerCount());
		forceWin = false;
		if(autoOrPlayer)
			gameAutoStart();
		else
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
	 * Deals a card to the play pile to start the game
	 */
	private void dealPlayDeckCard()
	{
		boolean done = false;
		Card temp = new Card();
		while(!done)
		{
			temp = deck.dealCard();
			if(temp.getColor() != 'W')
			{
				done = true;
			}
		}
		p.getPlayer(4).addCardToHand(temp);
	}
	/**
	 * Asks the user weather they wish to have the computer to play the game
	 * or the user to play the game.
	 */
	private void autoPlayUserSelect()
	{
		boolean done = false;
		while(!done)
		{
			System.out.println("Do you wish to have the computer play the game \n (y or n)");
			String input = sc.nextLine().toLowerCase();
			if(input.contains("y"))
			{
				autoOrPlayer = true;
				done = true;
			}
			else if(input.contains("n"))
			{
				autoOrPlayer = false;
				done = true;
			}
			else
			{
				System.out.println(input + " is not a valid responce.");
			}
		}
	}
	
	// -=- -=- -=- -=- -=- -=- -=- -=- Player Game Play Loop -=- -=- -=- -=- -=- -=- -=-
	/**
	 * The human gameplay loop
	 */
	private void gameStart()
	{
		while(!forceWin)
		{
			if(deck.getPosition() == deck.getTotalCardCount())
				deck.start();
			if(isAllCardsPlayable())
			{
				playerTurn();
			}
			else
			{
				System.out.println("player" + t.getTurnNumber() + " does not have any playable cards.\n"
						 + "*player" + t.getTurnNumber() + " gets a card and end thier turn*");
						p.getPlayer(t.getTurnNumber()).addCardToHand(1, deck);
			}
			t.nextTurn();
		}
		displayWinMessage();
	}
	/**
	 * Displays who's turn it is and that players cards.
	 * Asks for user commands.
	 * @param player : int
	 */
	private void playerTurn()
	{
		ErrorReporting.debug("GamePlay.java", 72, t.getTurnNumber(), true);
		System.out.println("-=- -=- Player" + t.getTurnNumber() + "'s Turn -=- -=-");
		System.out.println("Play Pile Card: " + getTopPlayPileCard().translateCard());
		p.displayPlayerHand(t.getTurnNumber());
		playerInput();
	}
	/**
	 * Gets the players commands and does actions from 
	 * the inputed command
	 * @param player : int
	 */
	private void playerInput()
	{
		boolean done = false;
		while(!done)
		{
			ErrorReporting.debug("GamePlay.java", 87, done, true);
			System.out.println("What do you wish to do? ('help' for commands)");
			String input = sc.nextLine();
			ErrorReporting.debug("GamePlay.java", 90, input, true);
			if(proc.isInteger(input)) 
			{
				int intInput = Integer.parseInt(input);
				if(intInput > -1)
				// If intinput is not negative
				{
					if(p.getPlayer(t.getTurnNumber()).getCardCount() > intInput)
					{
						if(isCardPlayable( p.getPlayer(t.getTurnNumber()).getCard(intInput) ) )
						{
							int player = t.getTurnNumber();
							done = true;
							
							// Adds the players card to the play deck
							p.getPlayer(4).addCardToHand( p.getPlayer(t.getTurnNumber()).getCard(intInput) );
							// Applies the card's effect to players
							cardEffect(p.getPlayer(t.getTurnNumber()).getCard(intInput));
							// Removes the played card from the player's hand
							p.getPlayer(player).removeCardfromHand(intInput);
							if(p.getPlayer(player).getCardCount() == 0)
							{
								forceWin = true;
								winner = player;
							}
								
						}
						else 
							System.out.println(input + " is not a playable card. Try another card. ('help' for commands)");
					}
					else 
						System.out.println(input + " is higher then the amount of cards you have. ('help' for commands)");
				}
				else 
					System.out.println(input + " is a negative number. Negative numbers are not allowed. ('help' for commands)");
			}
			else if(input.equals("help")) 
			// Help
				playerHelp();
			else if(input.equals("dc"))
			// Draw Cards
			{
				done = true;
				p.getPlayer(t.getTurnNumber()).addCardToHand(1, deck);
			}
			else if(input.equals("vc")) 
			// View Cards
			{
				System.out.println("Play Pile Card: " + getTopPlayPileCard().translateCard());
				System.out.println("-=- -=- -=- -=- -=- -=- -=- -=- -=- -=-");
				p.displayPlayerHand(t.getTurnNumber());
			}
			else if(input.equals("dd"))
			// Displays the debug info
				ErrorReporting.displaydebugAll();
			else if(input.equals("end"))
				forceWin = true;			
			else
				System.out.println(input + " is not a valid command. ('help' for commands)");
		}
	}
	/**
	 * Displays the command help message
	 */
	private void playerHelp()
	{
		System.out.println("--------------------Help--------------------\n"
						 + "help  |  displays all available commands 	\n"
						 + "(int) |  plays the selected card          	\n"
						 + "dc    |  Draws one card from the draw pile 	\n"
						 + "vc    |  Displays all held cards			  ");
	}
	/**
	 * Displays the win condition
	 */
	private void displayWinMessage()
	{
		System.out.println("\n \n \nPlayer" + winner + " has won the game!");
		System.exit(0);	
		}
	/**
	 * Checks to see if a player has playable cards
	 * @param player : int
	 * @return output : boolean
	 */
	private boolean isAllCardsPlayable()
	{
		for(int x = 0; x < p.getPlayer(t.getTurnNumber()).getCardCount(); x++)
		{
			Card card = p.getPlayer(t.getTurnNumber()).getCard(x);
			String topRank = proc.removeDupPrevent(getTopPlayPileCard().getRank());
			String cardRank = proc.removeDupPrevent(card.getRank());
			
			if(getTopPlayPileCard().getColor() == card.getColor())
			{
				return true;
			}
			else if(topRank.equals(cardRank))
			{
				return true;
			}
			else if(card.getColor() == 'W')
			{	
				return true;
			}
		}
		return false;
	}

	// -=- -=- -=- -=- -=- -=- -=- -=- Computer Game Play Loop -=- -=- -=- -=- -=- -=- -=-
	/**
	 * Computer only gameplay loop
	 */
	private void gameAutoStart()
	{
		while(!forceWin)
		{
			//System.out.println("Win Conditon: " + checkWinCondition());

			if(deck.getPosition() == deck.getTotalCardCount())
				deck.start();
			if(isAllCardsPlayable())
			{
				playerAutoTurn();
			}
			else
			{
				System.out.println("player" + t.getTurnNumber() + " does not have any playable cards.\n"
						 + "*player" + t.getTurnNumber() + " gets a card and end thier turn*");
						p.getPlayer(t.getTurnNumber()).addCardToHand(1, deck);
			}
			t.nextTurn();
		}
		displayWinMessage();
	}	
	/**
	 * Displays who's turn it is and that players cards.
	 * Asks for user commands.
	 * @param player : int
	 */
	private void playerAutoTurn()
	{
		ErrorReporting.debug("GamePlay.java", 72, t.getTurnNumber(), true);
		System.out.println("-=- -=- Player" + t.getTurnNumber() + "'s Turn -=- -=-");
		System.out.println("Play Pile Card: " + getTopPlayPileCard().translateCard());
		p.displayPlayerHand(t.getTurnNumber());
		playerAutoPlayCard();
	}
	/**
	 * The computer plays the first playable card in the hand
	 * @param player
	 */
	private void playerAutoPlayCard()
	{
		int player = t.getTurnNumber();
		boolean playCard = false;
		for(int x = 0; x < p.getPlayer(player).getCardCount(); x++)
		{
			Card card = p.getPlayer(player).getCard(x);
			String topRank = proc.removeDupPrevent(getTopPlayPileCard().getRank());
			String cardRank = proc.removeDupPrevent(card.getRank());
			
			if(getTopPlayPileCard().getColor() == card.getColor())
				playCard = true;
			else if(topRank.equals(cardRank))
				playCard = true;
			else if(card.getColor() == 'W')
				playCard = true;
			else
				playCard = false;
			
			if(playCard)
			{
				if(x > -1)
				// If intinput is not negative
				{
					if(!(p.getPlayer(player).getCardCount() < x))
					{
						if(isCardPlayable( p.getPlayer(player).getCard(x) ) )
						{
							// Adds the players card to the play deck
							p.getPlayer(4).addCardToHand( p.getPlayer(player).getCard(x));
							// Applies the card's effect to players
							cardEffect(p.getPlayer(player).getCard(x));
							// Removes the played card from the player's hand
							p.getPlayer(player).removeCardfromHand(x);
							
							x = p.getPlayer(player).getCardCount();
							
							if(p.getPlayer(player).getCardCount() == 0)
							{
								forceWin = true;
								winner = player;
							}
						}
						else 
							System.out.println(x + " is not a playable card. Try another card. ('help' for commands)");
					}
					else 
						System.out.println(x + " is higher then the amount of cards you have. ('help' for commands)");
				}
				else 
					System.out.println(x + " is a negative number. Negative numbers are not allowed. ('help' for commands)");
			}
			//break;
		}
	}
	
	// -=- -=- -=- -=- -=- -=- -=- -=- Play Cards GUI -=- -=- -=- -=- -=- -=- -=- 
	/**
	 * Checks to see if the card is Playable
	 * @param card : int
	 * @return output : boolean 
	 */
	private boolean isCardPlayable(Card card)
	{		
		String topRank = proc.removeDupPrevent(getTopPlayPileCard().getRank());
		String cardRank = proc.removeDupPrevent(card.getRank());
		
		if(getTopPlayPileCard().getColor() == card.getColor())
			return true;
		else if(topRank.equals(cardRank))
			return true;
		else if(card.getColor() == 'W')
			return true;
		else
			return false;
		
		

	}
	/**
	 * Applies the effect of the inputed card
	 * @param card : Card
	 */
	private void cardEffect(Card card)
	{
		System.out.println("Player" + t.getTurnNumber() + " plays " + card.translateCard());
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
		for(int x = 0; x < turns; x++)
		{
			if(t.getTurnDirection())
			{
				// FORWARDS
				if(tempTurnNumber == t.getPlayerCount())
					tempTurnNumber = 0;
				else
					tempTurnNumber = tempTurnNumber + 1;
			}
			else
			{
				// BACKWORDS
				if(tempTurnNumber == 0)
					tempTurnNumber = t.getPlayerCount();
				else
					tempTurnNumber = tempTurnNumber - 1;
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
		System.out.println("Player" + playerEffectTurns(1)
							+ " gets " + cards + " cards from player" 
							+ t.getTurnNumber() + "'s card effect.");
		p.getPlayer(playerEffectTurns(1)).addCardToHand(cards, deck);
		cardSkip();
	}
	/**
	 * Allows the player to change the color of play
	 */
	private void cardWild()
	{
		boolean done = false;
		String input = "";
		String colorOutput = "";
		while(!done)
		{
			if(!autoOrPlayer)
			{
			System.out.println("What color do you wish to change to? "
							 + "\n r : Red | y : Yellow | g : Green | b : Blue");
			input = sc.nextLine().toLowerCase();
			}
			else
			{
				int x = rnd.nextInt(3);
				if(x == 0)
					input = "r";
				else if(x==1)
					input = "y";
				else if(x == 2)
					input = "g";
				else 
					input = "b";
			}
			
			if(input.equals("r"))			
			{
				p.getPlayer(4).addCardToHand(new Card('R', "0"));
				colorOutput = "RED";
				done = true;
			}
			else if(input.equals("y"))		
			{
				p.getPlayer(4).addCardToHand(new Card('Y', "0"));
				colorOutput = "YELLOW";
				done = true;
			}
			else if(input.equals("g"))  	
			{
				p.getPlayer(4).addCardToHand(new Card('G', "0"));
				colorOutput = "GREEN";
				done = true;
			}
			else if(input.equals("b"))  	
			{
				p.getPlayer(4).addCardToHand(new Card('B', "0"));
				colorOutput = "BLUE";
				done = true;
			}
			else
				System.out.println(input + " is not a valid color.");
		}
		System.out.println("Player" + t.getTurnNumber() + " has changed the color to " + colorOutput);
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
		System.out.println("player" + playerEffectTurns(1)
							+ " turn has been skiped by player" 
							+  t.getTurnNumber()
							+ "'s card");
		t.nextTurn();
	}
	/**
	 * Reverses the direction of play
	 */
	private void cardReverse()
	{
		System.out.println("Direction of play has been reversed.");
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
		return p.getPlayer(4).getCard( p.getPlayer(4).getCardCount()-1);
	}
}
