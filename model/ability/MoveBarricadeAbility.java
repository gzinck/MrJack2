package model.ability;

import model.gameboard.TokenFinder;
import model.player.Player;
import model.tile.*;
import model.token.*;

public class MoveBarricadeAbility implements Ability{
	
	private TokenFinder tokenFinder;
	
	public static final String ABILITY = "MoveBarricade";
	public MoveBarricadeAbility(TokenFinder finder) {
		tokenFinder = finder;
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
		for(int i = 0; i < locations.length; i++)
			if(!exits[i].isBarricaded()) locations[numExits++] = exits[i].getTileLocation();
		
		return locations;
	}

	@Override
	public boolean isAbility(String abilityString) {
		return abilityString.equals(ABILITY);
	}
}
