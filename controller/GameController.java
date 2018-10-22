package controller;

import model.gameboard.GameBoard;
import model.player.*;
import model.table.Table;
import model.turnkeeper.TurnKeeper;

public class GameController {
	private GameBoard gb;
	private MrJack jack;
	private Detective detective;
	private TurnKeeper turnKeeper;
	private Table table;
	
	public void initializeGame()  {
		// First, we need to instantiate everything.
		gb = new GameBoard();
		jack = new MrJack();
		detective = new Detective();
		turnKeeper = new TurnKeeper(jack, detective, gb.getRemovableGaslights());
		table = new Table();
		jack.setCharacter(gb.getCharacter(table.getJackCard()));
		table.startRound();
		continueGame();
	}
	public void continueGame() {
		// This runs through a turn
		if(turnKeeper.roundOver()) {
			table.startRound();
		}
		Player currentPlayer;
		if(turnKeeper.roundOver())
			currentPlayer = turnKeeper.nextTurn();
		else
			currentPlayer = turnKeeper.getCurrPlayer();
		int turnStage = turnKeeper.nextStage();
		switch(turnStage) {
		case TurnKeeper.STAGE_CHOOSE_CHAR:
			break;
		case TurnKeeper.STAGE_CHOOSE_IFACTIONFIRST:
			// TurnKeeper.setActionTiming(enum);
			break;
		case TurnKeeper.STAGE_CHOOSE_ACTIONMOVEBEFORE:
			break;
		case TurnKeeper.STAGE_CHOOSE_CHARMOVE:
			break;
		case TurnKeeper.STAGE_CHOOSE_ACTIONMOVEAFTER:
			break;
		default:
			break;
		}
	}
	public boolean gameIsOver() {
		return false;
	}
}
