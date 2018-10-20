package model.ability;

import model.player.Player;

public class MoveCoverAbility extends Ability {
	
	public static final String ABILITY = "MoveCover";
	public MoveCoverAbility() {
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
