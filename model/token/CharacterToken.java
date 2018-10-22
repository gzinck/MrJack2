package model.token;

import java.util.HashSet;

import model.ability.Ability;
import model.player.Player;
import model.tile.Lightable;
import model.tile.Passable;

public class CharacterToken implements Token {
	private String name;
	private int maxNumMoves;
	private boolean isInnocent;
	private Ability ability;
	private Lightable currTile;
	
	public CharacterToken(String charName, int maxMoves, Lightable initialTile) {
		name = charName;
		maxNumMoves = maxMoves;
		currTile = initialTile;
	}
	
	public void setAbility(Ability charAbility) {
		ability = charAbility;
	}
	
	public boolean hasAbility(String abilityString) {
		if(ability == null) return false;
		return ability.isAbility(abilityString);
	}
	
	@Override
	public boolean equals(Object other) {
		if(other instanceof CharacterToken) {
			// If names are identical, then true
			if(this.name.equals(((CharacterToken)other).name)) return true;
		}
		return false;
	}
	
	public HashSet<Passable> getAccessibleTiles(Player currPlayer) {
		return currTile.getAccessibleTiles(maxNumMoves, this, currPlayer);
	}
	
	public boolean isLit() {
		return currTile.isLit();
	}
	
	public boolean evaluateInnocence(boolean jackWasSeen) {
		if(!isInnocent) {
			isInnocent = (jackWasSeen != isLit());
		}
		return isInnocent;
	}
	
	public void moveTo(Lightable tile) {
		currTile = tile;
	}
	
	@Override
	public int[] getTokenLocation() {
		return currTile.getTileLocation();
	}
}
