package model.ability;

import model.gameboard.TokenFinder;
import model.token.CharacterToken;

/**
 * Class for the move others ability given to certain character(s).
 * It enables a player to cause adjacent players to move three spaces
 * at the end of their turn.
 *
 * @author Joshua Cookson and Graeme Zinck
 * @version 1.2 ish
 */
public class MoveOthersAbility extends MoveTokenAbility {
    /**
     * String for the ability's name
     */
    public static final String ABILITY = "MoveOthers";

    /**
     * Constructs a MoveOthersAbility
     *
     * @param finder TokenFinder used to find the adjacent players on the game board
     */
    public MoveOthersAbility(TokenFinder finder) {
        super(finder);
    }

    @Override
    public Timing whenUseAbility() {
        return Timing.AFTER;
    }

    @Override
    public void performAbility(int[] tokenLocation, int[] tileLocation) {
        CharacterToken ch = tokenFinder.getCharacter(tokenLocation);
        //ch.moveCharacter(tokenFinder.getCharacter(tileLocation));
    }

    @Override
    public int[][] getAbilityTokenOptions() {
        CharacterToken[] characters = tokenFinder.getCharacters();
        TokenFinder finder = tokenFinder;

        return new int[0][];
    }

    @Override
    public int[][] getAbilityTileOptions() {
        CharacterToken[] characters = tokenFinder.getCharacters();
        int numAdjacent = 0;
        for(int i = 0; i < 6; i++){

        }
        return new int[0][];
    }


    @Override
    public boolean isAbility(String abilityString) {
        return abilityString.equals(ABILITY);
    }
}
