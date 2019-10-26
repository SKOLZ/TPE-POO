package backEnd;

import java.io.Serializable;

/**
 * @author Patricio Reller, German Romarion, Gabriel Zanzotti
 * 
 *         Represents the score in a match.
 */
public class Score implements Serializable {

	private static final long serialVersionUID = 1L;
	private int score;

	/**
	 * Creates an instance of Score with an initial score of zero.
	 */
	public Score() {
		score = 0;
	}

	/**
	 * Creates an instance of Score, with an initial value determined by the
	 * argument.
	 * 
	 * @param s
	 *            The initial score.
	 */
	public Score(int s) {
		score = s;
	}

	/**
	 * Increases the score by 'i'.
	 * 
	 * @param i
	 *            The amount of score that will be increased.
	 */
	public void increase(int i) {
		score = score + i;
	}

	/**
	 * A getter for the score value.
	 * 
	 * @return An int value that represents the score.
	 */
	public int getScore() {
		return score;
	}
}
