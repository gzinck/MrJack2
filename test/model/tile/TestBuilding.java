package test.model.tile;
/**
 * Test class for the Building classe
 * @author Graeme Zinck and Charles Jobin
 * @version 1.0
 */
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.tile.*;
import model.ability.*;
import model.token.CharacterToken;

public class TestBuilding {
	/** building to be tested */ 
	Building b;
	/** Regular tile for the building neighbour */
	RegularTile r;
	/** Character token to be placed */
	CharacterToken c;
	Ability a;

	@Before
	public void setUp() throws Exception {
		b = new Building(0,0);
		// Make sure that the building has a neighbour so we can test if it can access them (only when char is Stealthy)
		r = new RegularTile(0,0);
		b.setNeighbour(r, 0);
		c = new CharacterToken("Billy Bob Joe", 1, null);
		
	}

	@Test
	public void test() {
		// Assert that no tiles are accessible unless the ability is StealthyAbility.
		assertEquals(b.getAccessibleTiles(2, c, null).size(), 0);
		c.setAbility(a);
		assertNotEquals(b.getAccessibleTiles(2, c, null).size(), 0);
		
		// Assert that no tiles accessible when the distance to go is only 1
		assertEquals(b.getAccessibleTiles(1, c, null).size(), 0);
	}

}
