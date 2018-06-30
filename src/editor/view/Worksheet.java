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
		int size = Constants.DEFAULT_MAP_SIZE * Constants.TILE_SIZE;
		
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, size, size);
	}
}
