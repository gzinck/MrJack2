package model.ability;
/**
 * Interface class for the character's abilities
 * 
 * @author Graeme Zinck and Charles Jobin
 * @version 1.0
 */
public interface Ability {
	/**
	 * Enumerated type for the timing of an ability, which
	 * can be before, after, beforafter, instead, or none.
	 * 
	 * @author Graeme Zinck and Charles Jobin
	 *
	 */
	static enum Timing {
		BEFORE, AFTER, BEFORAFTER, INSTEAD, NONE
	}
	
	/**
	 * Starts the action defined by the ability, if applicable.
	 * Returns an array of (row, col) of the tiles which should
	 * be highlighted for the ability.
	 * 
	 * @return array of (row, col) of tiles that should be highlighted,
	 * if any
	 */
	public int[][] startAction();
	
	/**
	 * Continues the action defined by the ability, if applicable.
	 * Returns an array of (row, col) of the tiles which should
	 * be highlighted for the ability.
	 * 
	 * @return array of (row, col) of tiles that should be highlighted.
	 * If the ability has run its course, it returns an array of length
	 * 0. If the tile click input was illegal, then null is returned
	 * (and therefore, the game should not advance).
	 */
	public int[][] continueAction(int[] tileClickLoc);
	
	/**
	 * Gets when the ability can be used.
	 * 
	 * @return the timing of the ability
	 */
	public Timing whenUseAbility();
	/**
	 * Gets whether the ability is the same as the one
	 * passed in as a String.
	 * 
	 * @param abilityString the ability as a string
	 * @return <code>true</code> if the ability is equal
	 */
	public boolean isAbility(String abilityString);
}
