package editor.controller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import common.Constants;
import editor.TileType;
import editor.model.EditorModel;
import editor.view.EditorView;
import utils.Reader;
import utils.Writer;

public class EditorController {
	
	private EditorModel model;
	private EditorView  view;
	
	private TileType currentTile;
	private String currentFile;
	
	public EditorController(EditorModel model, EditorView view) {
		this.model = model;
		this.view  = view;
		
		this.currentTile = TileType.getTypeFromValue(1);
		this.currentFile = null;
		
		addListenersForToolbarItems();
		addListenersForMenuItems();
		
		this.view.setWorksheetListener(new WorksheetListener());
	}
	
	/**
	 * Adds listeners for all toolbar buttons.
	 */
	private void addListenersForToolbarItems() {
		// Second parameter is index at toolbar, but it's not the same
		// as position in Enum TileType. In enums values are greater by 1
		this.view.setToolbarButtonListeners(new TileBtnListener(1), 0); // bluestone
		this.view.setToolbarButtonListeners(new TileBtnListener(2), 1); // greystone
		this.view.setToolbarButtonListeners(new TileBtnListener(3), 2); // redbrick
		this.view.setToolbarButtonListeners(new TileBtnListener(4), 3); // wood
		this.view.setToolbarButtonListeners(new TileBtnListener(5), 4); // player
		this.view.setToolbarButtonListeners(new TileBtnListener(0), 5); // remove
	}
	
	/**
	 * Adds listeners for all menu items.
	 */
	private void addListenersForMenuItems() {
		view.setMenuItemListener("New",        new NewListener());
		view.setMenuItemListener("Open",       new OpenListener());
		view.setMenuItemListener("Save",       new SaveListener());
		view.setMenuItemListener("Save as",    new SaveAsListener());
		view.setMenuItemListener("Exit",       new ExitListener());
		view.setMenuItemListener("About",      new AboutListener());
		view.setMenuItemListener("How to use", new HowToUseListener());
	}
	
	/**
	 * Opens Save As dialog and returns selected directory + file.
	 */
	private String openSaveAsDialog() {
		String filePath = null;
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File(Constants.mapsLocation));
		int retVal = chooser.showSaveDialog(chooser);

		if(retVal == JFileChooser.APPROVE_OPTION) {
			filePath = chooser.getSelectedFile().getAbsolutePath();
		}
		
		return filePath;
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
		}
	}
	
	/**
	 * Below are listeners for all menu items.
	 */
	class NewListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// IDEA: maybe create seperate class for this dialog ?
			JTextField mapWidth = new JTextField("10");
			JTextField mapHeight = new JTextField("10");
			Object[] message = {
					"Width:", mapWidth,
					"Height:", mapHeight
			};
			
			int option = JOptionPane.showConfirmDialog(null, message, "New level", JOptionPane.OK_CANCEL_OPTION);
			if (option == JOptionPane.OK_OPTION) {
				try {
					int w = Integer.parseInt(mapWidth.getText());
					int h = Integer.parseInt(mapHeight.getText());
					
					model.createNewMap(w, h);
					paint();
				} catch (NumberFormatException exc) {
					System.out.println("Wrong input arguments for width or height.");
				}
			}
		}
	}
	
	class OpenListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser chooser = new JFileChooser();
			chooser.setCurrentDirectory(new File(Constants.mapsLocation));
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Maps files", "maps");
			chooser.setFileFilter(filter);

			int retVal = chooser.showOpenDialog(chooser);
			if(retVal == JFileChooser.APPROVE_OPTION) {
				currentFile = chooser.getSelectedFile().getAbsolutePath();
				int[][] map = Reader.readMapFromFile(currentFile);

				model.setMap(map);
				paint();
				
				System.out.println("Operation OPEN: New current file is " + currentFile);
			}
		}
	}

	class SaveListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (currentFile == null) {
				if ((currentFile = openSaveAsDialog()) == null) {
					return;
				}
			}

			String fullPath = currentFile + Constants.mapFileFormat;
			Writer.writeMapToFile(model.getMap(), fullPath);
			System.out.println("Operation SAVE: File is successfully "
					+ "saved to the location " + fullPath);
		}
	}

	class SaveAsListener implements ActionListener {
		// TODO: check if currentFile already has extension .maps attached to it
		@Override
		public void actionPerformed(ActionEvent e) {
			currentFile = openSaveAsDialog();
			String fullPath = currentFile + Constants.mapFileFormat;
			Writer.writeMapToFile(model.getMap(), fullPath);
			
			System.out.println("Operation SAVE AS: Map saved. New current file is " + fullPath);
		}
	}

	class ExitListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			int retVal = JOptionPane.showConfirmDialog(null, "Are you sure?", 
									"Quit editor", JOptionPane.YES_NO_OPTION);
			
			if (retVal == JOptionPane.YES_OPTION)
				view.setVisible(false); // @Hack ?
			
			System.out.println("Operation EXIT executed");
		}
	}

	class AboutListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "Author: Boris Sulicenko, 2018",
										"About", JOptionPane.INFORMATION_MESSAGE);
			
			System.out.println("Operation ABOUT: activated");
		}
	}

	class HowToUseListener implements ActionListener {
		// TODO: write 'how to use' text
		@Override
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "How to use Rayze level editor\nWork in progress!",
										"How to use", JOptionPane.INFORMATION_MESSAGE);
			
			System.out.println("Operation HOW TO USE: activated");
		}
	}
}
