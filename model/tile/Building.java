package model.tile;

import model.token.CharacterToken;
import model.player.*;
import model.ability.*;

public class Building extends Tile {
	public Tile[] getAccessibleTiles(int numMoves, CharacterToken character, Player player) {
		// If the ability allows walking through things...
		return new Tile[0];
	}
}
