package model.token;
import model.tile.*;
import model.ability.*;
import model.player.*;
import java.util.Observable;
public class Barricade extends Observable
{
	public Exit currExit;
	public Barricade(Exit initialExit)
	{
		currExit = initialExit;
		initialExit.placeBarricade(this);
	}
	public Exit getExit()
	{
		return currExit;
	}
	public void moveBarricade(Exit inExit, Player currPlayer)
	{
		MoveBarricadeAbility barrAbility = new MoveBarricadeAbility(inExit, this);
		barrAbility.performAbility(currPlayer);
		notifyObservers();
	}
	
	
}
