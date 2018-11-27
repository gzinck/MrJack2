package model.tile;
import java.util.*;
import model.token.CharacterToken;
import model.player.*;
import model.ability.*;
/**
 * This class extends tile and is a building, buildings cannot be lightable but they are passable
 * 
 * @author Graeme Zinck and Charles Jobin
 * @version 1.0
 */
public class Building extends Tile implements Passable {
	
	/**
	 * Constructs a building tile
	 * @param row the row on which the building will be located on the board
	 * @param col the column on which the building will be located on the board
	 */
	public Building(int row, int col) {
		super(row, col);
	}
	
	public HashSet<Passable> getAccessibleTiles(int minMoves, int maxMoves, CharacterToken character, Player player) {
		if(maxMoves < 0) throw new IllegalArgumentException("Cannot get accessible tiles when numMoves is less than 1.");
		// If the ability allows walking through things...
		HashSet<Passable> accessibleTiles = new HashSet<Passable>();
		
		// If out of moves, no tiles accessible (not even this tile)
		if(maxMoves == 0) return accessibleTiles;
		
		if(character.hasAbility(StealthyAbility.ABILITY)) {
			// Then we just need to get all the tiles around us, EXCLUDING the current tile
			for(int i = 0; i < NUM_NEIGHBOURS; i++)
				if(neighbours[i] != null)
					accessibleTiles.addAll(neighbours[i].getAccessibleTiles(minMoves,maxMoves - 1, character, player));
		}
		return accessibleTiles;
	}
}
