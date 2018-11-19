package test.model.player;
import model.player.*;
import static org.junit.Assert.*;

import model.token.*;
import org.junit.Before;
import org.junit.Test;
/**
 * Test class for the player classes
 * @author Graeme Zinck and Charles Jobin
 * @version 1.0
 */
public class PlayerTest 
{
	/**
	 * Test for the players, that they have the correct name
	 */
	@Test
	public void testGetPlayerName()
	{
		Detective dt = new Detective();
		MrJack mj = new MrJack();

		assertEquals("MrJack", mj.getPlayerName());
		assertEquals("Detective", dt.getPlayerName());
	}
	
}
