package test.model.token;
/**
 * Test class for the manhole cover
 * @author Graeme Zinck and Charles Jobin
 * @version 1.0
 */
import model.token.*;
import model.tile.*;

import static org.junit.Assert.*;

import org.junit.Test;
public class TestManholeCover {

	/** Tests that a manhole cover has a manhole when it is initialized */
	@Test
	public void testHasTile()
	{
		Manhole m1 = new Manhole(0,0);
		ManholeCover mc1 = new ManholeCover(m1);
		String excpect = "true";
		assertEquals(excpect, "" + (m1==mc1.currManhole));
	}
	
	/** Tests moving a manhole cover from one manhole to another*/
	@Test
	public void testSetManholeCover()
	{
		Manhole m1 = new Manhole(0,0);
		Manhole m2 = new Manhole(0,0);
		ManholeCover mc1 = new ManholeCover(m1);
		mc1.moveManholeCover(m2);
		String excpect = "true";
		assertEquals(excpect, "" + (m2==mc1.currManhole));
	}
	
	/** Tests moving a cover to a manhole that is already covered */
	@Test
	public void testSetManholeCoverThatAlreadyHasAManholeCover()
	{
		boolean didCatch = false;
		try{
		Manhole m1 = new Manhole(0,0);
		Manhole m2 = new Manhole(0,0);
		ManholeCover mc1 = new ManholeCover(m1);
		ManholeCover mc2 = new ManholeCover(m2);
		mc1.moveManholeCover(m2);
		}catch(IllegalArgumentException e){didCatch=true;}
		
		
		assertEquals(true, didCatch);
	}
}
