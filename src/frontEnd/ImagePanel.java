package frontEnd;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;


class ImagePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private Image image;
	private final int windowWidth = 800;
	private final int windowHeight = 600;

    ImagePanel(Image image) {
        this.image = image;
        setBounds(0, 0, windowWidth, windowHeight);
		setLayout(null);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }

}


