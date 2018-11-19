package model.ability;


/**
 * This is an ability for reducing the moves of nearby
 * characters.
 * 
 * @author Josh Cookson and Graeme Zinck
 * @version 1.1
 */
public class MoveReducerAbility implements Ability {

	/** String for the ability name */
	public static final String ABILITY = "Move Reducer";

	@Override
	public Timing whenUseAbility() {
		return Timing.NONE;
	}

	@Override
	public boolean isAbility(String abilityString) {
		return (abilityString == ABILITY);
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
