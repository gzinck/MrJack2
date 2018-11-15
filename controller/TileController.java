package controller;

import controller.clickresponders.TileClickResponder;
import model.TurnKeeper;
import model.ability.Ability;
import model.gameboard.CharTokenFinder;
import model.player.Player;
import model.token.*;
import view.board.BoardView;

/**
 * This responds to clicks on a tile in the view. It is
 * not a trivial case, because it needs to check what, exactly,
 * needs to be chosen (is it a character? A manhole? A tile to
 * move the token?) by first checking the stage, and then checking
 * the ability if it's the abiliy stage.
 * 
 * @author Graeme Zinck and Charles Jobin
 * @versin 1.0
 *
 */
public class TileController implements TileClickResponder {
	/** The turn keeper in the model which has the information on the stage. */
	private TurnKeeper turnKeeper;
	/** The class that stores information regarding how to move a token. */
	private TokenMover tokenMover;
	/** The class that stores information regarding how to move a character. */
	private CharTokenMover charMover;

	/** The view for the board. */
	private BoardView boardView;
	
	/** The controller that continues the game to the next stage. */
	private GameContinuer gameContinuer;
	
	/**
	 * Creates a new tile controller to allow moving
	 * tiles around in the model using clicks in the view.
	 * 
	 * @param inTurnKeeper the turn keeper in the model
	 * @param inBoardView the board view
	 * @param inGameContinuer the controller that continues the game
	 */
	public TileController(TurnKeeper inTurnKeeper, BoardView inBoardView, GameContinuer inGameContinuer)
	{
		turnKeeper = inTurnKeeper;
		boardView = inBoardView;
		gameContinuer = inGameContinuer;
		
		tokenMover = new TokenMover();
		charMover = new CharTokenMover();
	}
	
	/**
	 * Shows the move options for a character by highlighting
	 * all the options in the view.
	 * 
	 * @param character the character who can move
	 * @param currPlayer the player moving the character
	 * @param tokenFinder the finder for tokens
	 */
	public void showCharMoveOptions(CharacterToken character, Player currPlayer, CharTokenFinder tokenFinder) {
		int[][] options = charMover.getTileOptions(character, currPlayer, tokenFinder);
		boardView.highlightTiles(options);
	}
	
	/**
	 * Shows all the options for tokens that the character can choose
	 * to move during their action. It then highlights all these
	 * options in the view.
	 * 
	 * @param a the ability of the character performing the ability
	 */
	public void showActionOptions(Ability a) {
		int[][] options = tokenMover.getTokenOptions(a);
		boardView.highlightTiles(options);
	}
	@Override
	public void tileClicked(int row, int col) {
		if(gameContinuer.gameIsOver()) return;
		
		int turnStage = turnKeeper.getStage();
		switch(turnStage) {
		case TurnKeeper.STAGE_CHOOSE_ACTIONMOVEBEFORE:
			continueChoosingAction(row, col);
			break;
		case TurnKeeper.STAGE_CHOOSE_CHARMOVE:
			continueChoosingCharacterMove(row, col);
			break;
		case TurnKeeper.STAGE_CHOOSE_ACTIONMOVEAFTER:
			continueChoosingAction(row, col);
			break;
		default:
			break;
		}
	}
	
	/**
	 * Continues to choose an action, based on what token has been
	 * clicked. If the token clicked was acceptable, then it shows
	 * all the possible tiles that that token can move to.
	 * <p>
	 * If the token was already chosen and stored in the token mover,
	 * then it will instead check if the tile selected was valid. If
	 * so, then it performs the move and continues the game.
	 * 
	 * @param row the row of the tile clicked
	 * @param col the column of the tile clicked
	 */
	private void continueChoosingAction(int row, int col) {
		if(!tokenMover.tokenSelected()) {
			boolean success = tokenMover.selectToken(new int[]{row, col});
			if(success) {
				// Then highlight all the new places that can be clicked...
				int[][] tileOptions = tokenMover.getTileOptions();
				boardView.highlightTiles(tileOptions);
			}
		} else if(!tokenMover.tileSelected()) {
			boolean success = tokenMover.selectTile(new int[] {row, col});
			if(success) {
				// Then, we just perform the ability and move to next stage!
				tokenMover.performMove();
				boardView.unhighlightTiles();
				gameContinuer.continueGame();
			}
		}
	}
	
	/**
	 * Continues to choose a character's move. If the tile selected
	 * is valid, then it performs the move. It then checks end-game
	 * conditions for moves (including reaching an exit and finding
	 * Mr. Jack).
	 * 
	 * @param row the row of the tile clicked
	 * @param col the column of the tile clicked
	 */
	private void continueChoosingCharacterMove(int row, int col) {
		boolean success = charMover.selectTile(new int[] {row, col});
		if(success) {
			boolean foundExit = charMover.performMove();
			boardView.unhighlightTiles();
			if(foundExit) {
				gameContinuer.jackWins();
			} else {
				if(charMover.wasCollision()) {
					if(charMover.wasCollisionWithJack())
						gameContinuer.detectiveWins();
					else
						gameContinuer.jackWins();
				}
			}
			gameContinuer.continueGame();
		}
	}
}
