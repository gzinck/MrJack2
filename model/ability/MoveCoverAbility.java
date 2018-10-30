package model.ability;

import model.gameboard.TokenFinder;
import model.token.*;
import model.tile.*;
public class MoveCoverAbility implements Ability {
private TokenFinder tokenFinder;
	
	public static final String ABILITY = "MoveCover";
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
