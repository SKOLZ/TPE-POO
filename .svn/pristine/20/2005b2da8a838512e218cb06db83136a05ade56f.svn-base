package backEnd;

import java.io.Serializable;

/**
 * @author Patricio Reller, German Romarion, Gabriel Zanzotti
 * 
 *         Represents a position in the Board.
 */
public class Position implements Serializable {

	private static final long serialVersionUID = 1L;
	private int row;
	private int col;

	/**
	 * Creates a new Position object with a row and a column parameter.
	 * 
	 * @param row
	 *            The row in the board.
	 * @param col
	 *            The column in the board.
	 */
	public Position(int row, int col) {
		this.row = row;
		this.col = col;
	}

	/**
	 * Getter for the row component of the Position object.
	 * 
	 * @return An int value that represents the row component of the Position
	 *         object.
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Getter for the column component of the Position object.
	 * 
	 * @return An int value that represents the column component of the Position
	 *         object.
	 */
	public int getCol() {
		return col;
	}

	/**
	 * Adds to the current Position, a Direction object, which results on a new
	 * Position object.
	 * 
	 * @param d
	 *            The Direction that wants to be added to the current Position
	 *            object.
	 * @return A new Position object.
	 */
	public Position plus(Direction d) {
		return new Position(row + d.getRow(), col + d.getCol());
	}

	/**
	 * Determines whether the current Position object is equal to another
	 * Object. @ return A boolean value that determines whether the current
	 * Position object is equal to another Object.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		if (col != other.col)
			return false;
		if (row != other.row)
			return false;
		return true;
	}
}
