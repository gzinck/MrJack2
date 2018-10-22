package model.token;
import model.tile.*;
import model.ability.*;
import model.player.*;
import java.util.Observable;
public class ManholeCover extends Observable implements Token 
{
	public Manhole currManhole;
	public ManholeCover(Manhole initialManhole)
	{
		currManhole = initialManhole;
		initialManhole.placeCover(this);
	}
	public Manhole getManhole()
	{
		return currManhole;
	}
	public void setManholeCover(Manhole inManhole, Player currPlayer)
	{
		
		MoveCoverAbility coverAbility = new MoveCoverAbility(inManhole, this);
		coverAbility.performAbility(currPlayer);
		notifyObservers();
	}
	@Override
	public int[] getTokenLocation() {
		return currManhole.getTileLocation();
	}
}
