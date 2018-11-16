package model.deck;

import model.token.TokenConstants;

/**
 * This class is used for determining which characters can
 * be moved during a round, the players pick which character
 * they want to move
 * 
 * @author Graeme Zinck and Charles Jobin
 * @verison 1.0
 */
public class CharacterDeck extends Deck
{
	/**
	 * Constructs a CharacterDeck
	 */
	public CharacterDeck()
	{
		super();
	}
	
	@Override
	public String drawCard() {
		if(numCardsRemaining == 0) {
			// Otherwise, we need to reshuffle
			numCardsRemaining = TokenConstants.activeCharNames.length;
			shuffle();
		}
		return cards[--numCardsRemaining];
	}
}
