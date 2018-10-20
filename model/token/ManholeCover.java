package model.token;
import model.tile.*;
public class ManholeCover
{
	public Manhole currManhole;
	public ManholeCover(Manhole initialManhole)
	{
		currManhole = initialManhole;
		initialManhole.placeCover(this);
	}
	public void setManholeCover(Manhole inManhole)
	{
		
		if(!inManhole.isCovered())
		{
			currManhole = inManhole;
			inManhole.placeCover(this);
		}
		else
		{
			throw new IllegalArgumentException("Manhole already has a manhole cover");
		}
			
	}
}
