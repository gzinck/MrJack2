package model;

import java.util.Observable;
import model.deck.AlibiDeck;
import model.deck.CharacterDeck;
/**
 * Table the game is played on, has the character deck and alibi deck
 */
public class Table extends Observable {
	/** String array of the characters to be displayed on the table (picked from the character deck) */
	public String[] characters = new String[TurnKeeper.NUM_CHARS_TO_PLACE];
	/** Character deck on the table */
	public CharacterDeck charDeck;
	/** Alibi deck on the table */
	public AlibiDeck alibis;
	 /**
	  * Constructs the table with the character deck and the alibi deck
	  */
	public Table()
	{
		charDeck = new CharacterDeck();
		alibis = new AlibiDeck();
	}
	
	/**
	 * Starts the round by drawing MAX_TURNS amount of character cards onto the table
	 */
	public void startRound() {
		for(int i = 0; i < TurnKeeper.MAX_TURNS; i++) {
			characters[i] = charDeck.drawCard();
		}
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Starts the game by setting all characters down on the
	 * table to choose.
	 */
	public void startGame() {
		for(int i = 0; i < characters.length; i++)
			characters[i] = charDeck.drawCard();
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Returns the selection of character card
	 * @param index index of the character card that was selected by the user
	 * @return returns the character string with that index
	 */
	public String selectCharacter(int index) {
		String curr = characters[index];
		characters[index] = null;
		setChanged();
		notifyObservers();
		return curr;
	}
	
	/**
	 * Draws the alibi card from the alibi deck
	 * @return the name of the character that will be MrJack during the game
	 */
	public String getJackCard() {
		return alibis.drawCard();
	}
	
	/**
	 * Gets the cards that are on the table
	 * @return the cards on the table that were drawn from the character deck
	 */
	public String[] getCards() {
		return characters;
	}
}
