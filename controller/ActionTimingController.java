package controller;

import controller.clickresponders.ActionBtnClickResponder;
import model.TurnKeeper;

public class ActionTimingController implements ActionBtnClickResponder {
	private TurnKeeper turnKeeper;
	private GameContinuer continuer;
	
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
