package test.model.player;
import model.player.*;
import static org.junit.Assert.*;

import model.token.*;
import org.junit.Before;
import org.junit.Test;
public class PlayerTest 
{
	@Test
	public void testGetPlayerName()
	{
		Detective dt = new Detective();
		MrJack mj = new MrJack();
		assertEquals("MrJack", mj.getPlayerName());
		assertEquals("Detective", dt.getPlayerName());
	}
	
}
