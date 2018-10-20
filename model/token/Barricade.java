package model.token;
import model.tile.*;
public class Barricade extends Token
{
	public Barricade(Exit initialExit)
	{
		super(initialExit);
		initialExit.placeBarricade(this);
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
			throw new IllegalArgumentException("Exit already has a barricade");
		}
			
	}
}
