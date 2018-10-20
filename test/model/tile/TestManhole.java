package test.model.tile;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.tile.*;
import model.token.CharacterToken;
import model.token.ManholeCover;

public class TestManhole {
	Manhole m1, m2;
	ManholeCover mc;
	RegularTile r;
	CharacterToken c;

	@Before
	public void setUp() throws Exception {
		m1 = new Manhole();
		m2 = new Manhole();
		Manhole.setManholes(m1, m2);
		r = new RegularTile();
		c = new CharacterToken("Something", 1, null);
	}

	@Test
	public void test() {
		assertEquals(m1.getAccessibleTiles(2, c, null).size(), 2); // Can access other manhole
		assertEquals(m1.getAccessibleTiles(1, c, null).size(), 1); // Can only access self with 1 move
		m1.setNeighbour(r, 4);
		assertEquals(m1.getAccessibleTiles(2, c, null).size(), 3); // Can access manhole or neighbour
		mc = new ManholeCover(m1);
		assertEquals(m1.getAccessibleTiles(2, c, null).size(), 2); // Cannot get to any other manholes!
		assertEquals(m2.getAccessibleTiles(2, c, null).size(), 1); // Cannot get to any other manholes!
//		fail("Not yet implemented");
	}

}
