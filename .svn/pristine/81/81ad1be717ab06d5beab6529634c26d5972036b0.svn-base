package backEnd;

import java.awt.Color;
import java.awt.Image;
import java.io.Serializable;
import java.util.HashMap;

import frontEnd.BoardPanel;
import frontEnd.ImgSeter;

public class Destination extends Floor implements Serializable {

	/**
	 * @author Patricio Reller, German Romarion, Gabriel Zanzotti
	 * 
	 *         Represents a destination that expects a Box object with the same
	 *         Color than itself.
	 */
	private static final long serialVersionUID = 1L;
	private Color color;
	private boolean occupied;

	/**
	 * Creates a new Destination object, with a Position on the Board, the Board
	 * and a Color
	 * 
	 * @param position
	 *            A Position instance that determines where is the Destination
	 *            in the Board.
	 * @param board
	 *            A reference to the Board that contains it.
	 * @param color
	 *            A color that will be useful to determine whether a Box object
	 *            of the same Color has been placed over this Destination or
	 *            not.
	 */
	public Destination(Position position, Board board, Color color) {
		super(position, board);
		this.color = color;
		occupied = false;
	}

	/**
	 * A setter for the Piece instance variable.
	 * 
	 * @return this.
	 */
	public Cell setPiece(Piece p) {
		super.setPiece(p);
		return this;
	}

	/**
	 * Getter for the Color instance variable.
	 * 
	 * @return The Color instance variable.
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Determines whether the Box placed over this Destination has the same
	 * color than this.
	 * 
	 * @param color
	 *            The color of the incoming Box.
	 * @return A boolean value that determines whether the Box placed over this
	 *         Destination has the same color than this.
	 */
	private Boolean checkCorrespondece(Color color) {
		return this.color.equals(color);
	}

	/**
	 * Determines whether over this Destination is a Piece different from an
	 * Empty object.
	 * 
	 * @return A boolean value that determines whether over this Destination is
	 *         a Piece different from an Empty object.
	 */
	public Boolean isOccupied() {
		return occupied;
	}

	/**
	 * The Destination reacts to a Box by setting the instance variable
	 * 'occupied' on true
	 * 
	 * @return A boolean value that determines whether the Destination is
	 *         occupied or not.
	 */
	@Override
	public boolean react(Box b) {
		occupied = checkCorrespondece(b.getColor());
		return occupied;
	}

	/**
	 * The destination reacts towards a Hero by returning a boolean false value.
	 * 
	 * @return false.
	 */
	@Override
	public boolean react(Hero h) {
		return false;
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
	public void draw(ImgSeter imgSeter, HashMap<String, Image> map, BoardPanel p) {
		imgSeter.draw(this, map, p);
	}
}