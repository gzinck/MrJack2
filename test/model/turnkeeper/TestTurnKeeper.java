package test.model.turnkeeper;
/**
 * test class for the turnkeeper class
 * @author Graeme Zinck and Charles Jobin
 * @version 1.0
 */
import static org.junit.Assert.*;

import model.TurnKeeper;
import model.player.*;
import model.tile.*;
import model.token.*;
import org.junit.Test;
public class TestTurnKeeper 
{
	Lamppost l1 = new Lamppost(0,0);
	Lamppost l2 = new Lamppost(0,0);
	Lamppost l3 = new Lamppost(0,0);
	GasLight g1 = new GasLight(l1);
	GasLight g2 = new GasLight(l2);
	GasLight g3 = new GasLight(l3);
	GasLight[] gs = {g1, g2};
	MrJack mrJack = new MrJack();
	Detective dec = new Detective();

	/**
	 * Checks the begining round, stage and turn are correct
	 */
	@Test
	public void testNewTurnKeeper()
	{
		TurnKeeper tk = new TurnKeeper(mrJack, dec, gs);
		String expect = Integer.toString(tk.getRound());
		assertEquals(Integer.toString(0), expect);
		String expect2 = Integer.toString(tk.getTurn());
		assertEquals(Integer.toString(0), expect2);
		String expect3 = Integer.toString(tk.getStage());
		assertEquals(Integer.toString(-1), expect3);

	}	
	
	/**
	 * Tests the turnkeeper has no start player before the game begins
	 */
	@Test
	public void testCurrPlayerAtStart()
	{
		TurnKeeper tk = new TurnKeeper(mrJack, dec, gs);
		boolean b = false;
		try{
			tk.getCurrPlayer();
		}catch(IllegalArgumentException e){b = true;}
		assertEquals(true, b);
	}
	
	/** 
	 * Test the curr player is correct for every round and turn in the game
	 */
	@Test 
	public void testCurrPlayerAllRounds()
	{
		boolean t = false;
		TurnKeeper tk = new TurnKeeper(mrJack, dec, gs);
		tk.startGame();
		for(int i =0; i<tk.MAX_ROUNDS;i++)
		{
			for(int j = 0; j<tk.MAX_TURNS;j++)
			{
				
				if(i%2==0)
				{
					if(j==0)
					{
						
						 t = (dec==tk.getCurrPlayer() && tk.getRound()==i+1 && tk.getTurn()==j+1);
					}
					else
					{
						t = (mrJack==tk.getCurrPlayer() && tk.getRound()==i+1 && tk.getTurn()==j+1);
					}
				}
				else
				{
					if(j==0)
					{
						 t = (mrJack==tk.getCurrPlayer() && tk.getRound()==i+1 && tk.getTurn()==j+1);
					}
					else
					{
						t = (dec==tk.getCurrPlayer() && tk.getRound()==i+1 && tk.getTurn()==j+1);
					}
				}
				assertEquals(true, t);
				
				tk.nextTurn();
			}
		}


	}
	
	/**
	 * Tests the starting stage value
	 */
	@Test
	public void testGetStageAtStart()
	{
		TurnKeeper tk = new TurnKeeper(mrJack, dec, gs);
		assertEquals(-1, tk.getStage());
	}
	
	/**
	 * Tests the round, stage values at start of game
	 */
	@Test
	public void testStartGame()
	{
		TurnKeeper tk = new TurnKeeper(mrJack, dec, gs);
		Player curr = tk.startGame();
		assertEquals(true, tk.getRound()==1);
		assertEquals(true, tk.getStage()==0);
		assertEquals(dec, curr);
		
	}

}
