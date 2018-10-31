package model.deck;
/**
 * This abstract class is used for any deck of cards (Alibi or Character), holding an array of characters
 * 
 * @author Graeme Zinck and Charles Jobin
 * @version 1.0
 */
import java.util.Arrays;
import java.util.Random;

import model.token.TokenConstants;

public abstract class Deck 
{
	/** String array of characters in the deck */
	public static final String[] CHARACTERS = TokenConstants.CHAR_NAMES;
	
	/** String array of the cards in the deck */
	public String[] cards;
	
	/** Number of cards remaining in the deck (when cards are picked from the deck it will decrement */
	protected int numCardsRemaining; 
	
	/**
	 * Constructs a deck of cards
	 */
	public Deck()
	{
		cards = Arrays.copyOf(CHARACTERS, CHARACTERS.length); // Must copy so it does not affect all decks
		numCardsRemaining = CHARACTERS.length;
		shuffle();
	}
	
	/**
	 * Shuffles a deck of cards in a random order using the Random class for the index of each card
	 */
	public void shuffle()
	{
		Random rand = new Random();
		
		for(int i = 0; i < cards.length; i++)
		{
			int randNum = rand.nextInt(cards.length);
			String temp = cards[randNum];
			cards[randNum] = cards[i];
			cards[i] = temp;
		}
	}
	
	/**
	 * Abstract method for drawing a card from the deck, implemented in CharacterDeck and AlibiDeck classes
	 * @return
	 */
	public abstract String drawCard();
}
