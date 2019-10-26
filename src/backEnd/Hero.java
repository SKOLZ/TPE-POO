package backEnd;

import java.awt.Image;
import java.io.Serializable;
import java.util.HashMap;

import frontEnd.BoardPanel;
import frontEnd.ImgSeter;

/**
 * @author Patricio Reller, German Romarion, Gabriel Zanzotti
 * 
 *         The Hero class represents the main character of the game, and the
 *         player itself.
 */
public class Hero extends Piece implements Fallable, Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Creates a new instance of a Hero object. It receives the Cell in which
	 * the Hero is contained.
	 * 
	 * @param cell
	 *            The Cell in which the Hero is contained.
	 */
	public Hero(Cell cell) {
		super(cell);
	}

	/**
	 * This method first checks whether the Hero can be moved to the next Cell.
	 * If that's the case, then the Hero will be moved.
	 * 
	 * @param d
	 *            The Direction in which the Hero will be moved.
	 * @return this.
	 */
	public Piece move(Direction d) {
		Cell next = this.getCell(d);
		if (next.canReceive(this, d)) {
			getCell().getBoard().getMatch().getScore().increase(1);
			changePosition(d);
			next.react(this);
		}
		return this;
	}

	/**
	 * Getter for the Cell that's in the Direction d in respect of the Hero.
	 * 
	 * @param d
	 *            The Direction of the Cell that wants to be obtained.
	 * @return The Cell in the Direction d in respect of the Hero.
	 */
	public Cell getCell(Direction d) {
		return getCell().getBoard().at(getCell().getPosition().plus(d));
	}

	/**
	 * This method concludes the game: when the Hero falls into a Hole, dies.
	 */
	@Override
	public void fall() {
		getCell().getBoard().getMatch().onLose();
	}

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
	@Override
	public void draw(ImgSeter imgSeter, HashMap<String, Image> map, BoardPanel p) {
		imgSeter.draw(this, map, p);
	}

	/**
	 * Checks whether the current Piece is Empty or not.
	 * 
	 * @return false.
	 */
	@Override
	public boolean isEmpty() {
		return false;
	}
}