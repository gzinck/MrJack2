package model.ability;

public abstract class Ability {
	enum Timing {
		BEFORE, AFTER, BEFORAFTER, INSTEAD, NONE
	}
	public abstract void performAbility(Player currPlayer);
}
