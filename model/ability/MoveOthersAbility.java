package model.ability;

import model.gameboard.TokenFinder;
import model.token.CharTokenMover;
import model.token.Token;
import model.token.TokenMover;

/**
 * Class for the move others ability given to certain character(s).
 * It enables a player to cause adjacent players to move three spaces
 * at the end of their turn.
 *
 * @author Joshua Cookson and Graeme Zinck
 * @version 1.2 ish
 */
public class MoveOthersAbility extends MoveTokenAbility {
    private Token[] characters;
    private boolean[] mustBeChanged;
    private CharTokenMover tm;


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
    public MoveOthersAbility(TokenFinder finder) {
        super(finder);
        tm = new CharTokenMover();
    }

    @Override
    public Timing whenUseAbility() {
        return Timing.AFTER;
    }

    @Override
    public int[][] startAction() {
        characters = tokenFinder.getCharacters();
        mustBeChanged = new boolean[characters.length];
        int row = character.getTokenLocation()[0];
        int col = character.getTokenLocation()[1];
        for (int i = 0; i < characters.length; i++) {
            Token c = characters[i];
            for (int dir = 0; dir < 6; dir++) {
                int[] loc = tokenFinder.getLocation(row, col, dir);
                if (c.getTokenLocation()[0] == loc[0] && c.getTokenLocation()[1] == loc[1]) {
                    mustBeChanged[i] = true;
                }
            }
        }
        return getAbilityTokenOptions();
    }

    public void addCharacterToken(Token token) {
        character = token;
    }

    @Override
    public void performAbility(int[] tokenLocation, int[] tileLocation) {
        Token ch = tokenFinder.getCharacter(tokenLocation);
    }

    @Override
    public int[][] getAbilityTokenOptions() {
        int numToChange = 0;
        for(int i = 0;i<characters.length;i++){
            if(mustBeChanged[i])
                numToChange++;
        }
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
    public int[][] getAbilityTileOptions() {

        return new int[0][];
    }

    @Override
    public boolean isAbility(String abilityString) {
        return abilityString.equals(ABILITY);
    }
}
