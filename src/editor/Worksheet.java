package editor;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Worksheet extends JPanel {

	private static final long serialVersionUID = 1L;

	public Worksheet() {
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
        g.setColor(Color.GREEN);
        g.fillRect(0, 0, Editor.SCREEN_WIDTH, Editor.SCREEN_HEIGHT);
        g.setColor(Color.BLACK);
        g.fillOval(100, 100, 30, 30);
	}
}
