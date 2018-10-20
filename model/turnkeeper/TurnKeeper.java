package model.turnkeeper;
import model.token.*;
import model.player.*;
import model.player.*;
public class TurnKeeper
{
	private Player[] oddRoundOrder;
	private Player[] evenRoundOrder;
	private static final int START_ROUND = 1;
	private static final int START_TURN = 1;
	public int currTurn;
	public int currRound;
	private static final int MAX_ROUNDS = 8;
	private static final int MAX_TURNS = 2;
	private GasLight[] lightsToRemove;
	
	public TurnKeeper(MrJack jack, Detective det)
	{
		currTurn = START_TURN;
		currRound = START_ROUND;
		oddRoundOrder = new Player[] {det, jack};
		evenRoundOrder = new Player[] {jack, det};
	}
	public Player nextTurn()
	{
		if(!roundOver())
		{
			if(currRound%2==1)
			{
				return oddRoundOrder[currTurn];
			}
			else
			{
				return evenRoundOrder[currTurn];
			}
		}
		nextRound();
		return nextTurn();
	}
	
	private void removeLight()
	{
			lightsToRemove[currRound-1].removeFromBoard();
	}
	public boolean roundOver()
	{
		if(currTurn==2)
			return true;
			
		return false;
	}
	private void nextRound()
	{
		if(currRound<lightsToRemove.length+1)
			removeLight();
		currRound++;
		//if curr round==9 then detective wins
	}
	
	
	
}
