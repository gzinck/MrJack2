package model.token;
import model.tile.*;
import model.ability.*;
import model.player.*;
import java.util.Observable;
public class Barricade extends Observable implements Token
{
	public Exit currExit;
	public Barricade(Exit initialExit)
	{
		currExit = initialExit;
		initialExit.placeBarricade(this);
	}
	public Exit getExit() {
		return currExit;
	}
	public void moveBarricade(Exit inExit)
	{
		currExit.removeBarricade();
		inExit.placeBarricade(this);
		currExit = inExit;
	}
	
	@Override
	public int[] getTokenLocation() {
		return currExit.getTileLocation();
	}
}
