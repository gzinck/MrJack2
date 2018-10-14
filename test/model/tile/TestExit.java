package test.model.tile;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.player.*;
import model.tile.Exit;
import model.tile.Tile;
import model.token.CharacterToken;

public class TestExit {
	MrJack jack;
	Detective detective;
	Exit exit;

	@Before
	public void before() throws Exception {
		jack = new MrJack();
		detective = new Detective();
		exit = new Exit();
	}

	@Test
	public void exitAllowsOnlyJack() {
		// Allows Jack
		Tile[] tiles = exit.getAccessibleTiles(1, null, jack);
		assertEquals(tiles.length, 1);
		
		// Does not allow Detective
		tiles = exit.getAccessibleTiles(1, null, detective);
		assertEquals(tiles.length, 0);
	}

}
