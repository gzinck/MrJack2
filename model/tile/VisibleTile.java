package model.tile;

/**
 * This is the interface for a tile which has a location.
 * This interface could have been folded into another interface,
 * but was kept due to time constraints.
 * 
 * @author Graeme Zinck and Charles Jobin
 * @version 1.0
 *
 */
public interface VisibleTile {
	/**
	 * Gets the location of the tile.
	 * 
	 * @return the (row, col) of the tile
	 */
	public int[] getTileLocation();
	/**
	 * Sets the location of the tile to the given row
	 * and column.
	 * 
	 * @param row the desired row of the tile
	 * @param col the desired column of the tile
	 */
	public void setTileLocation(int row, int col);
}
