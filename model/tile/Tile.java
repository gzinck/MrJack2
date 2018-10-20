package model.tile;

public abstract class Tile {
	
	protected static final int NUM_NEIGHBOURS = 6; 
	protected Passable[] neighbours;
	
	public Tile() {
		neighbours = new Passable[NUM_NEIGHBOURS];
	}
	
	public void setNeighbour(Passable t, int direction) {
		neighbours[direction] = t;
	}
}
