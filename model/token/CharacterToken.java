package model.token;

import java.util.HashSet;
import java.util.Observable;

import model.ability.Ability;
import model.player.Player;
import model.tile.Lightable;
import model.tile.Passable;

/**
 * This class holds the required parameters for a character in the MrJack game.
 * 
 * @author Graeme Zinck and Charles Jobin
 * @version 1.0
 */
public class CharacterToken extends Observable implements Token {
	/** Name of the character */
	private String name;
	/** Maximum number of spaces a character can move */
	private int maxNumMoves;
	/** Innocence of a character */
	private boolean isInnocent;
	/** True if the character was chosen from the alibi deck */
	private boolean isMrJack;
	/** Ability that the character holds */
	private Ability ability;
	/** current and previous tile that the character was on */
	private Lightable currTile, prevTile;
	
	/**
	 * Constructs a CharacterToken
	 * @param charName input name of the character
	 * @param maxMoves maximum number of moves for the character
	 * @param initialTile initial tile the character is placed on a the start of a game
	 */
	public CharacterToken(String charName, int maxMoves, Lightable initialTile) {
		name = charName;
		maxNumMoves = maxMoves;
		currTile = initialTile;
		if(currTile != null)
			currTile.occupy();
		prevTile = initialTile;
		isMrJack = false;
	}
	
	/**
	 * Getter method for the character name
	 * @return returns the name of the character
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the character to MrJack when chosen from the alibi deck
	 */
	public void selectMrJack() {
		isMrJack = true;
	}
	
	/**
	 * Checks to see if the character is MrJack
	 * @return true if the character is MrJack, false otherwise
	 */
	public boolean isMrJack() {
		return isMrJack;
	}
	
	/**
	 * Sets the ability of the character
	 * @param charAbility input ability given to the character
	 */
	public void setAbility(Ability charAbility) {
		ability = charAbility;
	}
	
	/**
	 * Gets the ability of the character
	 * @return returns the ability of the character
	 */
	public Ability getAbility() {
		return ability;
	}
	
	/**
	 * Checks to see if a character has a certain ability with a given name
	 * @param abilityString input name that is being checked with the character's ability
	 * @return true if the input string is the character's ability, false if not or if no ability has been set
	 */
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
	
	/**
	 * Gets the accessible tiles for a character to move.
	 * 
	 * @param currPlayer the player trying to move the character
	 * @return a set of passable tiles
	 */
	public HashSet<Passable> getAccessibleTiles(Player currPlayer) {
		return getAccessibleTiles(currPlayer, 0,maxNumMoves);
	}
	
	/**
	 * Gets the accessible tiles for a character to move, given a maximum
	 * bound on the number of moves.
	 * 
	 * @param currPlayer the player trying to move the character
	 * @param maxMoves the max number of moves allowed
	 * @return a set of passable tiles
	 */
	public HashSet<Passable> getAccessibleTiles(Player currPlayer, int minMoves,int maxMoves) {
		maxMoves = (maxMoves > maxNumMoves) ? maxNumMoves : maxMoves;
		currTile.leave();
		HashSet<Passable> tiles = currTile.getAccessibleTiles(minMoves, maxMoves, this, currPlayer);
		currTile.occupy();
		return tiles;
	}
	
	/**
	 * Checks if the character is lit or not
	 * @return true if the character is lit, false otherwise
	 */
	public boolean isLit() {
		return currTile.isLit();
	}
	
	/**
	 * Checks to see if the character is innocent or not
	 * @param jackWasSeen boolean for if jack is lit on the board or not lit
	 * @return true if innocent, false if not innocent
	 */
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
	
	/**
	 * Moves a character from one tile to another
	 * @param tile A tile the character will move to
	 */
	public void moveTo(Lightable tile) {
		prevTile = currTile;
		if(prevTile != null)
			prevTile.leave();
		currTile = tile;
		currTile.occupy();
		setChanged();
		notifyObservers();
	}
	
	@Override
	public int[] getTokenLocation() {
		if(currTile == null) return null;
		return currTile.getTileLocation();
	}
	
	@Override
	public int[] getPrevTokenLocation() {
		if(prevTile == null) return null;
		return prevTile.getTileLocation();
	}
	
	@Override
	public int getTokenType() {
		// First, find the character name is charNames
		int index = 0;
		while(!TokenConstants.CHAR_NAMES[index].equals(name)) index++;
		return index;
	}
	
	@Override
	public void initializeObservers() {
		setChanged();
		notifyObservers();
	}
}
