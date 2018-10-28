package test.model.token;

import static org.junit.Assert.*;
import model.tile.*;
import model.token.*;


import org.junit.Test;
public class TestBarricade 
{
	@Test
	public void testHasTile()
	{
		Exit e1 = new Exit(0,0);
		Barricade b1 = new Barricade(e1);
		String excpect = "true";
		assertEquals(excpect, "" + (e1==b1.currExit));
	}
	@Test
	public void testSetBarricade()
	{
		Exit e1 = new Exit(0,0);
		Exit e2 = new Exit(0,0);
		Barricade b1 = new Barricade(e1);
		b1.moveBarricade(e2);
		String excpect = "true";
		assertEquals(excpect, "" + (e2==b1.currExit));
	}
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
	
