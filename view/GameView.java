package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import view.board.BoardView;

public class GameView implements CardView {
	@FXML private AnchorPane board;
	@FXML private Button actionBeforeBtn;
	@FXML private Button actionAfterBtn;
	@FXML private ImageView card1;
	@FXML private ImageView card2;
	private ImageView[] cards;
	private BoardView boardView;
	
	public void initialize() {
		// Initialize here
    }
	
	public BoardView drawBoard(char[][] boardTemplate) {
		cards = new ImageView[] {card1, card2};
		boardView = new BoardView(boardTemplate);
		AnchorPane.setTopAnchor(boardView, 0.0);
		AnchorPane.setBottomAnchor(boardView, 0.0);
		AnchorPane.setRightAnchor(boardView, 0.0);
		AnchorPane.setLeftAnchor(boardView, 0.0);
		board.getChildren().add(boardView);
		return boardView;
	}
	
	@FXML
	private void onClickActionBefore() {
		actionBeforeBtn.setDisable(true);
		actionAfterBtn.setDisable(true);
	}
	
	@FXML
	private void onClickActionAfter() {
		actionBeforeBtn.setDisable(true);
		actionAfterBtn.setDisable(true);
	}
	
	@FXML
	private void onClickCard1() {
		System.out.println("card1 was clicked");
	}
	
	@FXML
	private void onClickCard2() {
		System.out.println("card2 was clicked");
	}

	@Override
	public void placeCard(String cardName, int index) {
		// TODO Auto-generated method stub
		System.out.println(cardName + " was placed.");
	}
}
