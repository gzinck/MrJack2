package model.tile;

import java.util.HashSet;
import model.player.*;
import model.token.CharacterToken;

public abstract class Tile {
	
	protected static final int NUM_NEIGHBOURS = 6; 
	protected Tile[] neighbours;
	
	public Tile() {
		neighbours = new Tile[NUM_NEIGHBOURS];
	}
	
	public void setNeighbour(Tile t, int direction) {
		neighbours[direction] = t;
	}
	
	public abstract HashSet<Tile> getAccessibleTiles(int numMoves, CharacterToken character, Player player);
}
