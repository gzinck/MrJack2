package model.token;
import model.tile.*;
/**
 * A token that can be blaced on an exit tile
 * @author Graeme Zinck and Charles Jobin
 * @version 1.0
 */
import java.util.Observable;
public class Barricade extends Observable implements Token
{
	/** the current exit and previous exit the barriade was placed on */
	public Exit currExit, prevExit;
	
	/**
	 * Constructs a barriade on an initial exit
	 * @param initialExit initial exit for the barricade to be placed at the start of the game
	 */
	public Barricade(Exit initialExit)
	{
		currExit = initialExit;
		prevExit = currExit;
		initialExit.placeBarricade(this);
	}
	
	/**
	 * Gets the exit the barricade is placed on
	 * @return exit on which the barricade is currently placed
	 */
	public Exit getExit() {
		return currExit;
	}
	
	/**
	 * moves a barricade from one exit to another
	 * @param inExit exit the barricade will be moved to
	 */
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
	
	@Override
	public void initializeObservers() {
		setChanged();
		notifyObservers();
	}
}
