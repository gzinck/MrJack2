package model.player;

import model.token.CharacterToken;

public class MrJack extends Player
{
	public static final String PLAYER_NAME = "MrJack";
	CharacterToken jackChar;
	
	public void setCharacter(CharacterToken character) {
		jackChar = character;
		jackChar.selectMrJack();
	}
	
	@Override
	public String getPlayerName() {
		return PLAYER_NAME;
	}
	
	@Override
	public boolean canExitBoard(CharacterToken character) {
		if(character.equals(jackChar)) {
			return true;
		}
		return false;
	}
}
