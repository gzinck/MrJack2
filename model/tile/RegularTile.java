package model.tile;

import java.util.HashSet;

import model.player.Player;
import model.token.CharacterToken;

public class RegularTile extends Tile implements Occupiable {
	
	private Occupiable[] occupiableNeighbours;
	private Lamppost lamp;
	private boolean isOccupied;

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

	@Override
	public void setLamppost(Lamppost lamppost) {
		lamp = lamppost;
	}

	@Override
	public boolean isLit() {
		if(lamp.isLit()) return true;
		for(int i = 0; i < NUM_NEIGHBOURS; i++) {
			if(occupiableNeighbours[i] != null && occupiableNeighbours[i].isOccupied())
				return true;
		}
		return false;
	}

	@Override
	public void occupy() {
		isOccupied = true;
	}

	@Override
	public void leave() {
		isOccupied = false;
	}

	@Override
	public boolean isOccupied() {
		return isOccupied;
	}

	@Override
	public <T extends Tile & Occupiable> void setOccupiableNeighbour(T tile, int direction) {
		neighbours[direction] = tile;
		occupiableNeighbours[direction] = tile;
	}

}
