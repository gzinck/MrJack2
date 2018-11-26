package model;
/**
 * Class that is a witness card to see if the MrJack character is lit or not
 */
import java.util.Observable;

import model.token.CharacterToken;

public class WitnessCard extends Observable {
	/** Boolean to see if the MrJack character is seen after the round */
	private boolean wasSeen;
	/** The character token that is MrJack */
	private CharacterToken jackToken;
	/**
	 * COnstructs a witness card with the MrJack character
	 * @param jack the character who is Jack
	 */
	public WitnessCard(CharacterToken jack) {
		wasSeen = true;
		jackToken = jack;
	}
	
	/**
	 * Updates wasSeen by checking if the mrJack Character is lit or not
	 * @return true if the character is lit, false otherwise 
	 */
	public boolean updateWitnessed() {
		wasSeen = jackToken.isLit();
		setChanged();
		notifyObservers();
		return wasSeen;
	}
	
	/**
	 * Get the witness state of the mrjack character
	 * @return
	 */
	public boolean getWitnessed() {
		return wasSeen;
	}
	
	/**
	 * Gets the character that is mrjack
	 * @return the character token that is mrJack
	 */
	public CharacterToken getCharacterToken() {
		return jackToken;
	}
}
