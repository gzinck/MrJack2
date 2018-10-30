package model.deck;

public class CharacterDeck extends Deck
{
	public CharacterDeck()
	{
		super();
	}	
	@Override
	public String drawCard() {
		if(numCardsRemaining == 0) {
			// Otherwise, we need to reshuffle
			numCardsRemaining = Deck.CHARACTERS.length;
			shuffle();
		}
		return cards[--numCardsRemaining];
	}
}
