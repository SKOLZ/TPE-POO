package backEnd;

/**
 * 
 * @author  Patricio Reller, German Romarion, Gabriel Zanzotti
 * 
 * Interface used to get the piece from a cell sublclass.
 * Wall is a cell subclass but it doesn't have any piece so
 * when the message has a wall as a parameter the method 
 * should know how to respond that, in the rest of the
 * subclasses their piece is saved so you can ask 
 * for the piece later. 
 *
 */
public interface CellVisitor {
	
	/**
	 * 
	 * @param floor
	 * 		the interface implementer will get the piece from
	 * 		a floor type cell.
	 */
	void visit(Floor floor);
	
	/**
	 * 
	 * @param wall
	 * 		the interface implementer will get the piece from
	 * 		a wall type cell.
	 */
	void visit(Wall wall);
	
	/**
	 * 
	 * @param destination
	 * 		the interface implementer will get the piece from
	 * 		a destination type cell.
	 */
	void visit(Destination destination);
	
	/**
	 * 
	 * @param hole
	 * 		the interface implementer will get the piece from
	 * 		a hole type cell.
	 */
	void visit(Hole hole);
}
