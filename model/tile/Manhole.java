package model.tile;

import java.util.HashSet;

import model.player.Player;
import model.token.CharacterToken;
import model.token.ManholeCover;

public class Manhole extends Tile implements Occupiable {
	private static Manhole[] allManholes; // Static means we can access it from any instance
	private Occupiable[] occupiableNeighbours;
	private Lamppost lamp;
	private ManholeCover cover;
	private boolean isOccupied;
	
	public Manhole() {
		cover = null;
		isOccupied = false;
		occupiableNeighbours = new Occupiable[NUM_NEIGHBOURS];
		lamp = null;
	}
	
	public void placeCover(ManholeCover mcover) {
		cover = mcover;
	}
	
	public void removeCover() {
		cover.currTile=null;
		cover = null;
	}
	
	public boolean isCovered()
	{
		return(cover!=null);
	}
	public boolean manholeAccessible() {
		return (cover == null);
	}

	@Override
	public HashSet<Passable> getAccessibleTiles(int numMoves, CharacterToken character, Player player) {
		if(numMoves < 1) throw new IllegalArgumentException("Cannot get accessible tiles when numMoves is less than 1.");
		HashSet<Passable> accessibleTiles = new HashSet<Passable>();
		
		// If curr tile is not occupied, add it as a possibility
		if(!isOccupied) accessibleTiles.add(this);
		
		// If no moves left, quit here.
		if(numMoves - 1 == 0) return accessibleTiles;
		
		// We just need to get all the tiles around us!
		for(int i = 0; i < NUM_NEIGHBOURS; i++)
			if(neighbours[i] != null)
				accessibleTiles.addAll(neighbours[i].getAccessibleTiles(numMoves - 1, character, player));
		
		// Now, add all the other manholes as possible tiles we can visit.
		if(manholeAccessible()) {
			// Get all other accessible manholes
			for(int i = 0; i < allManholes.length; i++) {
				// If manhole is available, go for it!
				if(allManholes[i] != null && allManholes[i].manholeAccessible())
					accessibleTiles.addAll(allManholes[i].getAccessibleTiles(numMoves - 1, character, player));
			}
		}
		
		return accessibleTiles;
	}

	public static void setManholes(Manhole... manholes) {
		allManholes = manholes;
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
