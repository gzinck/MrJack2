package model.token;
import model.tile.*;
public class Barricade extends Token
{
	public Barricade(Tile initialTile)
	{
		super(initialTile);
	}
	public void setBarricade(Exit inExit)
	{
		
		if(!inExit.isBarricaded())
		{
			currTile = inExit;
			inExit.placeBarricade(this);
		}
		else
		{
			//throw something
		}
			
	}
}
