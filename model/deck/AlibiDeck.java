package model.deck;

public class AlibiDeck extends Deck
{
	@Override
	public String drawCard() {
		return cards[--numCardsRemaining];
	}
}
