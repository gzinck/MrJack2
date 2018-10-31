package controller;

import model.Table;
import model.TurnKeeper;
import model.WitnessCard;
import model.gameboard.GameBoard;
import model.player.*;
import model.tile.Exit;
import model.token.CharacterToken;
import view.GameView;
import view.board.BoardView;

/**
 * This instantiates most things, including all the controllers and
 * much of the model. This is also the class which allows progression
 * of the game forward.
 * 
 * @author Graeme Zinck and Charles Jobin
 *
 */
public class GameController implements GameContinuer {
	/** The game board in the model. */
	private GameBoard gb;
	/** The player Mr Jack in the model. */
	private MrJack jack;
	/** The player Detective in the model. */
	private Detective detective;
	/** The class keeping track of the turn/stage/round in the model. */
	private TurnKeeper turnKeeper;
	/** The table with cards laid out in the model.*/
	private Table table;
	/** The witness card in the model. */
	private WitnessCard witness;
	
	/** The controller responding to clicks on tiles. */
	private TileController tileController;
	/** The controller responding to movement of character tokens. */
	private CharTokenController charTokenController;
	/** The controller responding to movement of regular tokens. */
	private TokenController tokenController;
	/** The controller responding to changes in the turn keeper's turn/round. */
	private TurnKeeperController turnController;
	/** The controller responding to clicks on cards on the table. */
	private TableController tableController;
	/** The controller responding to clicks on the action buttons in the GUI. */
	private ActionTimingController actionController;
	/** The controller that updates the view when the witness card changes. */
	private WitnessController witnessController;
	
	/** The view of the board. */
	private BoardView boardView;
	/** The view with the entire game window. */
	private GameView gameView;
	
	/** Whether the game has already been won by a player. */
	private boolean gameIsOver;
	
	/**
	 * Instantiates most things in the model, as well as all
	 * the other controllers. This is ugly, but it only has to
	 * happen once.
	 * 
	 * @param inBoardView the view of the board
	 * @param inGameView the view of the entire game
	 */
	public GameController(BoardView inBoardView, GameView inGameView) {
		gameIsOver = false;
		boardView = inBoardView;
		gameView = inGameView;
		
		// First, we need to instantiate everything in the model
		gb = new GameBoard();
		jack = new MrJack();
		detective = new Detective();
		turnKeeper = new TurnKeeper(jack, detective, gb.getRemovableGaslights());
		table = new Table();
		
		// Get Jack's card and pass it around
		String jackCard = table.getJackCard();
		gameView.drawAlibiCard(jackCard);
		CharacterToken jackChar = gb.getCharacter(jackCard);
		witness = new WitnessCard(jackChar);
		Exit.setWitnessCard(witness);
		jack.setCharacter(jackChar);
		
		// Set up the controller for tiles
		tileController = new TileController(turnKeeper, boardView, this);
		boardView.setClickResponder(tileController);
		
		// Set up the controller for character tokens
		charTokenController = new CharTokenController(boardView);
		gb.addCharTokenObserver(charTokenController);
		
		// Set up the controller for regular tokens
		tokenController = new TokenController(boardView);
		gb.addTokenObserver(tokenController);
		
		// Set up the controller for the turn keeper
		turnController = new TurnKeeperController(gameView);
		turnKeeper.addObserver(turnController);
		
		// Set up the controller for the table
		tableController = new TableController(gameView, table, turnKeeper, gb, this);
		table.addObserver(tableController);
		
		// Set up the controller for the action buttons
		actionController = new ActionTimingController(turnKeeper, this);
		
		// Set up the controller for the witness card
		witnessController = new WitnessController(gameView);
		witness.addObserver(witnessController);
		
		// Set up the click responders in the view
		gameView.initializeClickResponders(tableController, actionController);
		
		// Then start the turn
		turnKeeper.startGame();
		table.startRound();
	}
	
	@Override
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
		} else {
			currentPlayer = turnKeeper.getCurrPlayer();
		}
		
		// If the game is over, jack has won
		if(turnKeeper.gameOver()) {
			jackWins();
			return;
		}
		
		// Figure out what to do, given what the current stage is
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
	}
	@Override
	public void jackWins() {
		gameIsOver = true;
		gameView.endGame(true);
	}
	@Override
	public boolean gameIsOver() {
		return gameIsOver;
	}
}
