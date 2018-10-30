package model.ability;


import model.gameboard.TokenFinder;
import model.tile.*;
import model.token.*;

public class MoveLightAbility implements Ability {
private TokenFinder tokenFinder;
	
	public static final String ABILITY = "MoveGasLight";
	public MoveLightAbility(TokenFinder finder) {
		tokenFinder = finder;
	}
	
	@Override
	public Timing whenUseAbility() {
		
		return Timing.BEFORAFTER;
	}

	@Override
	public void performAbility(int[] tokenLocation, int[] tileLocation) {
		GasLight g = tokenFinder.getGasLight(tokenLocation);
		g.moveGasLight(tokenFinder.getLamp(tileLocation));
	}

	@Override
	public int[][] getAbilityTokenOptions() {
		GasLight[] gasLights = tokenFinder.getGasLights();
		
		// Some gaslights might be extinguished, so don't consider those...
		int numLights = 0;
		for(int i = 0; i < gasLights.length; i++) {
			if(!gasLights[i].isExtinguished()) numLights++;
		}
		int[][] locations = new int[numLights][];
		numLights = 0;
		for(int i = 0; i < gasLights.length; i++) {
			if(!gasLights[i].isExtinguished())
				locations[numLights++] = gasLights[i].getTokenLocation();
		}
		return locations;
	}

	@Override
	public int[][] getAbilityTileOptions() {
		Lamppost[] lampposts = tokenFinder.getLamps();
		int numLamps = 0;
		for(int i = 0; i < lampposts.length; i++)
			if(!lampposts[i].isLit()) numLamps++;
		int[][] locations = new int[numLamps][2];
		numLamps = 0;
		
		// Place locations into an array.
		for(int i = 0; i < lampposts.length; i++)
			if(!lampposts[i].isLit()) locations[numLamps++] = lampposts[i].getTileLocation();
		return locations;
	}

	@Override
	public boolean isAbility(String abilityString) {
		return abilityString.equals(ABILITY);
	}

}
