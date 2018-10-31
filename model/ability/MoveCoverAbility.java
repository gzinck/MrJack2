package model.ability;
/**
 * Class for the move cover ability given to certain character(s)
 * 
 * @author Graeme Zinck and Charles Jobin
 * @version 1.0
 */
import model.gameboard.TokenFinder;
import model.token.*;
import model.tile.*;
public class MoveCoverAbility implements Ability {

	/** TokenFinder for the ability */
	private TokenFinder tokenFinder;
	
	/** String for the ability's name */
	public static final String ABILITY = "MoveCover";
	
	/**
	 * Constructs a MoveCoverAbility
	 * @param finder TokenFinder used to find the covers on the game board
	 */
	public MoveCoverAbility(TokenFinder finder) {
		tokenFinder = finder;
	}
	
	@Override
	public Timing whenUseAbility() {
		return Timing.BEFORAFTER;
	}

	@Override
	public void performAbility(int[] tokenLocation, int[] tileLocation) {
		ManholeCover c = tokenFinder.getManholeCover(tokenLocation);
		c.moveManholeCover(tokenFinder.getManhole(tileLocation));
	}

	@Override
	public int[][] getAbilityTokenOptions() {
		ManholeCover[] manholeCovers = tokenFinder.getManholeCovers();
		int[][] locations = new int[manholeCovers.length][];
		for(int i = 0; i < manholeCovers.length; i++) {
			locations[i] = manholeCovers[i].getTokenLocation();
		}
		return locations;
		
	}

	@Override
	public int[][] getAbilityTileOptions() {
		Manhole[] manholes= tokenFinder.getManholes();
		int numManholes = 0;
		for(int i = 0; i < manholes.length; i++)
			if(!manholes[i].isCovered()) numManholes++;
		int[][] locations = new int[numManholes][2];
		numManholes = 0;
		
		// Place locations into an array.
		for(int i = 0; i < manholes.length; i++)
			if(!manholes[i].isCovered()) locations[numManholes++] = manholes[i].getTileLocation();
		
		return locations;
	}

	@Override
	public boolean isAbility(String abilityString) {
		return abilityString.equals(ABILITY);
	}

}
