package model.tile;

import java.util.HashSet;

import model.player.Player;
import model.token.CharacterToken;
import model.token.GasLight;

public class Lamppost extends Tile {
	
	private GasLight light;
	
	public boolean isLit() {
		return (light != null);
	}

}
