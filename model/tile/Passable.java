package model.tile;

import java.util.HashSet;

import model.player.Player;
import model.token.CharacterToken;

public interface Passable extends VisibleTile {
	public void setNeighbour(Passable t, int direction);
	public abstract HashSet<Passable> getAccessibleTiles(int numMoves, CharacterToken character, Player player);
}
