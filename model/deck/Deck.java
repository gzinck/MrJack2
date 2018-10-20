package model.deck;

import java.util.Random;

public class Deck 
{
	public static final String[] CHARACTERS = {"Stealthy", "Bert", "Smith", "Lestrade"};
	public String[] cards;
	
	public Deck()
	{
		cards = CHARACTERS;
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
