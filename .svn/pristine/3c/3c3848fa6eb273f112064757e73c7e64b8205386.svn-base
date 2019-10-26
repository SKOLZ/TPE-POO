package backEnd;


/**
 * @author Patricio Reller, German Romarion, Gabriel Zanzotti The Direction
 *         object represents a direction in which the main character can move.
 *         The only possible directions can be Direction.LEFT, Direction.RIGHT,
 *         Direction.UP and Direction.DOWN.
 */
public enum Direction {
	LEFT(0, -1), RIGHT(0, 1), UP(-1, 0), DOWN(1, 0);

	private Position p;

	/**
	 * Creates a new Direction object with two parameters: a row and a column.
	 * 
	 * @param row
	 *            The row value for the Direction.
	 * @param col
	 *            The column value for the Direction.
	 */
	Direction(int row, int col) {
		p = new Position(row, col);
	}

	/**
	 * A getter for the row value of the Direction.
	 * 
	 * @return The row value for the Direction.
	 */
	public int getRow() {
		return p.getRow();
	}

	/**
	 * A getter for the column value of the Direction.
	 * 
	 * @return The column value for the Direction.
	 */
	public int getCol() {
		return p.getCol();
	}
}
