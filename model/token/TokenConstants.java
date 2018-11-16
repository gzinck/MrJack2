package model.token;
/**
 * Class for the all the token's constants.
 * 
 * @author Graeme Zinck and Charles Jobin
 * @version 1.0
 *
 */
public class TokenConstants {
	
	/** Total number of active characters that are "basic" */
	public static final int NUM_ACTIVE_BASIC_CHARS = 2;
	/** Total number of active characters that are "optional" */
	public static final int NUM_ACTIVE_OPTIONAL_CHARS = 2;
	/** Total number of active characters in the game. */
	public static final int NUM_ACTIVE_CHARACTERS = NUM_ACTIVE_BASIC_CHARS + NUM_ACTIVE_OPTIONAL_CHARS;
	
	/** Total number of POSSIBLE basic characters (which may or may not be included in the game) */
	public static final int NUM_BASIC_CHARS = 3;
	/** Total number of POSSIBLE optional characters (which may or may not be included in the game) */
	public static final int NUM_OPTIONAL_CHARS = 3;
	/** Total number of POSSIBLE characters in the game. */
	public static final int NUM_TOTAL_CHARS = NUM_BASIC_CHARS + NUM_OPTIONAL_CHARS;
	/** Character char array, Bert, Lestrade, Smith, Stealthy, Madame, Abberline */
	public static final char[] CHARACTER_CHARS = {'L', 'B', 's', 'S', 'M', 'A'};
	/** String array of the character name */
	public static final String[] CHAR_NAMES = {
			"LeStrade",
			"Bert",
			"Smith",
			"Stealthy",
			"Madame",
			"Abberline"
	};
	/** String array of the character names that are actually used in the game */
	public static String[] activeCharNames;
	/** Number of moves for each character */
	public static final int[] CHAR_NUM_MOVES = {3, 3, 3, 4, 6, 3};
	/** Locations of the character tokens at start of a game */
	public static final int[][] CHAR_LOCATIONS = {
			{1,1},
			{1,4},
			{3,0},
			{3,5},
			{2,0},
			{6,0}
	};
	
	/** Character array of the tiles in the game; Exit, Barricade, Null, Regular Tile, Lamppost, Manhole */
	public static final char[] TILE_CHARS = {'E', 'B', 'X', ' ', 'L', 'M'};
	
	/** CHaracter for the exit tile */
	public static final char EXIT_C 	= 'E';
	/** CHaracter for the building tile */
	public static final char BUILD_C = 'B';
	/** CHaracter for a non-existent tile */
	public static final char NONE_C 	= 'X';
	/** CHaracter for a regular tile */
	public static final char REG_C 	= ' ';
	/** CHaracter for the lamp tile */
	public static final char LAMP_C 	= 'L';
	/** CHaracter for the manhole tile */
	public static final char MANH_C 	= 'M';
	
	/** Barricade, Manhole Cover, and Gaslight token characters */ 
	public static final char[] TOKEN_CHARS = {'B', 'M', 'G', '1', '2', '3', '4'};
	/** Character for the barricade */
	public static final char BARRICADE_C = 'B';
	/** Character for the manhole cover */
	public static final char MANCOVER_C = 'M';
	/** Character for the gaslight */
	public static final char GASLIGHT_C = 'G';
	
	/**
	 * This is the actual game board, which uses the token
	 * characters indicated above.
	 */
	public static final char[][] TILE_FRAMEWORK = new char[][] {
		{'E', 'X', 'X', 'X', ' ', 'M', ' ', ' ', ' '},
		{'X', ' ', 'L', 'B', ' ', 'L', ' ', 'B', ' '},
		{' ', 'M', ' ', ' ', ' ', 'B', 'M', ' ', 'L'},
		{' ', 'B', 'B', 'B', ' ', ' ', ' ', ' ', 'E'}
	};
	
	/** The number of manholes in the TILE_FRAMEWORK */
	public static final int NUM_MANHOLES	= 3;
	/** The number of exits in the TILE_FRAMEWORK */
	public static final int NUM_EXITS	= 2;
	/** The number of lamps in the TILE_FRAMEWORK */
	public static final int NUM_LAMPS	= 3;
	
	/** The number of barricades that exist */
	public static final int NUM_BARRICADES = 1;
	/** The number of gaslights that exist */
	public static final int NUM_GASLIGHTS = 2;
	/** The number of gaslights (of those that exist) that can be removed */
	public static final int NUM_REMOVABLE_GASLIGHTS = 1;
	/** The number of manhole covers that exist */
	public static final int NUM_MANCOVERS = 1;
}
