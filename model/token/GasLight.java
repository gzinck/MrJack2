package model.token;
import model.tile.*;
import model.ability.*;
import model.player.*;
import java.util.Observable;
public class GasLight extends Observable implements Token 
{
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
		currLamppost.removeGasLight();
		currLamppost = inLamp;
		inLamp.placeGasLight(this);
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
