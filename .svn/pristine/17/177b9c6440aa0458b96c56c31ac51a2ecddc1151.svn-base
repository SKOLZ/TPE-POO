package backEnd;

import java.awt.Color;
import java.awt.Image;
import java.io.Serializable;
import java.util.HashMap;

import frontEnd.BoardPanel;
import frontEnd.ImgSeter;

/**
 * @author Patricio Reller, German Romarion, Gabriel Zanzotti
 * 
 *         Represents a box that can be moved through all the board. It has a
 *         specified color, so the box is placed in over a certain Destination
 *         object.
 */
public class Box extends Piece implements Fallable, Serializable {

	private static final long serialVersionUID = 1L;
	private Color color;
	private boolean arrived;

	/**
	 * Creates an instance of a Box in the specified Cell instance and with the
	 * color of the box.
	 * 
	 * @param cell
	 *            The specified Cell instance in which the BombBox will be
	 *            placed.
	 * @param color
	 *            The color of the BombBox.
	 */
	public Box(Cell cell, Color color) {
		super(cell);
		this.color = color;
		arrived = false;
	}

	/**
	 * Getter for the Box's Color.
	 * 
	 * @return The Color object of the Box.
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Determines whether the current object (in this case, the Box object), is
	 * an Empty object or not. In this class, the method is implemented so it
	 * always returns false.
	 * 
	 * @return false.
	 */
	public boolean isEmpty() {
		return false;
	}

	/**
	 * Moves the Box in the specified Direction, which is an enum.
	 * 
	 * @param d
	 *            The specified Direction: it can be Direction.LEFT,
	 *            Direction.RIGHT, Direction.UP or Direction.DOWN.
	 * @return The piece next to this one, in the Direction d.
	 */
	@Override
	public Piece move(Direction d) {
		Piece next = ((Floor) getCell(d)).piece();
		changePosition(d);
		arrive(getCell().react(this));
		getCell().getBoard().getMatch().checkStatus();
		return next;
	}

	/**
	 * A setter for the arrived instance variable.
	 * 
	 * @param reaction
	 *            A boolean value that will be assigned to the arrived instance
	 *            variable.
	 */
	public void arrive(boolean reaction) {
		arrived = reaction;
	}

	/**
	 * When the Box object steps over a Hole object, this last object makes the
	 * Box object fall(). This method removes the current instance of the Box
	 * class from a List<Box> in the Match object. Afterwards, the method checks
	 * whether the player has won the game or not.
	 */
	@Override
	public void fall() {
		getCell().getBoard().getMatch().getBoxes().remove(this);
		((Floor) getCell()).setPiece(new Empty(getCell()));
	}

	/**
	 * Determines whether the Box has arrived to its corresponding Destination
	 * or not.
	 * 
	 * @return a boolean value that represents whether the Box has arrived to
	 *         its corresponding Destination or not.
	 */
	public boolean arrived() {
		return arrived;
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