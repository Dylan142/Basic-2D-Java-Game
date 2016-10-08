package com.AGameStudio.NewMech.Entity.environment;

import com.AGameStudio.NewMech.gfx.Screen;
import com.AGameStudio.NewMech.gfx.Sprite;

public class Tree extends Entity {
	
	public Tree(int x, int y) {
		this.x = x;
		this.y = y;
		this.id = 15;
		this.sprite = Sprite.tree;
	}
	
	public void tick() {
		
	}
	
	public void render(Screen screen) {
		screen.renderSprite(x, y, sprite, true, 0);
	}
}
