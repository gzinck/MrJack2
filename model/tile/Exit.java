package model.tile;

import model.ability.Ability;
import model.player.*;

public class Exit extends Tile {
	public Tile[] getAccessibleTiles(int numMoves, Ability ability, Player player) {
		// If they have the same tile
		if(numMoves < 1) throw new IllegalArgumentException("Cannot get accessible tiles when numMoves is less than 1.");
		if(player.canExitBoard()) {
			return new Tile[]{this};
		}
		return new Tile[0];
	}
}
