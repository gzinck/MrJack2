package model.ability;

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
	
	@Override
	public Timing whenUseAbility() {
		return Timing.NONE;
	}

	@Override
	public boolean isAbility(String abilityString) {
		return (abilityString.equals(ABILITY));
	}

	@Override
	public int[][] startAction() {
		return null;
	}

	@Override
	public int[][] continueAction(int[] tileClickLoc) {
		return null;
	}
}
