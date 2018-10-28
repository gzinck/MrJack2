package model.player;

import model.token.CharacterToken;
import model.token.TokenConstants;

public abstract class Player {
	protected String[] alibiCards;
	protected int numCards;
	
	public Player() {
		alibiCards = new String[TokenConstants.CHAR_NAMES.length];
		numCards = 0;
	}
	
	public abstract String getPlayerName();
	public abstract boolean canExitBoard(CharacterToken character);
	
	public void takeCard(String alibiCard) {
		alibiCards[numCards++] = alibiCard;
	}
}
