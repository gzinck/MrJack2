package model.ability;

import model.player.Player;
import model.token.*;
import model.tile.*;
public class MoveCoverAbility extends Ability {
	public Manhole currManhole;
	public ManholeCover currCover;
	public static final String ABILITY = "MoveCover";
	public MoveCoverAbility(Manhole inManhole, ManholeCover inManholeCover) {
		super.ability = ABILITY;
		currManhole = inManhole;
		currCover = inManholeCover;
	}

	@Override
	public void performAbility(Player currPlayer) {
		// TODO Auto-generated method stub
		if(!currManhole.isCovered())
		{
			currManhole.placeCover(currCover);
		}
		else
		{
			throw new IllegalArgumentException("Manhole already has a manhole cover");
		}
	}

	@Override
	public Timing whenUseAbility() {
		// TODO Auto-generated method stub
		return Timing.BEFORAFTER;
	}

}
