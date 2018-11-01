package test.model.token;
/**
 * Test class for the barricade token
 * @author Graeme Zinck and Charles Jobin
 * @version 1.0
 */
import static org.junit.Assert.*;
import model.tile.*;
import model.token.*;


import org.junit.Test;
public class TestBarricade 
{
	/**
	 * Tests that the barricade is given an exit to be placed on when initialized
	 */
	@Test
	public void testHasTile()
	{
		Exit e1 = new Exit(0,0);
		Barricade b1 = new Barricade(e1);
		String excpect = "true";
		assertEquals(excpect, "" + (e1==b1.currExit));
	}
	/**
	 * Tests that a barricade can be moved from on exit to another
	 */
	@Test
	public void testMoveBarricade()
	{
		Exit e1 = new Exit(0,0);
		Exit e2 = new Exit(0,0);
		Barricade b1 = new Barricade(e1);
		b1.moveBarricade(e2);
		String excpect = "true";
		assertEquals(excpect, "" + (e2==b1.currExit));
	}
	/**
	 * Tests the case where an exit already has a barricade
	 */
	@Test
	public void testSetBarricadeThatAlreadyHasABarricade()
	{
		boolean didCatch = false;
		try{
		
		Exit e1 = new Exit(0,0);
		Exit e2 = new Exit(0,0);
		Barricade b1 = new Barricade(e1);
		Barricade b2 = new Barricade(e2);
		b1.moveBarricade(e2);
		}catch(IllegalArgumentException e){didCatch=true;}
		
		String excpect = "true";
		assertEquals(excpect, "" + didCatch);
	}
	
	
}
	
