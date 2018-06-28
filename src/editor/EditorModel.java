package editor;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import utils.ImageLoader;

public class EditorModel {

	private int[][] map;
	private ArrayList<BufferedImage> tiles;
	
	public EditorModel() {
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
		try {
			map[x][y] = type.getValue();
			
		} catch(IndexOutOfBoundsException e) {
			System.out.println("Index out of bounds!");
			e.printStackTrace();
		}
	}
	
	public BufferedImage getTileAtIndex(int index) {
		return tiles.get(index);
	}
	
	public int[][] getMap() {
		return map;
	}
}
