package controller;

import java.util.Observable;
import java.util.Observer;

import model.table.Table;
import view.CardView;

public class TableController implements Observer {
	
	private CardView view;
	
	public TableController(CardView cardView) {
		view = cardView;
	}

	@Override
	public void update(Observable o, Object arg) {
		Table t = (Table)o;
		String[] cards = t.getCards();
		for(int i = 0; i < cards.length; i++) {
			view.placeCard(cards[i], i);
		}
	}
}
