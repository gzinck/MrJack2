package model.tile;

import java.util.HashSet;

import model.player.Detective;
import model.player.Player;
import model.token.CharacterToken;
import model.token.ManholeCover;

public class Manhole extends Tile implements Lightable {
	private static Manhole[] allManholes; // Static means we can access it from any instance
	private static boolean[] coveredManholes;
	private Occupiable[] occupiableNeighbours;
	private Lamppost lamp;
	private ManholeCover cover;
	private boolean isOccupied;
	private static int numManholes;
	
	public Manhole(int row, int col) {
		super(row, col);
		cover = null;
		isOccupied = false;
		occupiableNeighbours = new Lightable[NUM_NEIGHBOURS];
		lamp = null;
	}
	
	public void placeCover(ManholeCover mcover) {
		cover = mcover;
		updateCoveredManholes();
	}
	
	public void removeCover() {
		cover.currManhole=null;
		cover = null;
		updateCoveredManholes();
	}
	
	public boolean isCovered()
	{
		return(cover!=null);
	}
	public boolean manholeAccessible() {
		return (cover == null);
	}
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
	public HashSet<Passable> getAccessibleTiles(int numMoves, CharacterToken character, Player player) {
		if(numMoves < 1) throw new IllegalArgumentException("Cannot get accessible tiles when numMoves is less than 1.");
		HashSet<Passable> accessibleTiles = new HashSet<Passable>();
		
		// If curr tile is not occupied (or player is the Detective), add it as a possibility
		if(!isOccupied || player.getPlayerName().equals(Detective.PLAYER_NAME)) accessibleTiles.add(this);
		
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
		numManholes = allManholes.length;
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
