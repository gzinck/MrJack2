package model.turnkeeper;
import model.token.*;
import model.player.*;
public class TurnKeeper
{
	public static enum StageTiming {
		ACTION_BEFORE, ACTION_AFTER, NO_ACTION
	}
	
	public static final int STAGE_TURN_NOT_STARTED = -1;
	public static final int STAGE_CHOOSE_CHAR = 0;
	public static final int STAGE_CHOOSE_IFACTIONFIRST = 1;
	public static final int STAGE_CHOOSE_ACTIONMOVEBEFORE = 2;
	public static final int STAGE_CHOOSE_CHARMOVE = 3;
	public static final int STAGE_CHOOSE_ACTIONMOVEAFTER = 4;
	public static final int STAGE_TURN_OVER = 5;
	
	private Player[] oddRoundOrder;
	private Player[] evenRoundOrder;
	private static final int START_ROUND = 1;
	private static final int START_TURN = 0;
	public int currTurn;
	public int currRound;
	private static final int MAX_ROUNDS = 8;
	public static final int MAX_TURNS = 2;
	private GasLight[] lightsToRemove;
	
	// Variables for holding the current stage in the turn
	private int currStage;
	private StageTiming timing;
	private CharacterToken currCharacter;
	
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
	public Player getCurrPlayer() {
		if(currRound % 2 == 1)
			return oddRoundOrder[currTurn - 1];
		return evenRoundOrder[currTurn - 1];
	}
	public Player nextTurn()
	{
		currStage = STAGE_TURN_NOT_STARTED;
		if(!roundOver()) {
			if(currRound%2==1) return oddRoundOrder[currTurn++];
			else return evenRoundOrder[currTurn++];
		}
		nextRound();
		return nextTurn();
	}
	public int getStage() {
		return currStage;
	}
	public void setActionTiming(StageTiming time) {
		timing = time;
	}
	public int nextStage() {
		currStage++;
		// Skip the stage if not applicable
		if(currStage == STAGE_CHOOSE_ACTIONMOVEAFTER && (timing == StageTiming.ACTION_BEFORE || timing == StageTiming.NO_ACTION))
			currStage++;
		if(currStage == STAGE_CHOOSE_ACTIONMOVEBEFORE && (timing == StageTiming.ACTION_AFTER || timing == StageTiming.NO_ACTION))
			currStage++;
		return currStage;
	}
	public boolean turnOver() {
		return (currStage == TurnKeeper.STAGE_TURN_OVER);
	}
	private void removeLight()
	{
		lightsToRemove[currRound-1].removeFromBoard();
	}
	public boolean roundOver()
	{
		return (currTurn==MAX_TURNS);
	}
	private void nextRound()
	{
		if(currRound<=lightsToRemove.length)
			removeLight();
		currTurn = 0;
		currRound++;
	}
	public boolean gameOver()
	{
		return (currRound == MAX_ROUNDS + 1);
	}
	public CharacterToken getCurrCharacter() {
		return currCharacter;
	}
	public void setCurrCharacter(CharacterToken newCharacter) {
		currCharacter = newCharacter;
	}
}
