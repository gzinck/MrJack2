package model.token;

/**
 * Interface for tokens that allows identification of the
 * token's location and the type of token. It also allows
 * initializing the observers for a token to update the
 * view. 
 * 
 * @author Graeme Zinck and Charles Jobin
 * @version 1.0
 *
 */
public interface Token {
	/**
	 * Gets the location of the token.
	 * 
	 * @return the (row, col) location of the token
	 */
	public int[] getTokenLocation();
	/**
	 * Gets the previous location of the token before
	 * the most recent move.
	 * 
	 * @return the (row, col) location of the token
	 */
	public int[] getPrevTokenLocation();
	/**
	 * Gets the token type as an integer.
	 * 
	 * @return the type of the token
	 */
	public int getTokenType();
	/**
	 * Initializes the observers so that they update the view.
	 */
	public void initializeObservers();
}
