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

	@Override
	public HashSet<Tile> getAccessibleTiles(int numMoves, CharacterToken character, Player player) {
		return new HashSet<Tile>(); // Nothing is accessible from here.
		// Note: this code smells, but it's super convenient... Right now, the price
		// of looking a bit odd is worth it considering how much time it saves!
	}

}
