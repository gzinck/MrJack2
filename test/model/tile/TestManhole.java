package test.model.tile;
/**
 * Test class for the manhole tile
 * @author Graeme Zinck and Charles Jobin
 * @version 1.0
 */
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.player.MrJack;
import model.tile.*;
import model.token.CharacterToken;
import model.token.ManholeCover;

public class TestManhole {
	MrJack p;
	Manhole m1, m2;
	ManholeCover mc;
	RegularTile r;
	CharacterToken c;

	/**
	 * Sets up the test
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		p = new MrJack();
		m1 = new Manhole(0,0);
		m2 = new Manhole(0,0);
		Manhole.setManholes(m1, m2);
		r = new RegularTile(0,0);
		c = new CharacterToken("Something", 1, r);
	}
	/**
	 * Tests that certain amount of tiles are accessible based on character and max moves
	 */
	@Test
	public void test() {
		assertEquals(m1.getAccessibleTiles(1, 1, c, p).size(), 1); // Can access other manhole
		assertEquals(m1.getAccessibleTiles(0, 2, c, p).size(), 2); // Can access other manhole
		m1.setOccupiableNeighbour(r, 4);
		assertEquals(m1.getAccessibleTiles(0, 2, c, p).size(), 3); // Can access manhole or neighbour
		mc = new ManholeCover(m1);
		assertEquals(m1.getAccessibleTiles(0, 2, c, p).size(), 2); // Cannot get to any other manholes!
		assertEquals(m2.getAccessibleTiles(0, 2, c, p).size(), 1); // Cannot get to any other manholes!

	}

}
