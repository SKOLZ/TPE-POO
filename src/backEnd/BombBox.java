package backEnd;

import java.awt.Color;
import java.awt.Image;
import java.io.Serializable;
import java.util.HashMap;

import frontEnd.BoardPanel;
import frontEnd.ImgSeter;

/**
 * @author Patricio Reller, German Romarion, Gabriel Zanzotti
 * 
 *         Represents a box that can explode after some moves. When the BombBox
 *         explodes, the game is over.
 */
public class BombBox extends Box implements Serializable {

	private static final long serialVersionUID = 1L;
	private int life;

	/**
	 * Creates an instance of a BombBox in the specified Cell instance, with the
	 * amount of movements allowed before it explodes, and the color of the box.
	 * 
	 * @param cell
	 *            The specified Cell instance in which the BombBox will be
	 *            placed.
	 * @param moves
	 *            The amount of movements allowed to do before the BombBox
	 *            explodes.
	 * @param color
	 *            The color of the BombBox.
	 */
	public BombBox(Cell cell, int moves, Color color) {
		super(cell, color);
		life = moves;
	}

	/**
	 * After each move, it is supposed to call this method, so the 'life' of the
	 * BombBox is reduced by one point. If the life of the BombBox is zero, then
	 * the game is over.
	 */
	private void reduceLife() {
		life--;
	}

	/**
	 * Moves the BombBox in the specified Direction, which is an enum.
	 * 
	 * @param d
	 *            The specified Direction: it can be Direction.LEFT,
	 *            Direction.RIGHT, Direction.UP or Direction.DOWN.
	 * @return The piece next to this one, in the Direction d.
	 */
	@Override
	public Piece move(Direction d) {
		reduceLife();
		if (exploded()) {
			getCell().getBoard().getMatch().onLose();
			return new Empty(getCell());
		} else {
		return super.move(d);
		}
	}

	/**
	 * Checks whether the BombBox has exploded or not.
	 * 
	 * @return A boolean that specifies if the BombBox has exploded or not.
	 */
	public boolean exploded() {
		return life == 0;
	}

	/**
	 * Getter for the BombBox life.
	 * 
	 * @return The BombBox life.
	 */
	public int getLife() {
		return life;
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
}