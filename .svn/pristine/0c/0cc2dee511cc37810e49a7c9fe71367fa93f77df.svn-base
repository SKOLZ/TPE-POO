package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;

import org.junit.Test;

import backEnd.BombBox;
import backEnd.Box;
import backEnd.Destination;
import backEnd.Direction;
import backEnd.Floor;
import backEnd.Match;
import backEnd.Parser;
import backEnd.Position;
import backEnd.Score;
import backEnd.exceptions.ReadErrorException;
import frontEnd.EventsImpl;
import frontEnd.GameFrame;

public class Tests {

	private Match match;

	@Test
	public void testMovingHeroTowardsAWallDoesntMoveTheHero()
			throws ReadErrorException, IOException {
		match = (new Parser("levels" + File.separator
				+ "TestMovingHeroTowardsAWallDoesntMoveTheHero.map")).match();
		match.moveHero(Direction.RIGHT);
		assertEquals(match.getHero().getCell().getPosition(), match.getHero()
				.getCell().getPosition());
	}

	@Test
	public void testMovingHeroTowardsAnEmptyCellMovesTheHero()
			throws ReadErrorException, IOException {
		match = (new Parser("levels" + File.separator
				+ "TestMovingHeroTowardsAnEmptyCellMovesTheHero.map")).match();
		Position p = match.getHero().getCell().getPosition();
		match.getHero().move(Direction.RIGHT);
		assertEquals(match.getHero().getCell().getPosition(),
				p.plus(Direction.RIGHT));
	}

	@Test
	public void testMovingHeroTowardsABoxMovesTheHeroAndTheBox()
			throws ReadErrorException, IOException {
		match = (new Parser("levels" + File.separator
				+ "TestMovingTheHeroTowardsABoxMovesTheHeroAndTheBox.map"))
				.match();
		Position p1 = new Position(1, 2);
		Position p2 = new Position(1, 3);
		match.getHero().move(Direction.RIGHT);
		assertTrue(match.getHero().getCell().getPosition().equals(p1)
				&& ((Floor) match.getBoard().at(p2)).getPiece() instanceof Box);
	}

	@Test
	public void testMovingHeroTowardsADoubleBoxDoesntMove()
			throws ReadErrorException, IOException {
		match = (new Parser("levels" + File.separator
				+ "TestMovingHeroTowardsADoubleBoxDoesntMove.map")).match();
		// Setting two match.getBoxes().
		Position p1 = new Position(1, 2);
		Position p2 = new Position(1, 3);

		// Getting match.getHero() position and boxes positions.
		Position heroPositionBeforeAction = match.getHero().getCell()
				.getPosition();
		Position p3 = match.getBoard().at(p1).getPosition();
		Position p4 = match.getBoard().at(p2).getPosition();
		// Performing action.
		match.getHero().move(Direction.RIGHT);

		assertTrue(match.getHero().getCell().getPosition()
				.equals(heroPositionBeforeAction)
				&& match.getBoard().at(p3).getPosition().equals(p3)
				&& match.getBoard().at(p4).getPosition().equals(p4));
	}

	@Test
	public void testMovingHeroTowardsABoxNearAWallDoesntMoveTheHero()
			throws ReadErrorException, IOException {
		match = (new Parser("levels" + File.separator
				+ "TestMovingHeroTowardsABoxNearAWallDoesntMovetheHero.map"))
				.match();
		Position hp = match.getHero().getCell().getPosition();
		match.getHero().move(Direction.RIGHT);
		assertTrue(hp.equals(new Position(1, 1)));
	}

	@Test
	public void testEmptyCellReceivesHero() throws IOException,
			ReadErrorException {
		match = (new Parser("levels" + File.separator
				+ "TestMovingHeroTowardsAnEmptyCellMovesTheHero.map")).match();
		Position p = new Position(1, 2);
		assertTrue(match.getBoard().at(p).canReceive(match.getHero()));
	}

	@Test
	public void testWallCellDoesntReceiveHero() throws ReadErrorException,
			IOException {
		match = (new Parser("levels" + File.separator
				+ "TestMovingHeroTowardsAWallDoesntMoveTheHero.map")).match();
		Position w = new Position(1, 2);
		assertFalse(match.getBoard().at(w).canReceive(match.getHero()));
	}

	@Test
	public void testEmptyCellReceivesBox() throws ReadErrorException,
			IOException {
		match = (new Parser("levels" + File.separator
				+ "TestMovingHeroTowardsAnEmptyCellMovesTheHero.map")).match();
		Position empty = new Position(1, 3);
		Position b = new Position(1, 2);

		assertTrue(match
				.getBoard()
				.at(empty)
				.canReceive(new Box(match.getBoard().at(b), new Color(0, 0, 0))));
	}

	@Test
	public void testWallCellDoesntReceivesBox() throws ReadErrorException,
			IOException {
		match = (new Parser("levels" + File.separator
				+ "TestMovingHeroTowardsAWallDoesntMoveTheHero.map")).match();
		Position wall = new Position(1, 2);
		assertFalse(match
				.getBoard()
				.at(wall)
				.canReceive(
						new Box(match.getBoard().at(1, 1), new Color(0, 0, 0))));
	}

	@Test
	public void testBoxCanReceiveAHeroIfBoxCanMove() throws ReadErrorException,
			IOException {
		match = (new Parser("levels" + File.separator
				+ "TestMovingTheHeroTowardsABoxMovesTheHeroAndTheBox.map"))
				.match();
		Position p = new Position(1, 2);
		assertTrue(match.getBoard().at(p)
				.canReceive(match.getHero(), Direction.RIGHT));
	}

	@Test
	public void testBoxCantReceiveAHeroIfBoxIsBlockedByWall()
			throws ReadErrorException, IOException {
		match = (new Parser("levels" + File.separator
				+ "TestMovingHeroTowardsABoxNearAWallDoesntMovetheHero.map"))
				.match();
		Position pb = new Position(1, 2);
		Position pw = new Position(1, 3);
		assertFalse(match.getBoard().at(pw)
				.canReceive(((Floor) match.getBoard().at(pb)).piece()));
	}

	@Test
	public void testBoxCantReceiveAHeroIfBoxIsBlockedByBox()
			throws ReadErrorException, IOException {
		match = (new Parser("levels" + File.separator
				+ "TestMovingHeroTowardsADoubleBoxDoesntMove.map")).match();
		Position a = new Position(1, 2);
		assertFalse(match.getBoard().at(a).canReceive(match.getHero()));
	}

	@Test
	public void testBombBoxLifeDoesntDecreasesWhenBlocked()
			throws ReadErrorException, IOException {
		match = (new Parser("levels" + File.separator
				+ "TestBombBoxLifeDoesntDecreasesWhenBlocked.map")).match();
		BombBox b = (BombBox) ((Floor) match.getBoard().at(new Position(1, 2)))
				.getPiece();
		match.getHero().move(Direction.RIGHT);
		assertTrue(b.getLife() == 5);
	}

	@Test
	public void testBombBoxLifeDecreasesWhenMoved() throws ReadErrorException,
			IOException {
		match = (new Parser("levels" + File.separator
				+ "TestBombBoxLifeDecreasesWhenMoved.map")).match();
		BombBox b = (BombBox) ((Floor) match.getBoard().at(new Position(1, 2)))
				.getPiece();
		match.getHero().move(Direction.RIGHT);
		assertTrue(match.getHero().getCell().getPosition()
				.equals(new Position(1, 2))
				&& b.getCell().getPosition().equals(new Position(1, 3))
				&& b.getLife() == 4);
	}

	@Test
	public void testMoveBoxToHoleBoxDissapears() throws ReadErrorException,
			IOException {
		match = (new Parser("levels" + File.separator
				+ "TestMoveBoxToHoleBoxDissapears.map")).match();
		match.setEvents(new EventsImpl(new GameFrame("Test"), new JPanel(),
				new Score()));
		Position p1 = new Position(1, 2);
		Box box = (Box) ((Floor) match.getBoard().at(p1)).getPiece();
		try {
			match.getHero().move(Direction.RIGHT);

		} catch (Exception e) {
			assertTrue(!match.getBoard().at(p1).hasPiece()
					&& match.getBoxes().indexOf(box) == -1);
		}
	}

	@Test
	public void TestHeroMovesToDestinationJustMoves()
			throws ReadErrorException, IOException {
		match = (new Parser("levels" + File.separator
				+ "TestHeroMovesToDestinationJustMoves.map")).match();
		Position p1 = new Position(1, 2);
		Destination d = (Destination) match.getBoard().at(p1);
		match.getHero().move(Direction.RIGHT);
		assertTrue(match.getBoard().at(p1).hasPiece() && !(d.isOccupied()));
	}

	@Test
	public void TestHeroMovesBoxToDestinationBoxArrives()
			throws ReadErrorException, IOException {
		match = (new Parser("levels" + File.separator
				+ "TestHeroMovesBoxToDestinationBoxArrives.map")).match();
		Position p = new Position(1, 2);
		Box b = (Box) ((Floor) match.getBoard().at(p)).getPiece();
		try {
			match.getHero().move(Direction.RIGHT);
		} catch (Exception e) {
			assertTrue(b.arrived());
		}
	}
}