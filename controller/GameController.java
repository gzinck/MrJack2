package controller;

import model.gameboard.GameBoard;
import model.player.*;
import model.table.Table;
import model.turnkeeper.TurnKeeper;
import view.board.BoardView;

public class GameController implements GameContinuer {
	private GameBoard gb;
	private MrJack jack;
	private Detective detective;
	private TurnKeeper turnKeeper;
	private Table table;
	private TileController tileController; // TODO: actually make this an instance variable
	private CharTokenController charTokenController;
	private TokenController tokenController;
	private BoardView boardView;
	
	public GameController(BoardView inBoardView) {
		// First, we need to instantiate everything.
		boardView = inBoardView;
		gb = new GameBoard();
		jack = new MrJack();
		detective = new Detective();
		turnKeeper = new TurnKeeper(jack, detective, gb.getRemovableGaslights());
		table = new Table();
		
		tileController = new TileController(turnKeeper, boardView, this);
		
		charTokenController = new CharTokenController(boardView);
		gb.addCharTokenObserver(charTokenController);
		
		tokenController = new TokenController(boardView);
		gb.addTokenObserver(tokenController);
		
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
		
		// Go to next turn if the turn is over
		if(turnKeeper.turnOver()) currentPlayer = turnKeeper.nextTurn();
		else currentPlayer = turnKeeper.getCurrPlayer();
		
		int turnStage = turnKeeper.nextStage();
		switch(turnStage) {
		case TurnKeeper.STAGE_CHOOSE_CHAR:
			break;
		case TurnKeeper.STAGE_CHOOSE_IFACTIONFIRST:
			// TurnKeeper.setActionTiming(enum);
			break;
		case TurnKeeper.STAGE_CHOOSE_ACTIONMOVEBEFORE:
			tileController.showActionOptions(turnKeeper.getCurrCharacter().getAbility());
			break;
		case TurnKeeper.STAGE_CHOOSE_CHARMOVE:
			tileController.showCharMoveOptions(turnKeeper.getCurrCharacter(), currentPlayer, gb);
			break;
		case TurnKeeper.STAGE_CHOOSE_ACTIONMOVEAFTER:
			tileController.showActionOptions(turnKeeper.getCurrCharacter().getAbility());
			break;
		default:
			break;
		}
	}
	public void jackWins() {
		// Do something to indicate that Jack won.
	}
	public boolean gameIsOver() {
		return false;
	}
}
