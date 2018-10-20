package test.model.token;
import model.token.*;
import model.tile.*;

import static org.junit.Assert.*;

import org.junit.Test;
public class TestGasLight {

	@Test
	public void testHasTile()
	{
		Lamppost l1 = new Lamppost();
		GasLight g1 = new GasLight(l1);
		String excpect = "true";
		assertEquals(excpect, "" + (l1==g1.currLamppost));
	}
	
	@Test
	public void testSetGasLight()
	{
		Lamppost l1 = new Lamppost();
		Lamppost l2 = new Lamppost();
		GasLight g1 = new GasLight(l1);
		g1.setGasLight(l2);
		String excpect = "true";
		assertEquals(excpect, "" + (l2==g1.currLamppost));
	}
	@Test
	public void testSetGasLightThatAlreadyHasAGasLight()
	{
		boolean didCatch = false;
		try{
		
		Lamppost l1 = new Lamppost();
		Lamppost l2 = new Lamppost();
		GasLight g1 = new GasLight(l1);
		GasLight g2 = new GasLight(l2);
		g1.setGasLight(l2);
		}catch(IllegalArgumentException e){didCatch=true;}
		
		String excpect = "true";
		assertEquals(excpect, "" + didCatch);
	}
	
	@Test
	public void testRemoveFromBoard()
	{
		Lamppost l1 = new Lamppost();
		GasLight g1 = new GasLight(l1);
		g1.removeFromBoard();
		String excpect = "true";
		assertEquals(excpect, "" + (null==g1.currLamppost));
	}

}
