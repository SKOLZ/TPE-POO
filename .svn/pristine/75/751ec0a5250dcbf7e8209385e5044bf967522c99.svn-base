package backEnd;

/**
 * 
 * @author  Patricio Reller, German Romarion, Gabriel Zanzotti
 * 
 * Implements cell visitor interface and it's called only 
 * when the cell subclasses use the method visit which
 * switches the receptor with the parameter to know 
 * the real type of the object
 * 
 */
public class CellPieceVisitor implements CellVisitor {

	private Piece piece;

	/**
	 * gets the Floor's piece to be recovered later
	 */
	@Override
	public void visit(Floor floor) {
		piece = floor.getPiece();
	}

	/**
	 * the wall has no piece so the piece is settled to
	 * null.
	 */
	@Override
	public void visit(Wall wall) {
		piece = null;
	}

	/**
	 * gets the piece saved by one of the methods 
	 * used to set it.
	 */
	public Piece getPiece() {
		return piece;
	}

	/**
	 * /**
	 * gets the Destination's piece to be recovered later
	 */
	@Override
	public void visit(Destination destination) {
		visit((Floor) destination);
	}

	 /**
	 * gets the Hole's piece to be recovered later
	 */
	@Override
	public void visit(Hole hole) {
		visit((Floor) hole);
	}
}