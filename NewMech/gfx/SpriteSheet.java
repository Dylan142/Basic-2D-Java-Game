package com.AGameStudio.NewMech.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	
	public final int width, height;
	private String path;
	public int[] pixels;
	
	//SpriteSheets
	public static SpriteSheet player = new SpriteSheet("/sheets/player_sheet.png", 256, 320);
	public static SpriteSheet playerDown = new SpriteSheet(player, 0, 0, 1, 3, 64);
	public static SpriteSheet playerUp = new SpriteSheet(player, 1, 0, 1, 3, 64);
	public static SpriteSheet playerLeft = new SpriteSheet(player, 3, 0, 1, 3, 64);
	public static SpriteSheet playerRight = new SpriteSheet(player, 2, 0, 1, 3, 64);
	
	public static SpriteSheet jack = new SpriteSheet("/sheets/jack_sheet.png", 256, 192);
	public static SpriteSheet jackUp = new SpriteSheet(jack, 0, 0, 1, 3, 64);
	public static SpriteSheet jackDown = new SpriteSheet(jack, 1, 0, 1, 3, 64);
	public static SpriteSheet jackLeft = new SpriteSheet(jack, 2, 0, 1, 3, 64);
	public static SpriteSheet jackRight = new SpriteSheet(jack, 3, 0, 1, 3, 64);
	
	public static SpriteSheet squirrel = new SpriteSheet("/sheets/mob_sheet.png", 512, 512);
	public static SpriteSheet squirrelUp = new SpriteSheet(squirrel, 3, 0, 1, 3, 32);
	public static SpriteSheet squirrelDown = new SpriteSheet(squirrel, 2, 0, 1, 3, 32);
	public static SpriteSheet squirrelLeft = new SpriteSheet(squirrel, 0, 0, 1, 3, 32);
	public static SpriteSheet squirrelRight = new SpriteSheet(squirrel, 1, 0, 1, 3, 32);
	
	public static SpriteSheet bandit = new SpriteSheet("/sheets/mob_sheet.png", 512, 512);
	public static SpriteSheet banditUp = new SpriteSheet(bandit, 7, 0, 1, 3, 32, 64);
	public static SpriteSheet banditDown = new SpriteSheet(bandit, 6, 0, 1, 3, 32, 64);
	public static SpriteSheet banditLeft = new SpriteSheet(bandit, 4, 0, 1, 3, 32, 64);
	public static SpriteSheet banditRight = new SpriteSheet(bandit, 5, 0, 1, 3, 32, 64);
	
	
	public static SpriteSheet enemy = new SpriteSheet("/sheets/enemy_sheet.png", 128,256);
	
	public static SpriteSheet npc = new SpriteSheet("/sheets/npc_sheet.png", 192, 192);
	public static SpriteSheet mob = new SpriteSheet("/sheets/mob_sheet.png", 512, 512);
	
	public static SpriteSheet terrain = new SpriteSheet("/sheets/terrain_sheet.png", 192, 192);
	public static SpriteSheet weapon = new SpriteSheet("/sheets/weapon_sheet.png", 192, 256);
	public static SpriteSheet fist_ver = new SpriteSheet(weapon, 0, 0, 2, 1, 32, 64);
	public static SpriteSheet fist_hor = new SpriteSheet(weapon, 0, 1, 2, 1, 32, 64);
	
	public static SpriteSheet font = new SpriteSheet("/sheets/font_sheet.png", 224, 24);
	public static SpriteSheet font2x = new SpriteSheet("/sheets/font_sheet2.png", 448, 48);
	
	private Sprite[] sprites;
	
	public SpriteSheet(String path, int width, int height) {
		this.path = path;
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		load();
	}
	
	public SpriteSheet(SpriteSheet sheet, int x, int y, int width, int height, int spriteSize) {
		int xx = x * spriteSize;
		int yy = y * spriteSize;
		int w = width * spriteSize;
		int h = height * spriteSize;
		this.width = w;
		this.height = h;
		pixels = new int[w * h];
		
		for(int y0 = 0; y0 < h; y0++) {
			int yp = yy + y0;
			for(int x0 = 0; x0 < w; x0++) {
				int xp = xx + x0;
				pixels[x0 + y0 * w] = sheet.pixels[xp + yp * sheet.width];
			}
		}
		
		int frame = 0;
		sprites = new Sprite[width * height];
		for(int ya = 0; ya < height; ya++) {
			for(int xa = 0; xa < width; xa++) {
				int[] spritePixels = new int[spriteSize * spriteSize];
				for(int y0 = 0; y0 < spriteSize; y0++) {
					for(int x0 = 0; x0 < spriteSize; x0++) {
						spritePixels[x0 + y0 * spriteSize] = pixels[(x0 + xa * spriteSize) + (y0 + ya * spriteSize)
								* w];
					}
				}
				Sprite sprite = new Sprite(spritePixels, spriteSize, spriteSize);
				sprites[frame++] = sprite;
			}
		}
		
	}
	
	public SpriteSheet(SpriteSheet sheet, int x, int y, int width, int height, int spriteW, int spriteH) {
		int xx = x * spriteW;
		int yy = y * spriteH;
		int w = width * spriteW;
		int h = height * spriteH;
		this.width = w;
		this.height = h;
		pixels = new int[w * h];
		
		for(int y0 = 0; y0 < h; y0++) {
			int yp = yy + y0;
			for(int x0 = 0; x0 < w; x0++) {
				int xp = xx + x0;
				pixels[x0 + y0 * w] = sheet.pixels[xp + yp * sheet.width];
			}
		}
		
		int frame = 0;
		sprites = new Sprite[width * height];
		for(int ya = 0; ya < height; ya++) {
			for(int xa = 0; xa < width; xa++) {
				int[] spritePixels = new int[spriteW * spriteH];
				for(int y0 = 0; y0 < spriteH; y0++) {
					for(int x0 = 0; x0 < spriteW; x0++) {
						spritePixels[x0 + y0 * spriteW] = pixels[(x0 + xa * spriteW) + (y0 + ya * spriteH)
								* w];
					}
				}
				Sprite sprite = new Sprite(spritePixels, spriteW, spriteH);
				sprites[frame++] = sprite;
			}
		}
		
	}
	
	public Sprite[] getSprites() {
		return sprites;
	}
	
	public void load() {
		try{
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			int w = image.getWidth();
			int h = image.getHeight();
			image.getRGB(0, 0, w, h, pixels, 0, w);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
