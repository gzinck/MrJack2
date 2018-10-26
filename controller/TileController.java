package controller;
import java.util.Observable;
import java.util.Observer;

import model.ability.Ability;
import model.gameboard.TokenFinder;
import model.player.Player;
import model.token.*;
import model.turnkeeper.TurnKeeper;
import view.BoardView;
public class TileController implements Observer
{
	private Barricade barr;
	private GasLight gasLight;
	private ManholeCover manholeCover;
	private TurnKeeper turnKeeper;
	private TokenMover tokenMover;
	private CharTokenMover charMover;
	
	// VIEW items
	private BoardView boardView;
	
	// CONTROLLER items
	private GameContinuer gameContinuer;
	
	public TileController(Barricade inBarr, GasLight inGasLight, ManholeCover inManholeCover, TurnKeeper inTurnKeeper, BoardView inBoardView, GameContinuer inGameContinuer)
	{
		barr = inBarr;
		gasLight = inGasLight;
		manholeCover = inManholeCover;
		turnKeeper = inTurnKeeper;
		boardView = inBoardView;
		gameContinuer = inGameContinuer;
		
		tokenMover = new TokenMover();
		charMover = new CharTokenMover();
		
		barr.addObserver(this);
		gasLight.addObserver(this);
		manholeCover.addObserver(this);
		
	}
	
	@Override
	public void update(Observable arg0, Object arg1) 
	{
		
		
	}
	public void showCharMoveOptions(CharacterToken character, Player currPlayer, TokenFinder tokenFinder) {
		int[][] options = charMover.getTileOptions(character, currPlayer, tokenFinder);
		boardView.highlightTiles(options);
	}
	public void showActionOptions(Ability a) {
		int[][] options = tokenMover.getTokenOptions(a);
		boardView.highlightTiles(options);
	}
	public void tileClicked(int row, int col) {
		int turnStage = turnKeeper.getStage();
		switch(turnStage) {
//		The below two cases have nothing to do with tiles
//		case TurnKeeper.STAGE_CHOOSE_CHAR:
//			break;
//		case TurnKeeper.STAGE_CHOOSE_IFACTIONFIRST:
//			break;
//		Do this before moving to the action time...
//		int[][] options = tokenMover.getTokenOptions(turnKeeper.getCurrCharacter().getAbility());
//		boardView.highlightTiles(options);
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
			if(foundExit) {
				boardView.unhighlightTiles();
				gameContinuer.jackWins();
			} else {
				gameContinuer.continueGame();
			}
		}
	}
}
