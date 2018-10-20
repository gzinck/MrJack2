package model.tile;

import java.util.HashSet;

import model.player.Player;
import model.token.CharacterToken;
import model.token.GasLight;
import model.token.ManholeCover;

public class Lamppost extends Tile {
	
	private GasLight light;
	private boolean isOccupied;
	public Lamppost()
	{
		light = null;
		isOccupied = false;
	}
	
	public void placeGasLight(GasLight inGasLight) {
		light = inGasLight;
	}
	
	public void removeCover() {
		light.currTile=null;
		light = null;
		
	}
	
	public boolean isLit() {
		return (light != null);
	}

}
