package view.board;

import controller.clickresponders.TileClickResponder;
import javafx.scene.image.*;
import javafx.scene.layout.Region;

import model.token.TokenConstants;

/**
 * This is the individual view for a tile which is displayed
 * in the <code>BoardView</code>. It has the image for the
 * tile itself, any token on it, and any character token on it.
 * 
 * @author Graeme Zinck and Charles Jobin
 * @version 1.0
 *
 */
public class TileView extends Region {
	/** This has all of the images for different types of tiles. */
	private static final Image[] TILE_IMGS = {
			new Image("/res/img/tiles/exit-tile.png"),
			new Image("/res/img/tiles/building-tile.png"),
			new Image("/res/img/tiles/empty-tile.png"),
			new Image("/res/img/tiles/regular-tile.png"),
			new Image("/res/img/tiles/lamppost-tile.png"),
			new Image("/res/img/tiles/manhole-tile.png"),
	};
	/** This has all of the images for different types of character tokens. */
	private static final Image[] CHARACTER_IMGS = {
			new Image("/res/img/character-tokens/lestrade.png"),
			new Image("/res/img/character-tokens/bert.png"),
			new Image("/res/img/character-tokens/smith.png"),
			new Image("/res/img/character-tokens/stealthy.png"),
			new Image("/res/img/character-tokens/madame.png"),
			new Image("/res/img/character-tokens/abberline.png")
	};
	/** This has all of the images for different types of regular tokens. */
	private static final Image[] TOKEN_IMGS = {
			new Image("/res/img/tokens/barricade-token.png"),
			new Image("/res/img/tokens/mc-token.png"),
			new Image("/res/img/tokens/gaslight-token.png"),
			new Image("/res/img/tokens/gaslight-token-1.png"),
			new Image("/res/img/tokens/gaslight-token-2.png"),
			new Image("/res/img/tokens/gaslight-token-3.png"),
			new Image("/res/img/tokens/gaslight-token-4.png")
	};
	
	/** The current tile's image view. */
	private ImageView tileImg;
	/** The current tile's token which is on the tile. */
	private ImageView tokenImg;
	/** The current tile's character token which is on the tile. */
	private ImageView characterImg;
	
	/**
	 * The responder that does actions in the controller when the user clicks
	 * a tile. 
	 */
	private TileClickResponder responder;
	/** Row and column of the tile. */
	private int row, col;
	
	/**
	 * Creates a new TileView using the type of tile given as a
	 * character (such as 'E' for exit, or 'L' for lamppost).
	 * 
	 * @param tileType the character representing the tile's type
	 * @param width the width of the tile's view
	 * @param height the height of the tile's view
	 * @param inRow the row of the tile
	 * @param inCol the column of the tile
	 */
	public TileView(char tileType, double width, double height, int inRow, int inCol) {
		row = inRow;
		col = inCol;
		
		this.setStyle("-fx-cursor: hand;");
		
		// Get the index for the tile's image
		char c = '`';
		int index = -1;
		while(c != tileType)
			c = TokenConstants.TILE_CHARS[++index];
		
		tileImg = new ImageView(TILE_IMGS[index]);
		tokenImg = new ImageView();
		characterImg = new ImageView();
		tileResize(width, height);
		this.getChildren().addAll(tileImg, tokenImg, characterImg);
	}
	
	/**
	 * Adds a responder for when the tile view is clicked.
	 * 
	 * @param clickResponder something that responds to the tile
	 * getting clicked
	 */
	public void addClickResponder(TileClickResponder clickResponder) {
		responder = clickResponder;
		addActionListener();
	}
	
	/**
	 * Adds a listener for when the tile is clicked.
	 */
	private void addActionListener() {
		this.setOnMouseClicked(e -> {
			responder.tileClicked(row, col);
		});
	}
	
	/**
	 * Adds a token to the tile.
	 * 
	 * @param tokenIndex index representing which token should be
	 * placed on the tile, as per <code>TokenConstants.java</code>
	 */
	public void addToken(int tokenIndex) {
		tokenImg.setImage(TOKEN_IMGS[tokenIndex]);
	}
	
	/**
	 * Removes any token existing on the tile (excludes a 
	 * character token.
	 */
	public void removeToken() {
		tokenImg.setImage(null);
	}
	
	/**
	 * Adds a character token to the tile.
	 * 
	 * @param characterName index of the character to place on the tile
	 * as per <code>TokenConstants.java</code>
	 */
	public void addCharacterToken(String characterName) {
		int charIndex = 0;
		while(!TokenConstants.CHAR_NAMES[charIndex].equals(characterName)) charIndex++;
		characterImg.setImage(CHARACTER_IMGS[charIndex]);
	}
	
	/**
	 * Removes the character token from the tile.
	 */
	public void removeCharToken() {
		characterImg.setImage(null);
	}
	
	/**
	 * Sets the innocence of the character on the tile.
	 * 
	 * @param isInnocent <code>true</code> if the character
	 * is innocent
	 */
	public void setInnocence(boolean isInnocent) {
		if(isInnocent)
			characterImg.setStyle("-fx-opacity: 0.5;");
		else
			characterImg.setStyle("-fx-opacity: 1;");
	}
	
	/**
	 * Resizes the tile.
	 * 
	 * @param width new width of the tile
	 * @param height new height of the tile
	 */
	public void tileResize(double width, double height) {
		tileImg.setFitWidth(width);
		tileImg.setFitHeight(height);
		tokenImg.setFitWidth(width);
		tokenImg.setFitHeight(height);
		characterImg.setFitWidth(width);
		characterImg.setFitHeight(height);
	}
	
	/**
	 * Highlights the tile.
	 */
	public void highlight() {
		tileImg.setStyle("-fx-opacity:0.5;");
	}
	
	/**
	 * Unhighlights the tile.
	 */
	public void unhighlight() {
		tileImg.setStyle("-fx-opacity:1;");
	}
}
