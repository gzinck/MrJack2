package model.deck;
/**
 * Class for the abili deck, a card is chosen from this deck to determine which character is MrJack
 * @author Graeme Zinck and Charles Jobin
 * @version 1.0
 */
 
public class AlibiDeck extends Deck
{
	@Override
	public String drawCard() {
		return cards[--numCardsRemaining];
	}
}
