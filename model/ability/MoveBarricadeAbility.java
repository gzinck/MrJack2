package model.ability;

import model.player.Player;
import model.tile.*;
import model.token.*;

public class MoveBarricadeAbility extends Ability{
	
	public Exit currExit;
	public Barricade currBarricade;
	public static final String ABILITY = "MoveBarricade";
	public MoveBarricadeAbility(Exit inExit, Barricade inBarricade) {
		
		super.ability = ABILITY;
		currExit = inExit;
		currBarricade = inBarricade;
	}

	@Override
	public void performAbility(Player currPlayer) {
		
		
		if(!currExit.isBarricaded())
		{
			currExit.placeBarricade(currBarricade);
		}
		else
		{
			throw new IllegalArgumentException("Exit already has a barricade");
		}
	}

	@Override
	public Timing whenUseAbility() {
		// TODO Auto-generated method stub
		return Timing.BEFORAFTER;
	}

}
