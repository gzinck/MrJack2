package model.token;

import model.tile.Tile;

public abstract class Token {
	protected Tile currTile;
	public Token(Tile initialTile) {
		currTile = initialTile;
	}
	public void setTile(Tile inTile)
	{
		currTile = inTile;
	}
}
