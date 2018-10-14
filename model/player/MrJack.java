package model.player;

public class MrJack extends Player
{
	public static final String PLAYER_NAME = "MrJack";
	private static final boolean CAN_EXIT_BOARD = true;
	
	public String getPlayerName() {
		return PLAYER_NAME;
	}
	
	public boolean canExitBoard() {
		return CAN_EXIT_BOARD;
	}
}
