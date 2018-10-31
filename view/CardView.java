package view;

/**
 * This interface allows the controllers to place cards onto the
 * view without having access to any of the other methods (or
 * having dependencies that shouldn't exist).
 * 
 * @author Graeme Zinck and Charles Jobin
 * @version 1.0
 *
 */
public interface CardView {
	/**
	 * Places a character card onto the table with the given
	 * card name.
	 * 
	 * @param cardName the name of the character card to place on the table
	 * @param index the index of the position to place the card on the table.
	 * This starts at 0.
	 */
	public void placeCard(String cardName, int index);
}
