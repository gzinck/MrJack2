package controller;

import controller.clickresponders.TileClickResponder;
import model.TurnKeeper;
import model.ability.Ability;
import model.gameboard.CharTokenFinder;
import model.player.Player;
import model.token.*;
import view.board.BoardView;

public class TileController implements TileClickResponder {
	private TurnKeeper turnKeeper;
	private TokenMover tokenMover;
	private CharTokenMover charMover;
	
	// VIEW items
	private BoardView boardView;
	
	// CONTROLLER items
	private GameContinuer gameContinuer;
	
	public TileController(TurnKeeper inTurnKeeper, BoardView inBoardView, GameContinuer inGameContinuer)
	{
		turnKeeper = inTurnKeeper;
		boardView = inBoardView;
		gameContinuer = inGameContinuer;
		
		tokenMover = new TokenMover();
		charMover = new CharTokenMover();
	}
	
	public void showCharMoveOptions(CharacterToken character, Player currPlayer, CharTokenFinder tokenFinder) {
		int[][] options = charMover.getTileOptions(character, currPlayer, tokenFinder);
		boardView.highlightTiles(options);
	}
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
