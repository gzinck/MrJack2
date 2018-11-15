package model.token;
import model.tile.*;
import java.util.Observable;
/**
 * This class is a gaslight token that is placed on a lamppost
 * @author Graeme Zinck and Charles Jobin
 * @version 1.0
 */
public class GasLight extends Observable implements Token 
{
	/** Current and previous lamppost the gas light is on */
	public Lamppost currLamppost, prevLamppost;
	
	/** 
	 * Constructs a gaslight to be placed on a lamppost
	 * @param initialLamppost intital lamppost the gaslight will be placed on at the start of a game
	 */
	public GasLight(Lamppost initialLamppost)
	{
		currLamppost = initialLamppost;
		prevLamppost = initialLamppost;
		initialLamppost.placeGasLight(this);
	}
	
	/**
	 * Current lamppost the gaslight is on
	 * @return lammpost the gaslight is on
	 */
	public Lamppost getLamppost()
	{
		return currLamppost;
		
	}
	
	/**
	 * Moves a gaslight from the current lamppost to another
	 * @param inLamp lamppost the gaslight will be moved to
	 */
	public void moveGasLight(Lamppost inLamp)
	{
		prevLamppost = currLamppost;
		currLamppost.removeGasLight();
		currLamppost = inLamp;
		inLamp.placeGasLight(this);
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Extinguishes a gaslight (removes from board after a round, only certain gaslights will do this during the game)
	 */
	public void extinguish()
	{
		prevLamppost = currLamppost;
		currLamppost.removeGasLight();
		currLamppost = null;
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Checks to see if the gaslight is extinguished 
	 * @return true if it is extinguished, false otherwise
	 */
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
