package frontEnd;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JPanel;

import backEnd.Board;
import backEnd.BombBox;
import backEnd.Box;
import backEnd.CellPieceVisitor;
import backEnd.Destination;
import backEnd.Floor;
import backEnd.Hero;
import backEnd.Hole;
import backEnd.Piece;
import backEnd.Wall;

/**
 * Panel que permite representar un tablero de N x M celdas. Permite colocar
 * imagenes en las celdas y superponer imagenes con transparencia.
 */
public class BoardPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private int cellSize;
	private int rows, columns;
	private Image[][] cells;
	private static Board board;
	private HashMap<String, Image> map;
	private JLabel score;
	private static final int labelPadding = 23;

	/**
	 * Crea un nuevo panel con una cantidad determinada de filas y columnas. El
	 * parametro cellSize indica el ancho y alto en pixels de las celdas.
	 */
	public BoardPanel(int rows, int cols, int cellSize) throws IOException {
		this.rows = rows;
		this.columns = cols;
		this.cellSize = cellSize;
		this.cells = new Image[rows][columns];

		setSize(columns * cellSize + 1, rows * cellSize + 1);
		map = loadImages();
		setLayout(null);
		score = new JLabel("Score: 0");
		score.setBounds(5, rows - labelPadding, cols, 10);
		add(score, BorderLayout.SOUTH);
	}

	public void setBoard(Board b) {
		board = b;
		for (int i = 0; i < b.rows(); i++) {
			for (int j = 0; j < b.cols(); j++) {

				DrawerSeter imgSeter = new DrawerSeter();
				CellPieceVisitor visitor = new CellPieceVisitor();

				board.at(i, j).draw(imgSeter, map, this);
				board.at(i, j).visit(visitor);
				Piece piece = visitor.getPiece();
				if (piece != null) {
					piece.draw(imgSeter, map, this);
				}
			}
		}
		setScore(b.getMatch().getScore().getScore());
		repaint();
	}

	public void setScore(int points) {
		score.setText("Score : " + points);
	}

	/**
	 * Elimina el contenido de una celda determinada. Para que el cambio se vea
	 * reflejado es necesario invocar al metodo repaint.
	 */
	public void clearImage(int row, int column) {
		cells[row][column] = null;
	}

	/**
	 * Coloca una imagen en una celda determinada. Si la celda ya contenía otra
	 * imagen, la reemplaza. Para que el cambio se vea reflejado es necesario
	 * invocar al metodo repaint.
	 */
	public void setImage(int row, int column, Image image) {
		cells[row][column] = new BufferedImage(cellSize, cellSize,
				BufferedImage.TYPE_INT_ARGB);
		cells[row][column].getGraphics().drawImage(image, 0, 0, null);
	}

	/**
	 * Superpone una imagen sobre una celda. Si la celda está vacía, es
	 * equivalente a usar {@code setImage}. Si la celda no está vacía y la
	 * imagen a superponer contiene transparencias, entonces se superpone la
	 * imagen encima de la existente. Para que el cambio se vea reflejado es
	 * necesario invocar al metodo repaint.
	 */
	public void appendImage(int row, int column, Image image) {
		if (cells[row][column] == null) {
			cells[row][column] = new BufferedImage(cellSize, cellSize,
					BufferedImage.TYPE_INT_ARGB);
		}
		cells[row][column].getGraphics().drawImage(image, 0, 0, null);
	}

	/**
	 * @see JPanel#paint(Graphics)
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if (cells[i][j] != null) {
					g.drawImage(cells[i][j], j * cellSize, i * cellSize, null);
				}
			}
		}
	}

	public HashMap<String, Image> loadImages() throws IOException {
		HashMap<String, Image> imageMap = new HashMap<String, Image>();
		Image floor = ImageUtils
				.loadImage("gui" + File.separator + "floor.png");
		Image hole = ImageUtils.loadImage("gui" + File.separator + "hole.png");
		Image destination = ImageUtils.loadImage("gui" + File.separator
				+ "destination.png");
		Image wall = ImageUtils.loadImage("gui" + File.separator + "wall.png");
		Image hero = ImageUtils.loadImage("gui" + File.separator + "hero.png");
		Image box = ImageUtils.loadImage("gui" + File.separator + "box.png");
		Image boxCenter = ImageUtils.loadImage("gui" + File.separator
				+ "boxcenter.png");
		Image buttonCenter = ImageUtils.loadImage("gui" + File.separator
				+ "buttoncenter.png");
		imageMap.put(Floor.class.getName(), floor);
		imageMap.put(Hole.class.getName(), hole);
		imageMap.put(Destination.class.getName(), destination);
		imageMap.put(Wall.class.getName(), wall);
		imageMap.put(Hero.class.getName(), hero);
		imageMap.put(Box.class.getName(), box);
		imageMap.put(BombBox.class.getName(), box);
		imageMap.put("buttonCenter", buttonCenter);
		imageMap.put("boxCenter", boxCenter);
		return imageMap;
	}
}
