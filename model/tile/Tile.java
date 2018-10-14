package model.tile;

import model.token.*;
import model.player.*;

public abstract class Tile {
	private Tile[] neighbours;
	
	public Tile() {
		
	}
	
	public void setNeighbour(Tile t, int direction) {
		neighbours[direction] = t;
	}
	
	public abstract Tile[] getAccessibleTiles(int numMoves, CharacterToken character, Player player);
}
