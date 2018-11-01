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
import model.gameboard.*;
public class TestBuilding {
	/** building to be tested */ 
	Building b;
	/** Regular tile for the building neighbour */
	RegularTile r;
	/** Character token to be placed on a tile */
	CharacterToken c;
	StealthyAbility sa;
	MoveBarricadeAbility mba;
	TokenFinder tk;

	@Before
	public void setUp() throws Exception {
		b = new Building(0,1);
		// Make sure that the building has a neighbour so we can test if it can access them (only when char is Stealthy)
		r = new RegularTile(0,0);
		b.setNeighbour(r, 0);
		c = new CharacterToken("Billy Bob Joe", 1, null);
		//c.setAbility(a);
	}

	/**
	 * Test for building, only stealthy should be able to go through a building
	 */
	@Test
	public void test() {
		// Assert that no tiles are accessible unless the ability is StealthyAbility.
		sa = new StealthyAbility(tk);
		c.setAbility(sa);
		assertEquals(1, b.getAccessibleTiles(2, c, null).size());
		c.setAbility(mba);
		assertEquals(0, b.getAccessibleTiles(2, c, null).size());
		
		// Assert that no tiles accessible when the distance to go is only 1
		assertEquals(b.getAccessibleTiles(1, c, null).size(), 0);
	}

}
