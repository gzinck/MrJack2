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
	private static final char MANH_C 	= 'M';
	
	private static final int NUM_HOLES	= 2;
	
	private Tile[][] tiles;
	private Lamppost[][] lamps;
	private Passable[][] passableTiles;
	private Lightable[][] lightableTiles;
	private Manhole[] manholes;
	private int numRows, numCols;
	
	public void initializeModelTiles() {
		numRows = TILE_FRAMEWORK.length;
		numCols = TILE_FRAMEWORK[0].length;
		
		tiles = new Tile[numRows][numCols];
		lamps = new Lamppost[numRows][numCols];
		passableTiles = new Passable[numRows][numCols];
		lightableTiles = new Lightable[numRows][numCols];
		manholes = new Manhole[NUM_HOLES];
		int manholesSoFar = 0;
		
		for(int row = 0; row < numRows; row++) {
			for(int col = 0; col < numCols; col++) {
				switch(TILE_FRAMEWORK[row][col]) {
				case REG_C:
					RegularTile rt = new RegularTile();
					tiles[row][col] = rt;
					passableTiles[row][col] = rt;
					lightableTiles[row][col] = rt;
					break;
				case EXIT_C:
					Exit e = new Exit();
					tiles[row][col] = e;
					passableTiles[row][col] = e;
					break;
				case BUILD_C:
					Building b = new Building();
					tiles[row][col] = b;
					passableTiles[row][col] = b;
					break;
				case MANH_C:
					Manhole m = new Manhole();
					tiles[row][col] = m;
					passableTiles[row][col] = m;
					lightableTiles[row][col] = m;
					manholes[manholesSoFar++] = m;
					break;
				case LAMP_C:
					Lamppost l = new Lamppost();
					tiles[row][col] = l;
					lamps[row][col] = l;
					break;
				default:
					break;
				}
				if(tiles[row][col] != null) tiles[row][col].setTileLocation(row, col);
			} // for col
		} // for row
		addNeighbours();
	}
	
	public void addNeighbours() {
		// Go through every place and get the neighbours
		for(int row = 0; row < numRows; row++) {
			for(int col = 0; col < numCols; col++) {
				if(tiles[row][col] == null) continue;
				addLamppostNeighbour(lightableTiles[row][col], row, col);
			}
		}
	}
	
	public void addLamppostNeighbour(Lightable tile, int row, int col) {
		if(tile == null) return;
		// Assumes can only have one lamppost as a neighbour
		Lamppost l = null;
		int[] loc = null;
		int dir = 0;
		while(l == null && dir < Tile.NUM_NEIGHBOURS) {
			loc = getLocation(row, col, dir);
			if(loc != null)
				l = lamps[loc[0]][loc[1]];
			dir++;
		}
		tile.setLamppost(l);
	}
	
	public int[] getLocation(int row, int col, int direction) {
		// This is some disgusting code, but it only ever has to be done ONCE.
		// That is, this is the only time we have to do disgusting code to get neighbours.
		// Justification: having 6 neighbours has no convenient representation with
		// array data types.
		if(direction >= Tile.NUM_NEIGHBOURS)
			throw new IllegalArgumentException("Cannot have a direction greater than or equal to " + Tile.NUM_NEIGHBOURS);
		switch(direction) {
		case 0:
			if(row - 1 >= 0)
				return new int[] {row - 1, col};
			return null;
		case 1:
			if(col % 2 == 0) {
				if(col + 1 < numCols)
					return new int[] {row, col + 1};
			} else {
				if(col + 1 < numCols && row - 1 >= 0)
					return new int[] {row - 1, col + 1};
			}
			return null;
		case 2:
			if(col % 2 == 0) {
				if(col + 1 < numCols && row + 1 < numRows)
					return new int[] {row + 1, col + 1};
			} else {
				if(col + 1 < numCols)
					return new int[] {row, col + 1};
			}
			return null;
		case 3:
			if(row + 1 < numRows)
				return new int[] {row + 1, col};
			return null;
		case 4:
			if(col % 2 == 0) {
				if(col - 1 >= 0 && row + 1 < numRows)
					return new int[] {row + 1, col - 1};
			} else {
				if(col - 1 >= 0)
					return new int[] {row, col - 1};
			}
			return null;
		case 5:
			if(col % 2 == 0) {
				if(col - 1 < numCols)
					return new int[] {row, col - 1};
			} else {
				if(col - 1 < numCols && row - 1 >= 0)
					return new int[] {row - 1, col - 1};
			}
			return null;
		}
		return null;
	}
}
