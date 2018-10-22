package model.gameboard;

import model.token.*;
import model.tile.*;

public interface TokenFinder {
	public ManholeCover getManholeCover(int[] location);
	public Barricade getBarricade(int[] location);
	public GasLight getGasLight(int[] location);
	public CharacterToken getCharacter(int[] location);
	
	public ManholeCover[] getManholeCovers();
	public Barricade[] getBarricades();
	public GasLight[] getGasLights();
	public CharacterToken[] getCharacters();
	
	public Manhole getManhole(int[] location);
	public Exit getExit(int[] location);
	public Lamppost getLamp(int[] location);
	public Lightable getTile(int[] location);
	
	public Manhole[] getManholes();
	public Exit[] getExits();
	public Lamppost[] getLamps();
}
