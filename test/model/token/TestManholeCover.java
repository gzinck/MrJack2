package test.model.token;

import model.token.*;
import model.tile.*;

import static org.junit.Assert.*;

import org.junit.Test;
public class TestManholeCover {

	@Test
	public void testHasTile()
	{
		Manhole m1 = new Manhole();
		ManholeCover mc1 = new ManholeCover(m1);
		String excpect = "true";
		assertEquals(excpect, "" + (m1==mc1.currManhole));
	}
	
	@Test
	public void testSetManholeCover()
	{
		Manhole m1 = new Manhole();
		Manhole m2 = new Manhole();
		ManholeCover mc1 = new ManholeCover(m1);
		mc1.moveManholeCover(m2);
		String excpect = "true";
		assertEquals(excpect, "" + (m2==mc1.currManhole));
	}
	@Test
	public void testSetManholeCoverThatAlreadyHasAManholeCover()
	{
		boolean didCatch = false;
		try{
		Manhole m1 = new Manhole();
		Manhole m2 = new Manhole();
		ManholeCover mc1 = new ManholeCover(m1);
		ManholeCover mc2 = new ManholeCover(m2);
		mc1.moveManholeCover(m2);
		}catch(IllegalArgumentException e){didCatch=true;}
		
		String excpect = "true";
		assertEquals(excpect, "" + didCatch);
	}
}
