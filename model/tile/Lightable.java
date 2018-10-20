package model.tile;

public interface Lightable extends Occupiable {
	public void setLamppost(Lamppost lamppost);
	public boolean isLit();
}
