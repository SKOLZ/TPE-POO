package backEnd;

import java.awt.Image;
import java.io.Serializable;
import java.util.HashMap;

import frontEnd.BoardPanel;
import frontEnd.ImgSeter;

/**
 * @author Patricio Reller, German Romarion, Gabriel Zanzotti
 * 
 *         Represents a Piece in the Board.
 */
public abstract class Piece implements Serializable {

	private static final long serialVersionUID = 1L;
	private Cell cell;

	/**
	 * Creates a new instance of the Piece, with the Cell in which the Piece
	 * object will be placed.
	 * 
	 * @param cell
	 *            The Cell in which the Piece object will be placed.
	 */
	public Piece(Cell cell) {
		this.cell = cell;
	}

	/**
	 * Changes the position of the current Piece to the Cell in the Direction d.
	 * 
	 * @param d
	 *            The Direction object in which the Piece object will be moved.
	 */
	protected void changePosition(Direction d) {
		Floor next = (Floor) getCell(d);
		((Floor) getCell()).setPiece(next.piece().move(d));
		next.setPiece(this);
	}

	/**
	 * Updates the Cell known by the Piece object after it has changed position.
	 * 
	 * @param cell
	 *            The new Cell to update the Piece.
	 */
	public void setCell(Cell cell) {
		this.cell = cell;
	}

	/**
	 * Checks whether the current Piece is Empty or not.
	 * 
	 * @return A boolean value that represents whether the current Piece is
	 *         Empty or not.
	 */
	public abstract boolean isEmpty();

	/**
	 * Gets the Cell in the Direction d in respect of the current Piece object.
	 * 
	 * @param d
	 *            The Direction in which the Cell is in respect of the current
	 *            Piece object.
	 * @return The Cell in the the Cell is in respect of the current Piece
	 *         object.
	 */
	public Cell getCell(Direction d) {
		return cell.getBoard().at(cell.getPosition().plus(d));
	}

	/**
	 * Getter for the Cell instance variable.
	 * 
	 * @return The Cell instance variable name.
	 */
	public Cell getCell() {
		return cell;
	}

	/**
	 * Moves the current piece in the wanted Direction d.
	 * 
	 * @param d
	 *            The Direction where the Piece wants to be moved.
	 * @return A Piece object.
	 */
	public abstract Piece move(Direction d);

	/**
	 * Makes the current Piece fall into a Hole object.
	 */
	public abstract void fall();

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
	public abstract void draw(ImgSeter imgSeter, HashMap<String, Image> map,
			BoardPanel p);
}