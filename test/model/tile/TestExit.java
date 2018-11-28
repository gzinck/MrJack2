package test.model.tile;
/**
 * Test class for the exit tile
 * @author Graeme Zinck and Charles Jobin
 * @version 1.0
 */
import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

import model.ability.Ability;
import model.player.*;
import model.tile.*;
import model.tile.Passable;
import model.token.CharacterToken;
import model.WitnessCard;

public class TestExit {
	MrJack jack;
	Detective detective;
	Exit exit;
	RegularTile reg;
	Ability a;
	CharacterToken c1, c2;
	WitnessCard wc;

	@Before
	public void before() throws Exception {
		jack = new MrJack();
		detective = new Detective();
		exit = new Exit(0,0);
		
		reg = new RegularTile(0,1);
		exit.setNeighbour(reg, 0);
		c1 = new CharacterToken("Billy Bob Joe", 1, reg);
		c2 = new CharacterToken("Billy Bob Jock", 1, reg);
		wc = new WitnessCard(c1);
		Exit.setWitnessCard(wc);
		jack.setCharacter(c1);
		wc.updateWitnessed();
	}

	/** Makes sure only jack can exit, and only when jack is not lit */
	@Test
	public void exitAllowsOnlyJack() {
		// Allows Jack
		HashSet<Passable> tiles = exit.getAccessibleTiles(0, 1, c1, jack);
		assertEquals(tiles.size(), 1);
		
		// Should not allow Jack when wrong character used
		HashSet<Passable> tiles2 = exit.getAccessibleTiles(0, 1, c2, jack);
		assertEquals(0, tiles2.size());
		
		// Does not allow Detective
		tiles = exit.getAccessibleTiles(0, 1, null, detective);
		assertEquals(0, tiles.size());
		
		// Does not allow when min moves is ABOVE 0
		tiles = exit.getAccessibleTiles(1, 1, c1, jack);
		assertEquals(0, tiles.size());
		
		// But ok if negative
		tiles = exit.getAccessibleTiles(-1, 1, c1, jack);
		assertEquals(1, tiles.size());
	}

}
