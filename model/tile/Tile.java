package model.tile;

public abstract class Tile implements Passable {
	
	public static final int NUM_NEIGHBOURS = 6; 
	protected Passable[] neighbours;
	private int[] tileLocation;
	
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
