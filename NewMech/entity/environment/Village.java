package com.AGameStudio.NewMech.Entity.environment;


public class Village extends Entity {
	
	private int width, height;
	private int population;
	private int houses;
	private boolean constructed = false;
	
	public Village(int x, int y, int width, int height, boolean bool) {
		setRemoved(false);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		population = 8;
		houses = 3;
		id = 0;
		constructed = bool;
	}
	public int getWidth() {
		return width << 5;
	}
	
	public int getHeight() {
		return height << 5;
	}
	
	public void tick() {
		if(constructed == false) {
			buildVillage();
			constructed = true;
		}
	}
	
	public void buildVillage() {
		int x0 = x;
		int y0 = y;
		int x1 = x + width;
		int y1 = y + height;
		
		//Add gates and entrances
		
		int g1 = (y1 - y0) / 2 + y0;
		int g2 = g1 - 1;
		int g3 = g1 + 1;
		int g4 = g1 + 2;
		for(int y = y0; y <= y1; y++) {
			for(int x = x0; x <= x1; x++) {
				if(x == x0 && y == y0) level.add(new VillageWallTop(x * 32, y * 32));
				else if(x == x0 && y == y1) level.add(new VillageWallBase(x * 32, y * 32));
				else if(x == x1 && y == y0) level.add(new VillageWallTop(x * 32, y * 32));
				else if(x == x1 && y == y1) level.add(new VillageWallBase(x * 32, y * 32));
				else if(y == y0) level.add(new VillageWallTop(x * 32, y * 32));
				else if(y == y1) level.add(new VillageWallTop(x * 32, y * 32));
				else if(x == x0 && y != g1 && y != g2 && y != g3 && y != g4) level.add(new VillageWall(x * 32, y * 32));
				else if(x == x0 && y == g4) level.add(new VillageWallTop(x * 32, y * 32));
				else if(x == x1) level.add(new VillageWall(x * 32, y * 32));
			}
		}
		
		for(int i = 0; i < houses; i++) {
			int xo = random.nextInt((width - 4) << 5) + ((x + 2) << 5);
			int yo = random.nextInt((height - 4) << 5) + ((y + 2) << 5);
			level.add(new VillageHouse(xo, yo));
		}
		
		for(int i = 0; i < population; i++) {
			int xo = random.nextInt((width - 4) << 5) + ((x + 2) << 5);
			int yo = random.nextInt((height - 5) << 5) + ((y + 2) << 5);
			//level.add(new Villager(xo, yo));
		}
	}
}
