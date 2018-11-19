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
	
	private Ability[] abilities;
	private TokenFinder finder;
	private boolean[] chosenChars;
	private CharacterToken[] characters;
	private String[] charNames;
	
	public CharGenerator(TokenFinder tf) {
		finder = tf;
		abilities = new Ability[]{
				new MoveBarricadeAbility(tf), new MoveCoverAbility(tf), new MoveLightAbility(tf), new StealthyAbility(),
				new ManholeIntoleranceAbility(), new MoveReducerAbility()
		};
	}
	
	public CharacterToken[] initializeCharacters() {
		characters = new CharacterToken[TokenConstants.NUM_ACTIVE_CHARACTERS];
		charNames = new String[TokenConstants.NUM_ACTIVE_CHARACTERS];
		chosenChars = new boolean[TokenConstants.NUM_TOTAL_CHARS];
		selectChars(0, TokenConstants.NUM_BASIC_CHARS, 0, TokenConstants.NUM_ACTIVE_BASIC_CHARS);
		selectChars(TokenConstants.NUM_BASIC_CHARS, TokenConstants.NUM_OPTIONAL_CHARS, TokenConstants.NUM_ACTIVE_BASIC_CHARS, TokenConstants.NUM_ACTIVE_OPTIONAL_CHARS);
		TokenConstants.activeCharNames = charNames;
		return characters;
	}
	
	private void selectChars(int totalCharOffset, int numTotalChars, int activeCharOffset, int numActiveChars) {
		for(int i  = activeCharOffset;i < numActiveChars + activeCharOffset; i++) {
			int k = (int)(Math.random()*numTotalChars + totalCharOffset);
			while(chosenChars[k]) {
				k = (int)(Math.random()*numTotalChars + totalCharOffset);
			}
			int[] loc = TokenConstants.CHAR_LOCATIONS[i];
			characters[i] = new CharacterToken(TokenConstants.CHAR_NAMES[k], TokenConstants.CHAR_NUM_MOVES[k], finder.getTile(loc));
			characters[i].setAbility(abilities[k]);
			charNames[i] = TokenConstants.CHAR_NAMES[k];
			chosenChars[k] = true;
		}
	}
}
