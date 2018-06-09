package engine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

public class Main extends JFrame implements Runnable {

	private static final long serialVersionUID = 1L;
	private static final int MAP_WIDTH  = 15;
	private static final int MAP_HEIGHT = 15;
	private static final int SCREEN_WIDTH  = 640;
	private static final int SCREEN_HEIGHT = 480;

	private Thread thread;
	private boolean running;
	
	private int pixels[];
	private BufferedImage image; 
	
	public static int[][] map = 
	{
		{1,1,1,1,1,1,1,1,2,2,2,2,2,2,2},
		{1,0,0,0,0,0,0,0,2,0,0,0,0,0,2},
		{1,0,3,3,3,3,3,0,0,0,0,0,0,0,2},
		{1,0,3,0,0,0,3,0,2,0,0,0,0,0,2},
		{1,0,3,0,0,0,3,0,2,2,2,0,2,2,2},
		{1,0,3,0,0,0,3,0,2,0,0,0,0,0,2},
		{1,0,3,3,0,3,3,0,2,0,0,0,0,0,2},
		{1,0,0,0,0,0,0,0,2,0,0,0,0,0,2},
		{1,1,1,1,1,1,1,1,4,4,4,0,4,4,4},
		{1,0,0,0,0,0,1,4,0,0,0,0,0,0,4},
		{1,0,0,0,0,0,1,4,0,0,0,0,0,0,4},
		{1,0,0,2,0,0,1,4,0,3,3,3,3,0,4},
		{1,0,0,0,0,0,1,4,0,3,3,3,3,0,4},
		{1,0,0,0,0,0,0,0,0,0,0,0,0,0,4},
		{1,1,1,1,1,1,1,4,4,4,4,4,4,4,4}
	};
	
	public Main() {
		thread = new Thread(this);
		image = new BufferedImage(SCREEN_WIDTH, SCREEN_HEIGHT, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData(); // "Connect" image and pixels
		
		// Init JFrame
		setTitle("Raycasting");
		setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBackground(Color.BLACK);
		setVisible(true);
		
		start();
	}
	
	private synchronized void start() {
		running = true;
		thread.start();
	}
	
	private synchronized void stop() {
		running = false;
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void render() {
		BufferStrategy bs = getBufferStrategy();
		
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
		bs.show();
	}

	@Override
	public void run() {
		long lastTime = System.nanoTime();
		final double ns = 1000000000.0 / 60.0;  
		double delta = 0;
		requestFocus();
		
		while(running) {
			long now = System.nanoTime();
			delta = delta + ((now-lastTime) / ns);
			lastTime = now;
			
			while (delta >= 1) { 
				delta--;
			}

			render(); 
		}
	}
	
	public static void main(String[] args) {
		new Main();
	}
}
