package model.tile;

import java.util.HashSet;

import model.ability.ManholeIntoleranceAbility;
import model.player.Detective;
import model.player.Player;
import model.token.CharacterToken;
import model.token.ManholeCover;
/**
 * This class extends tile and is a manhole, manhole covers can be placed on a manhole
 * 
 * @author Graeme Zinck, Charles Jobin, and Joshua Cookson
 * @version 1.0
 */
public class Manhole extends Tile implements Lightable {

	/** array of all the manholes on the gameboard */
	private static Manhole[] allManholes; // Static means we can access it from any instance
	/** boolean array of the manholes that are covered */
	private static boolean[] coveredManholes;
	/** neighbours of this manhole that are occupiable */
	private Occupiable[] occupiableNeighbours;
	/** lamp that may be lighting up the manhole */
	private Lamppost lamp;
	/** cover that may be placed on the manhole */
	private ManholeCover cover;
	/** used to know if a manhole has a cover */
	private boolean isOccupied;
	/** number of manholes on the gameboard */
	private static int numManholes;
	
	/**
	 * Constructs a manhole at a certain location on the gameboard
	 * @param row row location of the manhole on the gameboard
	 * @param col column location of the manhole on the gameboard
	 */
	public Manhole(int row, int col) {
		super(row, col);
		cover = null;
		isOccupied = false;
		occupiableNeighbours = new Occupiable[NUM_NEIGHBOURS];
		lamp = null;
	}
	
	/**
	 * places a cover on this manhole
	 * @param mcover cover to be placed on the manhole
	 */
	public void placeCover(ManholeCover mcover) {
		cover = mcover;
		updateCoveredManholes();
	}
	
	/**
	 * removes the cover from the manhole
	 */
	public void removeCover() {
		cover.currManhole=null;
		cover = null;
		updateCoveredManholes();
	}
	
	/**
	 * checks to see if a manhole is covered
	 * @return true if the manhole is covered, false otherwise
	 */
	public boolean isCovered()
	{
		return(cover!=null);
	}
	
	/**
	 * Checks to see if a manhole is accessible to a character to pass through
	 * @return true if accessible, false otherwise
	 */
	public boolean manholeAccessible() {
		return (cover == null);
	}
	
	/**
	 * updates the covered manholes array
	 */
	public void updateCoveredManholes()
	{
		coveredManholes = new boolean[numManholes];
		for( int i =0; i<numManholes; i++)
		{
			if(allManholes[i].isCovered())
			{	
				coveredManholes[i] = true;
			}	
			else
			{
				coveredManholes[i] = false;
			}
		}
	}
	@Override
	public HashSet<Passable> getAccessibleTiles(int minMoves, int maxMoves, CharacterToken character, Player player) {
		if(maxMoves < 0) throw new IllegalArgumentException("Cannot get accessible tiles when numMoves is less than 0.");
		HashSet<Passable> accessibleTiles = new HashSet<Passable>();
		// If curr tile is not occupied (or player is the Detective), add it as a possibility
		// Note that the min moves must be small enough!
		if(minMoves <= 0) {
			if(!isOccupied || (player != null && player.getPlayerName().equals(Detective.PLAYER_NAME)))
				accessibleTiles.add(this);
			// Add anyways if character at this location is the character we're moving
			if(character.getTokenLocation().equals(this.getTileLocation()))
				accessibleTiles.add(this);
		}
		
		// If no moves left, quit here.
		if(maxMoves == 0) return accessibleTiles;
		
		// We just need to get all the tiles around us!
		for(int i = 0; i < NUM_NEIGHBOURS; i++)
			if(neighbours[i] != null) {
				accessibleTiles.addAll(neighbours[i].getAccessibleTiles(minMoves - 1, maxMoves - 1, character, player));
			}
		
		// Now, add all the other manholes as possible tiles we can visit.
		if(manholeAccessible() && !character.hasAbility(ManholeIntoleranceAbility.ABILITY) && player != null) {
			// Get all other accessible manholes
			for(int i = 0; i < allManholes.length; i++) {
				// If manhole is available, go for it!
				if(allManholes[i] != null && allManholes[i].manholeAccessible())
					if(allManholes[i] != this) // Can't move to same location!
						accessibleTiles.addAll(allManholes[i].getAccessibleTiles(minMoves - 1, maxMoves - 1, character, player));
			}
		}
		
		return accessibleTiles;
	}

	/**
	 * Sets up the all manhole array with an input amount of manholes
	 * @param manholes manholes that are in the gameboard
	 */
	public static void setManholes(Manhole... manholes) {
		allManholes = manholes;
		numManholes = allManholes.length;
	}
	
	@Override
	public void setLamppost(Lamppost lamppost) {
		lamp = lamppost;
	}

	@Override
	public boolean isLit() {
		if(lamp != null && lamp.isLit()) return true;
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
