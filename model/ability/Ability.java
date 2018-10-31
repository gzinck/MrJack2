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
	 * Performs an ability given the tile location that was clicked.
	 * 
	 * @param tileLocation Integer 2-tuple representing the (row, col)
	 * of the tile to perform the ability 
	 */
	public void performAbility(int[] tokenLocation, int[] tileLocation);
	
	/**
	 * Gets all possible locations of tokens that the player can
	 * click in order to perform the ability.
	 * 
	 * @return Array of 2-tuples of integers representing the (row, col)
	 * of the possible tokens that can be clicked.
	 */
	public int[][] getAbilityTokenOptions();
	
	/**
	 * Gets all possible locations of tiles that the player can click
	 * in order to perform the ability.
	 * 
	 * @return Array of 2-tuples of integers representing the (row, col)
	 * of the possible tiles that can be clicked.
	 */
	public int[][] getAbilityTileOptions();
	
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
