package model.player;

import model.token.CharacterToken;
import model.token.TokenConstants;

/**
 * This is the abstract framework for a character.
 * A character can have a set of alibi cards, has
 * a name, and can exit the board or not provided
 * a character token.
 * 
 * @author Graeme Zinck and Charles Jobin
 * @version 1.0
 *
 */
public abstract class Player {
	/** All of the alibi cards in the player's hand. */
	protected String[] alibiCards;
	/** The number of cards in the player's hand. */
	protected int numCards;
	
	/**
	 * Creates a new player.
	 */
	public Player() {
		alibiCards = new String[TokenConstants.CHAR_NAMES.length];
		numCards = 0;
	}
	
	/**
	 * Gets the player's name.
	 * @return string representing the player's name
	 */
	public abstract String getPlayerName();
	
	/**
	 * Gets if the player can exit the board with the character given.
	 * 
	 * @param character the character token being moved
	 * @return if the character can exit the board or not
	 */
	public abstract boolean canExitBoard(CharacterToken character);
	
	/**
	 * Adds a card to the player's hand.
	 * 
	 * @param alibiCard the alibi card to add
	 */
	public void takeCard(String alibiCard) {
		alibiCards[numCards++] = alibiCard;
	}
}
