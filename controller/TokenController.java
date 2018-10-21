package controller;
import java.util.Observable;
import java.util.Observer;
import model.token.*;
public class TokenController implements Observer
{
	private Barricade barr;
	private GasLight gasLight;
	private ManholeCover manholeCover;
	public TokenController(Barricade inBarr, GasLight inGasLight, ManholeCover inManholeCover)
	{
		barr = inBarr;
		gasLight = inGasLight;
		manholeCover = inManholeCover;
		barr.addObserver(this);
		gasLight.addObserver(this);
		manholeCover.addObserver(this);
	}
	
	
	@Override
	public void update(Observable arg0, Object arg1) 
	{
		
		
	}
	public void initializeTokens()
	{
	
	}
	public void moveToken()
	{	
		
	}
	
}
