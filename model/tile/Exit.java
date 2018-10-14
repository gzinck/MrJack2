package model.tile;

import model.player.*;
import model.token.CharacterToken;

public class Exit extends Tile {
	public Tile[] getAccessibleTiles(int numMoves, CharacterToken character, Player player) {
		// If they have the same tile
		if(numMoves < 1) throw new IllegalArgumentException("Cannot get accessible tiles when numMoves is less than 1.");
		if(player.canExitBoard(character)) {
			return new Tile[]{this};
		}
		return new Tile[0];
	}
}
