package test.model.gameboard;

import static org.junit.Assert.*;
import model.gameboard.GameBoard;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the game board. Because many of the gameboard tests required user interaction, it was much harder to
 * test their functionality with junit, opposed to testing via trial and error. So while this may not look to do much
 * for testing, the game board was tested in several capacities via inspection.
 * @author Graeme Zinck and Josh Cookson
 * @version 1.1 ish
 */
public class GameBoardTest {
	GameBoard gb;

	@Before
	public void before(){
		gb = new GameBoard();
	}

	@Test
	public void initializationTest(){
		assertNotNull(gb.characters);
		assertNotNull(gb.getGasLights());
		assertNotNull(gb.getBarricades());
		assertNotNull(gb.getCharacters());
		assertNotNull(gb.getExits());
		assertNotNull(gb.getManholes());
	}
	
}
