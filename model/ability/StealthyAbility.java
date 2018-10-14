package model.ability;

import model.ability.Ability.Timing;
import model.player.Player;
import model.token.CharacterToken;

public class StealthyAbility extends Ability {
	public static final String ABILITY = "Stealthy";
	public StealthyAbility() {
		super.ability = ABILITY;
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
}
