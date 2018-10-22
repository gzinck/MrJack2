package model.ability;

import model.ability.Ability.Timing;
import model.player.Player;
import model.token.CharacterToken;
import model.gameboard.*;
public class StealthyAbility implements Ability {
	public static final String ABILITY = "Stealthy";
	private TokenFinder tokenFinder;
	public StealthyAbility(TokenFinder finder) {
		tokenFinder = finder;
	}
	
	public void performAbility(Player currPlayer) {
		// The ability is performed elsewhere
		// This code smells (it's unimplemented), but the benefits of having a
		// single abilities class were better than the benefits of having many
		// different types.
	}
	public Timing whenUseAbility() {
		return Timing.NONE;
	}

	@Override
	public void performAbility(int[] tokenLocation, int[] tileLocation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int[][] getAbilityTokenOptions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[][] getAbilityTileOptions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAbility(String abilityString) {
		// TODO Auto-generated method stub
		return (abilityString == ABILITY);
	}
}
