package model.player;

import model.token.CharacterToken;

public class Detective extends Player
{
	public static final String PLAYER_NAME = "Detective";
	private static final boolean CAN_EXIT_BOARD = false;
	
	public String getPlayerName() {
		return PLAYER_NAME;
	}
	
	public boolean canExitBoard(CharacterToken character) {
		return CAN_EXIT_BOARD;
	}
}
