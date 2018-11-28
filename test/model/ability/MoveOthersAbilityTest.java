package test.model.ability;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.ability.MoveOthersAbility;
import model.ability.MoveReducerAbility;
import model.gameboard.GameBoard;
import model.token.CharacterToken;

public class MoveOthersAbilityTest {
	
	MoveOthersAbility a;
	GameBoard gb;
	CharacterToken[] chars;

	@Before
	public void setUp() throws Exception {
		gb = new GameBoard();
		a = new MoveOthersAbility(gb);
		chars = gb.getCharacters();
		a.addCharacterToken(chars[0]);
		int[][] initialPositions = {{1,1},{2,1},{2,2},{2,3}};
		for(int i = 0; i < chars.length; i++) {
			chars[i].moveTo(gb.getTile(initialPositions[i]));
		}
	}

	@Test
	public void test() {
		assertEquals(a.startAction().length, 1);
		int[] loc = {2,1};
		int[][] options = a.continueAction(loc);
		
		// To know how many options we have, we have to see if
		// Aberline is on the board and if the person being moved
		// will be affected.
		boolean isNextToMoveReducer = false;
		for(CharacterToken c : gb.getCharacters()) {
			if(c.hasAbility(MoveReducerAbility.ABILITY)) {
				int[] detective = c.getTokenLocation();
				for(int i = 0; i < 6; i++) {
					int[] possible = gb.getLocation(loc[0], loc[1], i);
					if(possible != null && detective[0] == possible[0] && detective[1] == possible[1])
						isNextToMoveReducer = true;
				}
			}
		}
		if(isNextToMoveReducer)
			assertEquals(options.length, 1); // only one option!
		else
			assertEquals(options.length, 3); // there are three options
		
	}

}
