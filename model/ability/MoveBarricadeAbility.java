package model.ability;

import model.player.Player;
import model.tile.*;
public class MoveBarricadeAbility extends Ability {
	
	public static final String ABILITY = "MoveBarricade";
	public MoveBarricadeAbility() {
		super.ability = ABILITY;
	}

	@Override
	public void performAbility(Player currPlayer) {
		
		
	}

	@Override
	public Timing whenUseAbility() {
		// TODO Auto-generated method stub
		return Timing.BEFORAFTER;
	}

}
