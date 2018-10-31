package controller;

/**
 * This continues the game to the next stage, turn, and/or
 * round. It also sets off winning conditions. The interface
 * is useful for other controllers which need to progress the
 * game after performing an action, or end the game after reaching
 * an end condition.
 * 
 * @author Graeme Zinck and Charles Jobin
 *
 */
public interface GameContinuer {
	/**
	 * Continues the game's stage/turn/round, depending on what
	 * is coming next in the model's <code>TurnKeeper</code>.
	 */
	public void continueGame();
	
	/**
	 * Checks if the game is already over.
	 * 
	 * @return <code>true</code> if the game has finished
	 */
	public boolean gameIsOver();
	
	/**
	 * Sets off the end-game event for when the detective wins
	 * the game.
	 */
	public void detectiveWins();
	
	/**
	 * Sets off the end-game event for when Mr. Jack wins
	 * the game.
	 */
	public void jackWins();
}
