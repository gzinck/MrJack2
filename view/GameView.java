package view;

import controller.clickresponders.ActionBtnClickResponder;
import controller.clickresponders.CardClickResponder;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import model.token.TokenConstants;
import view.board.BoardView;

public class GameView implements CardView, TurnKeeperView, WitnessView {
	@FXML private AnchorPane board;
	@FXML private Button actionBeforeBtn;
	@FXML private Button actionAfterBtn;
	@FXML private ImageView card1;
	@FXML private ImageView card2;
	@FXML private Text roundText;
	@FXML private Text turnText;
	@FXML private Text playerText;
	@FXML private ImageView witnessCard;
	@FXML private StackPane winPane;
	@FXML private ImageView winImg;
	@FXML private ImageView cardDrawn;
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
	
	private static final Image[] WITNESS_IMGS = {
			new Image("/res/img/witness-cards/unseen.png"),
			new Image("/res/img/witness-cards/seen.png")
	};
	
	private static final Image[] WIN_IMGS = {
			new Image("/res/img/win-imgs/jack-win.png"),
			new Image("/res/img/win-imgs/detective-win.png")
	};
	
	public void initialize() {
		witnessCard.setImage(WITNESS_IMGS[1]);
		deactivateActionBtns();
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
	
	private void deactivateActionBtns() {
		actionBeforeBtn.setDisable(true);
		actionAfterBtn.setDisable(true);
	}
	
	public void drawAlibiCard(String card) {
		int cardIndex = 0;
		while(!TokenConstants.CHAR_NAMES[cardIndex].equals(card)) cardIndex++;
		cardDrawn.setImage(CARD_IMGS[cardIndex]);
		FadeTransition ft = new FadeTransition(Duration.millis(15000), cardDrawn);
		ft.setFromValue(1.0);
		ft.setToValue(0.0);
		ft.play();
	}
	
	public void endGame(boolean jackWon) {
		winPane.setDisable(false);
		if(jackWon) winImg.setImage(WIN_IMGS[0]);
		else winImg.setImage(WIN_IMGS[1]);
	}
	@FXML
	private void onClickActionBefore() {
		deactivateActionBtns();
		actionClicker.actionBeforeOnClick();
	}
	
	@FXML
	private void onClickActionAfter() {
		deactivateActionBtns();
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

	@Override
	public void updatePlayer(String playerName) {
		playerText.setText(playerName);
	}

	@Override
	public void updateTurn(int turn) {
		turnText.setText(turn + "");
	}

	@Override
	public void updateRound(int round) {
		roundText.setText(round + "");
	}

	@Override
	public void updateWitnessed(boolean wasWitnessed) {
		if(wasWitnessed) witnessCard.setImage(WITNESS_IMGS[1]);
		else witnessCard.setImage(WITNESS_IMGS[0]);
	}
}
