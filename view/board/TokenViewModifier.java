package view.board;

/**
 * This allows a controller to change aspects of the view implemented
 * in <code>BoardView.java</code> without knowing all the specifics.
 * In particular, it allows the changing of the appearance of normal
 * tokens on the game board (excludes character tokens).
 * 
 * @author Graeme Zinck and Charles Jobin
 * @version 1.0
 *
 */
public interface TokenViewModifier {
	/**
	 * Moves a token from one location to another.
	 * 
	 * @param tokenIndex index of the token type as defined in
	 * <code>TokenConstants.java</code>. It corresponds to the
	 * image index in the view.
	 * @param prevLocation the (row, col) of the previous location
	 * that the tile was at
	 * @param newLocation the (row, col) of the new location
	 * that the tile was at
	 */
	public void moveToken(int tokenIndex, int[] prevLocation, int[] newLocation);
}
