package model.token;

public class TokenConstants {
	
	public static final int NUM_CHARACTERS	= 4;
	// Bert, Lestrade, Smith, Stealthy
	public static final char[] CHARACTER_CHARS = {'S', 'L', 'B', 's'};
	public static final String[] CHAR_NAMES = {
			"Stealthy",
			"LeStrade",
			"Bert",
			"Smith"
	};
	public static final int[] CHAR_NUM_MOVES = {4, 3, 3, 3};
	public static final int[][] CHAR_LOCATIONS = {
			{1,1},
			{1,4},
			{3,0},
			{3,5}
	};
	
	// Exit, Barricade, Null, Regular Tile, Lamppost, Manhole
	public static final char[] TILE_CHARS = {'E', 'B', 'X', ' ', 'L', 'M'};
	// Characters for individual tiles
	public static final char EXIT_C 	= 'E';
	public static final char BUILD_C = 'B';
	public static final char NONE_C 	= 'X';
	public static final char REG_C 	= ' ';
	public static final char LAMP_C 	= 'L';
	public static final char MANH_C 	= 'M';
	
	// Barricade, Manhole cover, Gaslight
	public static final char[] TOKEN_CHARS = {'B', 'M', 'G', '1', '2', '3', '4'};
	// Characters for individual tokens
	public static final char BARRICADE_C = 'B';
	public static final char MANCOVER_C = 'M';
	public static final char GASLIGHT_C = 'G';
	
	
	public static final char[][] TILE_FRAMEWORK = new char[][] {
		{'E', 'X', 'X', 'X', ' ', 'M', ' ', ' '},
		{'X', ' ', 'L', 'B', ' ', 'L', ' ', ' '},
		{' ', 'M', ' ', ' ', ' ', 'B', 'M', 'L'},
		{' ', 'B', 'B', 'B', ' ', ' ', ' ', 'E'}
	};
	
	
	public static final int NUM_MANHOLES	= 3;
	public static final int NUM_EXITS	= 2;
	public static final int NUM_LAMPS	= 3;
	
	public static final int NUM_BARRICADES = 1;
	public static final int NUM_GASLIGHTS = 2;
	public static final int NUM_REMOVABLE_GASLIGHTS = 1;
	public static final int NUM_MANCOVERS = 1;
}
