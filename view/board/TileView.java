package view.board;

import controller.clickresponders.TileClickResponder;
import javafx.scene.image.*;
import javafx.scene.layout.Region;

import model.token.TokenConstants;

public class TileView extends Region {
	private static final Image[] TILE_IMGS = {
			new Image("/res/img/tiles/exit-tile.png"),
			new Image("/res/img/tiles/building-tile.png"),
			new Image("/res/img/tiles/empty-tile.png"),
			new Image("/res/img/tiles/regular-tile.png"),
			new Image("/res/img/tiles/lamppost-tile.png"),
			new Image("/res/img/tiles/manhole-tile.png"),
	};
	private static final Image[] CHARACTER_IMGS = {
			new Image("/res/img/character-tokens/bert.png"),
			new Image("/res/img/character-tokens/lestrade.png"),
			new Image("/res/img/character-tokens/smith.png"),
			new Image("/res/img/character-tokens/stealthy.png")
	};
	private static final Image[] TOKEN_IMGS = {
			new Image("/res/img/tokens/barricade-token.png"),
			new Image("/res/img/tokens/mc-token.png"),
			new Image("/res/img/tokens/gaslight-token.png"),
			new Image("/res/img/tokens/gaslight-token-1.png"),
			new Image("/res/img/tokens/gaslight-token-2.png"),
			new Image("/res/img/tokens/gaslight-token-3.png"),
			new Image("/res/img/tokens/gaslight-token-4.png")
	};
	
	private ImageView tileImg;
	private ImageView tokenImg;
	private ImageView characterImg;
	
	private TileClickResponder responder;
	private int row, col;
	
	public TileView(char tileType, double width, double height, int inRow, int inCol) {
		row = inRow;
		col = inCol;
		
		this.setStyle("-fx-cursor: hand;");
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
	
	public void addClickResponder(TileClickResponder clickResponder) {
		responder = clickResponder;
		addActionListener();
	}
	
	private void addActionListener() {
		this.setOnMouseClicked(e -> {
			responder.tileClicked(row, col);
		});
	}
	
	public void addToken(int tokenIndex) {
		tokenImg.setImage(TOKEN_IMGS[tokenIndex]);
	}
	public void removeToken() {
		tokenImg.setImage(null);
	}
	
	public void addCharacterToken(int tokenIndex) {
		characterImg.setImage(CHARACTER_IMGS[tokenIndex]);
	}
	public void removeCharToken() {
		characterImg.setImage(null);
	}
	public void setInnocence(boolean isInnocent) {
		if(isInnocent)
			characterImg.setStyle("-fx-opacity: 0.5;");
		else
			characterImg.setStyle("-fx-opacity: 1;");
	}
	
	public void tileResize(double width, double height) {
		tileImg.setFitWidth(width);
		tileImg.setFitHeight(height);
		tokenImg.setFitWidth(width);
		tokenImg.setFitHeight(height);
		characterImg.setFitWidth(width);
		characterImg.setFitHeight(height);
	}
	
	public void highlight() {
		tileImg.setStyle("-fx-opacity:0.5;");
	}
	public void unhighlight() {
		tileImg.setStyle("-fx-opacity:1;");
	}
}
