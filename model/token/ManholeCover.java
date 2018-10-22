package model.token;
import model.tile.*;
<<<<<<< HEAD
public class ManholeCover implements Token {
=======
import model.ability.*;
import model.player.*;
import java.util.Observable;
public class ManholeCover extends Observable
{
>>>>>>> fd1311bfbdf18ce6efc15be2dec2826dc1b1c073
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
