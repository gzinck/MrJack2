package controller;

import model.tile.*;

public class TokenController {
	private static final char[][] TILE_FRAMEWORK = new char[][] {
		{'E', 'X', 'X', 'X', ' ', 'M', ' '},
		{'X', ' ', 'L', 'B', ' ', 'L', ' '},
		{' ', 'M', ' ', ' ', ' ', 'B', ' '},
		{' ', 'B', 'B', 'B', ' ', ' ', ' '}
	};
	private static final char EXIT_C 	= 'E';
	private static final char BUILD_C 	= 'B';
	private static final char NONE_C 	= 'X';
	private static final char REG_C 		= ' ';
	private static final char LAMP_C 	= 'L';
	
	private Tile[][] tiles;
	private int numRows, numCols;
	
	public void initializeModelTiles() {
		numRows = TILE_FRAMEWORK.length;
		numCols = TILE_FRAMEWORK[0].length;
		
		tiles = new Tile[numRows][numCols];
		
		for(int row = 0; row < numRows; row++) {
			for(int col = 0; col < numCols; col++) {
				switch(TILE_FRAMEWORK[row][col]) {
				case REG_C:
					tiles[row][col] = new RegularTile();
				case EXIT_C:
					tiles[row][col] = new Exit();
					break;
				case BUILD_C:
					tiles[row][col] = new Building();
					break;
				case LAMP_C:
					tiles[row][col] = new Lamppost();
					break;
				default:
					break;
				}
			} // for col
		} // for row
		addNeighbours();
	}
	
	public void addNeighbours() {
		// Go through every place and get the neighbours
		for(int row = 0; row < numRows; row++) {
			for(int col = 0; col < numCols; col++) {
				if(tiles[row][col] == null) continue;
				
				Tile curr = null;
			}
		}
	}
}
