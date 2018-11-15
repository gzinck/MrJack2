package model.token;
import model.tile.*;
import java.util.Observable;
/**
 * Manhole cover token that is placed on a manhole
 * @author Graeme Zinck and Charles Jobin
 * @version 1.0
 */
public class ManholeCover extends Observable implements Token 
{
	/** Current and previous manholes the cover is on */
	public Manhole currManhole, prevManhole;
	/**
	 * Constructs a manhole cover to be placed on an initial tile at the start of the game
	 * @param initialManhole intital manhole the cover will be placed on at the start of the game
	 */
	public ManholeCover(Manhole initialManhole)
	{
		currManhole = initialManhole;
		prevManhole = initialManhole;
		initialManhole.placeCover(this);
	}
	
	/**
	 * Current manhole the cover is placed on
	 * @return manhole on which the cover is placed
	 */
	public Manhole getManhole()
	{
		return currManhole;
	}
	/**
	 * Moves a cover from the current manhole to another
	 * @param inManhole manhole the cover is moved to
	 */
	public void moveManholeCover(Manhole inManhole)
	{
		prevManhole = currManhole;
		currManhole.removeCover();
		currManhole = inManhole;
		inManhole.placeCover(this);
	
		setChanged();
		notifyObservers();
	}
	@Override
	public int[] getTokenLocation() {
		return currManhole.getTileLocation();
	}
	@Override
	public int[] getPrevTokenLocation() {
		return prevManhole.getTileLocation();
	}
	@Override
	public int getTokenType() {
		int index = 0;
		while(TokenConstants.TOKEN_CHARS[index] != TokenConstants.MANCOVER_C) index++;
		return index;
	}
	@Override
	public void initializeObservers() {
		setChanged();
		notifyObservers();
	}
}
