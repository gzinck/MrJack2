package model.tile;

import java.util.HashSet;

import model.ability.StealthyAbility;
import model.player.Player;
import model.token.CharacterToken;
import model.token.GasLight;
import model.token.ManholeCover;

/**
 * This class extends tile and is a lamppost, gas lights can be placed on a lamppost
 * 
 * @author Graeme Zinck and Charles Jobin
 * @version 1.0
 */
public class Lamppost extends Tile implements Passable {
	
	/** number of lamps */
	private static int numLamps;
	/** gas light that may or may not be placed on the lamppost */
	private GasLight light;
	/** Array of all the lampposts on the game board */
	private static Lamppost[] allLampposts;
	/** Array of the lit lampposts on the board */
	private static boolean[] litLampposts;
	
	/**
	 * Constructs a lamppost
	 * @param row the row on which the lamppost is located on the board
	 * @param col the column on which the lamppost is located on the board
	 */
	public Lamppost(int row, int col)
	{
		super(row, col);
		light = null;
	}
	
	/**
	 * 
	 * @param inGasLight
	 */
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
		if(numMoves < 0) throw new IllegalArgumentException("Cannot get accessible tiles when numMoves is less than 1.");
		// If the ability allows walking through things...
		HashSet<Passable> accessibleTiles = new HashSet<Passable>();
		
		// If out of moves, no tiles accessible (not even this tile)
		if(numMoves == 0) return accessibleTiles;
		
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
