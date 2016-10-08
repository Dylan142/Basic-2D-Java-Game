package com.AGameStudio.NewMech.level.tile;

import com.AGameStudio.NewMech.gfx.Screen;
import com.AGameStudio.NewMech.gfx.Sprite;
import com.AGameStudio.NewMech.level.Tile;

public class WoodTile extends Tile {
	
	public WoodTile(Sprite sprite, int id) {
		super(sprite, id);
	}
	
	public void render(Screen screen, int x, int y) {
		screen.renderSprite(x << 5, y << 5, sprite, true, 0);
	}
}
