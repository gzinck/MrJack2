package view;

import controller.clickresponders.ActionBtnClickResponder;
import controller.clickresponders.CardClickResponder;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.token.TokenConstants;
import view.board.BoardView;

public class GameView implements CardView {
	@FXML private AnchorPane board;
	@FXML private Button actionBeforeBtn;
	@FXML private Button actionAfterBtn;
	@FXML private ImageView card1;
	@FXML private ImageView card2;
	private ImageView[] cards;
	private BoardView boardView;
	
	private CardClickResponder cardClicker;
	private ActionBtnClickResponder actionClicker;
	
	private static final Image[] CARD_IMGS = {
			new Image("/res/img/characters-cards/stealthy.jpg"),
			new Image("/res/img/characters-cards/lestrade.jpg"),
			new Image("/res/img/characters-cards/bert.jpg"),
			new Image("/res/img/characters-cards/smith.jpg")
	};
	
//	public static final String[] CHAR_NAMES = {
//			"Stealthy",
//			"LeStrade",
//			"Bert",
//			"Smith"
//	};
	
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
	
	public void initializeClickResponders(CardClickResponder inCardClicker, ActionBtnClickResponder inActionClicker) {
		cardClicker = inCardClicker;
		actionClicker = inActionClicker;
	}
	
	public void activateActionBtns() {
		actionBeforeBtn.setDisable(false);
		actionAfterBtn.setDisable(false);
	}
	
	@FXML
	private void onClickActionBefore() {
		actionBeforeBtn.setDisable(true);
		actionAfterBtn.setDisable(true);
		actionClicker.actionBeforeOnClick();
	}
	
	@FXML
	private void onClickActionAfter() {
		actionBeforeBtn.setDisable(true);
		actionAfterBtn.setDisable(true);
		actionClicker.actionAfterOnClick();
	}
	
	@FXML
	private void onClickCard1() {
		cardClicker.cardClicked(0);
		System.out.println("card1 was clicked");
	}
	
	@FXML
	private void onClickCard2() {
		cardClicker.cardClicked(1);
		System.out.println("card2 was clicked");
	}

	@Override
	public void placeCard(String cardName, int index) {
		if(cardName == null) {
			cards[index].setImage(null);
			return;
		}
		int i = 0;
		while(!TokenConstants.CHAR_NAMES[i].equals(cardName)) {
			i++;
		}
		cards[index].setImage(CARD_IMGS[i]);
	}
}
