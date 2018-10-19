package model.tile;

import java.util.HashSet;
import model.player.*;
import model.token.CharacterToken;

public class Exit extends Tile {
	public HashSet<Tile> getAccessibleTiles(int numMoves, CharacterToken character, Player player) {
		if(numMoves < 1) throw new IllegalArgumentException("Cannot get accessible tiles when numMoves is less than 1.");
		
		HashSet<Tile> accessibleTiles = new HashSet<Tile>();
		if(player.canExitBoard(character)) {
			accessibleTiles.add(this);
		}
		return accessibleTiles;
	}
}
