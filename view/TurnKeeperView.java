package view;

/**
 * This interface allows the controllers to change the turn status
 * view without having access to any of the other methods (or
 * having dependencies that shouldn't exist).
 * 
 * @author Graeme Zinck and Charles Jobin
 * @version 1.0
 *
 */
public interface TurnKeeperView {
	/**
	 * Updates the player name registered on the turn keeper.
	 * 
	 * @param playerName the name of the player to go next
	 */
	public void updatePlayer(String playerName);
	
	/**
	 * Updates the turn number registered on the turn keeper.
	 * 
	 * @param turn the turn number
	 */
	public void updateTurn(int turn);
	
	/**
	 * Updates the round number registered on the turn keeper.
	 * 
	 * @param round the round number
	 */
	public void updateRound(int round);
}
