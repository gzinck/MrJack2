package controller;

import java.util.Observable;
import java.util.Observer;

import controller.clickresponders.CardClickResponder;
import model.gameboard.CharacterFinder;
import model.table.Table;
import model.turnkeeper.TurnKeeper;
import view.CardView;

public class TableController implements Observer, CardClickResponder {
	
	private CardView view;
	private Table table;
	private TurnKeeper turnKeeper;
	private CharacterFinder finder;
	GameContinuer continuer;
	
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
		if(turnKeeper.getStage() != TurnKeeper.STAGE_CHOOSE_CHAR) return;
		
		String character = table.selectCharacter(cardIndex);
		if(character == null) return;
		
		turnKeeper.setCurrCharacter(finder.getCharacter(character));
		continuer.continueGame();
	}
}
