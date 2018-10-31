package controller.clickresponders;

/**
 * This interface allows a view to register that a tile
 * was clicked without having a direct reference to
 * a controller. This is important for mediating the interactions
 * between the view and the model.
 * 
 * @author Graeme Zinck and Charles Jobin
 * @version 1.0
 *
 */
public interface TileClickResponder {
	/**
	 * This responds when a tile was clicked.
	 * 
	 * @param row the row of the tile which was clicked
	 * @param col the column of the tile which was clicked
	 */
	public void tileClicked(int row, int col);
}
