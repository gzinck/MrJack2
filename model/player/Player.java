package model.player;

import model.token.CharacterToken;

public abstract class Player {
	public abstract String getPlayerName();
	public abstract boolean canExitBoard(CharacterToken character);
}
