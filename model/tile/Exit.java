package model.tile;

import java.util.HashSet;
import model.player.*;
import model.token.CharacterToken;
import model.token.Barricade;
public class Exit extends Tile {
	private Barricade barricade;
	public HashSet<Tile> getAccessibleTiles(int numMoves, CharacterToken character, Player player) {
		if(numMoves < 1) throw new IllegalArgumentException("Cannot get accessible tiles when numMoves is less than 1.");
		
		HashSet<Tile> accessibleTiles = new HashSet<Tile>();
		if(player.canExitBoard(character)) {
			accessibleTiles.add(this);
		}
		return accessibleTiles;
	}
	public void placeBarricade(Barricade inBarr)
	{
		barricade = inBarr;
	}
	public void removeBarricade()
	{
		barricade = null;
	}	
	public boolean isBarricaded()
	{
		return(barricade!=null);
	}
}
