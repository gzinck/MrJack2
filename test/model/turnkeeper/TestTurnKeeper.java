package test.model.turnkeeper;
import static org.junit.Assert.*;
import model.player.*;
import model.tile.*;
import model.token.*;
import model.turnkeeper.*;
import org.junit.Test;
public class TestTurnKeeper 
{
	Lamppost l1 = new Lamppost();
	Lamppost l2 = new Lamppost();
	Lamppost l3 = new Lamppost();
	GasLight g1 = new GasLight(l1);
	GasLight g2 = new GasLight(l2);
	GasLight g3 = new GasLight(l3);
	GasLight[] gs = {g1, g2};
	MrJack mrJack = new MrJack();
	Detective dec = new Detective();


	@Test
	public void testNewTurnKeeper()
	{
		TurnKeeper tk = new TurnKeeper(mrJack, dec, gs);
		String expect = Integer.toString(tk.getRound());
		assertEquals(expect, Integer.toString(1));
		String expect2 = Integer.toString(tk.getTurn());
		assertEquals(expect2, Integer.toString(0));
		String expect3 = Integer.toString(tk.getStage());
		assertEquals(expect3, Integer.toString(-1));

	}	
	@Test
	public void testCurrPlayerAtStart()
	{
		TurnKeeper tk = new TurnKeeper(mrJack, dec, gs);
		boolean b = false;
		try{
			tk.getCurrPlayer();
		}catch(IllegalArgumentException e){b = true;}
		assertEquals(b, true);
	}
	@Test
	public void testCurrPlayerR1T1()
	{
		boolean t = false;
		TurnKeeper tk = new TurnKeeper(mrJack, dec, gs);
		for(int i =0; i<tk.MAX_ROUNDS;i++)
		{
			for(int j = 0; j<tk.MAX_TURNS;j++)
			{
				tk.nextTurn();
				if(i%2==1)
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
				assertEquals(t, true);
			}
		}


	}
	@Test
	public void testCurrPlayerR1T2()
	{
		TurnKeeper tk = new TurnKeeper(mrJack, dec, gs);
		tk.nextTurn();
		tk.nextTurn();
		boolean t = (mrJack==tk.getCurrPlayer() && tk.getRound()==1 && tk.getTurn()==2);
		assertEquals(t, true);
	}
	@Test
	public void testCurrPlayerR2T1()
	{
		TurnKeeper tk = new TurnKeeper(mrJack, dec, gs);
		tk.nextTurn();
		tk.nextTurn();
		tk.nextTurn();
		boolean t = (mrJack==tk.getCurrPlayer() && tk.getRound()==2 && tk.getTurn()==1 );
		assertEquals(t, true);
	}
	@Test
	public void testCurrPlayerR2T2()
	{
		TurnKeeper tk = new TurnKeeper(mrJack, dec, gs);
		tk.nextTurn();
		tk.nextTurn();
		tk.nextTurn();
		tk.nextTurn();
		boolean t = (dec==tk.getCurrPlayer() && tk.getRound()==2 && tk.getTurn()==2 );
		assertEquals(t, true);
	}
}
