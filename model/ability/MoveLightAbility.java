package model.ability;

import model.player.Player;
import model.tile.*;
import model.token.*;

public class MoveLightAbility extends Ability {
	public GasLight currGasLight;
	public Lamppost currLamppost;
	public static final String ABILITY = "MoveLight";
	public MoveLightAbility( Lamppost inLamppost, GasLight inGasLight) {
		super.ability = ABILITY;
		currGasLight = inGasLight;
		currLamppost = inLamppost;
	}

	@Override
	public void performAbility(Player currPlayer) {
		// TODO Auto-generated method stub
		if(!currLamppost.isLit())
		{
			currLamppost.placeGasLight(currGasLight);
		}
		else
		{
			throw new IllegalArgumentException("Lamppost already has a gaslight");
		}
	}

	@Override
	public Timing whenUseAbility() {
		// TODO Auto-generated method stub
		return Timing.BEFORAFTER;
	}

}
