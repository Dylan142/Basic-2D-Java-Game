package com.AGameStudio.NewMech.level.tile;

import com.AGameStudio.NewMech.gfx.Screen;
import com.AGameStudio.NewMech.gfx.Sprite;
import com.AGameStudio.NewMech.level.Tile;

public class WaterTile extends Tile {
	
	private int time = 0;
	
	public WaterTile(Sprite sprite, int id) {
		super(sprite, id);
	}
	
	public void render(Screen screen, int x, int y) {
		screen.renderSprite(x << 5, y << 5, sprite, true, 0);
	}
	
	public void tick() {
		time++;
		if(time % 400 < 100) sprite = Sprite.water;
		else if(time % 400 >= 100 && time % 400 < 200) sprite = Sprite.water2;
		else if(time % 400 >= 200 && time % 400 < 300) sprite = Sprite.water;
		else sprite = Sprite.water3;
	}
}
