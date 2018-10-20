package model.tile;

import java.util.HashSet;
import model.player.*;
import model.token.CharacterToken;
import model.witnesscard.WitnessCard;
import model.token.Barricade;







public class Exit extends Tile implements Passable {

	private static WitnessCard witnessCard;
	private Barricade barricade;
	
	public static void setWitnessCard(WitnessCard wc) {
		witnessCard = wc;
	}
	


	
	@Override
	public HashSet<Passable> getAccessibleTiles(int numMoves, CharacterToken character, Player player) {

		if(numMoves < 1) throw new IllegalArgumentException("Cannot get accessible tiles when numMoves is less than 1.");
		
		HashSet<Passable> accessibleTiles = new HashSet<Passable>();
		
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
		barricade.currTile=null;
		barricade = null;
	}	
	public boolean isBarricaded()
	{
		return(barricade!=null);
	}
}
