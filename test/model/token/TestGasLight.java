package test.model.token;
/**
 * Test class for the gaslight
 * @author Graeme Zinck and Charles Jobin
 * @version 1.0
 */
import model.token.*;
import model.tile.*;

import static org.junit.Assert.*;

import org.junit.Test;
public class TestGasLight {

	/**
	 * Tests that a gaslight has a tile associated when it is initialized
	 */
	@Test
	public void testHasTile()
	{
		Lamppost l1 = new Lamppost(0,0);
		GasLight g1 = new GasLight(l1);
		String excpect = "true";
		assertEquals(excpect, "" + (l1==g1.currLamppost));
	}
	/**
	 * Tests the moving of a gaslight from one lamppost to another
	 */
	@Test
	public void testSetGasLight()
	{
		Lamppost l1 = new Lamppost(0,0);
		Lamppost l2 = new Lamppost(0,0);
		GasLight g1 = new GasLight(l1);
		g1.moveGasLight(l2);
		String excpect = "true";
		assertEquals(excpect, "" + (l2==g1.currLamppost));
	}
	/**
	 * Test for moving a gaslight to a lamppost that already has a gaslight
	 */
	@Test
	public void testSetGasLightThatAlreadyHasAGasLight()
	{
		boolean didCatch = false;
		try{
		
		Lamppost l1 = new Lamppost(0,0);
		Lamppost l2 = new Lamppost(0,0);
		GasLight g1 = new GasLight(l1);
		GasLight g2 = new GasLight(l2);
		g1.moveGasLight(l2);
		}catch(IllegalArgumentException e){didCatch=true;}
		
		String excpect = "true";
		assertEquals(excpect, "" + didCatch);
	}
	
	/**
	 * Test removing a gaslight from the board (for end of rounds)
	 */
	@Test
	public void testRemoveFromBoard()
	{
		Lamppost l1 = new Lamppost(0,0);
		GasLight g1 = new GasLight(l1);
		g1.extinguish();
		String excpect = "true";
		assertEquals(excpect, "" + (null==g1.currLamppost));
	}

}
