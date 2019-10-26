package backEnd;

import java.awt.Image;
import java.io.Serializable;
import java.util.HashMap;

import frontEnd.BoardPanel;
import frontEnd.ImgSeter;

/**
 * @author Patricio Reller, German Romarion, Gabriel Zanzotti
 * 
 *         The Empty class represents the empty space occupied in a Floor cell
 *         type. Because Floor and its subclasses can store a Piece, to avoid
 *         storing null when there's no Piece, we decided to store an object
 *         like Empty, that has some more behavior than null.
 */
public class Empty extends Piece implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Creates a new instance of Empty object, with the Cell that will store
	 * this object as a parameter.
	 * 
	 * @param cell
	 *            The Cell that will store this object.
	 */
	public Empty(Cell cell) {
		super(cell);
	}

	/**
	 * The implementation of this method returns always true, as this object is
	 * an instance of an Empty object.
	 * 
	 * @return true.
	 */
	public boolean isEmpty() {
		return true;
	}

	/**
	 * Because the Empty object can move freely, the method only returns this.
	 * 
	 * @return this.
	 */
	@Override
	public Piece move(Direction d) {
		return this;
	}

	/**
	 * When the Empty object is placed over a Hole object, it doesn't fall in
	 * the Hole.
	 */
	public void fall() {

	}

	/**
	 * This method calls another method named 'imageSeter' to draw this object.
	 * 
	 * @param imgSeter
	 *            The imageSeter object.
	 * @param map
	 *            A HashMap of classes name and images.
	 * @param p
	 *            A BoardPanel object.
	 */
	@Override
	public void draw(ImgSeter imgSeter, HashMap<String, Image> map, BoardPanel p) {
		imgSeter.draw(this, map, p);
	}
}
