package frontEnd;

import java.awt.Color;
import java.awt.Image;

public class AboutPanel extends ImagePanel {
		
	private static final long serialVersionUID = 1L;

	public AboutPanel(Image image){
		super(image);
		setSize(450, 250);
		setLocation(300, 190);
		setBackground(new Color(255,255,255,180));
	}
}
