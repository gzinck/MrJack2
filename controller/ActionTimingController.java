package controller;

import controller.clickresponders.ActionBtnClickResponder;
import model.TurnKeeper;

/**
 * This controller has methods called by the view when action
 * buttons are clicked. Whenever the user clicks an action button,
 * the model's <code>TurnKeeper</code> is updated and the turn's
 * stage goes to the next stage.
 * 
 * @author Graeme Zinck and Charles Jobin
 *
 */
public class ActionTimingController implements ActionBtnClickResponder {
	/** The TurnKeeper in the model which needs to be updated. */
	private TurnKeeper turnKeeper;
	/**
	 * An interface to the controller that allows continuing the game
	 * to the next stage.
	 */
	private GameContinuer continuer;
	
	/**
	 * Creates a new controller which responds to the action
	 * buttons in the view.
	 * 
	 * @param inTurnKeeper the turn keeper from the model
	 * @param gameContinuer the controller that progresses to the
	 * next stage of the game
	 */
	public ActionTimingController(TurnKeeper inTurnKeeper, GameContinuer gameContinuer) {
		turnKeeper = inTurnKeeper;
		continuer = gameContinuer;
	}

	@Override
	public void actionBeforeOnClick() {
		turnKeeper.setActionTiming(TurnKeeper.StageTiming.ACTION_BEFORE);
		continuer.continueGame();
	}

	@Override
	public void actionAfterOnClick() {
		turnKeeper.setActionTiming(TurnKeeper.StageTiming.ACTION_AFTER);
		continuer.continueGame();
	}
	
	
}
