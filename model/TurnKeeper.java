package model;
/**
 * Class that keeps track of rounds, turns and gaslight to remove for the game
 * @author Graeme Zinck and Charles Jobin
 * @version 1.0
 */
import model.token.*;

import java.util.Observable;

import model.ability.Ability;
import model.player.*;
public class TurnKeeper extends Observable {
	/** Timing of the character's ability */
	public static enum StageTiming {
		ACTION_BEFORE, ACTION_AFTER, NO_ACTION
	}
	/** Value for a stage that has not been started */
	public static final int STAGE_TURN_NOT_STARTED = -1;
	/** Value for the stage where a user must select a character to play */
	public static final int STAGE_CHOOSE_CHAR = 0;
	/** Stage where the action must be done before the move */
	public static final int STAGE_CHOOSE_IFACTIONFIRST = 1;
	/** Stage where the action is performed before the move */
	public static final int STAGE_CHOOSE_ACTIONMOVEBEFORE = 2;
	/** Stage where the move is being performed */
	public static final int STAGE_CHOOSE_CHARMOVE = 3;
	/** Stage where the action is performed after the move */
	public static final int STAGE_CHOOSE_ACTIONMOVEAFTER = 4;
	/** Stage value for the end of a turn */
	public static final int STAGE_TURN_OVER = 5;
	
	/** Array the player turn order for odd rounds */
	private Player[] oddRoundOrder;
	/** Array of the player turn order for even rounds */
	private Player[] evenRoundOrder;
	/** Round value for before the start of a game */
	private static final int START_ROUND = 0;
	/** Turn value for before the start of a turn */
	private static final int START_TURN = 0;
	/** Value for the current turn */
	public int currTurn;
	/** Value for the current round */
	public int currRound;
	/** Maximum number of rounds in a game */
	public static final int MAX_ROUNDS = 8;
	/** Maximum number of turns in a round */
	public static final int MAX_TURNS = 2;
	/** Array of gaslights that will be removed based on end of round */
	private GasLight[] lightsToRemove;
	
	/** Variables for holding the current stage in the turn */
	private int currStage;
	private StageTiming timing;
	/** Current character being played in a turn */
	private CharacterToken currCharacter;
	
	/**
	 * Constructs a turn keeper for a game
	 * @param jack the jack player in the game
	 * @param det the detective player in the game 
	 * @param removableLights lights that are to be removed during the game
	 */
	public TurnKeeper(MrJack jack, Detective det, GasLight[] removableLights)
	{
		currTurn = START_TURN;
		currRound = START_ROUND;
		oddRoundOrder = new Player[] {det, jack};
		evenRoundOrder = new Player[] {jack, det};
		lightsToRemove = removableLights;
		currStage = STAGE_TURN_NOT_STARTED;
		currCharacter = null;
	}
	
	/**
	 * Determines which players turn it is
	 * @return the Player that must play their turn
	 */
	public Player getCurrPlayer() {
		if(currTurn == 0)
		{
			throw new IllegalArgumentException("Turn has not started");
		}	
		else if(currRound % 2 == 1)
			return oddRoundOrder[currTurn - 1];
		return evenRoundOrder[currTurn - 1];
	}
	
	/**
	 * Begins the game, starts the round and stage, returns the first player to play a turn
	 * @return the player who's turn is first
	 */
	public Player startGame() {
		currRound++; // Make the round equal to 1
		currStage = STAGE_CHOOSE_CHAR; // Go to the first stage
		Player nextPlayer = oddRoundOrder[currTurn++];
		setChanged();
		notifyObservers();
		return nextPlayer;
	}
	
	/**
	 * Goes to the next turn in the round
	 * @return the player who's turn it is
	 */
	public Player nextTurn() {
		currStage = STAGE_TURN_NOT_STARTED;
		if(!roundOver()) {
			Player nextPlayer = null;
			if(currRound % 2 == 1) nextPlayer = oddRoundOrder[currTurn++];
			else nextPlayer = evenRoundOrder[currTurn++];
			setChanged();
			notifyObservers();
			return nextPlayer;
		}
		nextRound();
		return nextTurn();
	}
	
	/**
	 * Gets the current stage in the turn
	 * @return the stage the turn is in
	 */
	public int getStage() {
		return currStage;
	}
	
	/**
	 * Gets the current round of the game 
	 * @return the round of the game
	 */
	public int getRound()
	{
		return currRound;
	}
	
	/**
	 * Gets the current turn of the round
	 * @return the current turn in the round
	 */
	public int getTurn()
	{
		return currTurn;
	}	
	
	/**
	 * Sets the timing of the action based on what character was chosen
	 * @param time timing of the action to be performed
	 */
	public void setActionTiming(StageTiming time) {
		timing = time;
	}
	
	/**
	 * Goes to the next stage of the turn
	 * @return the next stage of the turn
	 */
	public int nextStage() {
		currStage++;
		// Skip if the character doesn't have any action...
		if(currStage == STAGE_CHOOSE_IFACTIONFIRST && currCharacter.getAbility().whenUseAbility() == Ability.Timing.NONE) {
			timing = StageTiming.NO_ACTION;
			currStage++;
		}
		// Skip the stage if not applicable
		if(currStage == STAGE_CHOOSE_ACTIONMOVEAFTER && (timing == StageTiming.ACTION_BEFORE || timing == StageTiming.NO_ACTION))
			currStage++;
		if(currStage == STAGE_CHOOSE_ACTIONMOVEBEFORE && (timing == StageTiming.ACTION_AFTER || timing == StageTiming.NO_ACTION))
			currStage++;
		return currStage;
	}
	
	/**
	 * Checks if the turn is over 
	 * @return true if it is over, false otherwise
	 */
	public boolean turnOver() {
		return (currStage >= STAGE_TURN_OVER);
	}
	
	/**
	 * Removes the next gaslight based on the current round
	 */
	private void removeLight()
	{
		lightsToRemove[currRound-1].extinguish();
	}
	
	/**
	 * Checks to see if the round is over
	 * @return true if the round is over, false otherwise
	 */
	public boolean roundOver()
	{
		return (currTurn==MAX_TURNS);
	}
	
	/**
	 * Moves to the next round
	 */
	private void nextRound()
	{
		if(currRound<=lightsToRemove.length && currRound > 0)
			removeLight();
		currTurn = START_TURN;
		currRound++;
	}
	
	/**
	 * Checks if the game is over, where the MrJack player will win
	 * @return
	 */
	public boolean gameOver()
	{
		return (currRound == MAX_ROUNDS + 1);
	}
	
	/**
	 * Gets the current character to be played
	 * @return the player to be played
	 */
	public CharacterToken getCurrCharacter() {
		return currCharacter;
	}
	
	/**
	 * Sets the current character to the one chosen by the player
	 * @param newCharacter character that was chosen
	 */
	public void setCurrCharacter(CharacterToken newCharacter) {
		currCharacter = newCharacter;
	}
}
