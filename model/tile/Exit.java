package model.tile;

import java.util.HashSet;

import model.WitnessCard;
import model.player.*;
import model.token.CharacterToken;
import model.token.Barricade;

/**
 * This class extends tile and is an exit, exits cannot be lightable but they are passable (to win the game)
 * 
 * @author Graeme Zinck and Charles Jobin
 * @version 1.0
 */
public class Exit extends Tile implements Passable {

	/** Witness card for determining if MrJack character is lit on board */
	private static WitnessCard witnessCard;
	/** Barricade that the exit may or may not have placed on it */
	private Barricade barricade;
	
	/**
	 * Constructs an exit tile
	 * @param row the row on which the exit will be located on the board
	 * @param col the column on which the exit will be located on the board
	 */
	public Exit(int row, int col) {
		super(row, col);
	}
	
	/**
	 * Passes a witness card to the witnessCard instance variable of this Exit
	 * @param wc witness card to be passed to the exit
	 */
	public static void setWitnessCard(WitnessCard wc) {
		witnessCard = wc;
	}
	
	@Override
	public HashSet<Passable> getAccessibleTiles(int minMoves, int maxMoves, CharacterToken character, Player player) {

		if(maxMoves < 0) throw new IllegalArgumentException("Cannot get accessible tiles when numMoves is less than 0.");
		
		HashSet<Passable> accessibleTiles = new HashSet<Passable>();
		
		// Can only exit if right player with right character and the witness card
		// shows not witnessed.
		if(minMoves <= 0 && player != null && player.canExitBoard(character) && !witnessCard.getWitnessed()) {
			accessibleTiles.add(this);
		}
		return accessibleTiles;
	}
	
	/**
	 * Places a barricade token on the exit
	 * @param inBarr barricade to be placed on the exit
	 */
	public void placeBarricade(Barricade inBarr)
	{
		barricade = inBarr;
	}
	
	/**
	 * Removes a barricade from the exit
	 */
	public void removeBarricade()
	{
		barricade.currExit=null;
		barricade = null;
	}	
	
	/**
	 * Checks if an exit is already barricaded
	 * @return true if barricaded, false otherwise
	 */
	public boolean isBarricaded()
	{
		return(barricade!=null);
	}
}
