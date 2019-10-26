package frontEnd;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

import backEnd.Score;

public class EventsImpl implements backEnd.Events {

	private Image endingImg;
	private static final int fixWidth = 6;
	private static final int fixHeight = 28;
	private static final int menuHeight = 23;
	private GameFrame frame;
	private JPanel panel;
	private Score score;

	public EventsImpl(GameFrame frame, JPanel panel, Score score) {
		this.frame = frame;
		this.panel = panel;
		this.score = score;
	}

	@Override
	public void onWin() {
		frame.getJMenuBar().getMenu(0).getMenuComponent(2).setVisible(false);
		setter("images/win.png");
		panel.setVisible(false);
		frame.remove(panel);
		final ImagePanel p = new ImagePanel(endingImg);
		JLabel scoreLabel = new JLabel("Game won in " + score.getScore()
				+ " moves");

		p.setSize(398, 299);
		p.setVisible(true);
		frame.setActualPanel(p);
		frame.add(p);
		scoreLabel.setBounds(5, 0, 300, 19);
		scoreLabel.setForeground(Color.LIGHT_GRAY);
		p.add(scoreLabel);
		frame.repaint();
		Sounds.WIN.play();
	}

	@Override
	public void onLose() {
		frame.getJMenuBar().getMenu(0).getMenuComponent(2).setVisible(false);
		setter("images/lose.png");
		panel.setVisible(false);
		frame.remove(panel);
		final ImagePanel p = new ImagePanel(endingImg);

		p.setSize(398, 299);
		p.setVisible(true);
		frame.setActualPanel(p);
		frame.add(p);
		frame.repaint();
		Sounds.LOSE.play();
	}

	private void setter(String fileName) {
		File img = new File(fileName);
		try {
			endingImg = ImageIO.read(img);
		} catch (IOException error) {
			throw new RuntimeException();
		}
		frame.setLayout(null);
		Toolkit toolkit = frame.getToolkit();
		Dimension size = toolkit.getScreenSize();
		frame.setSize(398 + fixWidth, 299 + fixHeight + menuHeight);
		frame.setLocation(size.width / 2 - frame.getWidth() / 2, size.height
				/ 2 - frame.getHeight() / 2);
	}
}