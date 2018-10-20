package model.tile;

public interface Occupiable {
	public void occupy();
	public void leave();
	public boolean isOccupied();
	public void setLamppost(Lamppost lamppost);
	public boolean isLit();
	public <T extends Tile & Occupiable> void setOccupiableNeighbour(T tile, int direction);
}
