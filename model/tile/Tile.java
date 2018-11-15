package model.tile;

/**
 * This is an abstract class for a tile.
 * 
 * @author Graeme Zinck and Charles Jobin
 *
 */
public abstract class Tile implements Passable {
	/** The number of possible neighbours for a tile. */
	public static final int NUM_NEIGHBOURS = 6; 
	/** The passable neighbours for the tile. */
	protected Passable[] neighbours;
	/** The (row, col) of the current tile. */
	private int[] tileLocation;
	
	/**
	 * Creates a new tile at the given location.
	 * 
	 * @param row the row of the tile
	 * @param col the column of the tile
	 */
	public Tile(int row, int col) {
		neighbours = new Passable[NUM_NEIGHBOURS];
		tileLocation = new int[]{row, col};
	}
	
	@Override
	public void setNeighbour(Passable t, int direction) {
		neighbours[direction] = t;
	}
	
	@Override
	public int[] getTileLocation() {
		return tileLocation;
	}
	
	@Override
	public void setTileLocation(int row, int col) {
		tileLocation = new int[] {row, col};
	}
}
