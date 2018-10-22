package view;

import javafx.scene.layout.AnchorPane;

public class BoardView extends AnchorPane {
	
	private char[][] board;
	private int numRows, numCols;
	private TileView[][] tiles;
	private double tW, tH; // Tile positions and sizes.
	
	public BoardView(char[][] boardTemplate) {
		board = boardTemplate;
		this.widthProperty().addListener((obs, oldVal, newVal) -> {
			if(tiles != null)
				resizeTiles();
		});
		this.heightProperty().addListener((obs, oldVal, newVal) -> {
			if(tiles != null)
				resizeTiles();
		});
	}
	
	public void drawBoard() {
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
	
	private void addTile(int row, int col) {
		tiles[row][col] = new TileView(board[row][col], tW, tH);
		tiles[row][col].resizeRelocate(getTileX(col), getTileY(row, col), tW, tH);
		this.getChildren().add(tiles[row][col]);
	}
	
	private double getTileWidth() {
		return getWidth() / (numCols * 0.75 + 0.25);
	}
	
	private double getTileX(int col) {
		double xOffset = getWidth() / (numCols + 0.25);
		return col * xOffset;
	}
	
	private double getTileHeight() {
		return getHeight() / (numRows + 0.5);
	}
	
	private double getTileY(int row, int col) {
		double yOffset = getTileHeight();
		if(col % 2 == 0) return (row + 0.5) * yOffset;
		return row * yOffset;
	}
}
