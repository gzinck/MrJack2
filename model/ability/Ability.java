package model.ability;

import model.player.*;

public abstract class Ability {
	enum Timing {
		BEFORE, AFTER, BEFORAFTER, INSTEAD, NONE
	}
	public abstract void performAbility(Player currPlayer);
}
