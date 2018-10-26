package controller;

import java.util.Observable;
import java.util.Observer;

import model.token.CharacterToken;

public class CharTokenController implements Observer{

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		CharacterToken c = (CharacterToken)o;
		boolean isInnocent = c.isInnocent();
		
	}

}
