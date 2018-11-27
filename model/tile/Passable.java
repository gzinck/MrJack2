package model.tile;

import java.util.HashSet;

import model.player.Player;
import model.token.CharacterToken;

/**
 * This interface is for tiles which can be passed through by a
 * Character Token, but not necessarily occupied by. Example:
 * a Building, which can be passed through by Stealthy.
 * 
 * @author Graeme Zinck and Charles Jobin
 * @version 1.0
 *
 */
public interface Passable extends VisibleTile {
	/**
	 * Sets a neighbour of the tile in the direction given.
	 * 
	 * @param t a passable tile
	 * @param direction the direction of the neighbour (from 0 to 5 inclusive)
	 */
	public void setNeighbour(Passable t, int direction);
	/**
	 * Gets all the accessible tiles from this tile given the character,
	 * number of moves, and the player. If the player is Jack, exits are
	 * accessible. If the character is Stealthy, buildings can be passed
	 * through. This is called recursively.
	 *
	 * @param minMoves the minimum number of move for the character from this tile
	 * @param maxMoves the maximum number of moves for the character from this tile
	 * @param character the character which is being moved
	 * @param player the player who is moving the characters
	 * @return a hashset of passable tiles which are accessible
	 */
	public abstract HashSet<Passable> getAccessibleTiles(int minMoves, int maxMoves, CharacterToken character, Player player);
}
