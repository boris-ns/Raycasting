package editor.model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import common.Constants;
import editor.TileType;
import utils.ImageLoader;

public class EditorModel {
	
	private int[][] map;
	private ArrayList<BufferedImage> tiles;
	
	public EditorModel() {
		map = new int[Constants.DEFAULT_MAP_SIZE][Constants.DEFAULT_MAP_SIZE];
		tiles = new ArrayList<BufferedImage>();
		loadTiles();
	}
	
	/**
	 * Loads all images (tiles) and stores them in array list (tiles)
	 */
	private void loadTiles() {
		tiles.add(ImageLoader.loadBufferedImage("res/icons/bluestone.jpg"));
		tiles.add(ImageLoader.loadBufferedImage("res/icons/greystone.jpg"));
		tiles.add(ImageLoader.loadBufferedImage("res/icons/redbrick.jpg"));
		tiles.add(ImageLoader.loadBufferedImage("res/icons/wood.jpg"));
		tiles.add(ImageLoader.loadBufferedImage("res/icons/player.jpg"));
	}
	
	public void setTileToMap(int x, int y, TileType type) {
		// TODO: if player already exists, don't add him again
		try {
			map[x][y] = type.getValue();
		} catch(IndexOutOfBoundsException e) {
			System.out.println("Index out of bounds! X: " + x + " Y: " + y + " tile type: " + type);
		}
	}
	
	public void createNewMap(int width, int height) {
		map = new int[width][height];
	}
	
	public BufferedImage getTileAtIndex(int index) {
		return tiles.get(index);
	}
	
	public int[][] getMap() {
		return map;
	}
	
	public void setMap(int[][] map) {
		this.map = map;
	}
}
