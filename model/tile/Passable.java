package model.tile;

import java.util.HashSet;

import model.player.Player;
import model.token.CharacterToken;

public interface Passable {
	public abstract HashSet<Passable> getAccessibleTiles(int numMoves, CharacterToken character, Player player);
}
