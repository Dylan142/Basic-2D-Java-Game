package com.AGameStudio.NewMech.Entity.environment;

import com.AGameStudio.NewMech.gfx.Screen;
import com.AGameStudio.NewMech.gfx.Sprite;

public class VillageWallBase extends Entity {
	
	public VillageWallBase(int x, int y) {
		this.x = x;
		this.y = y;
		this.sprite = Sprite.villageWallBase;
		id = 9;
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
