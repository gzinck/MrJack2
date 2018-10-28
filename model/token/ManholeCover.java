package model.token;
import model.tile.*;
import model.ability.*;
import model.player.*;
import java.util.Observable;
public class ManholeCover extends Observable implements Token 
{
	public Manhole currManhole, prevManhole;
	public ManholeCover(Manhole initialManhole)
	{
		currManhole = initialManhole;
		prevManhole = initialManhole;
		initialManhole.placeCover(this);
	}
	public Manhole getManhole()
	{
		return currManhole;
	}
	public void moveManholeCover(Manhole inManhole)
	{
		prevManhole = currManhole;
		currManhole.removeCover();
		inManhole.placeCover(this);
		currManhole = inManhole;
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
