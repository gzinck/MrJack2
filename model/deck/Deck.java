package model.deck;

import java.util.Arrays;
import java.util.Random;

import model.gameboard.GameBoard;

public abstract class Deck 
{
	public static final String[] CHARACTERS = GameBoard.CHAR_NAMES;
	public String[] cards;
	protected int numCardsRemaining; 
	
	public Deck()
	{
		cards = Arrays.copyOf(CHARACTERS, CHARACTERS.length); // Must copy so it does not affect all decks
		numCardsRemaining = CHARACTERS.length;
		shuffle();
	}
	public void shuffle()
	{
		Random rand = new Random();
		
		for(int i =0; i<cards.length;i++)
		{
			int randNum = rand.nextInt(cards.length);
			String temp = cards[randNum];
			cards[randNum] = cards[i];
			cards[i] = temp;
		}
	}
}
