package backEnd;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import backEnd.exceptions.ReadErrorException;

/**
 * @author Patricio Reller, German Romarion, Gabriel Zanzotti
 * 
 *         Parses a '.board' file and returns a new Match object.
 */
public class Parser {

	private int destinations;
	private List<Box> boxes = new ArrayList<Box>();
	private Board board;
	private Hero hero;
	private static final int DATALENGTH = 7;
	private static final int MAXDIM = 20;
	private static final int MINDIM = 5;
	private int rowSize;
	private int colSize;
	private int reading = 0;
	private BufferedReader inStream = null;
	private String fileName;
	private int lineNumber = 0;

	public Parser(String fileName) {
		this.fileName = fileName;
	}

	private enum dataIndexes {

		rowPosition(0), colPosition(1), cellType(2), movements(3), colorR(4), colorG(
				5), colorB(6);

		private int id;

		private dataIndexes(int id) {
			this.id = id;
		}

		public int getValue() {
			return id;
		}
	}

	public Match match() throws IOException, ReadErrorException {
		board = board(fileName);
		Match match = new Match(board, boxes, hero, destinations);
		board.setMatch(match);
		closeFile();
		return match;
	}

	/**
	 * Parses a given '.board' file line by line and validates it.
	 * 
	 * @param inFileName
	 *            The path of the file to be parsed.
	 * @return A new instance of Board, ready to be used.
	 * @throws IOException
	 *             If the file can't be read.
	 * @throws ReadErrorException
	 *             If the validation fails.
	 */
	private Board board(String inFileName) throws IOException,
			ReadErrorException {
		inStream = new BufferedReader(new FileReader(inFileName));
		String line;
		try {
			while ((line = inStream.readLine()) != null) {
				line = cleanLine(line);
				lineNumber++;
				if (line.length() != 0) {
					switch (reading) {
					case 0:
						parseMapSize(line);
						reading = 1;
						this.board = new Board(this.rowSize, this.colSize);
						break;

					case 1:
						parseLine(line);
						break;
					}
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		if (hero == null) {
			throw new ReadErrorException("Error in line " + lineNumber
					+ ": there's no 'Player' in the board file.");
		}
		board.initialize();
		return board;
	}

	/**
	 * Closes the file that is being read.
	 * 
	 * @throws IOException
	 *             If the file can't be closed.
	 */
	private void closeFile() throws IOException {
		if (inStream != null) {
			inStream.close();
		}
	}

	/**
	 * Removes all spaces, tabulations and enters. It also ignores every line
	 * that begins with a '#'.
	 * 
	 * @param line
	 *            A line taken from the board file.
	 * @return A line with information ready to be analyzed.
	 */
	private String cleanLine(String line) {
		line = line.replaceAll(" ", "");
		line = line.replaceAll("\t", "");
		line = line.replaceAll("\n", "");

		if (line.contains("#")) {
			int cut = line.indexOf("#");
			line = line.substring(0, cut);
		}
		return line;
	}

	/**
	 * Converts a line like '1,1,1,0,0,0,0' into '1110000'; this is, the method
	 * transforms a String into an Array of ints.
	 * 
	 * @param line
	 *            A line taken from the '.board' file, which previously passed
	 *            through cleanLine(line).
	 * @throws ReadErrorException
	 *             If the validation fails.
	 */
	private void parseLine(String line) throws ReadErrorException {
		if (countOccurrencesOf(line, ',') == DATALENGTH - 1) {
			String[] sdata = line.split(",");
			int[] idata = new int[sdata.length];
			for (int i = 0; i < sdata.length; i++) {
				checkAllNumbers(sdata[i]);
				idata[i] = Integer.parseInt(sdata[i]);
			}
			putCell(idata);
		} else {
			throw new ReadErrorException("Error in line " + lineNumber
					+ ": the amount of parameters is incorrect.");
		}
	}

	/**
	 * Parses the size of the board: if the dimensions are out of bounds, then a
	 * ReadErrorException is thrown.
	 * 
	 * @param mapSizeLine
	 *            The line that has the board size data.
	 * @throws ReadErrorException
	 *             If the validation fails.
	 */
	private void parseMapSize(String mapSizeLine) throws ReadErrorException {
		if (countOccurrencesOf(mapSizeLine, ',') == 1) {
			int aux = mapSizeLine.indexOf(',');
			String rowsString = mapSizeLine.substring(0, aux);
			String colsString = mapSizeLine.substring(aux + 1, mapSizeLine
					.length());
			checkAllNumbers(rowsString);
			checkAllNumbers(colsString);
			int intRows = Integer.parseInt(rowsString);
			int intCols = Integer.parseInt(colsString);
			if (intCols >= MINDIM && intCols <= MAXDIM && intRows >= MINDIM
					&& intRows <= MAXDIM) {
				this.rowSize = intRows;
				this.colSize = intCols;
			} else
				throw new ReadErrorException("Error in line " + lineNumber
						+ ": the size of the board is incorrect.");
		} else
			throw new ReadErrorException("Error in line " + lineNumber
					+ ": unexpected character.");
	}

	/**((Floor) board.at(p)).setPiece(hero);
	 * Checks how many needles are in the current line.
	 * 
	 * @param line
	 *            A line taken from the '.board' file.
	 * @param needle
	 *            The needle character.
	 * @return The number of occurrences of the needle character in the current
	 *         line.
	 */
	private int countOccurrencesOf(String line, char needle) {
		int count = 0;
		for (int i = 0; i < line.length(); i++) {
			if (line.charAt(i) == needle) {
				count++;
			}
		}
		return count;
	}

	/**
	 * Verifies that all data in the '.board' file are numbers.
	 * 
	 * @param string
	 *            The String data.
	 * @throws ReadErrorException
	 *             If the validation fails.
	 */
	private void checkAllNumbers(String string) throws ReadErrorException {

		try {
			Integer.parseInt(string);
		} catch (NumberFormatException nfe) {
			throw new ReadErrorException("Error in line " + lineNumber
					+ ": the number format is incorrect.");
		}
	}

	/**
	 * Verifies that the current Cell is inside the Board.
	 * 
	 * @param row
	 *            A row in the Board.
	 * @param col
	 *            A column in the Board.
	 * @throws ReadErrorException
	 *             If the validation fails.
	 */
	private void checkCellBounds(int row, int col) throws ReadErrorException {
		if (row > rowSize || row < 0 || col > colSize || col < 0) {
			throw new ReadErrorException("Error in line " + lineNumber
					+ ": cell bound incorrect.");
		}
	}

	/**
	 * Verifies that the color values are greater than zero and smaller than
	 * 255.
	 * 
	 * @param data
	 *            An array containing the color values.
	 * @throws ReadErrorException
	 *             If the validation fails.
	 */
	private void checkColorValues(int[] data) throws ReadErrorException {
		for (int i = 4; i < 7; i++) {
			if (data[i] < 0 || data[i] > 255) {
				throw new ReadErrorException("Error in line " + lineNumber
						+ ": color values for current tile are incorrect.");
			}
		}
	}

	/**
	 * Verifies the amount of movements allowed for a certain type of Cell.
	 * 
	 * @param cellType
	 *            An int value that is related to a Cell type.
	 * @param movements
	 *            The amount of movements of the Cell.
	 * @throws ReadErrorException
	 *             If the validation fails.
	 */
	private void checkMovements(int cellType, int movements)
			throws ReadErrorException {
		if (cellType != 6) {
			if (movements != 0) {
				throw new ReadErrorException("Error in line " + lineNumber
						+ ": movement value for current tile is incorrect.");
			}
		}
	}

	/**
	 * Puts a certain Cell in the Board.
	 * 
	 * @param data
	 *            An array containing the row, the column, the cell type, the
	 *            amount of movements, and three numbers that represent the
	 *            color in 'r g b' format.
	 * @throws ReadErrorException
	 *             If the validation fails.
	 */
	private void putCell(int[] data) throws ReadErrorException {
		int row = data[dataIndexes.rowPosition.getValue()];
		int col = data[dataIndexes.colPosition.getValue()];
		checkCellBounds(row, col);

		Position p1 = new Position(row, col);
		Floor floor = new Floor(p1, board);
		Empty empty = new Empty(floor);
		switch (data[dataIndexes.cellType.getValue()]) {
		case 1:
			if (hero != null) {
				throw new ReadErrorException("Error in line " + lineNumber
						+ ": creating more than one player is incorrect.");
			}
			checkMovements(1, data[3]);
			checkColorValues(data);
			hero = new Hero(floor);
			board.put(p1, hero);
			break;

		case 2:
			checkMovements(2, data[3]);
			checkColorValues(data);
			Box box = new Box(floor, new Color(data[4], data[5], data[6]));
			board.put(p1, box);
			boxes.add(box);
			break;

		case 3:
			checkMovements(3, data[3]);
			checkColorValues(data);
			Destination d = new Destination(p1, board, new Color(data[4],
					data[5], data[6]));
			board.put(p1, d.setPiece(empty));
			destinations++;
			break;

		case 4:
			checkMovements(4, data[3]);
			checkColorValues(data);
			board.put(p1, new Wall(p1, board));
			break;

		case 5:
			checkMovements(5, data[3]);
			checkColorValues(data);
			Hole hole = new Hole(p1, board);
			board.put(p1, hole);
			break;

		case 6:
			checkMovements(6, data[3]);
			checkColorValues(data);
			BombBox bombBox = new BombBox(floor, data[3], new Color(data[4],
					data[5], data[6]));
			board.put(p1, bombBox);
			boxes.add(bombBox);
			break;

		default:
			throw new ReadErrorException("Error in line " + lineNumber
					+ ": the tile type is incorrect.");
		}
	}
}