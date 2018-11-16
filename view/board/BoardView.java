package view.board;

import controller.clickresponders.TileClickResponder;
import javafx.scene.layout.AnchorPane;

/**
 * BoardView allows the user to see the game board. It has all of the
 * tiles, the tokens, and the character tokens displayed. It handles
 * a variable sized canvas by stretching the tiles, and it resizes
 * according to the board template passed in upon instantiation.
 * 
 * @author Graeme Zinck and Charles Jobin
 * @version  1.0
 *
 */
public class BoardView extends AnchorPane implements CharTokenViewModifier, TokenViewModifier {
	/** 2D array of characters representing the types of tiles at each position of the board. */
	private char[][] board;
	/** The number of rows and columns of the game board. */
	private int numRows, numCols;
	/** The view objects for each individual tile. Each of these will aso display the tokens on said tiles. */
	private TileView[][] tiles;
	/** This is an array of 2-tuples (row, col) of the indices of highlighted tiles. */
	private int[][] highlightedTiles;
	/** Height and width of the tiles. */
	private double tW, tH;
	/** Responder that performs actions upon clicking on a tile. */
	TileClickResponder responder;
	
	/**
	 * Creates a new BoardView with a template for the tiles which will go
	 * on the new board. These should follow the guidelines set out in
	 * <code>TokenConstants.java</code>.
	 * 
	 * @param boardTemplate 2D character array with characters indicating the
	 * type of tile to go in a given location of the board.
	 */
	public BoardView(char[][] boardTemplate) {
		board = boardTemplate;
		highlightedTiles = null;
		drawBoard();
		// Enable resizing
		this.widthProperty().addListener((obs, oldVal, newVal) -> {
			if(tiles != null)
				resizeTiles();
		});
		this.heightProperty().addListener((obs, oldVal, newVal) -> {
			if(tiles != null)
				resizeTiles();
		});
	}
	
	/**
	 * Draws the board for the first time.
	 */
	private void drawBoard() {
		numRows = board.length;
		numCols = board[0].length;
		tiles = new TileView[numRows][numCols];
		tW = getTileWidth();
		tH = getTileHeight();
		for(int row = 0; row < numRows; row++) {
			for(int col = 0; col < numCols; col++) {
				addTile(row, col);
			} // for each col
		} // for each row
	}
	
	/**
	 * Sets the click responder for every tile to be the input
	 * TileClickResponder.
	 * 
	 * @param clickResponder interface that responds to click
	 * actions on tiles.
	 */
	public void setClickResponder(TileClickResponder clickResponder) {
		responder = clickResponder;
		// For every tile, if the tile is not null, add the responder
		for(TileView[] tileArr : tiles) for(TileView tile : tileArr)
			if(tile != null) tile.addClickResponder(clickResponder);
	}
	
	/**
	 * Resizes the tiles to the proper size (especially applicable
	 * when the window resizes).
	 */
	private void resizeTiles() {
		tW = getTileWidth();
		tH = getTileHeight();
		for(int row = 0; row < numRows; row++) {
			for(int col = 0; col < numCols; col++) {
				tiles[row][col].relocate(getTileX(col), getTileY(row, col));
				tiles[row][col].tileResize(tW, tH);
			} // for each col
		} // for each row
	}
	
	/**
	 * Adds a tile at the given row and column indices and resizes it
	 * appropriately.
	 * 
	 * @param row the row index (starts at 0)
	 * @param col the column index (starts at 0)
	 */
	private void addTile(int row, int col) {
		tiles[row][col] = new TileView(board[row][col], tW, tH, row, col);
		tiles[row][col].resizeRelocate(getTileX(col), getTileY(row, col), tW, tH);
		this.getChildren().add(tiles[row][col]);
	}
	
	/**
	 * Gets the desired width of a tile.
	 * @return the width of a tile
	 */
	private double getTileWidth() {
		return getWidth() / (numCols * 0.75 + 0.25);
	}
	
	/**
	 * Gets the x-position of the tile within the BoardView,
	 * given the column of the tile.
	 * 
	 * @param col the column of the tile (starts at 0)
	 * @return the x-coordinate of the top left corner of the tile
	 */
	private double getTileX(int col) {
		double xOffset = getWidth() / (numCols + 0.25);
		return col * xOffset;
	}
	
	/**
	 * Gets the desired height of a tile.
	 * @return the height of a tile
	 */
	private double getTileHeight() {
		return getHeight() / (numRows + 0.5);
	}
	
	/**
	 * Gets the y-position of the tile within the BoardView,
	 * given the row and column of the tile.
	 * 
	 * @param row the row of the tile (starts at 0)
	 * @param col the column of the tile (starts at 0)
	 * @return the y-coordinate of the top left corner of the tile
	 */
	private double getTileY(int row, int col) {
		double yOffset = getTileHeight();
		if(col % 2 == 0) return (row + 0.5) * yOffset;
		return row * yOffset;
	}
	
	/**
	 * Highlights all the tiles with the (row, col) coordinates
	 * indicated array of 2-tuples passed as input.
	 * 
	 * @param tileLocations Array of 2-tuples (row, col) which indicate
	 * which tile locations should be highlighted.
	 */
	public void highlightTiles(int[][] tileLocations) {
		if(highlightedTiles != null)
			unhighlightTiles();
		highlightedTiles = tileLocations;
		for(int i = 0; i < highlightedTiles.length; i++)
			tiles[highlightedTiles[i][0]][highlightedTiles[i][1]].highlight();
	}
	
	/**
	 * Makes all tiles that were previously highlighted unhighlighted
	 * again, as normal.
	 */
	public void unhighlightTiles() {
		if(highlightedTiles != null)
			for(int i = 0; i < highlightedTiles.length; i++)
				tiles[highlightedTiles[i][0]][highlightedTiles[i][1]].unhighlight();
		highlightedTiles = null;
	}
	
	@Override
	public void moveCharacter(String characterName, int[] prevLocation, int[] newLocation) {
		tiles[prevLocation[0]][prevLocation[1]].removeCharToken();
		tiles[newLocation[0]][newLocation[1]].addCharacterToken(characterName);
	}
	
	@Override
	public void setInnocence(int[] location, boolean isInnocent) {
		tiles[location[0]][location[1]].setInnocence(isInnocent);
	}
	
	@Override
	public void moveToken(int tokenIndex, int[] prevLocation, int[] newLocation) {
		tiles[prevLocation[0]][prevLocation[1]].removeToken();
		// If newLocation is null, token is removed from board.
		if(newLocation != null)
			tiles[newLocation[0]][newLocation[1]].addToken(tokenIndex);
	}
}
