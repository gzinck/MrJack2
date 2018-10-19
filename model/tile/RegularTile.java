package model.tile;

import java.util.HashSet;

import model.player.Player;
import model.token.CharacterToken;

public class RegularTile extends Tile {

	@Override
	public HashSet<Tile> getAccessibleTiles(int numMoves, CharacterToken character, Player player) {
		if(numMoves < 1) throw new IllegalArgumentException("Cannot get accessible tiles when numMoves is less than 1.");
		HashSet<Tile> accessibleTiles = new HashSet<Tile>();
		// We just need to get all the tiles around us!
		accessibleTiles.add(this);
		
		// But if no moves left, quit here.
		if(numMoves - 1 == 0) return accessibleTiles;
		
		for(int i = 0; i < NUM_NEIGHBOURS; i++)
			if(neighbours[i] != null)
				accessibleTiles.addAll(neighbours[i].getAccessibleTiles(numMoves - 1, character, player));
		return accessibleTiles;
	}

}
