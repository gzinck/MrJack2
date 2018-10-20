package model.token;
import model.tile.*;
public class GasLight
{
	public Lamppost currLamppost;
	public GasLight(Lamppost initialLamppost)
	{
		currLamppost = initialLamppost;
		initialLamppost.placeGasLight(this);
	}
	public void setGasLight(Lamppost inLamp)
	{
		if(!inLamp.isLit())
		{
			currLamppost = inLamp;
			inLamp.placeGasLight(this);
		}
		else
		{
			throw new IllegalArgumentException("Lamppost already has a gaslight");
		}
	}
	public void removeFromBoard()
	{
		currLamppost.removeGasLight();
	}
}
