package com.AGameStudio.NewMech.Entity.environment;

import com.AGameStudio.NewMech.gfx.Screen;
import com.AGameStudio.NewMech.gfx.Sprite;

public class VillageWall extends Entity {
	
	public VillageWall(int x, int y) {
		this.x = x;
		this.y = y;
		this.sprite = Sprite.villageWall;
		id = 2;
	}
	
	public void tick() {
		
	}
	
	public void render(Screen screen) {
		screen.renderSprite(x, y, sprite, true, 0);
	}
	
	public boolean isSolid() {
		return true;
	}
}
