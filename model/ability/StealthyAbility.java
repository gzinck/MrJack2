package model.ability;
/**
 * Class for the stealthy ability given to certain character(s)
 * 
 * @author Graeme Zinck and Charles Jobin
 * @version 1.0
 */
import model.player.Player;
import model.gameboard.*;
public class StealthyAbility implements Ability {

	/** String for the ability name */
	public static final String ABILITY = "Stealthy";
	
	/** TokenFinder for the ability */
	private TokenFinder tokenFinder;
	
	/** Constructs a Stealthy Ability */
	public StealthyAbility(TokenFinder finder) {
		tokenFinder = finder;
	}
	
	/**The ability is performed elsewhere
	 * This code smells (it's unimplemented), but the benefits of having a
	 * single abilities class were better than the benefits of having many
	 * different types. */
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
		return (abilityString == ABILITY);
	}
}
