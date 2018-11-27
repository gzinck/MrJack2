package model.ability;

import model.gameboard.CharTokenFinder;
import model.token.CharTokenMover;
import model.token.CharacterToken;
import model.token.Token;

/**
 * Class for the move others ability given to certain character(s).
 * It enables a player to cause adjacent players to move three spaces
 * at the end of their turn.
 *
 * @author Joshua Cookson and Graeme Zinck
 * @version 1.2 ish
 */
public class MoveOthersAbility implements Ability {
    private CharacterToken[] characters;
    private CharacterToken currCharacter;
    private boolean[] mustBeChanged;
    private CharTokenMover tm;
    private CharTokenFinder tf;


    /**
     * String for the ability's name
     */
    public static final String ABILITY = "MoveOthers";
    private Token character;

    /**
     * Constructs a MoveOthersAbility
     *
     * @param finder TokenFinder used to find the adjacent players on the game board
     */
    public MoveOthersAbility(CharTokenFinder finder) {
        tf = finder;
        tm = new CharTokenMover();
    }
    
    @Override
	public int[][] continueAction(int[] tileClickLoc) {
    		if(!tm.characterSelected()) {
    			currCharacter = tf.getCharacter(tileClickLoc);
    			if(currCharacter == null) return null;
    			return tm.getTileOptions(currCharacter, null, tf, 3, 3);
    		} else {
			if(tm.selectTile(tileClickLoc)) {
				// We have selected everything, and we're good to move
				performAbility();
				return getAbilityTokenOptions();
			} else return null;
		}
	}

    @Override
    public Timing whenUseAbility() {
        return Timing.AFTER;
    }

    @Override
    public int[][] startAction() {
        characters = tf.getCharacters();
        mustBeChanged = new boolean[characters.length];
        int row = character.getTokenLocation()[0];
        int col = character.getTokenLocation()[1];
        for (int i = 0; i < characters.length; i++) {
            Token c = characters[i];
            for (int dir = 0; dir < 6; dir++) {
                int[] loc = tf.getLocation(row, col, dir);
                if(loc != null) if (c.getTokenLocation()[0] == loc[0] && c.getTokenLocation()[1] == loc[1]) {
                    mustBeChanged[i] = true;
                }
            }
        }
        return getAbilityTokenOptions();
    }

    public void addCharacterToken(Token token) {
        character = token;
    }

    private void performAbility() {
    		tm.performMove();
    		CharacterToken curr = tm.getCharacter();
    		for(int i = 0; i < characters.length; i++) {
    			if(characters[i] == curr) mustBeChanged[i] = false;
    		}
    		tm.clear();
    }

    public int[][] getAbilityTokenOptions() {
        int numToChange = 0;
        for(int i = 0;i<characters.length;i++){
            if(mustBeChanged[i])
                numToChange++;
        }
        if(numToChange == 0) return new int[0][0];
        int[][] locations = new int[numToChange][];
        numToChange = 0;
        for(int i = 0; i < characters.length;i++){
            if(mustBeChanged[i]){
                locations[numToChange++] = characters[i].getTokenLocation();
            }
        }
        return locations;
    }

    @Override
    public boolean isAbility(String abilityString) {
        return abilityString.equals(ABILITY);
    }
}
