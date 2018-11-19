package model.ability;


/**
 * Class for the stealthy ability given to certain character(s).
 * 
 * @author Graeme Zinck and Charles Jobin
 * @version 1.0
 */
public class StealthyAbility implements Ability {

	/** String for the ability name */
	public static final String ABILITY = "Stealthy";
	
	@Override
	public Timing whenUseAbility() {
		return Timing.NONE;
	}

	@Override
	public boolean isAbility(String abilityString) {
		// TODO Auto-generated method stub
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
