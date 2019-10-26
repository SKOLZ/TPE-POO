package backEnd;

/**
 * @author Patricio Reller, German Romarion, Gabriel Zanzotti
 * 
 *         All the objects that could fall into a Hole should implement this
 *         interface.
 */
public interface Fallable {

	/**
	 * This method should be called from the Hole class when a 'Fallable' object
	 * steps into it.
	 */
	void fall();
}
