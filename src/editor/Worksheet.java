package editor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class Worksheet extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final int  TILE_SIZE = 32;
	
	public static int[][] map;

	public Worksheet(int mapWidth, int mapHeight) {
		map = new int[mapWidth][mapHeight];

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int mapX = Math.floorDiv(e.getX(), TILE_SIZE);
				int mapY = Math.floorDiv(e.getY(), TILE_SIZE);

				try {
					Worksheet.map[mapX][mapY] = 1;
        		} catch (ArrayIndexOutOfBoundsException exc) {
        			System.out.println("Tile out of map range! Your coordinates: " + mapX + " " + mapY);
        		}
				
				repaint();
			}
		});
	}

	/** 
	 * Paints all tiles to the canvas according to the
	 * current "brush" in Editor class. (brush is a tile)
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	
        for (int i = 0; i < map.length; ++i) {
        	for (int j = 0; j < map[i].length; ++j) {
        		// TODO: change with real textures, not colors
        		if (map[i][j] == 0)
        			g.setColor(Color.GRAY);
        		else if (map[i][j] == 1)
        			g.drawImage(Editor.currentTile, i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE, null);

        		g.fillRect(i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        	}
        }
	}
}
