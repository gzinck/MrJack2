package model.table;

import java.util.Observable;

import model.deck.AlibiDeck;
import model.deck.CharacterDeck;
import model.turnkeeper.TurnKeeper;

public class Table extends Observable {
	public String[] characters = new String[TurnKeeper.MAX_TURNS];
	public CharacterDeck charDeck;
	public AlibiDeck alibis;
	
	public Table()
	{
		charDeck = new CharacterDeck();
		alibis = new AlibiDeck();
	}
	
	public void startRound() {
		for(int i = 0; i < TurnKeeper.MAX_TURNS; i++) {
			characters[i] = charDeck.drawCard();
		}
		setChanged();
		notifyObservers();
	}
	
	public String selectCharacter(int index) {
		String curr = characters[index];
		characters[index] = null;
		setChanged();
		notifyObservers();
		return curr;
	}
	public String getJackCard() {
		return alibis.drawCard();
	}
	public String[] getCards() {
		return characters;
	}
}
