package view;

/**
 * This interface allows updating the witness card easily.
 * 
 * @author Graeme Zinck and Josh Cookson
 * @version 1.2
 */
public interface WitnessView {
	/**
	 * This updates the state of the witness card.
	 * 
	 * @param wasWitnessed if Jack was witnessed
	 */
	public void updateWitnessed(boolean wasWitnessed);
}
