package model.tile;

/**
 * Interface for a tile which is lightable with a lamppost.
 * Has methods to allow for adding a neighbouring lamppost
 * and checking if the tile is lit by said lamppost. Note
 * that there can only be one lamppost for a given lightable
 * tile.
 * 
 * @author Graeme Zinck and Charles Jobin
 * @version 1.0
 *
 */
public interface Lightable extends Occupiable {
	/**
	 * Sets the tile's lamppost to the input lamppost.
	 * 
	 * @param lamppost the lamppost which will neighbour the tile
	 */
	public void setLamppost(Lamppost lamppost);
	/**
	 * Checks if the tile is lit or not.
	 * 
	 * @return <code>true</code> if the tile is lit
	 */
	public boolean isLit();
}
