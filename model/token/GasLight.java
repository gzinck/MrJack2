package model.token;
import model.tile.*;
import model.ability.*;
import model.player.*;
import java.util.Observable;
public class GasLight extends Observable implements Token 
{
	public Lamppost currLamppost, prevLamppost;
	public GasLight(Lamppost initialLamppost)
	{
		currLamppost = initialLamppost;
		prevLamppost = initialLamppost;
		initialLamppost.placeGasLight(this);
	}
	public Lamppost getLamppost()
	{
		return currLamppost;
		
	}
	public void moveGasLight(Lamppost inLamp)
	{
		prevLamppost = currLamppost;
		currLamppost.removeGasLight();
		currLamppost = inLamp;
		inLamp.placeGasLight(this);
		setChanged();
		notifyObservers();
	}
	public void removeFromBoard()
	{
		setChanged();
		currLamppost.removeGasLight();
		notifyObservers();
	}
	@Override
	public int[] getTokenLocation() {
		return currLamppost.getTileLocation();
	}
	@Override
	public int[] getPrevTokenLocation() {
		return prevLamppost.getTileLocation();
	}
}
