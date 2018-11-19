package model.token;

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


	public void setTokenOptions(int[][] options) {
		tokenLocationOptions = options;
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
	 * Sets the options for a tile's moves
	 * 
	 * @param options array of 2-tuples representing the (row, col)
	 * of every possible tile option
	 */
	public void setTileOptions(int[][] options) {
		tileLocationOptions = options;
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
	 * Gets the token selected.
	 * 
	 * @return (row, col) of the token selected
	 */
	public int[] getSelectedToken() {
		return selectedToken;
	}
	
	/**
	 * Gets the tile selected.
	 * 
	 * @return (row, col) of the tile selected
	 */
	public int[] getSelectedTile() {
		return selectedTile;
	}
	
	/**
	 * Flushes the token mover to set it up for the
	 * next time it is used.
	 */
	public void finishMove() {
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
