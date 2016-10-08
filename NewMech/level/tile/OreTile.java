package com.AGameStudio.NewMech.level.tile;

import com.AGameStudio.NewMech.gfx.Sprite;
import com.AGameStudio.NewMech.level.Tile;

public abstract class OreTile extends Tile {
	
	public OreTile(Sprite sprite, int id) {
		super(sprite, id);
	}
	
	public boolean breakable() {
		return true;
	}
}
