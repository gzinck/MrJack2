package model.token;
import model.tile.*;
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
	public void extinguish()
	{
		prevLamppost = currLamppost;
		currLamppost.removeGasLight();
		currLamppost = null;
		setChanged();
		notifyObservers();
	}
	public boolean isExtinguished() {
		return (currLamppost == null);
	}
	@Override
	public int[] getTokenLocation() {
		if(currLamppost == null) return null;
		return currLamppost.getTileLocation();
	}
	@Override
	public int[] getPrevTokenLocation() {
		return prevLamppost.getTileLocation();
	}
	@Override
	public int getTokenType() {
		int index = 0;
		while(TokenConstants.TOKEN_CHARS[index] != TokenConstants.GASLIGHT_C) index++;
		return index;
	}
	@Override
	public void initializeObservers() {
		setChanged();
		notifyObservers();
	}
}
