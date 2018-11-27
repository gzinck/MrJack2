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

/**
 * GameView is the main component of the view, pulling in the XML of the
 * GUI and interacting with the controllers. It handles showing the cards,
 * showing the turn/round, showing the status of the witness card, showing
 * the end game, and instantiating the game board. Not everything was broken
 * into separate classes/interfaces because this would have increased
 * the complication when working with the XML.
 * 
 * @author Graeme Zinck and Charles Jobin
 * @version 1.0
 *
 */
public class GameView implements CardView, TurnKeeperView, WitnessView {
	/** The pane containing the view for the game board. */
	@FXML private AnchorPane board;
	/**
	 * The button to let the user perform an action before their move.
	 * This is enabled/disabled depending on the turn stage. 
	 */
	@FXML private Button actionBeforeBtn;
	/** The button to let the user perform an action after their move. */
	@FXML private Button actionAfterBtn;
	/** First character card visible on the game board. */
	@FXML private ImageView card1;
	/** Second character card visible on the game board. */
	@FXML private ImageView card2;
	/** Third character card visible on the game board. */
	@FXML private ImageView card3;
	/** Fourth character card visible on the game board. */
	@FXML private ImageView card4;
	/** Text indicating the round number. */
	@FXML private Text roundText;
	/** Text indicating the turn number. */
	@FXML private Text turnText;
	/** Text indicating the current player. */
	@FXML private Text playerText;
	/** Image with the current state of the witness card. */
	@FXML private ImageView witnessCard;
	/** Pane for the winning image at the end of the game. This is initially disabled. */
	@FXML private StackPane winPane;
	/** Image for the end-game celebration. */
	@FXML private ImageView winImg;
	/** Image showing the card recently drawn from the alibi deck. */
	@FXML private ImageView cardDrawn;
	/** Convenient array of image views allowing quick reference to cards on the table. */
	private ImageView[] cards;
	/** The board view which gets instantiated and placed in the game view. */
	private BoardView boardView;
	
	/** Responder to clicks on cards. */
	private CardClickResponder cardClicker;
	/** Responder to clicks on the action before/after buttons. */
	private ActionBtnClickResponder actionClicker;
	
	/** Images for all the character cards. */
	private static final Image[] CARD_IMGS = {
			new Image("/res/img/characters-cards/lestrade.jpg"),
			new Image("/res/img/characters-cards/bert.jpg"),
			new Image("/res/img/characters-cards/smith.jpg"),
			new Image("/res/img/characters-cards/stealthy.jpg"),
			new Image("/res/img/characters-cards/madame.jpg"),
			new Image("/res/img/characters-cards/abberline.jpg"),
			new Image("/res/img/characters-cards/pizer.jpg")
	};
	
	/** Images for both of the possible witness states. */
	private static final Image[] WITNESS_IMGS = {
			new Image("/res/img/witness-cards/unseen.png"),
			new Image("/res/img/witness-cards/seen.png")
	};
	
	/** Images for whichever character wins the game. */
	private static final Image[] WIN_IMGS = {
			new Image("/res/img/win-imgs/jack-win.png"),
			new Image("/res/img/win-imgs/detective-win.png")
	};
	
	/** Initializes the game view upon loading the FXML. */
	public void initialize() {
		deactivateActionBtns();
    }
	
	/**
	 * Draws the game board on the screen and places it appropriately.
	 * 
	 * @param boardTemplate 2D character array indicating what tiles are in what
	 * locations
	 * @return the boardview that is created
	 */
	public BoardView drawBoard(char[][] boardTemplate) {
		cards = new ImageView[] {card1, card2, card3, card4};
		boardView = new BoardView(boardTemplate);
		AnchorPane.setTopAnchor(boardView, 0.0);
		AnchorPane.setBottomAnchor(boardView, 0.0);
		AnchorPane.setRightAnchor(boardView, 0.0);
		AnchorPane.setLeftAnchor(boardView, 0.0);
		board.getChildren().add(boardView);
		return boardView;
	}
	
	/**
	 * Initializes the click responders for the game view, responding
	 * to actions in the GUI. This must be done for actions to actually
	 * cause reactions in the controllers.
	 * 
	 * @param inCardClicker controller for when cards on the table get clicked.
	 * @param inActionClicker controller for when the action before /
	 * after buttons are clicked.
	 */
	public void initializeClickResponders(CardClickResponder inCardClicker, ActionBtnClickResponder inActionClicker) {
		cardClicker = inCardClicker;
		actionClicker = inActionClicker;
	}
	
	/**
	 * Activates the action before/after buttons.
	 */
	public void activateActionBtns() {
		actionBeforeBtn.setDisable(false);
		actionAfterBtn.setDisable(false);
	}
	
	/**
	 * Deactivates the action before/after buttons.
	 */
	private void deactivateActionBtns() {
		actionBeforeBtn.setDisable(true);
		actionAfterBtn.setDisable(true);
	}
	
	/**
	 * Draws an alibi card and shows it for 10 seconds, gradually fading out.
	 * 
	 * @param card string representing the character card drawn.
	 */
	public void drawAlibiCard(String card) {
		int cardIndex = 0;
		while(!TokenConstants.CHAR_NAMES[cardIndex].equals(card)) cardIndex++;
		cardDrawn.setImage(CARD_IMGS[cardIndex]);
		FadeTransition ft = new FadeTransition(Duration.millis(15000), cardDrawn);
		ft.setFromValue(1.0);
		ft.setToValue(0.0);
		ft.play();
	}
	
	/**
	 * Ends the game with an image representing who won the game.
	 * 
	 * @param jackWon <code>true</code> if Jack won
	 */
	public void endGame(boolean jackWon) {
		winPane.setDisable(false);
		if(jackWon) winImg.setImage(WIN_IMGS[0]);
		else winImg.setImage(WIN_IMGS[1]);
	}
	
	/**
	 * Response to when the action before button is clicked.
	 */
	@FXML
	private void onClickActionBefore() {
		deactivateActionBtns();
		actionClicker.actionBeforeOnClick();
	}
	
	/**
	 * Response to when the action after button is clicked.
	 */
	@FXML
	private void onClickActionAfter() {
		deactivateActionBtns();
		actionClicker.actionAfterOnClick();
	}
	
	/**
	 * Response to when the first card on the table is clicked.
	 */
	@FXML
	private void onClickCard1() {
		cardClicker.cardClicked(0);
	}
	
	/**
	 * Response to when the second card on the table is clicked.
	 */
	@FXML
	private void onClickCard2() {
		cardClicker.cardClicked(1);
	}
	
	/**
	 * Response to when the third card on the table is clicked.
	 */
	@FXML
	private void onClickCard3() {
		cardClicker.cardClicked(2);
	}

	/**
	 * Response to when the fourth card on the table is clicked.
	 */
	@FXML
	private void onClickCard4() {
		cardClicker.cardClicked(3);
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
