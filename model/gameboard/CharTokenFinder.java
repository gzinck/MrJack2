package model.gameboard;

import model.token.CharacterToken;

/**
 * Interface that allows finding tokens, as well as checking
 * if a character token has collided with another character
 * (or even specifically Mr. Jack).
 * 
 * @author Graeme Zinck and Charles Jobin
 * @version 1.0
 *
 */
public interface CharTokenFinder extends TokenFinder {
	/**
	 * Checks if there was a collision between the character token
	 * recently moved, and another token in the set of character
	 * tokens.
	 * 
	 * @param recentlyMoved the recently moved character token
	 * @return <code>true</code> if there was a collision
	 */
	public boolean characterCollision(CharacterToken recentlyMoved);
	/**
	 * Checks if there was a collision between  the character token
	 * recently moved, and the character token for Mr Jack.
	 * 
	 * @param recentlyMoved the recently moved character token
	 * @return <code>true</code> if there was a collision with Mr Jack's
	 * token
	 */
	public boolean collisionWithJack(CharacterToken recentlyMoved);
	/**
	 * This gets a character token using the name of a character.
	 *
	 * @param characterName the name of the character to find
	 * @return the corresponding character token, if it exists
	 */
	public CharacterToken getCharacter(String characterName);
}
