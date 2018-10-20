package model.deck.alibideck;

import model.deck.Deck;
import model.player.Player;

public class AlibiDeck extends Deck
{
	public void drawCard(Player p) {
		p.takeCard(cards[--numCardsRemaining]);
	}
}
