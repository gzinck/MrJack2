package model.token;
import java.util.HashSet;

import model.ability.MoveReducerAbility;
import model.gameboard.CharTokenFinder;
import model.player.Player;
import model.tile.Lightable;
import model.tile.Passable;

/**
 * This moves a character token to a new location.
 * It stores all the intermediary data on what is allowed,
 * what the user has actually selected so far, and what still
 * remains to be selected before the move can actually occur.
 * 
 * @author Graeme Zinck and Charles Jobin
 * @version 1.0
 *
 */
public class CharTokenMover {
	/** All of the (row, col) of the tiles that are possible for the move. */
	private int[][] tileLocationOptions;
	/** The (row, col) for the tile selected to move to. */ 
	private int[] selectedTile;
	/** The token of the character which is going to be moved. */
	private CharacterToken character;
	/** The finder which can find character tokens upon request. */
	private CharTokenFinder finder;
	
	public int[][] getInitialTileOptions(CharacterToken inCharacter, Player currPlayer, CharTokenFinder tokenFinder) {
		character = inCharacter;
		finder = tokenFinder;
		tileLocationOptions = finder.getAllUnoccupiedLocations();
		return tileLocationOptions;
	}
	
	/**
	 * This gets the possible options for where a character can move.
	 * 
	 * @param inCharacter the character to move
	 * @param currPlayer the player moving the character
	 * @param tokenFinder the token finder
	 * @return an array of (row, col) locations that are possible
	 */
	public int[][] getTileOptions(CharacterToken inCharacter, Player currPlayer, CharTokenFinder tokenFinder) {
		return getTileOptions(inCharacter, currPlayer, tokenFinder, 0, Integer.MAX_VALUE);
	}
	
	public int[][] getTileOptions(CharacterToken inCharacter, Player currPlayer, CharTokenFinder tokenFinder, int minNumMoves, int maxNumMoves) {
		character = inCharacter;
		finder = tokenFinder;
		HashSet<Passable> tiles = null;
		
		boolean isNextToMoveReducer = false;
		for(CharacterToken c : finder.getCharacters()) {
			if(c.hasAbility(MoveReducerAbility.ABILITY)) {
				int[] currLoc = character.getTokenLocation();
				int[] detective = c.getTokenLocation();
				for(int i = 0; i < 6; i++) {
					int[] possible = finder.getLocation(currLoc[0], currLoc[1], i);
					if(possible != null && detective[0] == possible[0] && detective[1] == possible[1])
						isNextToMoveReducer = true;
				}
			}
		}
		
		if(isNextToMoveReducer) maxNumMoves = 1;
		while(tiles == null || tiles.size() == 0)
			tiles = character.getAccessibleTiles(currPlayer, minNumMoves--, maxNumMoves);
		
		tileLocationOptions = new int[tiles.size()][];
		int i = 0;
		for(Passable tile : tiles) {
			tileLocationOptions[i++] = tile.getTileLocation();
		}
		return tileLocationOptions;
	}
	/**
	 * Selects a tile at a given location (row, col).
	 * 
	 * @param location (row, col) of the tile selected
	 * @return <code>true</code> if the tile was legal and
	 * if it was therefore accepted.
	 */
	public boolean selectTile(int[] location) {
		// Must see if it exists in the tokenLocationOptions
		for(int i = 0; i < tileLocationOptions.length; i++) {
			boolean isEqual = true;
			for(int j = 0 ; j < location.length; j++) if(tileLocationOptions[i][j] != location[j]) isEqual = false;
			if(isEqual) {
				selectedTile = location;
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Performs the move, but if it cannot find the tile, returns true.
	 * It will only do this when it has reached an exit!
	 * 
	 * @return True if the move reached an exit
	 */
	public boolean performMove() {
		Lightable tile = finder.getTile(selectedTile);
		if(tile == null) return true;
		character.moveTo(tile);
		return false;
	}
	
	public void clear() {
		character = null;
		tileLocationOptions = null;
		selectedTile = null;
	}
	
	/**
	 * Checks if there was a collision with another character.
	 * 
	 * @return <code>true</code> if there was a collision.
	 */
	public boolean wasCollision() {
		return finder.characterCollision(character);
	}
	
	/**
	 * Checks if there was a collision with Mr. Jack.
	 * 
	 * @return <code>true</code> if there was a collision with
	 * Mr. Jack.
	 */
	public boolean wasCollisionWithJack() {
		return finder.collisionWithJack(character);
	}
	
	public boolean characterSelected() {
		return (character != null);
	}
	
	public CharacterToken getCharacter() {
		return character;
	}
	
	/**
	 * Checks if there was a tile selected.
	 * 
	 * @return <code>true</code> if there was a tile selected
	 */
	public boolean tileSelected() {
		return (selectedTile != null);
	}
}
