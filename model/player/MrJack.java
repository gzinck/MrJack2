package model.player;

import model.token.CharacterToken;

/**
 * Mr Jack can get a character and exit the board on certain conditions.
 * 
 * @author Graeme Zinck and Charles Jobin
 * @version 1.0
 *
 */
public class MrJack extends Player
{
	/** The name of the player. */
	public static final String PLAYER_NAME = "MrJack";
	/** Jack's character token. */
	CharacterToken jackChar;
	
	/**
	 * Sets Mr. Jack's character.
	 * 
	 * @param character the character token for Jack
	 */
	public void setCharacter(CharacterToken character) {
		jackChar = character;
		jackChar.selectMrJack();
	}
	
	@Override
	public String getPlayerName() {
		return PLAYER_NAME;
	}
	
	@Override
	public boolean canExitBoard(CharacterToken character) {
		if(character.equals(jackChar)) {
			return true;
		}
		return false;
	}
}
