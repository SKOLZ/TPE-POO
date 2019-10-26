package backEnd;

import java.awt.Image;
import java.io.Serializable;
import java.util.HashMap;

import frontEnd.BoardPanel;
import frontEnd.ImgSeter;

/**
 * @author Patricio Reller, German Romarion, Gabriel Zanzotti
 * 
 *         It's an abstract class that represents a Cell in the Board object. It
 *         knows its own position on the Board, and the Board that contains the
 *         Cell.
 */
public abstract class Cell implements Serializable {

	private static final long serialVersionUID = 1L;
	private Position position;
	private Board board;

	/**
	 * Creates an instance of a Cell in the specified Position instance and with
	 * the Board that contains it.
	 * 
	 * @param position
	 *            The specified Position instance in which the BombBox will be
	 *            placed.
	 * @param board
	 *            The Board that contains the Cell.
	 */
	public Cell(Position position, Board board) {
		this.position = position;
		this.board = board;
	}

	/**
	 * Getter for the Cell's Position.
	 * 
	 * @return The Position in which the current Cell instance is placed.
	 */
	public Position getPosition() {
		return position;
	}

	/**
	 * Getter for the Cell's Board.
	 * 
	 * @return The Board that contains the current Cell instance.
	 */
	public Board getBoard() {
		return board;
	}

	/**
	 * This method determines whether the current Cell instance can receive a
	 * Piece or not.
	 * 
	 * @param p
	 *            The Piece that the Cell is about to receive.
	 * @return A boolean value that specifies whether the current Cell instance
	 *         can receive the Piece or not.
	 */
	public abstract boolean canReceive(Piece p);

	/**
	 * This method determines whether the current Cell instance can receive a
	 * Hero or not.
	 * 
	 * @param h
	 *            The Hero that the Cell is about to receive.
	 * @return A boolean value that specifies whether the current Cell instance
	 *         can receive the Piece or not.
	 */
	public abstract boolean canReceive(Hero h, Direction d);

	/**
	 * This method determines whether the current Cell has a Piece different
	 * from an Empty instance.
	 * 
	 * @return A boolean value that determines whether the current Cell has a
	 *         Piece different from an Empty instance.
	 */
	public abstract boolean hasPiece();

	/**
	 * When a Box is placed over the Cell, the Cell reacts in a specific way.
	 * 
	 * @param b
	 *            The Box that's going to make the Cell react.
	 */
	public abstract boolean react(Box b);

	/**
	 * When a Hero is placed over the Cell, the Cell reacts in a specific way.
	 * 
	 * @param b
	 *            The Hero that's going to make the Cell react.
	 */
	public abstract boolean react(Hero h);

	/**
	 * This method is used to determine which Piece is stored in any Cell by
	 * using the double dispatch method. The Piece is stored in the CellVisitor
	 * object: if the Cell was a Wall, then the Piece will be null, otherwise,
	 * it will store the exact Piece.
	 */
	public abstract void visit(CellVisitor visitor);

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