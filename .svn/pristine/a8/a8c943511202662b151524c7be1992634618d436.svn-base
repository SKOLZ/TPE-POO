package backEnd;

/**
 * @author Patricio Reller, German Romarion, Gabriel Zanzotti
 * 
 *         Match class has an instance variable that has a reference to an
 *         object that implements this interface, so, when the Match is over,
 *         the class sends a message to that instance variable in order to pop
 *         up a window that notifies the user that the game is over.
 */
public interface Events {

	/**
	 * This method should be called from the Match class when all the boxes are
	 * placed on their respective destinations, and there aren't any more boxes
	 * left.
	 */
	public void onWin();

	/**
	 * This method should be called from the Match class when the Hero falls
	 * into a Hole object.
	 */
	public void onLose();
}
