package model.gameboard;
import model.ability.*;
import model.tile.*;
import model.token.*;

public class GameBoard implements TokenFinder
{
	public static final char[][] TILE_FRAMEWORK = new char[][] {
		{'E', 'X', 'X', 'X', ' ', 'M', ' '},
		{'X', ' ', 'L', 'B', ' ', 'L', ' '},
		{' ', 'M', ' ', ' ', ' ', 'B', 'M'},
		{' ', 'B', 'B', 'B', ' ', ' ', 'E'}
	};
	private static final char EXIT_C 	= 'E';
	private static final char BUILD_C 	= 'B';
	private static final char NONE_C 	= 'X';
	private static final char REG_C 		= ' ';
	private static final char LAMP_C 	= 'L';
	private static final char MANH_C 	= 'M';
	
	private static final int NUM_MANHOLES	= 3;
	private static final int NUM_EXITS	= 2;
	private static final int NUM_LAMPS	= 2;
	
	private static final int NUM_CHARACTERS	= 4;
	private static final int[][] CHAR_LOCATIONS = {
			{1,1},
			{1,4},
			{4,1},
			{4,6}
	};
	public static final String[] CHAR_NAMES = {
			"Stealthy",
			"LeStrade",
			"Bert",
			"Smith"
	};
	private final Ability[] CHAR_ABILITIES = {
			new StealthyAbility(), new MoveBarricadeAbility(this), new MoveCoverAbility(new Manhole(), new ManholeCover(new Manhole())), new MoveLightAbility(new Lamppost(), new GasLight(new Lamppost()))
	};
	private static final int[] CHAR_NUM_MOVES = {4, 3, 3, 3};
	
	public static final int NUM_BARRICADES = 1;
	private static final int NUM_GASLIGHTS = 1;
	private static final int NUM_REMOVABLE_GASLIGHTS = 1;
	private static final int NUM_MANCOVERS = 1;
	
	private Tile[][] tiles;
	private Lamppost[][] lamps;
	private Lightable[][] lightableTiles;
	private Manhole[] manholes;
	private Exit[] exits;
	private Lamppost[] lampList;
	private int numRows, numCols;
	
	public CharacterToken[] characters;
	private Barricade[] barricades;
	private GasLight[] gaslights;
	private ManholeCover[] mancovers; 
	
	public GameBoard()
	{
		initializeTiles();
		initializeTokens();
		initializeCharacters();
	}
	
	private void initializeTiles() {
		numRows = TILE_FRAMEWORK.length;
		numCols = TILE_FRAMEWORK[0].length;
		
		tiles = new Tile[numRows][numCols];
		lamps = new Lamppost[numRows][numCols];
		lightableTiles = new Lightable[numRows][numCols];
		manholes = new Manhole[NUM_MANHOLES];
		exits = new Exit[NUM_EXITS];
		lampList = new Lamppost[NUM_LAMPS];
		int manholesSoFar = 0;
		int lampsSoFar = 0;
		int exitsSoFar = 0;
		
		for(int row = 0; row < numRows; row++) {
			for(int col = 0; col < numCols; col++) {
				switch(TILE_FRAMEWORK[row][col]) {
				case REG_C:
					RegularTile rt = new RegularTile();
					tiles[row][col] = rt;
					lightableTiles[row][col] = rt;
					break;
				case EXIT_C:
					Exit e = new Exit();
					tiles[row][col] = e;
					exits[exitsSoFar++] = e;
					break;
				case BUILD_C:
					Building b = new Building();
					tiles[row][col] = b;
					break;
				case MANH_C:
					Manhole m = new Manhole();
					tiles[row][col] = m;
					lightableTiles[row][col] = m;
					manholes[manholesSoFar++] = m;
					break;
				case LAMP_C:
					Lamppost l = new Lamppost();
					tiles[row][col] = l;
					lamps[row][col] = l;
					lampList[lampsSoFar++] = l;
					break;
				default:
					break;
				}
				if(tiles[row][col] != null) tiles[row][col].setTileLocation(row, col);
			} // for col
		} // for row
		addNeighbours();
		Manhole.setManholes(manholes);
	}
	
	private void addNeighbours() {
		// Go through every place and get the neighbours
		for(int row = 0; row < numRows; row++) {
			for(int col = 0; col < numCols; col++) {
				addLamppostNeighbour(lightableTiles[row][col], row, col);
				addPassableNeighbours(tiles[row][col], row, col);
			}
		}
	}
	
	private void addLamppostNeighbour(Lightable tile, int row, int col) {
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
	
	private void addPassableNeighbours(Passable tile, int row, int col) {
		if(tile == null) return;
		int[] loc = null;
		for(int dir = 0; dir < Tile.NUM_NEIGHBOURS; dir++) {
			loc = getLocation(row, col, dir);
			if(loc != null) {
				Passable next = tiles[loc[0]][loc[1]];
				if(next != null) tile.setNeighbour(next, dir);
			} // if location is not null
		} // for every direction
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
	
	private void initializeTokens()
	{
		barricades = new Barricade[NUM_BARRICADES];
		for(int i = 0; i < NUM_BARRICADES; i++) barricades[i] = new Barricade(exits[i]);
		gaslights = new GasLight[NUM_GASLIGHTS];
		for(int i = 0; i < NUM_GASLIGHTS; i++) gaslights[i] = new GasLight(lampList[i]);
		mancovers = new ManholeCover[NUM_MANCOVERS];
		for(int i = 0; i < NUM_GASLIGHTS; i++) mancovers[i] = new ManholeCover(manholes[i]);
	}
	
	private void initializeCharacters()
	{
		characters = new CharacterToken[NUM_CHARACTERS];
		for(int i = 0; i < NUM_CHARACTERS; i++) {
			int[] loc = CHAR_LOCATIONS[i];
			characters[i] = new CharacterToken(CHAR_NAMES[i], CHAR_NUM_MOVES[i], lightableTiles[loc[0]][loc[1]]);
			characters[i].setAbility(CHAR_ABILITIES[i]);
		}
	}
	
	public void evaluateInnocence(Boolean jackWasSeen)
	{
		for(int i = 0; i < NUM_CHARACTERS; i++) {
			characters[i].evaluateInnocence(jackWasSeen);
		}
	}
	
	public GasLight[] getRemovableGaslights() {
		GasLight[] removable = new GasLight[NUM_REMOVABLE_GASLIGHTS];
		for(int i = 0; i < NUM_REMOVABLE_GASLIGHTS; i++) {
			removable[i] = gaslights[i];
		}
		return removable;
	}
	
	public CharacterToken getCharacter(String characterName) {
		for(int i = 0 ; i < NUM_CHARACTERS; i++)
			if(CHAR_NAMES[i].equals(characterName)) return characters[i];
		return null;
	}

	public <T extends Token> T getToken(T[] tokenArr, int[] location) {
		for(int i = 0; i < tokenArr.length; i++) {
			int[] thisLoc = tokenArr[i].getTokenLocation();
			if(thisLoc[0] == location[0] && thisLoc[1] == location[1]) return tokenArr[i];
		}
		return null;
	}
	
	@Override
	public ManholeCover getManholeCover(int[] location) {
		return getToken(mancovers, location);
	}

	@Override
	public Barricade getBarricade(int[] location) {
		return getToken(barricades, location);
	}

	@Override
	public GasLight getGasLight(int[] location) {
		return getToken(gaslights, location);
	}

	@Override
	public CharacterToken getCharacter(int[] location) {
		return getToken(characters, location);
	}

	@Override
	public ManholeCover[] getManholeCovers() {
		return mancovers;
	}

	@Override
	public Barricade[] getBarricades() {
		return barricades;
	}

	@Override
	public GasLight[] getGasLights() {
		return gaslights;
	}

	@Override
	public CharacterToken[] getCharacters() {
		return characters;
	}

	public <T extends VisibleTile> T getTile(T[] tileArr, int[] location) {
		for(int i = 0; i < tileArr.length; i++) {
			int[] thisLoc = tileArr[i].getTileLocation();
			if(thisLoc[0] == location[0] && thisLoc[1] == location[1]) return tileArr[i];
		}
		return null;
	}
	
	@Override
	public Manhole getManhole(int[] location) {
		return getTile(manholes, location);
	}

	@Override
	public Exit getExit(int[] location) {
		return getTile(exits, location);
	}

	@Override
	public Lamppost getLamp(int[] location) {
		return getTile(lampList, location);
	}

	@Override
	public Lightable getTile(int[] location) {
		return lightableTiles[location[0]][location[1]];
	}

	@Override
	public Manhole[] getManholes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exit[] getExits() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Lamppost[] getLamps() {
		// TODO Auto-generated method stub
		return null;
	}
}
