package model.ability;

import model.gameboard.TokenFinder;
import model.player.Player;

/**
 * This is an ability for preventing a character from using
 * manholes.
 * 
 * @author Josh Cookson and Graeme Zinck
 * @version 1.1
 */
public class ManholeIntoleranceAbility implements Ability {

	/** String for the ability name */
	public static final String ABILITY = "Manhole Intolerance";
	
	/** TokenFinder for the ability */
	private TokenFinder tokenFinder;
	
	/** Constructs a Manhole Intolerance Ability */
	public ManholeIntoleranceAbility(TokenFinder finder) {
		tokenFinder = finder;
	}
	
	/**
	 * The ability is performed elsewhere
	 * This code smells (it's unimplemented), but the benefits of having a
	 * single abilities class were better than the benefits of having many
	 * different types, at least for this version of Mr. Jack.
	 */
	public void performAbility(Player currPlayer) {
		
	}
	
	/** 
	 * Timing of when to use the ability
	 */
	public Timing whenUseAbility() {
		return Timing.NONE;
	}

	@Override
	public void performAbility(int[] tokenLocation, int[] tileLocation) {
	}

	@Override
	public int[][] getAbilityTokenOptions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[][] getAbilityTileOptions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAbility(String abilityString) {
		// TODO Auto-generated method stub
		return (abilityString.equals(ABILITY));
	}
}
