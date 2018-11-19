package model.ability;

import model.gameboard.TokenFinder;
import model.token.TokenMover;

public abstract class MoveTokenAbility implements Ability {
	
	/** The finder which can find tokens on the game baord. */
	protected TokenFinder tokenFinder;
	/** The mover that moves the barricade tokens. */
	protected TokenMover tokenMover;
	
	/**
	 * Constructs a MoveTokenAbility
	 * @param finder TokenFinder that is used to find the tokens on the gameboard
	 */
	public MoveTokenAbility(TokenFinder finder) {
		tokenFinder = finder;
		tokenMover = new TokenMover();
	}
	
	@Override
	public int[][] startAction() {
		int[][] options = getAbilityTokenOptions();
		tokenMover.setTokenOptions(options);
		return options;
	}
	
	@Override
	public int[][] continueAction(int[] tileClickLoc) {
		if(!tokenMover.tokenSelected()) {
			boolean success = tokenMover.selectToken(tileClickLoc);
			if(success) {
				// Then highlight all the new places that can be clicked...
				int[][] tileOptions = getAbilityTileOptions();
				tokenMover.setTileOptions(tileOptions);
				return tileOptions;
			}
		} else if(!tokenMover.tileSelected()) {
			boolean success = tokenMover.selectTile(tileClickLoc);
			if(success) {
				// Then, we just perform the ability and move to next stage!
				performAbility(tokenMover.getSelectedToken(), tokenMover.getSelectedTile());
				tokenMover.finishMove();
				return new int[0][0];
			}
		}
		return null;
	}
	
	/**
	 * Performs an ability given the tile location that was clicked.
	 * 
	 * @param tileLocation Integer 2-tuple representing the (row, col)
	 * of the tile to perform the ability 
	 */
	public abstract void performAbility(int[] tokenLocation, int[] tileLocation);
	
	/**
	 * Gets all possible locations of tokens that the player can
	 * click in order to perform the ability.
	 * 
	 * @return Array of 2-tuples of integers representing the (row, col)
	 * of the possible tokens that can be clicked.
	 */
	public abstract int[][] getAbilityTokenOptions();
	
	/**
	 * Gets all possible locations of tiles that the player can click
	 * in order to perform the ability.
	 * 
	 * @return Array of 2-tuples of integers representing the (row, col)
	 * of the possible tiles that can be clicked.
	 */
	public abstract int[][] getAbilityTileOptions();
}
