package model.turnkeeper;/
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
		
	}
	
	private void removeLight()
	{
		if(currRound<lightsToRemove.length)
			lightsToRemove[currRound].removeFromBoard();
	}
	public boolean roundOver()
	{
		
	}
	private void nextRound()
	{
		currRound++;
		//if curr round==9 then detective wins
	}
	
	
	
}
