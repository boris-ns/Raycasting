package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Camera implements KeyListener {

	private static final double MOVE_SPEED     = 0.08;
	private static final double ROTATION_SPEED = 0.045;
	
	private double xPos, yPos; // position of the player
	private double xDir, yDir; // directions where player is pointing
	private double xPlane, yPlane; // camera
	private boolean keyUpPressed, keyDownPressed,
					keyLeftPressed, keyRightPressed; // keys pressed
	
	
	public Camera(double xPos, double yPos, double xDir, double yDir,
					double xPlane, double yPlane) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.xDir = xDir;
		this.yDir = yDir;
		this.xPlane = xPlane;
		this.yPlane = yPlane;
	}
	
	public void update(int[][] map) {
		if (keyUpPressed) {
			if (map[(int)(xPos + xDir * MOVE_SPEED)][(int)yPos] == 0)
				xPos += xDir * MOVE_SPEED;
		
			if (map[(int)xPos][(int)(yPos + yDir * MOVE_SPEED)] == 0) 
				yPos += yDir * MOVE_SPEED;
		}
		
		if (keyDownPressed) {
			if (map[(int)(xPos - xDir * MOVE_SPEED)][(int)yPos] == 0)
				xPos -= xDir * MOVE_SPEED;
		
			if (map[(int)xPos][(int)(yPos - yDir * MOVE_SPEED)] == 0) 
				yPos -= yDir * MOVE_SPEED;
		}
		
		if (keyRightPressed) {
			double oldxDir=xDir;
			xDir=xDir*Math.cos(-ROTATION_SPEED) - yDir*Math.sin(-ROTATION_SPEED);
			yDir=oldxDir*Math.sin(-ROTATION_SPEED) + yDir*Math.cos(-ROTATION_SPEED);
			double oldxPlane = xPlane;
			xPlane=xPlane*Math.cos(-ROTATION_SPEED) - yPlane*Math.sin(-ROTATION_SPEED);
			yPlane=oldxPlane*Math.sin(-ROTATION_SPEED) + yPlane*Math.cos(-ROTATION_SPEED);
		}
		
		if (keyLeftPressed) {
			double oldxDir=xDir;
			xDir=xDir*Math.cos(ROTATION_SPEED) - yDir*Math.sin(ROTATION_SPEED);
			yDir=oldxDir*Math.sin(ROTATION_SPEED) + yDir*Math.cos(ROTATION_SPEED);
			double oldxPlane = xPlane;
			xPlane=xPlane*Math.cos(ROTATION_SPEED) - yPlane*Math.sin(ROTATION_SPEED);
			yPlane=oldxPlane*Math.sin(ROTATION_SPEED) + yPlane*Math.cos(ROTATION_SPEED);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
		case KeyEvent.VK_W:
			keyUpPressed = true;
			break;
			
		case KeyEvent.VK_DOWN:
		case KeyEvent.VK_S:
			keyDownPressed = true;
			break;
			
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_A:
			keyLeftPressed = true;
			break;
			
		case KeyEvent.VK_RIGHT:
		case KeyEvent.VK_D:
			keyRightPressed = true;
			break;
			
		default:
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
		case KeyEvent.VK_W:
			keyUpPressed = false;
			break;
			
		case KeyEvent.VK_DOWN:
		case KeyEvent.VK_S:
			keyDownPressed = false;
			break;
			
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_A:
			keyLeftPressed = false;
			break;
			
		case KeyEvent.VK_RIGHT:
		case KeyEvent.VK_D:
			keyRightPressed = false;
			break;
			
		default:
			break;
		}
	}

	/**
	 * Unused!
	 */
	@Override
	public void keyTyped(KeyEvent e) {
	}

	public double getxPos() {
		return xPos;
	}

	public double getyPos() {
		return yPos;
	}

	public double getxDir() {
		return xDir;
	}

	public double getyDir() {
		return yDir;
	}

	public double getxPlane() {
		return xPlane;
	}

	public double getyPlane() {
		return yPlane;
	}
}
