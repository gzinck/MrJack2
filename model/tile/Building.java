package model.tile;
import java.util.*;
import model.token.CharacterToken;
import model.player.*;
import model.ability.*;

public class Building extends Tile implements Passable {
	
	public Building(int row, int col) {
		super(row, col);
	}
	
	public HashSet<Passable> getAccessibleTiles(int numMoves, CharacterToken character, Player player) {
		if(numMoves < 1) throw new IllegalArgumentException("Cannot get accessible tiles when numMoves is less than 1.");
		// If the ability allows walking through things...
		HashSet<Passable> accessibleTiles = new HashSet<Passable>();
		
		// If out of moves, no tiles accessible (not even this tile)
		if(numMoves - 1 == 0) return accessibleTiles;
		
		if(character.hasAbility(StealthyAbility.ABILITY)) {
			// Then we just need to get all the tiles around us, EXCLUDING the current tile
			for(int i = 0; i < NUM_NEIGHBOURS; i++)
				if(neighbours[i] != null)
					accessibleTiles.addAll(neighbours[i].getAccessibleTiles(numMoves - 1, character, player));
		}
		
		return accessibleTiles;
	}
}
