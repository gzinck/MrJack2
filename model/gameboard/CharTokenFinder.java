package model.gameboard;

import model.token.CharacterToken;

public interface CharTokenFinder extends TokenFinder {
	public boolean characterCollision(CharacterToken recentlyMoved);
	public boolean collisionWithJack(CharacterToken recentlyMoved);
}
