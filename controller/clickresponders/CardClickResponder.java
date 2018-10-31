package controller.clickresponders;

/**
 * This interface allows a view to register that a card
 * was clicked without having a direct reference to
 * a controller. This is important for mediating the interactions
 * between the view and the model.
 * 
 * @author Graeme Zinck and Charles Jobin
 * @version 1.0
 *
 */
public interface CardClickResponder {
	/**
	 * Responds to the click of a card at a given card index
	 * which is on the table.
	 * 
	 * @param cardIndex index of the card which was clicked
	 */
	public void cardClicked(int cardIndex);
}
