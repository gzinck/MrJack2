package model.turnkeeper;
import model.token.*;
import model.player.*;
public class TurnKeeper
{
	private boolean[] turnCardOrder;
	public int currTurn;
	public int currRound;
	private static final int MAX_ROUNDS = 8;
	private GasLight[] lightsToRemove;
	
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
