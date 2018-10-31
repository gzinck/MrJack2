package controller;

import java.util.Observable;
import java.util.Observer;

import model.token.Token;
import view.board.TokenViewModifier;

/**
 * This observes all tokens and updates their corresponding view
 * whenever they change (that is, when they are moved).
 * 
 * @author Graeme Zinck and Charles Jobin
 * @version 1.0
 *
 */
public class TokenController implements Observer{
	/** The view for the tokens. */
	private TokenViewModifier view;
	
	/**
	 * Creates a new token controller that updates the view
	 * based on the model's changes.
	 * 
	 * @param viewMod the view's token modifier interface
	 */
	public TokenController(TokenViewModifier viewMod) {
		view = viewMod;
	}

	@Override
	public void update(Observable o, Object arg) {
		Token t = (Token)o;
		view.moveToken(t.getTokenType(), t.getPrevTokenLocation(), t.getTokenLocation());
	}

}
