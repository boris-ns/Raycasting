package editor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;

import utils.ImageLoader;

public class Editor extends JFrame {

	private static final long serialVersionUID = 1L;
	public static final int SCREEN_WIDTH  = 1024;
	public static final int SCREEN_HEIGHT = 768;

	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenu helpMenu;
	
	private Worksheet worksheet;
	
	public Editor() {
		setTitle("Rayze - level editor");
		setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBackground(Color.BLACK);
	
		initMenu();
		initToolbar();
		
		worksheet = new Worksheet();
		add(worksheet, BorderLayout.CENTER);
		
		setVisible(true);
	}
	
	/**
	 * Initializes menu bar, menus and all menu items.
	 */
	private void initMenu() {
		menuBar = new JMenuBar();
		
		fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		
		helpMenu = new JMenu("Help");
		menuBar.add(helpMenu);
		
		// Init items for File menu
		// @TODO: add action listeners, keyboard shortcuts, and show those shortcuts in menu
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
	 * Initializes toolbar and it's buttons.
	 */
	private void initToolbar() {
		JToolBar toolbar = new JToolBar();
	    toolbar.setRollover(true);
	    
	    // @TODO: Add action listeners!
	    
	    JButton btnTexture1 = new JButton();
	    btnTexture1.setIcon(new ImageIcon(ImageLoader.loadImage("res/icons/bluestone.jpg")));
	    toolbar.add(btnTexture1);
	    
	    JButton btnTexture2 = new JButton();
	    btnTexture2.setIcon(new ImageIcon(ImageLoader.loadImage("res/icons/greystone.jpg")));
	    toolbar.add(btnTexture2);
	    
	    JButton btnTexture3 = new JButton();
	    btnTexture3.setIcon(new ImageIcon(ImageLoader.loadImage("res/icons/redbrick.jpg")));
	    toolbar.add(btnTexture3);
	    
	    JButton btnTexture4 = new JButton();
	    btnTexture4.setIcon(new ImageIcon(ImageLoader.loadImage("res/icons/wood.jpg")));
	    toolbar.add(btnTexture4);
	    
	    Container contentPane = getContentPane();
	    contentPane.add(toolbar, BorderLayout.NORTH);
	}
	
	public static void main(String[] args) {
		new Editor();
	}
}
