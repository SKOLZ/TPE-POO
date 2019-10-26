package backEnd;

import java.io.Serializable;
import java.util.List;

/**
 * @author Patricio Reller, German Romarion, Gabriel Zanzotti
 * 
 *         Represents a match in the game.
 */
public class Match implements Serializable {

	private static final long serialVersionUID = 1L;
	private int destinations;
	private List<Box> boxes;
	private Board board;
	private Score score;
	private Hero hero;
	private transient Events events;

	/**
	 * Creates a new instance of a Match. The Match object has references to a
	 * Board object, a List of Boxes, a Hero and the amount of Destination
	 * objects. The List of boxes are used to know which boxes have been well
	 * placed in their respective destinations.
	 * 
	 * @param board
	 *            A reference to the Board.
	 * @param boxes
	 *            A List of all the Box objects in the Board.
	 * @param hero
	 *            A reference to the Hero.
	 * @param destinations
	 *            The amount of Destination cell object in the Board.
	 */
	public Match(Board board, List<Box> boxes, Hero hero, int destinations) {
		this.board = board;
		this.boxes = boxes;
		this.hero = hero;
		this.destinations = destinations;
		this.score = new Score();
	}

	/**
	 * Moves the Hero in the wanted Direction d.
	 * 
	 * @param d
	 *            A Direction object.
	 */
	public void moveHero(Direction d) {
		hero.move(d);
	}

	public void checkStatusBegining() {
		hero.getCell().react(hero);
		try {
			for (Box box : boxes) {
				box.arrive(box.getCell().react(box));
			}			
		} catch (Exception e) {
		}
		checkStatus();
	}

	/**
	 * A getter for the List of Boxes.
	 * 
	 * @return The list of Boxes.
	 */
	public List<Box> getBoxes() {
		return boxes;
	}

	/**
	 * A getter for the Board.
	 * 
	 * @return An instance of the Board object.
	 */
	public Board getBoard() {
		return board;
	}

	/**
	 * A getter for the Hero.
	 * 
	 * @return An instance of the Hero object.
	 */
	public Hero getHero() {
		return hero;
	}

	/**
	 * A getter for the Score.
	 * 
	 * @return An instance of the Score object.
	 */
	public Score getScore() {
		return score;
	}

	/**
	 * A getter for the Destinations.
	 * 
	 * @return The amount of Destination objects in the Board.
	 */
	public int getDestinations() {
		return destinations;
	}

	/**
	 * Getter for the Events.
	 * 
	 * @return An implementation of the Events interface.
	 */
	public Events getEvents() {
		return events;
	}

	/**
	 * A setter for the Events.
	 * 
	 * @param events
	 *            An implementation of the Events interface.
	 */
	public void setEvents(Events events) {
		this.events = events;
	}

	/**
	 * This method checks whether the game has been won or not.
	 */
	public void checkStatus() {
		boolean arrived = true;
		for (Box box : boxes) {
			arrived = arrived && box.arrived();
		}
		if (arrived && destinations == boxes.size()) {
			events.onWin();
		}
	}

	public void onLose() {
		events.onLose();
	}
}