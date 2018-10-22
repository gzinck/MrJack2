package view;

import java.util.HashMap;
import javafx.scene.image.*;
import javafx.scene.layout.Region;

public class TileView extends Region {
	private static final char[] TILE_CHARS = {'E', 'B', 'X', ' ', 'L', 'M'};
	private static Image[] TILE_IMGS = {
			new Image("/res/img/tiles/exit-tile.png"),
			new Image("/res/img/tiles/building-tile.png"),
			new Image("/res/img/tiles/empty-tile.png"),
			new Image("/res/img/tiles/regular-tile.png"),
			new Image("/res/img/tiles/lamppost-tile.png"),
			new Image("/res/img/tiles/manhole-tile.png"),
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
		tileResize(width, height);
		this.getChildren().add(tileImg);
		
		this.setOnMouseClicked(e -> {
			
		});
	}
	
	public void tileResize(double width, double height) {
		tileImg.setFitWidth(width);
		tileImg.setFitHeight(height);
	}
}
