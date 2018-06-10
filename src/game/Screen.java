package game;

import java.awt.Color;
import java.util.ArrayList;

public class Screen {
	
	private int[][] map;
	private ArrayList<Texture> textures;
	
	public Screen(int[][] map, ArrayList<Texture> textures) {
		this.map = map;
		this.textures = textures;
	}
	
	public int[] update(Camera cam, int[] pixels) {
		paintBackground(pixels);
		
		for (int x = 0; x < Game.SCREEN_WIDTH; ++x) {
			int mapX = (int) cam.getxPos();
			int mapY = (int) cam.getyPos();
			
			double cameraX = 2 * x / (double)Game.SCREEN_WIDTH - 1;
			double rayDirX = cam.getxDir() + cam.getxPlane() * cameraX;
			double rayDirY = cam.getyDir() + cam.getyPlane() * cameraX;
		
			double sideDistX = 0.0;
			double sideDistY = 0.0;
			
			double deltaDistX = Math.sqrt(1 + (rayDirY * rayDirY) / (rayDirX * rayDirX));
			double deltaDistY = Math.sqrt(1 + (rayDirX * rayDirX) / (rayDirY * rayDirY));
			
			double perpWallDist = 0.0;
			int stepX = 0, stepY = 0;
			boolean wallHit = false;
			int hitSide = 0; // vertical or horizontal side of box is hit
			
			if (rayDirX < 0) {
			    stepX = -1;
			    sideDistX = (cam.getxPos() - mapX) * deltaDistX;
			} else {
			    stepX = 1;
			    sideDistX = (mapX + 1.0 - cam.getxPos()) * deltaDistX;
			}
			
			if (rayDirY < 0) {
			    stepY = -1;
			    sideDistY = (cam.getyPos() - mapY) * deltaDistY;
			} else {
			    stepY = 1;
			    sideDistY = (mapY + 1.0 - cam.getyPos()) * deltaDistY;
			}
			
			while (!wallHit) {
				if (sideDistX < sideDistY) {
					sideDistX += deltaDistX;
					mapX += stepX;
					hitSide = 0;
				} else {
					sideDistY += deltaDistY;
					mapY += stepY;
					hitSide = 1;
				}
				
				if (map[mapX][mapY] != 0)
					wallHit = true;
			}
			
			//Calculate distance to the point of impact
			if(hitSide==0)
			    perpWallDist = Math.abs((mapX - cam.getxPos() + (1 - stepX) / 2) / rayDirX);
			else
			    perpWallDist = Math.abs((mapY - cam.getyPos() + (1 - stepY) / 2) / rayDirY);    
			
			//Now calculate the height of the wall based on the distance from the camera
			int lineHeight;
			if(perpWallDist > 0) lineHeight = Math.abs((int)(Game.SCREEN_HEIGHT / perpWallDist));
			else lineHeight = Game.SCREEN_HEIGHT;
			//calculate lowest and highest pixel to fill in current stripe
			int drawStart = -lineHeight / 2 +  Game.SCREEN_HEIGHT / 2;
			
			if(drawStart < 0)
			    drawStart = 0;
			
			int drawEnd = lineHeight / 2 +  Game.SCREEN_HEIGHT / 2;
			
			if(drawEnd >=  Game.SCREEN_HEIGHT) 
			    drawEnd =  Game.SCREEN_HEIGHT - 1;
			
			// What texture to draw ?
			int textNum = map[mapX][mapY] - 1;
			double wallX = 0.0;
			
			if (hitSide == 0) {
				wallX = (cam.getyPos() + ((mapX - cam.getxPos() + (1 - stepX) / 2) / rayDirX) * rayDirY);
			} else {
				wallX = (cam.getxPos() + ((mapY - cam.getyPos() + (1 - stepY) / 2) / rayDirY) * rayDirX);
			}
			
			wallX -= Math.floor(wallX);
			
			int texX = (int)(wallX * Game.TEXTURE_SIZE);
			if ((hitSide == 0 && rayDirX > 0) || (hitSide == 1 && rayDirY < 0))
				texX = Game.TEXTURE_SIZE - texX - 1;

			for(int y=drawStart; y<drawEnd; y++) {
			    int texY = (((y*2 - Game.SCREEN_HEIGHT + lineHeight) << 6) / lineHeight) / 2;
			    int color;
			    
			    if(hitSide == 0) 
			    	color = textures.get(textNum).getPixels()[texX + (texY * Game.TEXTURE_SIZE)];
			    else 
			    	color = (textures.get(textNum).getPixels()[texX + (texY * Game.TEXTURE_SIZE)]>>1) & 8355711;//Make y sides darker
			    
			    pixels[x + y * (Game.SCREEN_WIDTH)] = color;
			}
		}
		
		return pixels;
	}
	
	/**
	 * Clears the screen by painting it in 2 colors.
	 * Top color is gray, bottom color is dark gray.
	 * Paints it in 2 colors for nicer effect (floor and ceiling).
	 * 
	 * @param pixels - representation of the screen
	 */
	private void paintBackground(int[] pixels) {
		for (int i = 0; i < pixels.length / 2; ++i) 
			pixels[i] = Color.GRAY.getRGB();
		
		for (int i = pixels.length / 2; i < pixels.length; ++i)
			pixels[i] = Color.DARK_GRAY.getRGB();
	}
}
