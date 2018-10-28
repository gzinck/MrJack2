package model.gameboard;

import model.token.CharacterToken;

public interface CharacterFinder {
	public CharacterToken getCharacter(String characterName);
}
