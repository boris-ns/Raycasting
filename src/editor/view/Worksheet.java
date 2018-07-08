package editor.view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

import common.Constants;

public class Worksheet extends JComponent {

	private static final long serialVersionUID = 1L;
	
	public Worksheet() {
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, Constants.DEFAULT_WORKSHEET_SIZE, Constants.DEFAULT_WORKSHEET_SIZE);
	}
}
