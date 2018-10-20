package model.ability;

import model.player.Player;

public class MoveLightAbility extends Ability {
	
	public static final String ABILITY = "MoveLight";
	public MoveLightAbility() {
		super.ability = ABILITY;
	}

	@Override
	public void performAbility(Player currPlayer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Timing whenUseAbility() {
		// TODO Auto-generated method stub
		return Timing.BEFORAFTER;
	}

}
