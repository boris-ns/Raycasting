package editor.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;

import utils.ImageLoader;

public class EditorView extends JFrame {

	private static final long serialVersionUID = 1L;
	public  static final int  SCREEN_WIDTH     = 1024;
	public  static final int  SCREEN_HEIGHT    = 768;
	
	private JMenuBar menuBar;
	private JToolBar toolbar;
	private JLabel labelStatusBar;
	private Worksheet worksheet;
	
	private HashMap<String, JMenuItem> menuItems;
	
	public EditorView() {
		menuItems = new HashMap<String, JMenuItem>();
		setTitle("Rayze - level editor");
		setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		setLocationRelativeTo(null);
		setBackground(Color.BLACK);
	
		initMenu(); 
		initToolbar();
		initWorksheet();
		addStatusBar();
		
		setVisible(true);
	}
	
	/**
	 * Initializes menu bar, menus and all menu items.
	 */
	private void initMenu() {
		menuBar = new JMenuBar();		
		JMenu fileMenu = new JMenu("File");
		JMenu helpMenu = new JMenu("Help");
		
		menuBar.add(fileMenu);
		menuBar.add(helpMenu);
		
		// Items for file menu
		addItemToMenu(fileMenu, "New");
		addItemToMenu(fileMenu, "Open");
		fileMenu.addSeparator();
		addItemToMenu(fileMenu, "Save");
		addItemToMenu(fileMenu, "Save as");
		fileMenu.addSeparator();
		addItemToMenu(fileMenu, "Exit");
		
		// Init items for help menu
		addItemToMenu(helpMenu, "About");
		addItemToMenu(helpMenu, "How to use");
		
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
	    addButtonToToolbar("res/icons/remove.jpg");
	    
	    Container contentPane = getContentPane();
	    contentPane.add(toolbar, BorderLayout.NORTH);
	}
	
	/**
	 * TODO: finish this scrollpane
	 */
	private void initWorksheet() {
		worksheet = new Worksheet();
		JScrollPane scroll = new JScrollPane(worksheet, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		add(scroll, BorderLayout.CENTER);
	}

	/**
	 * Creates status bar.
	 */
	private void addStatusBar() {
		JPanel statusPanel = new JPanel();
		labelStatusBar = new JLabel("Ready");
		statusPanel.add(labelStatusBar);		// TODO: maybe position it to the left ?
		add(statusPanel, BorderLayout.SOUTH);
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
	 * Adds new JMenuItem object to the parentMenu and in menuItems hashmap
	 */
	private void addItemToMenu(JMenu parentMenu, String itemName) {
		JMenuItem menuItem = new JMenuItem(itemName);
		menuItems.put(itemName, menuItem);
		parentMenu.add(menuItem);
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
	
	/**
	 * Returns Graphics object of worksheet component, 
	 * so controller can access it for drawing.
	 */
	public Graphics getWorksheetGraphics() {
		return worksheet.getGraphics();
	}
	
	/**
	 * Sets action listeners for menu items
	 */
	public void setMenuItemListener(String menuKey, ActionListener al) {
		JMenuItem menuItem = menuItems.get(menuKey);
		menuItem.addActionListener(al);
	}
	
	/**
	 * Adds component listener to the frame. Main purpose is 
	 * to update frame when resizing.
	 */
	public void setComponentListener(ComponentListener cl) {
		addComponentListener(cl);
	}
	
	/**
	 * Sets text to the status bar label.
	 */
	public void setStatusBarText(String text) {
		labelStatusBar.setText(text);
	}
}
