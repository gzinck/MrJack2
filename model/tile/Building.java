package model.tile;

import model.token.CharacterToken;
import model.player.*;

public class Building extends Tile {
	public Tile[] getAccessibleTiles(int numMoves, CharacterToken character, Player player) {
		// Must make an exception if the player has an ability that allows them to walk through stuff
		return new Tile[0];
	}
}
