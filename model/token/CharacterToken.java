package model.token;

import java.util.HashSet;
import java.util.Observable;

import model.ability.Ability;
import model.player.Player;
import model.tile.Lightable;
import model.tile.Passable;

public class CharacterToken extends Observable implements Token {
	private String name;
	private int maxNumMoves;
	private boolean isInnocent;
	private Ability ability;
	private Lightable currTile, prevTile;
	
	public CharacterToken(String charName, int maxMoves, Lightable initialTile) {
		name = charName;
		maxNumMoves = maxMoves;
		currTile = initialTile;
		prevTile = initialTile;
	}
	
	public void setAbility(Ability charAbility) {
		ability = charAbility;
	}
	
	public Ability getAbility() {
		return ability;
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
			// If we are now innocent, let the controller know
			if(isInnocent) {
				setChanged();
				notifyObservers();
			}
		}
		return isInnocent;
	}
	
	/**
	 * This gets if the character is innocent based on previously computed
	 * data. This is useful for the controller that updates the view.
	 * 
	 * @return True if the character has previously been conputed innocent,
	 * false otherwise.
	 */
	public boolean isInnocent() {
		return isInnocent;
	}
	
	public void moveTo(Lightable tile) {
		prevTile = currTile;
		currTile = tile;
		setChanged();
		notifyObservers();
	}
	
	@Override
	public int[] getTokenLocation() {
		return currTile.getTileLocation();
	}
	
	@Override
	public int[] getPrevTokenLocation() {
		return prevTile.getTileLocation();
	}
}
