package com.AGameStudio.NewMech.level.tile.ore;

import com.AGameStudio.NewMech.gfx.Screen;
import com.AGameStudio.NewMech.gfx.Sprite;
import com.AGameStudio.NewMech.level.tile.OreTile;

public class GoldOreTile extends OreTile {
	
	public GoldOreTile(Sprite sprite, int id) {
		super(sprite, id);
	}
	
	public void render(Screen screen, int x, int y) {
		screen.renderSprite(x << 5, y << 5, sprite, true, 0);
	}
}
