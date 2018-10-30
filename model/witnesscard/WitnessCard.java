package model.witnesscard;

import java.util.Observable;

import model.token.CharacterToken;

public class WitnessCard extends Observable {
	private boolean wasSeen;
	private CharacterToken jackToken;
	
	public WitnessCard(CharacterToken jack) {
		wasSeen = true;
		jackToken = jack;
	}
	
	public boolean updateWitnessed() {
		wasSeen = jackToken.isLit();
		setChanged();
		notifyObservers();
		return wasSeen;
	}
	
	public boolean getWitnessed() {
		return wasSeen;
	}
	
	public CharacterToken getCharacterToken()
	{
		return jackToken;
	}
}
