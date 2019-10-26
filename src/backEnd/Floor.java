package backEnd;

import java.io.Serializable;

/**
 * @author Patricio Reller, German Romarion, Gabriel Zanzotti
 * 
 *         The Floor class represents a Cell that can store a Piece, such as a
 *         Box, a BombBox or a Hero.
 */
public class Floor extends Cell implements Serializable {

	private static final long serialVersionUID = 1L;
	private Piece piece;

	/**
	 * Creates an instance of Floor, with the Position of the floor and a
	 * reference to the Board that contains it.
	 * 
	 * @param position
	 *            The Position of the Floor in the Board.
	 * @param board
	 *            A reference to the Board.
	 */
	public Floor(Position position, Board board) {
		super(position, board);
		piece = new Empty(this);
	}

	/**
	 * When the Floor receives a Piece, it is stored in the Floor. The Floor
	 * also updates the reference to the Cell contained in the Piece
	 * 
	 * @param p
	 *            The incoming Piece.
	 * @return this.
	 */
	public Cell setPiece(Piece p) {
		p.setCell(this);
		piece = p;
		return this;
	}

	/**
	 * A getter for the Piece stored in the Floor.
	 * 
	 * @return The Piece stored in the Floor.
	 */
	public Piece piece() {
		return piece;
	}

	/**
	 * This method determines whether the Floor can receive an incoming Piece or
	 * not. If it is not occupied or of the Piece is an instance of Empty class,
	 * then it can receive it. Otherwise not.
	 * 
	 * @return A boolean value that determines whether the Floor can receive an
	 *         incoming Piece or not.
	 */
	@Override
	public boolean canReceive(Piece p) {
		return !(this.hasPiece()) || p.isEmpty();
	}

	/**
	 * This method determines whether the Floor can receive an incoming Hero or
	 * not. If it is not occupied or of the Piece is an instance of Empty class,
	 * then it can receive it. Otherwise not.
	 * 
	 * @return A boolean value that determines whether the Floor can receive an
	 *         incoming Piece or not.
	 */
	@Override
	public boolean canReceive(Hero h, Direction d) {
		Cell next = piece.getCell(d);
		return next.canReceive(piece);
	}

	/**
	 * This method determines whether the current Piece is an Empty object or
	 * not.
	 * 
	 * @return A boolean value that determines whether the current Piece is an
	 *         Empty object or not.
	 */
	@Override
	public boolean hasPiece() {
		return !(piece.isEmpty());
	}

	/**
	 * The destination reacts towards a Hero by returning a boolean false value.
	 * 
	 * @return false.
	 */
	public boolean react(Hero h) {
		return false;
	}

	/**
	 * The destination reacts towards a Box by returning a boolean false value.
	 * 
	 * @return false.
	 */
	@Override
	public boolean react(Box b) {
		return false;
	}

	/**
	 * A getter for the Piece stored in the Floor.
	 * 
	 * @return The Piece stored in the Floor.
	 */
	public Piece getPiece() {
		return piece;
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