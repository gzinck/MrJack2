package model.token;

import model.tile.Tile;

public abstract class Token {
	private Tile currTile;
	public Token(Tile initialTile) {
		currTile = initialTile;
	}
}
