package model.player;

import model.gameboard.GameBoard;
import model.token.CharacterToken;

public abstract class Player {
	protected String[] alibiCards;
	protected int numCards;
	
	public Player() {
		alibiCards = new String[GameBoard.CHAR_NAMES.length];
		numCards = 0;
	}
	
	public abstract String getPlayerName();
	public abstract boolean canExitBoard(CharacterToken character);
	
	public void takeCard(String alibiCard) {
		alibiCards[numCards++] = alibiCard;
	}
}
