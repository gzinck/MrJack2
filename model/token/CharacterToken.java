package model.token;

import model.ability.Ability;

public class CharacterToken {
	private String name;
	private int minNumMoves;
	private int maxNumMoves;
	private boolean isSeen;
	private boolean isInnocent;
	private Ability ability;
	
	public CharacterToken(String charName, int minMoves, int maxMoves) {
		name = charName;
		minNumMoves = minMoves;
		maxNumMoves = maxMoves;
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
}
