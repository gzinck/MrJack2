package controller;

import java.util.Observable;
import java.util.Observer;

import model.witnesscard.WitnessCard;
import view.WitnessView;

public class WitnessController implements Observer {
	
	private WitnessView view;
	
	public WitnessController(WitnessView witnessView) {
		view = witnessView;
	}

	@Override
	public void update(Observable o, Object arg) {
		WitnessCard w = (WitnessCard)o;
		view.updateWitnessed(w.getWitnessed());
	}

}
