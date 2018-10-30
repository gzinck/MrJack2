package controller;

import model.gameboard.GameBoard;
import model.player.*;
import model.table.Table;
import model.tile.Exit;
import model.token.CharacterToken;
import model.turnkeeper.TurnKeeper;
import model.witnesscard.WitnessCard;
import view.GameView;
import view.board.BoardView;

public class GameController implements GameContinuer {
	private GameBoard gb;
	private MrJack jack;
	private Detective detective;
	private TurnKeeper turnKeeper;
	private Table table;
	private WitnessCard witness;
	
	private TileController tileController; // TODO: actually make this an instance variable
	private CharTokenController charTokenController;
	private TokenController tokenController;
	private TurnKeeperController turnController;
	private TableController tableController;
	private ActionTimingController actionController;
	private WitnessController witnessController;
	
	private BoardView boardView;
	private GameView gameView;
	
	private boolean gameIsOver;
	
	public GameController(BoardView inBoardView, GameView inGameView) {
		gameIsOver = false;
		// First, we need to instantiate everything.
		boardView = inBoardView;
		gameView = inGameView;
		
		gb = new GameBoard();
		jack = new MrJack();
		detective = new Detective();
		turnKeeper = new TurnKeeper(jack, detective, gb.getRemovableGaslights());
		table = new Table();
		
		String jackCard = table.getJackCard();
		gameView.drawAlibiCard(jackCard);
		
		CharacterToken jackChar = gb.getCharacter(jackCard);
		witness = new WitnessCard(jackChar);
		Exit.setWitnessCard(witness);
		
		jack.setCharacter(jackChar);
		
		tileController = new TileController(turnKeeper, boardView, this);
		boardView.setClickResponder(tileController);
		
		charTokenController = new CharTokenController(boardView);
		gb.addCharTokenObserver(charTokenController);
		
		tokenController = new TokenController(boardView);
		gb.addTokenObserver(tokenController);
		
		turnController = new TurnKeeperController(gameView);
		turnKeeper.addObserver(turnController);
		
		tableController = new TableController(gameView, table, turnKeeper, gb, this);
		table.addObserver(tableController);
		
		actionController = new ActionTimingController(turnKeeper, this);
		
		witnessController = new WitnessController(gameView);
		witness.addObserver(witnessController);
		
		gameView.initializeClickResponders(tableController, actionController);
		
		// Then start the turn
		turnKeeper.startGame();
		table.startRound();
	}
	public void continueGame() {
		if(gameIsOver) return;
		// This runs through a turn
		
		Player currentPlayer;
		
		// Go to next turn if the turn is over
		int turnStage = turnKeeper.nextStage();
		if(turnKeeper.turnOver()) {
			if(turnKeeper.roundOver()) {
				gb.evaluateInnocence(witness.updateWitnessed());
				table.startRound();
			}
			currentPlayer = turnKeeper.nextTurn();
			turnStage = turnKeeper.nextStage();
		}
		else currentPlayer = turnKeeper.getCurrPlayer();
		
		if(turnKeeper.gameOver()) {
			jackWins();
			return;
		}
		
		System.out.println(turnStage);
		switch(turnStage) {
		case TurnKeeper.STAGE_CHOOSE_CHAR:
			boardView.unhighlightTiles();
			break;
		case TurnKeeper.STAGE_CHOOSE_IFACTIONFIRST:
			gameView.activateActionBtns();
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
	@Override
	public void detectiveWins() {
		gameIsOver = true;
		gameView.endGame(false);
		System.out.println("DETECTIVE WINS");
	}
	@Override
	public void jackWins() {
		// Do something to indicate that Jack won.
		gameIsOver = true;
		gameView.endGame(true);
		System.out.println("JACK WINS");
	}
	@Override
	public boolean gameIsOver() {
		return gameIsOver;
	}
}
