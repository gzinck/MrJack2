package controller;

import java.util.Observable;
import java.util.Observer;

import model.turnkeeper.TurnKeeper;
import view.TurnKeeperView;

public class TurnKeeperController implements Observer {
	
	private TurnKeeperView view;
	
	public TurnKeeperController(TurnKeeperView turnView) {
		view = turnView;
	}

	@Override
	public void update(Observable o, Object arg) {
		TurnKeeper t = (TurnKeeper)o;
		view.updatePlayer(t.getCurrPlayer().getPlayerName());
		view.updateRound(t.getRound());
		view.updateTurn(t.getTurn());
	}
}
