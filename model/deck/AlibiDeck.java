package model.deck;

import model.player.Player;

public class AlibiDeck extends Deck
{
	public void drawCard(Player p) {
		p.takeCard(cards[--numCardsRemaining]);
	}
	
	public String drawCard() {
		return cards[--numCardsRemaining];
	}
}
