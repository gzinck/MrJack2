package controller;

import java.util.Observable;
import java.util.Observer;

import model.token.CharacterToken;
import view.board.CharTokenViewModifier;

/**
 * This updates the view whenever a character token is moved
 * (or if it changes to not be innocent).
 * 
 * @author Graeme Zinck and Charles Jobin
 *
 */
public class CharTokenController implements Observer{
	/** The view for the character token */
	private CharTokenViewModifier view;
	
	/** 
	 * Creates a new character token controller that
	 * observes the model and updates the view.
	 * 
	 * @param viewMod the view interface for updating the
	 * character tokens
	 */
	public CharTokenController(CharTokenViewModifier viewMod) {
		view = viewMod;
	}

	@Override
	public void update(Observable o, Object arg) {
		CharacterToken c = (CharacterToken)o;
		view.moveCharacter(c.getTokenType(), c.getPrevTokenLocation(), c.getTokenLocation());
		view.setInnocence(c.getTokenLocation(), c.isInnocent());
	}

}
