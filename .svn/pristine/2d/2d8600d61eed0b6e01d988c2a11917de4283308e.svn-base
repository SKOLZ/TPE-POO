package backEnd;

import java.io.Serializable;

/**
 * @author Patricio Reller, German Romarion, Gabriel Zanzotti
 * 
 *         Represents a hole in the Board: when the Hero falls, the game is
 *         over. When a Box or a BombBox falls, it disappears from the board.
 */
public class Hole extends Floor implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Creates a new instance of a Hole object, with a Position on the Board,
	 * and a reference to the Board.
	 * 
	 * @param position
	 *            The position of the Hole in the Board.
	 * @param board
	 *            A reference to the Board.
	 */
	public Hole(Position position, Board board) {
		super(position, board);
	}

	/**
	 * A setter for the Piece instance variable.
	 * 
	 * @param h
	 *            The Hero.
	 * @return this.
	 */
	public Cell setPiece(Hero h) {
		super.setPiece(h);
		setPiece(new Empty(this));
		return this;
	}

	/**
	 * Because Boxes and Heros react the same way, this method is the one that's
	 * called always when the Hole must react to the Piece above it.
	 * 
	 * @param p
	 *            The Piece above the Hole.
	 * @return false.
	 */
	public boolean react(Piece p) {
		p.fall();
		setPiece(new Empty(this));
		return false;
	}

	/**
	 * Because Boxes and Heros react the same way, the react(Piece p) method is
	 * the one that's called always when the Hole must react to the Piece above
	 * it.
	 * 
	 * @param b
	 *            The Box above the Hole.
	 * @return false.
	 */
	public boolean react(Box b) {
		return react((Piece) b);
	}

	/**
	 * Because Boxes and Heros react the same way, the react(Piece p) method is
	 * the one that's called always when the Hole must react to the Piece above
	 * it.
	 * 
	 * @param h
	 *            The Hero above the Hole.
	 * @return false.
	 */
	public boolean react(Hero h) {
		return react((Piece) h);
	}

	/**
	 * This method is used to determine which Piece is stored in any Cell by
	 * using the double dispatch method. The Piece is stored in the CellVisitor
	 * object: if the Cell was a Wall, then the Piece will be null, otherwise,
	 * it will store the exact Piece.
	 */
	@Override
	public void visit(CellVisitor visitor) {
		visitor.visit(this);
	}
}