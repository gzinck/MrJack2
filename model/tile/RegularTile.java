package model.tile;

import java.util.HashSet;

import model.player.Detective;
import model.player.Player;
import model.token.CharacterToken;
/**
 * Regular tile that extends tile and is lightable (characters can land on this tile)
 * @author Graeme Zinck and Charles Jobin and Josh Cookson
 * @version 1.0
 */
public class RegularTile extends Tile implements Lightable {
	/** occupiable tiles around the regular tile */
	private Occupiable[] occupiableNeighbours;
	/** lamp that may be lighting the tile */
	private Lamppost lamp;
	/** Whether the tile is occupied or not */
	private boolean isOccupied;
	/** 
	 * Constructs a regular tile at a location on the gameboard
	 * @param row row on which the regular tile will be located on the gameboard
	 * @param col column on which the regular tile will be located on the gameboard
	 */
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
		
		if(lamp != null && lamp.isLit()) return true;
		
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
