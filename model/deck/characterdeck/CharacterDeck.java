package model.deck.characterdeck;

import java.util.Random;

import model.deck.Deck;

public class CharacterDeck extends Deck
{
	public static final String[] CHARACTERS = {"Stealthy", "Bert", "Smith", "Lestrade"};
	public String[] cards;
	public int numTurns;
	public int numDiscards;
	public String[] discard;
	
	public CharacterDeck()
	{
		cards = CHARACTERS;
		discard = new String[CHARACTERS.length];
		numDiscards=0;
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
	public void drawOnTable()
	{
		
	}
	public void setChar(int x, String newChar)
	{
		cards[x]=newChar;
	}
	public String getChar(int x)
	{
		return cards[x];
	}
	public int getLength()
	{
		return cards.length;
	}
	public void discard(String character)
	{
		if(numDiscards<CHARACTERS.length)
			discard[numDiscards] = character;
	}
}
