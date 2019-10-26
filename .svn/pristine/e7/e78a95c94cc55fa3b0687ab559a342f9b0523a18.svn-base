package frontEnd;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;

import backEnd.Board;
import backEnd.Direction;
import backEnd.Match;
import backEnd.Parser;
import backEnd.exceptions.ReadErrorException;

public class GameFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private Image background;
	private Toolkit toolkit;
	private static final int cellSize = 40;
	private static final int fixWidth = 6;
	private static final int fixHeight = 28;
	private static final int menuHeight = 23;
	private KeyAdapter ka;
	private JPanel actualPanel;
	private JMenuBar bar = new JMenuBar();
	private Match match;
	private String mapPath;
	private AboutPanel about;

	public GameFrame(String s) {
		super(s);
		File icon = new File("images/icon.png");
		File bg = new File("images/sokoban.png");
		try {
			background = ImageIO.read(bg);
			Image iconImg = ImageIO.read(icon);
			setIconImage(iconImg);
		} catch (IOException e) {
			showErrorMessage(e.getMessage(), "System error");
		}
		setResizable(false);
		setLayout(null);
		toolkit = getToolkit();
		Dimension size = toolkit.getScreenSize();
		setSize(800, 615);
		setLocation(size.width / 2 - getWidth() / 2, size.height / 2
				- getHeight() / 2);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		actualPanel = new ImagePanel(background);
		add(actualPanel);

		MenuButton newGame = new MenuButton("new game", 170, 220);
		MouseOver mo1 = new MouseOver() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String auxMapPath = fileName("Load map", "levels", ".map");
				loadMap(auxMapPath);
			}
		};

		mo1.setButton(newGame);
		newGame.addMouseListener(mo1);
		actualPanel.add(newGame);

		MenuButton load = new MenuButton("load game", 170, 260);
		MouseOver mo2 = new MouseOver() {
			@Override
			public void mouseClicked(MouseEvent e) {
				loadGame();
			}
		};
		mo2.setButton(load);
		load.addMouseListener(mo2);
		actualPanel.add(load);

		MenuButton aboutUs = new MenuButton("about us", 170, 300);
		MouseOver mo3 = new MouseOver() {
			@Override
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				File aboutUs = new File("images/about.png");
				Image aboutUsImg = null;
				try {
					aboutUsImg = ImageIO.read(aboutUs);
					about = new AboutPanel(aboutUsImg);
					add(about);
					getContentPane().setComponentZOrder(about, 0);
					getContentPane().setComponentZOrder(actualPanel, 1);
					repaint();
				} catch (IOException e1) {
					showErrorMessage(e1.getMessage(), "System error");
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				about.setVisible(false);
				remove(about);
			}
		};
		mo3.setButton(aboutUs);
		aboutUs.addMouseListener(mo3);
		actualPanel.add(aboutUs);

		MenuButton quit = new MenuButton("quit", 170, 340);
		MouseOver mo4 = new MouseOver() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}
		};
		mo4.setButton(quit);
		quit.addMouseListener(mo4);
		actualPanel.add(quit);
		setBar(bar);
	}

	private void loadGame() {
		String fileName = fileName("Load game", "savedGames", "");
		if (fileName != null) {
			loadGame(fileName);
			Sounds.stopAll();
		}
	}

	private void loadGame(String fileName) {
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new BufferedInputStream(
					new FileInputStream(fileName)));
			String auxMapPath = (String) ois.readObject();
			Match auxMatch = (Match) ois.readObject();
			ois.close();
			mapPath = auxMapPath;
			match = auxMatch;
			startGame();
			match.setEvents(new EventsImpl(this, actualPanel, match.getScore()));
		} catch (Exception e) {
			showErrorMessage(e.getMessage(), "System error");
		}
	}

	private void saveGame() {
		JFileChooser chooser = new JFileChooser();
		File fileName = null;
		try {
			chooser.setCurrentDirectory(new File(new File(".")
					.getCanonicalPath() + File.separator + "savedGames"));
		} catch (Exception e) {
			showErrorMessage(e.getMessage(), "System error");
		}
		int ret = chooser.showDialog(getContentPane(), "Save");
		if (ret == JFileChooser.APPROVE_OPTION) {
			fileName = chooser.getSelectedFile();
			try {
				ObjectOutputStream oos = new ObjectOutputStream(
						new BufferedOutputStream(new FileOutputStream(fileName)));
				oos.writeObject(mapPath);
				oos.writeObject(match);
				oos.close();
			} catch (IOException ex) {
				showErrorMessage(ex.getMessage(), "System error");
			}
		}
	}

	private String fileName(String fileChooserName, String folderName,
			final String filter) {
		JFileChooser fileopen = new JFileChooser();
		fileopen.setFileFilter(new FileFilter() {

			public boolean accept(File file) {
				String filename = file.getName();
				return filename.endsWith(filter);
			}

			public String getDescription() {
				return "*" + filter;
			}
		});
		try {
			fileopen.setCurrentDirectory(new File(new File(".")
					.getCanonicalPath() + File.separator + folderName));
		} catch (Exception e) {
			showErrorMessage(e.getMessage(), "System error");
		}
		int ret = fileopen.showDialog(getContentPane(), fileChooserName);
		if (ret == JFileChooser.APPROVE_OPTION) {
			return fileopen.getSelectedFile().getPath();
		}
		return null;
	}

	private void loadBoard(String file) {
		try {
			match = (new Parser(file)).match();
			removeKeyListener(ka);
			startGame();
			match.setEvents(new EventsImpl(this, actualPanel, match.getScore()));
			mapPath = file;
			match.checkStatusBegining();
			Sounds.stopAll();
		} catch (ReadErrorException e) {
			addKeyListener(ka);
			showErrorMessage(e.getMessage(), "Parsing error");
		} catch (IOException e) {
			showErrorMessage(e.getMessage(), "Parsing error");
		}
	}

	private void startGame() {
		Board board = match.getBoard();
		int rows = board.rows() * cellSize + fixHeight;
		int cols = board.cols() * cellSize + fixWidth;
		try {
			final BoardPanel bp = new BoardPanel(rows, cols, cellSize);
			remove(actualPanel);
			actualPanel = bp;
			bp.setBoard(board);
			setSize(cols, rows + menuHeight + 20);
			add(actualPanel);
			Dimension size = toolkit.getScreenSize();
			setLocation(size.width / 2 - getWidth() / 2, size.height / 2
					- getHeight() / 2);
			actualPanel.setVisible(true);
			actualPanel.repaint();

			setJMenuBar(bar);
			getJMenuBar().getMenu(0).getMenuComponent(2).setVisible(true);

			ka = new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
						match.moveHero(Direction.RIGHT);
					} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
						match.moveHero(Direction.LEFT);
					} else if (e.getKeyCode() == KeyEvent.VK_UP) {
						match.moveHero(Direction.UP);
					} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
						match.moveHero(Direction.DOWN);
					}
					bp.setBoard(match.getBoard());
				}
			};
		addKeyListener(ka);
		
		Sounds.stopAll();
		} catch (Exception e){
			
		}
	}

	private void setBar(JMenuBar b) {
		JMenu file = new JMenu("File");
		JMenuItem newGame = new JMenuItem("New game");
		JMenuItem restart = new JMenuItem("Restart");
		JMenuItem save = new JMenuItem("Save as...");
		JMenuItem loadGame = new JMenuItem("Load game");
		JMenuItem quit = new JMenuItem("Quit");

		file.add(newGame);
		file.add(restart);
		file.add(save);
		file.add(loadGame);
		file.add(quit);

		newGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				String temp = fileName("Load map", "levels", ".map");
				if (temp != null) {
					removeKeyListener(ka);
					loadMap(temp);
				}
			}
		});
		restart.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				loadBoard(mapPath);
			}
		});
		save.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				saveGame();
			}
		});
		loadGame.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				removeKeyListener(ka);
				loadGame();
			}
		});
		quit.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				System.exit(0);
			}
		});
		b.add(file);
	}

	private void loadMap(String path) {
		if (path != null) {
			loadBoard(path);
		}
	}

	public void setActualPanel(JPanel p) {
		actualPanel = p;
	}

	private void showErrorMessage(String error, String title) {
		JLabel errorMessage = new JLabel(error);
		errorMessage.setSize(errorMessage.getText().length() * 7, 80);
		errorMessage.setVisible(true);
		JFrame popup = new JFrame();
		Dimension size = popup.getToolkit().getScreenSize();
		popup.setTitle(title);
		popup.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		popup.setSize(errorMessage.getSize());
		JPanel panel = new JPanel();
		panel.setSize(popup.getSize());
		popup.setLocation(size.width / 2 - popup.getWidth() / 2, size.height
				/ 2 - popup.getHeight() / 2);
		panel.setBackground(new Color(255, 255, 255));
		panel.add(errorMessage);
		panel.setVisible(true);
		popup.add(panel);
		popup.setVisible(true);
		popup.setResizable(false);
	}
}