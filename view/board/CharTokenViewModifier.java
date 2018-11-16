package view.board;

/**
 * This allows a controller to change aspects of the view implemented
 * in <code>BoardView.java</code> without knowing all the specifics.
 * In particular, it allows the changing of the appearance of character
 * tokens on the game board.
 * 
 * @author Graeme Zinck and Charles Jobin
 * @version 1.0
 *
 */
public interface CharTokenViewModifier {
	/**
	 * Moves a character from one location to the next location.
	 * 
	 * @param characterName The name of the character. This
	 * name corresponds to the name in the array of characters
	 * in <code>TokenConstants.java</code>.
	 * @param prevLocation the previous (row, col) location of the
	 * character (starting at 0)
	 * @param newLocation the new (row, col) location of the character
	 * (starting at 0)
	 */
	public void moveCharacter(String characterName, int[] prevLocation, int[] newLocation);
	/**
	 * Sets a character's view to be innocent or not known.
	 * 
	 * @param location the (row, col) of the character to change the
	 * innocence values.
	 * @param innocent <code>true</code> if the character is innocent
	 */
	public void setInnocence(int[] location, boolean innocent);
}
