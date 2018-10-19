package model.tile;

import java.util.HashSet;

import model.player.Player;
import model.token.CharacterToken;
import model.token.ManholeCover;

public class Manhole extends Tile {
	private static Manhole[] allManholes; // Static means we can access it from any instance
	private ManholeCover cover;
	
	public Manhole() {
		cover = null;
	}
	
	public void placeCover(ManholeCover mcover) {
		cover = mcover;
	}
	
	public void removeCover() {
		cover = null;
	}
	
	public boolean manholeAccessible() {
		return (cover == null);
	}

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
}
