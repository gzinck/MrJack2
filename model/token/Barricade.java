package model.token;
import model.tile.*;
import java.util.Observable;
public class Barricade extends Observable
{
	public Exit currExit;
	public Barricade(Exit initialExit)
	{
		currExit = initialExit;
		initialExit.placeBarricade(this);
	}
	public void moveBarricade(Exit inExit)
	{
		
		if(!inExit.isBarricaded())
		{
			currExit = inExit;
			inExit.placeBarricade(this);
		}
		else
		{
			throw new IllegalArgumentException("Exit already has a barricade");
		}
			
	}
}
