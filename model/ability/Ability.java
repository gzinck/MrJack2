package model.ability;

import model.player.*;
import model.tile.*;
public abstract class Ability {
	public String ability;
	static enum Timing {
		BEFORE, AFTER, BEFORAFTER, INSTEAD, NONE
	}
	public abstract void performAbility(Player currPlayer);
	public abstract Timing whenUseAbility();
	public boolean isAbility(String abilityString) {
		if(ability.equals(abilityString)) return true;
		return false;
	}
}
