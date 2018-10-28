package controller;

import java.util.Observable;
import java.util.Observer;

import model.token.Token;
import view.board.TokenViewModifier;

public class TokenController implements Observer{
	
	private TokenViewModifier view;
	
	public TokenController(TokenViewModifier viewMod) {
		view = viewMod;
	}

	@Override
	public void update(Observable o, Object arg) {
		Token t = (Token)o;
		view.moveToken(t.getTokenType(), t.getPrevTokenLocation(), t.getTokenLocation());
	}

}
