package model.table;

import model.deck.alibideck.AlibiDeck;
import model.gameboard.GameBoard;
import model.witnesscard.WitnessCard;

public class Table 
{
	public GameBoard board;
	public String[] characters = new String[4];
	public AlibiDeck alibis;
	public WitnessCard witness;
	
	public void discardCharacter(String character)
	{
		for(int i =0; i<characters.length; i++)
		{
			if(characters[i].equals(character))
			{
				characters[i] = "";
			}
		}
	}
	public void initializeTable()
	{
		
	}
}
