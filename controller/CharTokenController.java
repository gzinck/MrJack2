package controller;

import java.util.Observable;
import java.util.Observer;

import model.token.CharacterToken;
import view.board.CharTokenViewModifier;

public class CharTokenController implements Observer{
	
	private CharTokenViewModifier view;
	
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
