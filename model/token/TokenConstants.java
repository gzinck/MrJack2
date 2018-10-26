package model.token;

public class TokenConstants {
	// Exit, Barricade, Null, Regular Tile, Lamppost, Manhole
	public static final char[] TILE_CHARS = {'E', 'B', 'X', ' ', 'L', 'M'};
	// Barricade, Manhole cover, Gaslight
	public static final char[] TOKEN_CHARS = {'B', 'M', 'G', '1', '2', '3', '4'};
	
	// Bert, Lestrade, Smith, Stealthy
	public static final char[] CHARACTER_CHARS = {'S', 'L', 'B', 's'};
	public static final String[] CHAR_NAMES = {
			"Stealthy",
			"LeStrade",
			"Bert",
			"Smith"
	};
	// Characters for individual tiles
	public static final char EXIT_C 	= 'E';
	public static final char BUILD_C 	= 'B';
	public static final char NONE_C 	= 'X';
	public static final char REG_C 		= ' ';
	public static final char LAMP_C 	= 'L';
	public static final char MANH_C 	= 'M';
}
