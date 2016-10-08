package com.AGameStudio.NewMech.gfx;


public class Sprite extends Sprites{
	
	public int x, y;
	private final int width, height;
	public int[] pixels;
	protected SpriteSheet sheet;
	
	public Sprite(SpriteSheet sheet, int x, int y, int width, int height) {
		this.sheet = sheet;
		this.x = x * width;
		this.y = y * width;
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		load();
	}
	
	public Sprite(SpriteSheet sheet, int width, int height) {
		this.sheet = sheet;
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		load();
	}
	
	public Sprite(int[] pixels, int width, int height) {
		this.pixels = pixels;
		this.width = width;
		this.height = height;
	}
	
	public Sprite(int width, int height, int color) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		for(int i = 0; i < pixels.length; i++) {
			pixels[i] = color;
		}
	}
	
	public void load() {
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				pixels[x + y * width] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.width];
			}
		}
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
}
