package model.player;

import model.token.CharacterToken;

/**
 * The detective is cool. He cannot exit the board though.
 * Honestly, he's pretty dull. But he has that information
 * for when it's needed.
 * 
 * @author Graeme Zinck and Charles Jobin
 * @version 1.0
 *
 */
public class Detective extends Player
{
	/** The name of the player. */
	public static final String PLAYER_NAME = "Detective";
	/** Boolean representing if the player can exit the board.
	 * That is, he cannot.
	 */
	private static final boolean CAN_EXIT_BOARD = false;
	
	@Override
	public String getPlayerName() {
		return PLAYER_NAME;
	}
	
	@Override
	public boolean canExitBoard(CharacterToken character) {
		return CAN_EXIT_BOARD;
	}
}
