package com.AGameStudio.NewMech.level;

import java.util.Random;

public class GenorateLevel {
	
	private int width, height;
	private int probability, count;
	private Random random = new Random();
	private Level level;
	private Tile current;
	
	public GenorateLevel(int width, int height, Level level) {
		this.width = width;
		this.height = height;
		this.level = level;
	}
	
	public byte[] genLevel(int startX, int startY, Tile start) {
		byte[] data = new byte[(startX + width) + (startY + height) * width];
		
		
		return genLoop(startX, startY, data, start);
	}
	
	public byte[] genLoop(int startX, int startY, byte[] data, Tile start) {
		for(int y = startY; y < startY + height; y++) {
			count++;
			for(int x = startX; x < startX + width; x++) {
				count++;
				int i = x + y * width;
				
				if(x == startX || y == startY) {
					data[i] = start.id;
					current = start;
				}
				
				else if(random.nextInt(probability) == 0 && count > 0) {
					current = getRelative(current);
					data[i] = current.id;
					probability = 100;
					count = -30;
				}
				
				else data[i] = current.id;
			}
		}
		
		return data;
	}
	
	public Tile getRelative(Tile tile) {
		if(tile.id == 0) return Tile.voidTile;
		else if(tile.id == 1) return Tile.grass;
		else if(tile.id == 2) return Tile.dirt;
		return Tile.voidTile;
	}
}
