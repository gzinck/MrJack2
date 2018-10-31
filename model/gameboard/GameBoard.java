package model.gameboard;
import java.util.Observer;

import model.ability.*;
import model.tile.*;
import model.token.*;
/**
 * Class for the gameboard, this class essentially instantiates
 * the board of tiles, tokens and characters.
 * 
 * @author Graeme Zinck and Charles Jobin
 * @version 1.0
 */
public class GameBoard implements TokenFinder, CharacterFinder, CharTokenFinder
{
	/** Array of abilites to be given to characters in the game.
	 * The indexes correspond to the character indices in
	 * <code>TokenConstants.java</code>. */
	private Ability[] CHAR_ABILITIES = {
			new StealthyAbility(this), new MoveBarricadeAbility(this), new MoveCoverAbility(this), new MoveLightAbility(this)
	};
	
	/** 2D Array of tiles that are placed on the gameboard */
	private Tile[][] tiles;
	/**  2D array of lampposts on the gameboard */
	private Lamppost[][] lamps;
	/** 2D array of lightable tiles on the gameboard */
	private Lightable[][] lightableTiles;
	/** Array of manholes on the gameboard */
	private Manhole[] manholes;
	/** Array of exits on the gameboard */
	private Exit[] exits;
	/** Arrau of lampposts on the gamboard */
	private Lamppost[] lampList;
	/** Number of rows and columns on the gameboard */
	private int numRows, numCols;
	
	/** Array of the characters on the gameboard */
	public CharacterToken[] characters;
	/** array of barricades on the gameboard */
	private Barricade[] barricades;
	/** Array of gaslights on the gameboard */
	private GasLight[] gaslights;
	/** Array of manhole covers on the gameboard */
	private ManholeCover[] mancovers; 
	
	/**
	 * Constructs a gameboard, intializes the tiles tokens and characters
	 */
	public GameBoard()
	{
		initializeTiles();
		initializeTokens();
		initializeCharacters();
	}
	
	/**
	 * Assigns the locations of all tiles on the gameboard
	 */
	private void initializeTiles() {
		numRows = TokenConstants.TILE_FRAMEWORK.length;
		numCols = TokenConstants.TILE_FRAMEWORK[0].length;
		
		tiles = new Tile[numRows][numCols];
		lamps = new Lamppost[numRows][numCols];
		lightableTiles = new Lightable[numRows][numCols];
		manholes = new Manhole[TokenConstants.NUM_MANHOLES];
		exits = new Exit[TokenConstants.NUM_EXITS];
		lampList = new Lamppost[TokenConstants.NUM_LAMPS];
		int manholesSoFar = 0;
		int lampsSoFar = 0;
		int exitsSoFar = 0;
		
		// We have to go through every row and column in the template
		// and place the tiles in the appropriate places.
		// Many different storage mechanisms are used in order to avoid
		// the hideous world of the instanceof
		for(int row = 0; row < numRows; row++) {
			for(int col = 0; col < numCols; col++) {
				switch(TokenConstants.TILE_FRAMEWORK[row][col]) {
				case TokenConstants.REG_C:
					RegularTile rt = new RegularTile(row, col);
					tiles[row][col] = rt;
					lightableTiles[row][col] = rt;
					break;
				case TokenConstants.EXIT_C:
					Exit e = new Exit(row, col);
					tiles[row][col] = e;
					exits[exitsSoFar++] = e;
					break;
				case TokenConstants.BUILD_C:
					Building b = new Building(row, col);
					tiles[row][col] = b;
					break;
				case TokenConstants.MANH_C:
					Manhole m = new Manhole(row, col);
					tiles[row][col] = m;
					lightableTiles[row][col] = m;
					manholes[manholesSoFar++] = m;
					break;
				case TokenConstants.LAMP_C:
					Lamppost l = new Lamppost(row, col);
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
	
	/**
	 * Adds the neighbours for all of the tokens.
	 */
	private void addNeighbours() {
		// Go through every place and get the neighbours
		for(int row = 0; row < numRows; row++) {
			for(int col = 0; col < numCols; col++) {
				addLamppostNeighbour(lightableTiles[row][col], row, col);
				addPassableNeighbours(tiles[row][col], row, col);
			}
		}
	}
	
	/**
	 * Add the lamppost neighbour to a tile (design decision: each tile
	 * can only have on lamppost as a neighbour). It cycles through all
	 * the neighbouring tiles, looking for a lamppost. If it finds one,
	 * then it assigns the lamppost to the lightable tile. 
	 * 
	 * @param tile a lightable tile which can have a neighbouring lamppost
	 * @param row the row of the tile
	 * @param col the column of the tile
	 */
	private void addLamppostNeighbour(Lightable tile, int row, int col) {
		if(tile == null) return;
		// Assumes can only have one lamppost as a neighbour
		Lamppost l = null;
		int[] loc = null;
		int dir = 0;
		// Check every direction for a lamppost
		while(l == null && dir < Tile.NUM_NEIGHBOURS) {
			loc = getLocation(row, col, dir);
			if(loc != null)
				l = lamps[loc[0]][loc[1]];
			dir++;
		}
		tile.setLamppost(l);
	}
	
	/**
	 * Adds all poassable neighbours for a tile based on its row and
	 * column.
	 * 
	 * @param tile a passable tile (i.e. anything that could be walked
	 * through by a character, even Stealthy).
	 * @param row the row of the tile
	 * @param col the column of the tile
	 */
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
	
	/**
	 * Gets the location of the neighbour of a tile in a given direction
	 * from a specified row and column. This is nontrivial because there
	 * are six neighbours to a hexagonal tile, and the locations of those
	 * neighbours depends on whether the column is even or odd.
	 *  
	 * @param row the row of the original tile
	 * @param col the column of the original tile
	 * @param direction the direction to find the neighbouring tile
	 * @return the {row, col} of the neighbour, if it exists, or null
	 * if it does not exist.
	 */
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
				if(col - 1 >= 0 && row + 1 < numRows) {
					return new int[] {row + 1, col - 1};
				}
			} else {
				if(col - 1 >= 0) {
					return new int[] {row, col - 1};
				}
			}
			return null;
		case 5:
			if(col % 2 == 0) {
				if(col - 1 >= 0)
					return new int[] {row, col - 1};
			} else {
				if(col - 1 >= 0 && row - 1 >= 0)
					return new int[] {row - 1, col - 1};
			}
			return null;
		}
		return null;
	}
	
	/**
	 * Initializes all the tokens in the appropriate locations.
	 */
	private void initializeTokens()
	{
		barricades = new Barricade[TokenConstants.NUM_BARRICADES];
		for(int i = 0; i < TokenConstants.NUM_BARRICADES; i++) barricades[i] = new Barricade(exits[i]);
		gaslights = new GasLight[TokenConstants.NUM_GASLIGHTS];
		for(int i = 0; i < TokenConstants.NUM_GASLIGHTS; i++) gaslights[i] = new GasLight(lampList[i]);
		mancovers = new ManholeCover[TokenConstants.NUM_MANCOVERS];
		for(int i = 0; i < TokenConstants.NUM_MANCOVERS; i++) mancovers[i] = new ManholeCover(manholes[i]);
	}
	
	/**
	 * Initializes all the character tokens in their appriate locations,
	 * as defined by <code>TokenConstants.java</code>.
	 */
	private void initializeCharacters()
	{
		characters = new CharacterToken[TokenConstants.NUM_CHARACTERS];
		for(int i = 0; i < TokenConstants.NUM_CHARACTERS; i++) {
			int[] loc = TokenConstants.CHAR_LOCATIONS[i];
			characters[i] = new CharacterToken(TokenConstants.CHAR_NAMES[i], TokenConstants.CHAR_NUM_MOVES[i], lightableTiles[loc[0]][loc[1]]);
			characters[i].setAbility(CHAR_ABILITIES[i]);
		}
	}
	
	/**
	 * Adds the observer (controller) to the character tokens.
	 * 
	 * @param obs the observer/controller for the characters
	 */
	public void addCharTokenObserver(Observer obs) {
		for(int i = 0; i < TokenConstants.NUM_CHARACTERS; i++) {
			characters[i].addObserver(obs);
			characters[i].initializeObservers();
		}
	}
	
	/**
	 * Adds the observer (controller) to the regular tokens.
	 * 
	 * @param obs the observer/controller for the tokens
	 */
	public void addTokenObserver(Observer obs) {
		for(Barricade b : barricades) {
			b.addObserver(obs);
			b.initializeObservers();
		}
		for(GasLight g : gaslights) {
			g.addObserver(obs);
			g.initializeObservers();
		}
		for(ManholeCover m : mancovers) {
			m.addObserver(obs);
			m.initializeObservers();
		}
	}
	
	/**
	 * Evaluates the innocence of every character based on whether
	 * Mr Jack was seen/witnessed.
	 * 
	 * @param jackWasSeen <code>true</code> if jack was seen
	 */
	public void evaluateInnocence(boolean jackWasSeen) {
		for(int i = 0; i < TokenConstants.NUM_CHARACTERS; i++) {
			characters[i].evaluateInnocence(jackWasSeen);
		}
	}
	
	/**
	 * Gets the list of gaslights which can be removed.
	 * This is used to pass to the <code>TurnKeeper</code>.
	 * 
	 * @return the list of gaslights which are removable
	 */
	public GasLight[] getRemovableGaslights() {
		GasLight[] removable = new GasLight[TokenConstants.NUM_REMOVABLE_GASLIGHTS];
		for(int i = 0; i < TokenConstants.NUM_REMOVABLE_GASLIGHTS; i++) {
			removable[i] = gaslights[i];
		}
		return removable;
	}
	
	/**
	 * Gets the token at the given location that exists in
	 * the array passed in. This generic method is used for
	 * many other methods for getting specialized types of
	 * tokens, and to avoid code duplication, this uses generic
	 * types.
	 * 
	 * @param tokenArr the array of tokens
	 * @param location the location of the desired token (row, col)
	 * @return the token with the corresponding location, found in the
	 * input array
	 */
	public <T extends Token> T getToken(T[] tokenArr, int[] location) {
		for(int i = 0; i < tokenArr.length; i++) {
			int[] thisLoc = tokenArr[i].getTokenLocation();
			// Check only if the token wasn't removed from the board.
			if(thisLoc != null)
				if(thisLoc[0] == location[0] && thisLoc[1] == location[1]) return tokenArr[i];
		}
		return null;
	}
	
	@Override
	public CharacterToken getCharacter(String characterName) {
		for(int i = 0 ; i < characters.length; i++)
			if(characters[i].getName().equals(characterName)) return characters[i];
		return null;
	}
	
	@Override
	public boolean characterCollision(CharacterToken recentlyMoved) {
		int[] loc = recentlyMoved.getTokenLocation();
		for(CharacterToken t : characters) {
			if(!t.equals(recentlyMoved)) {
				int[] currLoc = t.getTokenLocation();
				if(currLoc[0] == loc[0] && currLoc[1] == loc[1]) return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean collisionWithJack(CharacterToken recentlyMoved) {
		int[] loc = recentlyMoved.getTokenLocation();
		for(CharacterToken t : characters) {
			if(!t.equals(recentlyMoved)) {
				int[] currLoc = t.getTokenLocation();
				if(currLoc[0] == loc[0] && currLoc[1] == loc[1]) {
					return t.isMrJack();
				}
			}
		}
		return false;
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
		return manholes;
	}

	@Override
	public Exit[] getExits() {
		return exits;
	}

	@Override
	public Lamppost[] getLamps() {
		return lampList;
	}
}
