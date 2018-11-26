package model.gameboard;

import model.ability.Ability;
import model.ability.ManholeIntoleranceAbility;
import model.ability.MoveBarricadeAbility;
import model.ability.MoveCoverAbility;
import model.ability.MoveLightAbility;
import model.ability.MoveReducerAbility;
import model.ability.StealthyAbility;
import model.token.CharacterToken;
import model.token.TokenConstants;

/**
 * This class aids the game board in the initialization of the characters.
 *
 * @author Graeme Zinck and Josh Cookson
 * @version 1.1 ish
 */
public class CharGenerator {
	/** The abilities for all characters */
	private Ability[] abilities;
	/** The booleans for chosen characters */
	private boolean[] chosenChars;
	/** The character token objects created */
	private CharacterToken[] characters;
	/** The names of the characters created */
	private String[] charNames;
	
	/**
	 * Creates a new character generator.
	 * 
	 * @param tf a token finder, used by some characters' abilities.
	 */
	public CharGenerator(TokenFinder tf) {
		abilities = new Ability[]{
				new MoveBarricadeAbility(tf), new MoveCoverAbility(tf), new MoveLightAbility(tf), new StealthyAbility(),
				new ManholeIntoleranceAbility(), new MoveReducerAbility()
		};
	}
	
	/**
	 * Initializes the characters.
	 * 
	 * @return the character tokens created
	 */
	public CharacterToken[] initializeCharacters() {
		characters = new CharacterToken[TokenConstants.NUM_ACTIVE_CHARACTERS];
		charNames = new String[TokenConstants.NUM_ACTIVE_CHARACTERS];
		chosenChars = new boolean[TokenConstants.NUM_TOTAL_CHARS];
		// Selects characters from the basic character set
		selectChars(0, TokenConstants.NUM_BASIC_CHARS, 0, TokenConstants.NUM_ACTIVE_BASIC_CHARS);
		// Selects characters from the optional character set
		selectChars(TokenConstants.NUM_BASIC_CHARS, TokenConstants.NUM_OPTIONAL_CHARS, TokenConstants.NUM_ACTIVE_BASIC_CHARS, TokenConstants.NUM_ACTIVE_OPTIONAL_CHARS);
		TokenConstants.activeCharNames = charNames;
		return characters;
	}
	
	/**
	 * Selects characters to create (and subsequently instantiates them).
	 * 
	 * @param totalCharOffset the index offset of the first character
	 * that can be selected
	 * @param numTotalChars the number of characters from which to choose
	 * @param activeCharOffset the index offset of the first character position
	 * in the final array of *selected* characters
	 * @param numActiveChars the number of characters to select
	 */
	private void selectChars(int totalCharOffset, int numTotalChars, int activeCharOffset, int numActiveChars) {
		for(int i  = activeCharOffset;i < numActiveChars + activeCharOffset; i++) {
			int k = (int)(Math.random()*numTotalChars + totalCharOffset);
			while(chosenChars[k]) {
				k = (int)(Math.random()*numTotalChars + totalCharOffset);
			}
			characters[i] = new CharacterToken(TokenConstants.CHAR_NAMES[k], TokenConstants.CHAR_NUM_MOVES[k], null);
			characters[i].setAbility(abilities[k]);
			charNames[i] = TokenConstants.CHAR_NAMES[k];
			chosenChars[k] = true;
		}
	}
}
