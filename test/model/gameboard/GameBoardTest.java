package test.model.gameboard;

import static org.junit.Assert.*;

import model.gameboard.GameBoard;
import org.junit.Before;
import org.junit.Test;


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

	@Test
	public void testAddNeightbours()
	{


	}
	
}
