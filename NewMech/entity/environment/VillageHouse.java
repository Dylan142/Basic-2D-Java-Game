package com.AGameStudio.NewMech.Entity.environment;

import com.AGameStudio.NewMech.gfx.Screen;
import com.AGameStudio.NewMech.gfx.Sprite;

public class VillageHouse extends Entity {
	
	public VillageHouse(int x, int y) {
		this.x = x;
		this.y = y;
		sprite = Sprite.villageHouse;
		id = 1;
	}
	
	public int getWidth() {
		return 64;
	}
	
	public int getHeight() {
		return 128;
	}
	
	public void tick() {
		
	}
	
	public void render(Screen screen) {
		screen.renderSprite(x, y, sprite, true, 0);
		screen.renderSprite(x - 32, y + 64, sprite, true, 0);
		screen.renderSprite(x + 32, y, sprite, true, 1);
		screen.renderSprite(x + 64, y + 64, sprite, true, 1);
		screen.renderSprite(x, y + 64, Sprite.villageHouseCenter, true, 0);
		screen.renderSprite(x + 32, y + 64, Sprite.villageHouseCenter, true, 1);
	}
	
	public boolean isSolid() {
		return true;
	}
}
