package editor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;

import utils.ImageLoader;

public class EditorView extends JFrame {

	private static final long serialVersionUID    = 1L;
	public static final int   SCREEN_WIDTH        = 1024;
	public static final int   SCREEN_HEIGHT       = 768;
	
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenu helpMenu;
	private JToolBar toolbar;
	private Worksheet worksheet;
	
	public EditorView() {
		setTitle("Rayze - level editor");
		setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		setLocationRelativeTo(null);
		setBackground(Color.BLACK);
	
		//initMenu(); // TODO: finish
		initToolbar();
		
		worksheet = new Worksheet();
		add(worksheet, BorderLayout.CENTER);
		
		setVisible(true);
	}
	
	/**
	 * TODO: add menu to MVC pattern
	 * Initializes menu bar, menus and all menu items.
	 */
	private void initMenu() {
		menuBar = new JMenuBar();
		
		fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		
		helpMenu = new JMenu("Help");
		menuBar.add(helpMenu);
		
		JMenuItem itemNew = new JMenuItem("New");
		fileMenu.add(itemNew);
		
		JMenuItem itemOpen = new JMenuItem("Open");
		fileMenu.add(itemOpen);
		
		fileMenu.addSeparator();
		
		JMenuItem itemSave = new JMenuItem("Save");
		fileMenu.add(itemSave);
		
		JMenuItem itemSaveAs = new JMenuItem("Save as");
		fileMenu.add(itemSaveAs);
		
		fileMenu.addSeparator();
		
		JMenuItem itemExit = new JMenuItem("Exit");
		fileMenu.add(itemExit);

		// Init items for help menu
		JMenuItem itemAbout = new JMenuItem("About");
		helpMenu.add(itemAbout);
		
		JMenuItem itemHowTo = new JMenuItem("How to use");
		helpMenu.add(itemHowTo);
		
		this.setJMenuBar(menuBar);
	}
	
	/**
	 * Initializes toolbar and all it's buttons. 
	 * Buttons represent tiles(textures) you use to draw a level.
	 */
	private void initToolbar() {
		toolbar = new JToolBar();
	    toolbar.setRollover(true);
	    
	    addButtonToToolbar("res/icons/bluestone.jpg");
	    addButtonToToolbar("res/icons/greystone.jpg");
	    addButtonToToolbar("res/icons/redbrick.jpg");
	    addButtonToToolbar("res/icons/wood.jpg");
	    addButtonToToolbar("res/icons/player.jpg");
	    
	    Container contentPane = getContentPane();
	    contentPane.add(toolbar, BorderLayout.NORTH);
	}
	
	/**
	 * Creates new buttons and places it in the toolbar.
	 */
	private void addButtonToToolbar(String iconPath) {
		JButton btn = new JButton();
		btn.setIcon(new ImageIcon(ImageLoader.loadImage(iconPath)));
	    toolbar.add(btn);
	}
	
	/**
	 * Sets action listener for buttons at position of toolbar.
	 * Returns if index is invalid.
	 */
	public void setToolbarButtonListeners(ActionListener al, int position) {
		Component button = toolbar.getComponentAtIndex(position);
		
		if (button == null) {
			System.out.println("Button with index " + position + " in toolbar, doesn't exist!");
			return;
		}
		
		JButton temp = (JButton) button;
		temp.addActionListener(al);
	}
	
	/**
	 * Sets mouse listener to the worksheet.
	 */
	public void setWorksheetListener(MouseAdapter mp) {
		this.worksheet.addMouseListener(mp);
	}
	
	public Graphics getWorksheetGraphics() {
		return worksheet.getGraphics();
	}
}
