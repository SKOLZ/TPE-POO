package backEnd;

import java.io.Serializable;

import backEnd.exceptions.GameLogicException;

/**
 * @author Patricio Reller, German Romarion, Gabriel Zanzotti
 * 
 *         Represents a wall in the Board.
 */
public class Wall extends Cell implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Creates a new instance of Wall object, with a Position, and a reference
	 * to the Board as parameters.
	 * 
	 * @param position
	 *            A position in the Board.
	 * @param board
	 *            A reference to the Board.
	 */
	public Wall(Position position, Board board) {
		super(position, board);
	}

	/**
	 * A wall can only receive an Empty object.
	 * 
	 * @return A boolean value that represents whether the Wall can receive the
	 *         current Piece or not: if the Piece is an instance of Empty, then
	 *         it will return 'true'; otherwise it will return 'false'.
	 */
	@Override
	public boolean canReceive(Piece p) {
		return p.isEmpty();
	}

	/**
	 * Because a Wall can't let pass the player through, it can't receive a
	 * player, so this method always returns false when the icoming Piece is a
	 * Hero.
	 * 
	 * @return false.
	 */
	@Override
	public boolean canReceive(Hero h, Direction d) {
		return false;
	}

	/**
	 * 
	 */
	@Override
	public boolean hasPiece() {
		return true;
	}

	/**
	 * Because a Wall cannot store a Piece over itself, if someone reaches this
	 * method, it will throw a GameLogicException.
	 */
	@Override
	public boolean react(Box b) {
		throw new GameLogicException("A wall shouldn't be reacting to a Box");
	}

	/**
	 * Because a Wall cannot store a Piece over itself, if someone reaches this
	 * method, it will throw a GameLogicException.
	 */
	@Override
	public boolean react(Hero h) {
		throw new GameLogicException("A wall shouldn't be reacting to a Hero");
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
