package model.token;
import model.tile.*;
public class ManholeCover extends Token
{
	public ManholeCover(Manhole initialManhole)
	{
		super(initialManhole);
		initialManhole.placeCover(this);
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
