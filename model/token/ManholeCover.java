package model.token;
import model.tile.*;
public class ManholeCover extends Token
{
	public ManholeCover(Manhole initialTile)
	{
		super(initialTile);
		initialTile.placeCover(this);
	}
	public void setManholeCover(Manhole inManhole)
	{
		
		if(!inManhole.isCovered())
		{
			currTile = inManhole;
			inManhole.placeCover(this);
		}
		else
		{
			//throw something
		}
			
	}
}
