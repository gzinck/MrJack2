package model.ability;
public interface Ability{
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
	public Timing whenUseAbility();
	public boolean isAbility(String abilityString);
}
