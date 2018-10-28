package model.token;
import model.tile.*;
import java.util.Observable;
public class Barricade extends Observable implements Token
{
	public Exit currExit, prevExit;
	public Barricade(Exit initialExit)
	{
		currExit = initialExit;
		prevExit = currExit;
		initialExit.placeBarricade(this);
	}
	public Exit getExit() {
		return currExit;
	}
	public void moveBarricade(Exit inExit)
	{
		prevExit = currExit;
		currExit.removeBarricade();
		inExit.placeBarricade(this);
		currExit = inExit;
		setChanged();
		notifyObservers();
	}
	
	@Override
	public int[] getTokenLocation() {
		return currExit.getTileLocation();
	}
	@Override
	public int[] getPrevTokenLocation() {
		return prevExit.getTileLocation();
	}
	@Override
	public int getTokenType() {
		int index = 0;
		while(TokenConstants.TOKEN_CHARS[index] != TokenConstants.BARRICADE_C) index++;
		return index;
	}
}
