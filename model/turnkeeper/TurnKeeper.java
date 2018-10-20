package model.turnkeeper;
import model.token.*;
import model.player.*;
public class TurnKeeper
{
	private Player[] oddRoundOrder;
	private Player[] evenRoundOrder;
	private static final int START_ROUND = 1;
	private static final int START_TURN = 0;
	public int currTurn;
	public int currRound;
	private static final int MAX_ROUNDS = 8;
	public static final int MAX_TURNS = 2;
	private GasLight[] lightsToRemove;
	
	public TurnKeeper(MrJack jack, Detective det, GasLight[] removableLights)
	{
		currTurn = START_TURN;
		currRound = START_ROUND;
		oddRoundOrder = new Player[] {det, jack};
		evenRoundOrder = new Player[] {jack, det};
		lightsToRemove = removableLights;
	}
	public Player nextTurn()
	{
		if(!roundOver())
		{
			if(currRound%2==1)
			{
				return oddRoundOrder[currTurn++];
			}
			else
			{
				return evenRoundOrder[currTurn++];
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
		return (currTurn==MAX_TURNS);
	}
	private void nextRound()
	{
		if(currRound<=lightsToRemove.length)
			removeLight();
		currRound++;
	}
	public boolean gameOver()
	{
		return (currRound == MAX_ROUNDS + 1);
	}
}
