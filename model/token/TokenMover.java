package model.token;

import model.ability.Ability;

/**
 * This moves tokens from one location to another, storing all the
 * intermediary data during selection of the token to move and the
 * place to move it to. The token moves are defined by the character's
 * ability.
 * 
 * @author Graeme Zinck and Charles Jobin
 * @version 1.0
 *
 */
public class TokenMover {
	/** Arrays for the (row, col) of the possible token/tile location options. */ 
	private int[][] tokenLocationOptions, tileLocationOptions;
	/** The (row, col) of the selected token location and the selected tile location. */
	private int[] selectedToken, selectedTile;
	
	/** The ability which is being used to move the token at that time. */
	private Ability currAbility;

	/**
	 * Given an ability, this gets the possible locations (row, col) of
	 * the tokens that can be moved.
	 * 
	 * @param ability the ability which defines which tokens can be moved.
	 * @return array of (row, col) 2-tuples which define the locations of
	 * the tokens which can be moved.
	 */
	public int[][] getTokenOptions(Ability ability) {
		currAbility = ability;
		tokenLocationOptions = ability.getAbilityTokenOptions();
		return tokenLocationOptions;
	}
	
	/**
	 * Selects the token at the input (row, col), but only if
	 * it happens to be one of the options. If it is not one of
	 * the options, it returns false.
	 * 
	 * @param location the (row, col) of the token selected
	 * @return <code>true</code> if the token was successfully selected.
	 */
	public boolean selectToken(int[] location) {
		// Must see if it exists in the tokenLocationOptions
		for(int i = 0; i < tokenLocationOptions.length; i++) {
			boolean isEqual = true;
			for(int j = 0 ; j < location.length; j++) if(tokenLocationOptions[i][j] != location[j]) isEqual = false;
			if(isEqual) {
				selectedToken = location;
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Gets all the possible tile locations that the selected token
	 * can move to.
	 * 
	 * @return array of (row, col) 2-tuples representing the locations
	 * of the tiles that the token can move to.
	 */
	public int[][] getTileOptions() {
		tileLocationOptions = currAbility.getAbilityTileOptions();
		return tileLocationOptions;
	}
	
	/**
	 * Selects the tile at the location (row, col) specified.
	 * 
	 * @param location the (row, col) location of the tile
	 * @return <code>true</code> if the tile was actually a
	 * tile which was allowable and then selected
	 */
	public boolean selectTile(int[] location) {
		// Must see if it exists in the tokenLocationOptions
		for(int i = 0; i < tileLocationOptions.length; i++) {
			boolean isEqual = true;
			for(int j = 0 ; j < location.length; j++) if(tileLocationOptions[i][j] != location[j]) isEqual = false;
			if(isEqual) {
				selectedTile = location;
				return true;
			}
		}
		return false;
	}
	/**
	 * Performs the move on the selected token to the selected
	 * tile.
	 */
	public void performMove() {
		currAbility.performAbility(selectedToken, selectedTile);
		// Reset variables so that logic still works
		currAbility = null;
		tokenLocationOptions = null;
		tileLocationOptions = null;
		selectedToken = null;
		selectedTile = null;
	}
	/**
	 * Checks if a token was selected.
	 * 
	 * @return <code>true</code> if a token was selected.
	 */
	public boolean tokenSelected() {
		return (selectedToken != null);
	}
	/**
	 * Checks if a tile was selected.
	 * 
	 * @return <code>true</code> if a tile was selected.
	 */
	public boolean tileSelected() {
		return (selectedTile != null);
	}
}
