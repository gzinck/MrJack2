package model.tile;

public abstract class Tile implements VisibleTile, Passable {
	
	public static final int NUM_NEIGHBOURS = 6; 
	protected Passable[] neighbours;
	private int[] tileLocation;
	
	public Tile() {
		neighbours = new Passable[NUM_NEIGHBOURS];
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
