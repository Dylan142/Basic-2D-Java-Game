package com.AGameStudio.NewMech.gfx;

import java.util.Random;

import com.AGameStudio.NewMech.Entity.environment.Entity;

public class Screen {
	private Random random = new Random();
	
	public int width, height;
	public final int MAP_WIDTH = 64;
	public final int MAP_WIDTH_MASK = MAP_WIDTH - 1;
	public int[] pixels;
	public int[] tiles = new int[MAP_WIDTH * MAP_WIDTH];
	public int xOffset, yOffset;
	
	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		
		for(int i = 0; i < tiles.length; i++) {
			tiles[i] = random.nextInt(0xffffff);
			tiles[0] = 0;
		}
	}
	
	public void clear(int color) {
		for(int i = 0; i < pixels.length; i++) {
			pixels[i] = color;
		}
	}
	
	public void setOffsets(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
	public int getXOffset() {
		return xOffset;
	}
	
	public int getYOffset() {
		return yOffset;
	}
	
	public void renderSprite(int xp, int yp, Sprite sprite, boolean fixed, int flip) {
		if(fixed) {
			xp -= xOffset;
			yp -= yOffset;
		}
		
		for(int y = 0; y < sprite.getHeight(); y++) {
			int ya = y + yp;
			int ys = y;
			if(flip == 2 || flip == 3) ys = sprite.getHeight() - 1 - y;
			for(int x = 0; x < sprite.getWidth(); x++) {
				int xa = x + xp;
				int xs = x;
				if(flip == 1 || flip == 3) xs  = sprite.getWidth() - 1 - x;
				if(xa < 0 || xa >= width || ya < 0 || ya >= height) continue;
				int col = sprite.pixels[xs + ys * sprite.getWidth()];
				if(col != 0xffff40ff && col != 0xffadadad) pixels[xa + ya * width] = col;
			}
		}
	}
	
	public void renderEntity(int xp, int yp, Entity entity, boolean fixed) {
		if(fixed) {
			xp -= xOffset;
			yp -= yOffset;
		}
		
		for(int y = 0; y < entity.getHeight(); y++) {
			int ya = y + yp;
			for(int x = 0; x < entity.getWidth(); x++) {
				int xa = x + xp;
				if(xa < 0 || xa >= width || ya < 0 || ya >= height) break;
				int col = entity.getSprite().pixels[x + y * entity.getWidth()];
				if(col != 0xffff40ff) pixels[xa + ya * width] = col;
			}
		}
	}
	
	public void renderText(int xp, int yp, Sprite sprite, boolean fixed, int flip) {
		if(fixed) {
			xp -= xOffset;
			yp -= yOffset;
		}
		
		for(int y = 0; y < sprite.getHeight(); y++) {
			int ya = y + yp;
			int ys = y;
			if(flip == 2) ys = sprite.getHeight() - 1 - y;
			for(int x = 0; x < sprite.getWidth(); x++) {
				int xa = x + xp;
				if(xa < 0 || ya < 0 || xa >= width || ya >= height) continue;
				int col = sprite.pixels[x + ys * sprite.getWidth()];
				if(col == 0xffffffff) col = 0xff404040;
				if(col != 0xffff40ff) pixels[xa + ya * width] = col;
			}
		}
	}
}
