package frontEnd;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.SwingConstants;

public class MenuButton extends JButton {

	private static final long serialVersionUID = 1L;

	private Font font = new Font(Font.SANS_SERIF, Font.BOLD, 18);
	private final int ButtonWidth = 135;
	private final int ButtonHeight = 30;

	public MenuButton(String s, int x, int y) {
		super(s);

		MouseOver textColor = new MouseOver();

		setBounds(x, y, ButtonWidth, ButtonHeight);
		setBorderPainted(false);
		setContentAreaFilled(false);
		setFocusPainted(false);
		setForeground(Color.LIGHT_GRAY);
		setFont(font);
		setHorizontalAlignment(SwingConstants.LEFT);
		textColor.setButton(this);
		addMouseListener(textColor);

	}
}