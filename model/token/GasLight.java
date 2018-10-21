package model.token;
import model.tile.*;
import model.ability.*;
import model.player.*;
import java.util.Observable;
public class GasLight extends Observable
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
		MoveLightAbility lightAbility = new MoveLightAbility(inLamp, this);
		lightAbility.performAbility(currPlayer);
		notifyObservers();
	}
	public void removeFromBoard()
	{
		currLamppost.removeGasLight();
		notifyObservers();
	}
}
