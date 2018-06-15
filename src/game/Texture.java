package game;

import java.awt.image.BufferedImage;

import utils.ImageLoader;

public class Texture {

	private int pixels[];
	private int size;
	private String path;
	
	public Texture(String path, int size) {
		this.size = size;
		this.path = path;
		pixels = new int[size * size];
		
		BufferedImage image = ImageLoader.loadBufferedImage(path);
		image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());
	}
	
	public int[] getPixels() {
		return pixels;
	}
}
