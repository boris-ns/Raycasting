package editor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import common.Constants;
import utils.Writer;

public class EditorController {
	
	private EditorModel model;
	private EditorView  view;
	
	private TileType currentTile;
	
	public EditorController(EditorModel model, EditorView view) {
		this.model = model;
		this.view  = view;
		
		this.currentTile = TileType.getTypeFromValue(1);
		
		// Second parameter is index at toolbar, but it's not the same
		// as position in Enum TileType. In enums values are greater by 1
		this.view.setToolbarButtonListeners(new TileBtnListener(1), 0); // bluestone
		this.view.setToolbarButtonListeners(new TileBtnListener(2), 1); // greystone
		this.view.setToolbarButtonListeners(new TileBtnListener(3), 2); // redbrick
		this.view.setToolbarButtonListeners(new TileBtnListener(4), 3); // wood
		this.view.setToolbarButtonListeners(new TileBtnListener(5), 4); // player
		
		this.view.setWorksheetListener(new WorksheetListener());
	}
	
	/**
	 * Class represents listener for buttons that are located 
	 * in the toolbar. btnVal is a number of tile type. 
	 */
	class TileBtnListener implements ActionListener {
		private int btnVal;
		
		public TileBtnListener(int btnVal) {
			this.btnVal = btnVal;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			currentTile = TileType.getTypeFromValue(btnVal);
			System.out.println("New tile is: " + currentTile);
		}
	}
	
	/**
	 * Listener for worksheet. This class is responsible for all the drawings.
	 */
	class WorksheetListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			int mapX = Math.floorDiv(e.getX(), Constants.TILE_SIZE);
			int mapY = Math.floorDiv(e.getY(), Constants.TILE_SIZE);
			
			model.setTileToMap(mapX, mapY, currentTile);
			paint();
			
			// This is here for now just for testing purposes
			Writer.writeMapToFile(model.getMap(), Constants.mapsLocation + "testMap.txt");
		}
		
		/**
		 * Paints whole map to the worksheet.
		 */
		private void paint() {
			Graphics g = view.getWorksheetGraphics();
			int map[][] = model.getMap();
			final int TILE_SIZE = Constants.TILE_SIZE;
			
			for (int i = 0; i < map.length; ++i) {
	        	for (int j = 0; j < map[i].length; ++j) {
	        		if (map[i][j] == 0) {
	        			g.setColor(Color.GRAY);
	        			g.fillRect(i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE);
	        		} else {
	        			g.drawImage(model.getTileAtIndex(map[i][j] - 1), i * TILE_SIZE, 
	        						j * TILE_SIZE, TILE_SIZE, TILE_SIZE, null);
	        		}
	        	}
	        }
		}
	}
	
}
