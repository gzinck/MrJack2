package controller;

import java.util.Observable;
import java.util.Observer;

import controller.clickresponders.CardClickResponder;
import model.Table;
import model.TurnKeeper;
import model.gameboard.CharacterFinder;
import view.CardView;

/**
 * This updates the table in the model as cards get chosen from the view,
 * and continues the game if the choice was valid. It also updates the
 * view if it was successful.
 * 
 * @author Graeme Zinck and Charles Jobin
 * @version 1.0
 *
 */
public class TableController implements Observer, CardClickResponder {
	/** The view for the cards on the table. */
	private CardView view;
	/** The table to ask if a card exists. */
	private Table table;
	/** The turn keeper to ask if it is the right stage to pick a card. */
	private TurnKeeper turnKeeper;
	/** The finder that finds a character, if possible. */
	private CharacterFinder finder;
	/** The controller that allows the game to continue after choosing a card. */
	GameContinuer continuer;
	
	/**
	 * Creates a new table controller.
	 * 
	 * @param cardView the view for the cards
	 * @param t the table in the model
	 * @param inTurnKeeper the turn keeper in the model
	 * @param charFinder the finder for character tokens
	 * @param gameContinuer the controller that continues the game
	 */
	public TableController(CardView cardView, Table t, TurnKeeper inTurnKeeper, CharacterFinder charFinder, GameContinuer gameContinuer) {
		view = cardView;
		table = t;
		turnKeeper = inTurnKeeper;
		finder = charFinder;
		continuer = gameContinuer;
	}

	@Override
	public void update(Observable o, Object arg) {
		Table t = (Table)o;
		String[] cards = t.getCards();
		for(int i = 0; i < cards.length; i++) {
			view.placeCard(cards[i], i);
		}
	}

	@Override
	public void cardClicked(int cardIndex) {
		if(turnKeeper.getStage() == TurnKeeper.STAGE_CHOOSE_CHAR) {
			String character = table.selectCharacter(cardIndex);
			if(character == null) return;
			
			turnKeeper.setCurrCharacter(finder.getCharacter(character));
			continuer.continueGame();
		} else if(turnKeeper.getStage() == TurnKeeper.STAGE_INIT_CHOOSE_CARD) {
			String character = table.selectCharacter(cardIndex);
			if(character == null) return;
			turnKeeper.setCurrCharacter(finder.getCharacter(character));
			continuer.continueGame();
		}
	}
}
