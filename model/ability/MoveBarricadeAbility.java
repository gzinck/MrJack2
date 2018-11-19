package model.ability;
/**
 * Class for the move barricade ability given to certain character(s).
 * It enables a player to select a barricade and an exit to move it to
 * at the beginning of their turn.
 * 
 * @author Graeme Zinck and Charles Jobin
 * @version 1.0
 */
import model.gameboard.TokenFinder;
import model.tile.*;
import model.token.*;

public class MoveBarricadeAbility extends MoveTokenAbility {
	/** The ability's name. */
	public static final String ABILITY = "MoveBarricade";
	
	/**
	 * Constructs a MoveBarricadeAbility
	 * @param finder TokenFinder that is used to find the barricade tokens on the gameboard
	 */
	public MoveBarricadeAbility(TokenFinder finder) {
		super(finder);
	}
	
	@Override
	public Timing whenUseAbility() {
		// TODO Auto-generated method stub
		return Timing.BEFORAFTER;
	}

	@Override
	public void performAbility(int[] tokenLocation, int[] tileLocation) {
		Barricade b = tokenFinder.getBarricade(tokenLocation);
		b.moveBarricade(tokenFinder.getExit(tileLocation));
	}

	@Override
	public int[][] getAbilityTokenOptions() {
		Barricade[] barricades = tokenFinder.getBarricades();
		int[][] locations = new int[barricades.length][];
		for(int i = 0; i < barricades.length; i++) {
			locations[i] = barricades[i].getTokenLocation();
		}
		return locations;
		
	}

	@Override
	public int[][] getAbilityTileOptions() {
		Exit[] exits = tokenFinder.getExits();
		int numExits = 0;
		for(int i = 0; i < exits.length; i++)
			if(!exits[i].isBarricaded()) numExits++;
		int[][] locations = new int[numExits][2];
		numExits = 0;
		
		// Place locations into an array.
		for(int i = 0; i < exits.length; i++)
			if(!exits[i].isBarricaded())
				locations[numExits++] = exits[i].getTileLocation();
		
		return locations;
	}

	@Override
	public boolean isAbility(String abilityString) {
		return abilityString.equals(ABILITY);
	}
}
