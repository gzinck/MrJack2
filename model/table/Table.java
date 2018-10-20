package model.table;

import model.deck.alibideck.*;
import model.deck.characterdeck.*;

import model.gameboard.GameBoard;
import model.turnkeeper.TurnKeeper;
import model.witnesscard.WitnessCard;

public class Table 
{
	public String[] characters = new String[TurnKeeper.MAX_TURNS];
	public CharacterDeck charDeck;
	public AlibiDeck alibis;
	
	public Table()
	{
		charDeck = new CharacterDeck();
		alibis = new AlibiDeck();
	}
	public void startRound() {
		for(int i = 0; i < TurnKeeper.MAX_TURNS; i++) {
			characters[i] = charDeck.drawCard();
		}
	}
	
	/**
	 * Selects a character from the table using a String as input.
	 * If the character is on the table, then it is removed from the
	 * table and true is returned. Else, false is returned.
	 * 
	 * @param character String representing the character to select.
	 * @return True if the character was on the table and was 
	 * successfully removed, false otherwise.
	 */
	public boolean selectCharacter(String character) {
		for(int i = 0; i < characters.length; i++) {
			if(characters[i].equals(character)) {
				characters[i] = null;
				return true;
			}
		}
		return false;
	}
	public String getJackCard() {
		return alibis.drawCard();
	}
//	public void discardCharacter(String character)
//	{
//		for(int i =0; i<charDeck.getLength(); i++)
//		{
//			if(charDeck.getChar(i).equals(character))
//			{
//				charDeck.setChar(i, null);
//				charDeck.discard(character);
//				charDeck.numDiscards++;
//			}
//			
//		}
//	}
	public void initializeTable()
	{
		
	}
}
