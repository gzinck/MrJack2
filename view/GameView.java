package view;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import view.board.BoardView;

public class GameView {
	@FXML private AnchorPane board;
	private BoardView boardView;
	
	public void initialize() {
		// Initialize here
    }
	
	public BoardView drawBoard(char[][] boardTemplate) {
		boardView = new BoardView(boardTemplate);
		AnchorPane.setTopAnchor(boardView, 0.0);
		AnchorPane.setBottomAnchor(boardView, 0.0);
		AnchorPane.setRightAnchor(boardView, 0.0);
		AnchorPane.setLeftAnchor(boardView, 0.0);
		board.getChildren().add(boardView);
		// This runs later so that the appropriate sizes apply
		Platform.runLater(() -> {
			boardView.drawBoard();
		});
		return boardView;
	}
}
