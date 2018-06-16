package startScreen;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import editor.Editor;
import game.Game;

public class StartScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final int  SCREEN_WIDTH     = 400;
	private static final int  SCREEN_HEIGHT    = 200;
	
	private JButton btnStart;
	private JButton btnLoadLevel;
	private JButton btnCreateLevel;
	private JButton btnExit;
	
	public StartScreen() {
		setLookAndFeel();
		setTitle("Rayze");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		setLocationRelativeTo(null);
		setResizable(false);

		setLayout(new BorderLayout());
		JLabel bgPanel = new JLabel(new ImageIcon("res/bg_start_screen2.png"));
		add(bgPanel);
		bgPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 120));
		
		initComponents(bgPanel);
		
		setVisible(true);
	}
	
	private void initComponents(Container panelButtons) {
		btnStart = new JButton("Start");
		panelButtons.add(btnStart);
		
		btnStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Game();
				//setVisible(false);
			}
		});
		
		btnLoadLevel = new JButton("Load level");
		panelButtons.add(btnLoadLevel);
		
		btnLoadLevel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				int retVal = chooser.showOpenDialog(StartScreen.this);
			}
		});
		
		btnCreateLevel = new JButton("Create level");
		panelButtons.add(btnCreateLevel);
		
		btnCreateLevel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Editor();
			}
		});
		
		btnExit = new JButton("Exit");
		panelButtons.add(btnExit);
		
		btnExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}
	
	/**
	 * Sets system look and feel.
	 */
	private void setLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new StartScreen();
	}
}
