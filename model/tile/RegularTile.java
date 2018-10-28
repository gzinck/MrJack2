package model.tile;

import java.util.HashSet;

import model.player.Detective;
import model.player.Player;
import model.token.CharacterToken;

public class RegularTile extends Tile implements Lightable {
	
	private Occupiable[] occupiableNeighbours;
	private Lamppost lamp;
	private boolean isOccupied;
	
	public RegularTile(int row, int col) {
		super(row, col);
		occupiableNeighbours = new Occupiable[NUM_NEIGHBOURS];
	}
	
	@Override
	public HashSet<Passable> getAccessibleTiles(int numMoves, CharacterToken character, Player player) {
		if(numMoves < 0) throw new IllegalArgumentException("Cannot get accessible tiles when numMoves is less than 0.");
		HashSet<Passable> accessibleTiles = new HashSet<Passable>();
		
		// If curr tile is not occupied (or player is detective, add it as a possibility
		if(!isOccupied || player.getPlayerName().equals(Detective.PLAYER_NAME)) accessibleTiles.add(this);
		
		// If no moves left, quit here.
		if(numMoves == 0) return accessibleTiles;
		
		// Check all the neighbours if they're accessible
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
		System.out.println("WHERE ARE");
		if(lamp != null && lamp.isLit()) return true;
		System.out.println("WHERE ARE WE");
		for(int i = 0; i < NUM_NEIGHBOURS; i++) {
			if(occupiableNeighbours[i] != null  && occupiableNeighbours[i].isOccupied())
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
