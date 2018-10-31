package controller;

import java.util.Observable;
import java.util.Observer;

import model.TurnKeeper;
import view.TurnKeeperView;

/**
 * This updates the view for the turn keeper whenever
 * the turn/round gets updated.
 * 
 * @author Graeme Zinck and Charles Jobin
 *
 */
public class TurnKeeperController implements Observer {
	
	/** The view for the turn keeper. */
	private TurnKeeperView view;
	
	/**
	 * Instantiates the controller.
	 * 
	 * @param turnView the view for the turn keeper, which
	 * will be updated whenever the model changes.
	 */
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
