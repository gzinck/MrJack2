package model.tile;

/**
 * This interface is for tiles which can be occupied by a
 * Character Token.
 * 
 * @author Graeme Zinck and Charles Jobin
 * @version 1.0
 *
 */
public interface Occupiable extends Passable {
	/**
	 * Sets the tile to be occupied, letting the tile know.
	 */
	public void occupy();
	/**
	 * Sets the tile to no longer be occupied.
	 */
	public void leave();
	/**
	 * Gets if the tile is occupied.
	 * 
	 * @return <code>true</code> if the tile is occupied.
	 */
	public boolean isOccupied();
	/**
	 * Sets an occupiable neighbour for the tile in a given direction.
	 * 
	 * @param tile the tile neighbouring the current tile
	 * @param direction the direction (a number from 0 to 5, inclusive)
	 */
	public <T extends Tile & Occupiable> void setOccupiableNeighbour(T tile, int direction);
}
