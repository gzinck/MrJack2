package test.model.tile;

import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

import model.ability.Ability;
import model.player.*;
import model.tile.Exit;
import model.tile.Tile;
import model.token.CharacterToken;

public class TestExit {
	MrJack jack;
	Detective detective;
	Exit exit;
	Ability a;
	CharacterToken c1, c2;

	@Before
	public void before() throws Exception {
		jack = new MrJack();
		detective = new Detective();
		exit = new Exit();
		c1 = new CharacterToken("Billy Bob Joe", 1, 4);
		c2 = new CharacterToken("Billy Bob Jock", 1, 3);
	}

	@Test
	public void exitAllowsOnlyJack() {
		// Allows Jack
		jack.setCharacter(c1);
		HashSet<Tile> tiles = exit.getAccessibleTiles(1, c1, jack);
		assertEquals(tiles.size(), 1);
		
		// Should not allow Jack when wrong character used
		tiles = exit.getAccessibleTiles(1, c2, jack);
		assertEquals(tiles.size(), 0);
		
		// Does not allow Detective
		tiles = exit.getAccessibleTiles(1, null, detective);
		assertEquals(tiles.size(), 0);
	}

}
