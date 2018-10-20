package model.token;
import model.tile.*;
public class GasLight extends Token
{
	
	public GasLight(Lamppost initialLamppost)
	{
		super(initialLamppost);
		initialLamppost.placeGasLight(this);
	}
	public void setGasLight(Lamppost inLamp)
	{
		
		if(!inLamp.isLit())
		{
			currTile = inLamp;
			inLamp.placeGasLight(this);
		}
		else
		{
			throw new IllegalArgumentException("Lamppost already has a gaslight");
		}
			
	}
	public void removeFromBoard()
	{
		setGasLight(null);
	}
}
