package model.turnkeeper;
import model.token.*;
import model.player.*;
public class TurnKeeper
{
	private boolean[] turnCardOrder;
	private static final int START_ROUND = 1;
	private static final int START_TURN = 1;
	public int currTurn;
	public int currRound;
	private static final int MAX_ROUNDS = 8;
	private GasLight[] lightsToRemove;
	
	public TurnKeeper()
	{
		currTurn = START_TURN;
		currRound = START_ROUND;
	}
	public Player nextTurn()
	{
		throw new UnsupportedOperationException("UNIMPLEMENTED");
	}
	
	private void removeLight()
	{
		if(currRound<lightsToRemove.length)
			lightsToRemove[currRound].removeFromBoard();
	}
	public boolean roundOver()
	{
		throw new UnsupportedOperationException("UNIMPLEMENTED");
	}
	private void nextRound()
	{
		currRound++;
		//if curr round==9 then detective wins
	}
	
	
	
}
