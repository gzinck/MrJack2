package model.tile;

public interface Occupiable extends Passable {
	public void occupy();
	public void leave();
	public boolean isOccupied();
	public <T extends Tile & Occupiable> void setOccupiableNeighbour(T tile, int direction);
}
