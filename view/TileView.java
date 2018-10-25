package view;

import java.util.HashMap;
import javafx.scene.image.*;
import javafx.scene.layout.Region;

public class TileView extends Region {
	private static final char[] TILE_CHARS = {'E', 'B', 'X', ' ', 'L', 'M'};
	private static final Image[] TILE_IMGS = {
			new Image("/res/img/tiles/exit-tile.png"),
			new Image("/res/img/tiles/building-tile.png"),
			new Image("/res/img/tiles/empty-tile.png"),
			new Image("/res/img/tiles/regular-tile.png"),
			new Image("/res/img/tiles/lamppost-tile.png"),
			new Image("/res/img/tiles/manhole-tile.png"),
	};
	private static final char[] CHARACTER_CHARS = {'B', 'L', 'S', 's'};
	private static final Image[] CHARACTER_IMGS = {
			new Image("/res/img/character-tokens/bert.png"),
			new Image("/res/img/character-tokens/lestrade.png"),
			new Image("/res/img/character-tokens/smith.png"),
			new Image("/res/img/character-tokens/stealthy.png")
	};
	
	private static final char[] TOKEN_CHARS = {'B', 'M', 'G', '1', '2', '3', '4'};
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
	
	public TileView(char tileType, double width, double height) {
		this.setStyle("-fx-cursor: hand;");
		char c = '`';
		int index = -1;
		while(c != tileType)
			c = TILE_CHARS[++index];
		tileImg = new ImageView(TILE_IMGS[index]);
		tokenImg = new ImageView();
		characterImg = new ImageView();
		tileResize(width, height);
		this.getChildren().addAll(tileImg, tokenImg, characterImg);
	}
	
	public void addToken(char tokenType) {
		char c = '`';
		int index = -1;
		while(c != tokenType)
			c = TOKEN_CHARS[++index];
		tokenImg.setImage(TOKEN_IMGS[index]);
		tokenImg = new ImageView(TOKEN_IMGS[index]);
	}
	public void removeToken() {
		tokenImg.setImage(null);
	}
	
	public void addCharacterToken(char tokenType) {
		char c = '`';
		int index = -1;
		while(c != tokenType)
			c = CHARACTER_CHARS[++index];
		characterImg.setImage(CHARACTER_IMGS[index]);
		characterImg = new ImageView(TILE_IMGS[index]);
	}
	public void removeCharToken() {
		characterImg.setImage(null);
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
