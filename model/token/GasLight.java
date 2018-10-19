package model.token;
import model.tile.*;
public class GasLight extends Token
{
	public GasLight(Tile initialTile)
	{
		super(initialTile);
	}
	public void removeFromBoard()
	{
		setTile(null);
	}
}
