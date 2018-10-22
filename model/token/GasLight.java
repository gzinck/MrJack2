package model.token;
import model.tile.*;
<<<<<<< HEAD
public class GasLight implements Token {
=======
import model.ability.*;
import model.player.*;
import java.util.Observable;
public class GasLight extends Observable
{
>>>>>>> fd1311bfbdf18ce6efc15be2dec2826dc1b1c073
	public Lamppost currLamppost;
	public GasLight(Lamppost initialLamppost)
	{
		currLamppost = initialLamppost;
		initialLamppost.placeGasLight(this);
	}
	public Lamppost getLamppost()
	{
		return currLamppost;
		
	}
	public void setGasLight(Lamppost inLamp, Player currPlayer)
	{
		MoveLightAbility lightAbility = new MoveLightAbility(inLamp, this);
		lightAbility.performAbility(currPlayer);
		notifyObservers();
	}
	public void removeFromBoard()
	{
		currLamppost.removeGasLight();
		notifyObservers();
	}
	@Override
	public int[] getTokenLocation() {
		return currLamppost.getTileLocation();
	}
}
