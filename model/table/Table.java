package model.table;

import model.deck.alibideck.*;
import model.deck.characterdeck.*;

import model.gameboard.GameBoard;
import model.witnesscard.WitnessCard;

public class Table 
{
	public GameBoard board;
	//public String[] characters = new String[4];
	public CharacterDeck charDeck;
	public AlibiDeck alibis;
	public WitnessCard witness;
	
	public Table()
	{
		board = new GameBoard();
		charDeck = new CharacterDeck();
		
	}
	public void discardCharacter(String character)
	{
		for(int i =0; i<charDeck.getLength(); i++)
		{
			if(charDeck.getChar(i).equals(character))
			{
				charDeck.setChar(i, null);
				charDeck.discard(character);
				charDeck.numDiscards++;
			}
			
		}
	}
	public void initializeTable()
	{
		
	}
}
