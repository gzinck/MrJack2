package controller;

import java.util.Observable;
import java.util.Observer;

import model.WitnessCard;
import view.WitnessView;

/**
 * This updates the view for the witness card whenever the
 * witness card is updated in the model (that is, at the end
 * of every turn and round).
 * 
 * @author Graeme Zinck and Charles Jobin
 * @version 1.0
 *
 */
public class WitnessController implements Observer {
	/** The view for the witness card. */
	private WitnessView view;
	
	/**
	 * Instantiates the controller.
	 * 
	 * @param witnessView the view for the witness card
	 */
	public WitnessController(WitnessView witnessView) {
		view = witnessView;
	}

	@Override
	public void update(Observable o, Object arg) {
		WitnessCard w = (WitnessCard)o;
		view.updateWitnessed(w.getWitnessed());
	}

}
