package model.tile;

import java.util.HashSet;

import model.ability.StealthyAbility;
import model.player.Player;
import model.token.CharacterToken;
import model.token.GasLight;
import model.token.ManholeCover;

public class Lamppost extends Tile implements Passable {
	
	private static int numLamps;
	private GasLight light;
	private static Lamppost[] allLampposts;
	private static boolean[] litLampposts;
	public Lamppost()
	{
		light = null;
	}
	
	public void placeGasLight(GasLight inGasLight) {
		light = inGasLight;
		updateLit();
	}
	
	public void removeGasLight() {
		light.currLamppost=null;
		light = null;
		updateLit();
	}
	
	public boolean isLit() {
		return (light != null);
	}

	@Override
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
	public void updateLit()
	{
		litLampposts = new boolean[numLamps];
		for(int i = 0; i<numLamps;i++)
		{
			if(allLampposts[i].isLit())
			{
				litLampposts[i]=true;
			}
			else{
				litLampposts[i]=false;
			}
		}
	}
	public static void setLampposts(Lamppost... inLampposts)
	{
		allLampposts = inLampposts;
		numLamps = allLampposts.length;
	}
}
