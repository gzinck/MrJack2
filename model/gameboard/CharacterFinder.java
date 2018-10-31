package model.gameboard;

import model.token.CharacterToken;

/**
 * Interface which allows finding a character token using
 * the character token's name.
 * 
 * @author Graeme Zinck and Charles Jobin
 *
 */
public interface CharacterFinder {
	/**
	 * This gets a character token using the name of a character.
	 * 
	 * @param characterName the name of the character to find
	 * @return the corresponding character token, if it exists
	 */
	public CharacterToken getCharacter(String characterName);
}
