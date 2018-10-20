package model.token;
import model.tile.*;
public class GasLight extends Token
{
	
	public GasLight(Tile initialTile)
	{
		super(initialTile);
		
	}
	public void setGasLight(Lamppost inLamp)
	{
		
		if(!inLamp.isLit())
		{
			currTile = inLamp;
			inLamp.placeGasLight(this);
		}
		else
		{
			//throw something
		}
			
	}
	public void removeFromBoard()
	{
		setGasLight(null);
	}
}
