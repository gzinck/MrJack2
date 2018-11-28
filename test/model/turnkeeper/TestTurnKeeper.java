package test.model.turnkeeper;
/**
 * test class for the turnkeeper class
 * @author Graeme Zinck and Charles Jobin
 * @version 1.0
 */
import static org.junit.Assert.*;

import model.TurnKeeper;
import model.ability.StealthyAbility;
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
		assertEquals(0, tk.getRound());
		assertEquals(0, tk.getTurn());
		assertEquals(TurnKeeper.STAGE_GAME_NOT_STARTED, tk.getStage());

	}	
	
	/**
	 * Tests the turnkeeper has start player before the game begins
	 */
	@Test
	public void testCurrPlayerAtStart()
	{
		TurnKeeper tk = new TurnKeeper(mrJack, dec, gs);
		boolean b = false;
		try{
			tk.getCurrPlayer();
		}catch(IllegalArgumentException e){b = true;}
		assertEquals(false, b);
	}
	
	/** 
	 * Test the curr player is correct for every round and turn in the game
	 */
	@Test 
	public void testCurrPlayerAllRounds()
	{
		// Check out if the initial stages work for setting the characters
		// on the table
		// NEW----------------------------------------------------
		boolean t = false;
		TurnKeeper tk = new TurnKeeper(mrJack, dec, gs);
		assertEquals(TurnKeeper.STAGE_GAME_NOT_STARTED, tk.getStage());
		tk.startGame();
		CharacterToken c = new CharacterToken(null, 0, null);
		c.setAbility(new StealthyAbility());
		tk.setCurrCharacter(c);
		assertEquals(TurnKeeper.STAGE_INIT_CHOOSE_CARD, tk.getStage());
		assertEquals(TurnKeeper.STAGE_INIT_CHOOSE_TILE, tk.nextStage());
		for(int i = 1; i < TurnKeeper.NUM_CHARS_TO_PLACE; i++) {
			assertEquals(TurnKeeper.STAGE_INIT_CHOOSE_CARD, tk.nextStage());
			assertEquals(TurnKeeper.STAGE_INIT_CHOOSE_TILE, tk.nextStage());
		}
		// NEW----------------------------------------------------
		
		// Now, do all the subsequent turns
		assertEquals(tk.roundOver(), true);
		tk.nextTurn();
		assertEquals(tk.getTurn(), 1);
		assertEquals(tk.getRound(), 1);
		for(int i =0; i<TurnKeeper.MAX_ROUNDS;i++) {
			for(int j = 0; j<TurnKeeper.MAX_TURNS;j++)	{
				if(i%2==0) {
					if(j==0) {
						t = (dec==tk.getCurrPlayer() && tk.getRound()==i+1 && tk.getTurn()==j+1);
					}
					else {
						t = (mrJack==tk.getCurrPlayer() && tk.getRound()==i+1 && tk.getTurn()==j+1);
					}
				}
				else {
					if(j==0) {
						 t = (mrJack==tk.getCurrPlayer() && tk.getRound()==i+1 && tk.getTurn()==j+1);
					}
					else {
						t = (dec==tk.getCurrPlayer() && tk.getRound()==i+1 && tk.getTurn()==j+1);
					}
				}
				assertEquals(true, t);
				
				tk.nextTurn();
			}
		}
	}

}
