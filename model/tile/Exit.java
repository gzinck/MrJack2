package model.tile;

import java.util.HashSet;
import model.player.*;
import model.token.CharacterToken;
<<<<<<< HEAD
import model.token.Barricade;
public class Exit extends Tile {
	private Barricade barricade;
=======
import model.witnesscard.WitnessCard;

public class Exit extends Tile {
	private static WitnessCard witnessCard;
	
	public static void setWitnessCard(WitnessCard wc) {
		witnessCard = wc;
	}
	
>>>>>>> f8ef99080e79fe711fa928696658d05c154a0509
	public HashSet<Tile> getAccessibleTiles(int numMoves, CharacterToken character, Player player) {
		if(numMoves < 1) throw new IllegalArgumentException("Cannot get accessible tiles when numMoves is less than 1.");
		
		HashSet<Tile> accessibleTiles = new HashSet<Tile>();
		
		// Can only exit if right player with right character and the witness card
		// shows not witnessed.
		if(player.canExitBoard(character) && !witnessCard.getWitnessed()) {
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
