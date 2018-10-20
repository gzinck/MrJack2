package model.deck.characterdeck;

import model.deck.Deck;

public class CharacterDeck extends Deck
{
	public CharacterDeck()
	{
		super();
	}	
	public String drawCard() {
		if(numCardsRemaining == 0) {
			// Otherwise, we need to reshuffle
			numCardsRemaining = Deck.CHARACTERS.length;
			shuffle();
		}
		return cards[--numCardsRemaining];
	}
}
