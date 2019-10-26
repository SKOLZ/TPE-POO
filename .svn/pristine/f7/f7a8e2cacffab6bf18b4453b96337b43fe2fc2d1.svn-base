package backEnd;

import java.io.Serializable;

import backEnd.exceptions.ReadErrorException;

/**
 * @author Patricio Reller, German Romarion, Gabriel Zanzotti
 * 
 *         The Board class represents the game board. In order to avoid the main
 *         character from falling, the board has two more rows and columns than
 *         usual, so there can be placed some walls.
 */
public class Board implements Serializable {

	private static final long serialVersionUID = 1L;
	private Cell[][] board;
	private Match match;

	/**
	 * Creates a Board instance with two more rows and columns than the wanted
	 * amount.
	 * 
	 * @param rows
	 *            The amount of rows.
	 * @param cols
	 *            The amount of columns.
	 */
	public Board(int rows, int cols) {
		board = new Cell[rows + 2][cols + 2];
	}

	/**
	 * Getter for the row amount.
	 * 
	 * @return The amount of rows in the board.
	 */
	public int rows() {
		return board.length - 2;
	}

	/**
	 * Getter for the column amount.
	 * 
	 * @return The amount of columns in the board.
	 */
	public int cols() {
		return board[0].length - 2;
	}

	/**
	 * Returns an instance of Cell class at some Position in the board.
	 * 
	 * @param p
	 *            The positi [javadoc] Loading source files for package
	 *            backEnd... on in the board.
	 * @return The Cell instance at the position p.
	 */
	public Cell at(Position p) {
		return board[p.getRow() + 1][p.getCol() + 1];
	}

	/**
	 * Returns an instance of Cell class at some row and columns position in the
	 * board.
	 * 
	 * @param row
	 *            The row in which the specified Cell is placed.
	 * @param col
	 *            The column in which the specified Cell is placed.
	 * @return
	 */
	public Cell at(int row, int col) {
		return board[row + 1][col + 1];
	}

	/**
	 * Puts the wanted Cell instance in a specified Position.
	 * 
	 * @param p
	 *            The specified Position to put the Cell in.
	 * @param cell
	 *            The specified Cell to put in.
	 * @throws ReadErrorException
	 *             In case the Cell could not be placed in the desired Position.
	 */
	public void put(Position p, Cell cell) throws ReadErrorException {
		if (at(p) != null) {
			if (cell instanceof Wall) {
				throw new ReadErrorException(
						"Error: Cannot place a Wall over another Cell.");
			} else if (cell instanceof Floor && at(p) instanceof Wall) {
				throw new ReadErrorException(
						"Error: Cannot place a Floor over a Wall.");
			} else if (cell instanceof Destination
					&& at(p).getClass() == Floor.class) {
				Floor f = (Floor) at(p);
				Piece piece = f.getPiece();
				board[p.getRow() + 1][p.getCol() + 1] = ((Destination) cell)
						.setPiece(piece);
			} else {
				throw new ReadErrorException(
						"Error: Cannot place a Cell over another Cell.");
			}
		} else {
			board[p.getRow() + 1][p.getCol() + 1] = cell;
		}
	}

	/**
	 * Puts the wanted Piece instance in a specified Position.
	 * 
	 * @param p
	 *            The specified Position to put the Cell in.
	 * @param cell
	 *            The specified Cell to put in.
	 * @throws ReadErrorException
	 *             In case the Piece could not be placed in the desired
	 *             Position.
	 */
	public void put(Position p, Piece piece) throws ReadErrorException {
		if (at(p) instanceof Floor) {
			Floor f = (Floor) at(p);
			if (!(f.getPiece() instanceof Empty)) {
				throw new ReadErrorException(
						"Error: Cannot place a Piece over another Piece.");
			} else {
				f.setPiece(piece);
			}
		} else if (at(p) == null) {
			board[p.getRow() + 1][p.getCol() + 1] = (new Floor(p, this))
					.setPiece(piece);
		} else {
			throw new ReadErrorException(
					"Error: Cannot place a Piece over a Wall.");
		}
	}

	/**
	 * The Board is being created in the Parser object. Because the Floor cells
	 * are not specified in the file that the Parser object reads in order to
	 * create an instance of a Board, this method puts Floor objects in the
	 * places that are null. After that, it calls the private method
	 * initializeWalls(), that surrounds the board with an invisible wall for
	 * the user, so, in case the board doesn't have any Wall objects, they keep
	 * the main character inside the board.
	 */
	public void initialize() throws ReadErrorException {
		for (int i = 0; i < board.length - 1; i++) {
			for (int j = 0; j < board[0].length - 1; j++) {
				Position p = new Position(i, j);
				if (at(p) == null) {
					put(p, new Floor(p, this).setPiece(new Empty(at(p))));
				}
			}
		}
		initializeWalls();
	}

	/**
	 * After the Board object has been created, this method is supposed to be
	 * called from the Parser object to set an instance of a Match object.
	 * 
	 * @param match
	 *            An instance of a Match object.
	 */
	public void setMatch(Match match) {
		this.match = match;
	}

	/**
	 * Getter for the Match instance variable.
	 * 
	 * @return The current Match stored in the Board object.
	 */
	public Match getMatch() {
		return match;
	}

	/**
	 * A private method that surrounds the Board with walls, so the Hero doesn't
	 * 'fall' from it.
	 */
	private void initializeWalls() {
		for (int i = 0; i < rows() + 2; i++) {
			Position p1 = new Position(i, 0);
			board[i][0] = new Wall(p1, this);
			Position p2 = new Position(i, cols() - 1);
			board[i][cols() + 2 - 1] = new Wall(p2, this);
		}
		for (int i = 0; i < cols() + 2; i++) {
			Position p1 = new Position(0, i);
			board[0][i] = new Wall(p1, this);
			Position p2 = new Position(rows() - 1, i);
			board[rows() + 2 - 1][i] = new Wall(p2, this);
		}
	}
}
