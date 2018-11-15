package controller.clickresponders;

/**
 * This interface allows a view to register that an action
 * button was clicked without having a direct reference to
 * a controller. This is important for mediating the interactions
 * between the view and the model. In particular, this allows
 * the controllers to respond when the view registers a click
 * on the "Action Before" and "Action After" buttons, meaning
 * that the user wishes to perform an action either before
 * or after their move.
 * 
 * @author Graeme Zinck and Charles Jobin
 * @version 1.0
 *
 */
public interface ActionBtnClickResponder {
	/**
	 * Registers that the user clicked the "Action Before"
	 * button and wants to perform the action before moving.
	 */
	public void actionBeforeOnClick();
	
	/**
	 * Registers that the user clicked the "Action After"
	 * button and wants to perform the action after moving.
	 */
	public void actionAfterOnClick();
}
