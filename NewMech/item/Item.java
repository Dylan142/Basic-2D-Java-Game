package com.AGameStudio.NewMech.item;

import java.util.Random;

import com.AGameStudio.NewMech.Entity.environment.Entity;
import com.AGameStudio.NewMech.Entity.mob.Player;
import com.AGameStudio.NewMech.gfx.Font2x;
import com.AGameStudio.NewMech.gfx.Screen;
import com.AGameStudio.NewMech.gfx.Sprite;
import com.AGameStudio.NewMech.level.Level;

public class Item {
	protected Random random = new Random();
	
	protected String name;
	
	protected Sprite sprite;
	protected Level level;
	protected Font2x font;
	
	public byte id;
	public int count = 1;
	public int range;
	public int animationTime;
	
	public Item(String name) {
		this.name = name;
		this.font = new Font2x();
	}
	
	public void renderIcon(Screen screen, int x, int y, int dir) {
		
	}
	
	public void renderInventory(Screen screen, int x, int y) {
		
	}
	
	public void tick() {
		
	}
	
	public Sprite getSprite() {
		return sprite;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean matches(Item item) {
		return item.getClass() == getClass();
	}
	
	public boolean isDelpeted() {
		return false;
	}
	
	public boolean canAttack() {
		return false;
	}
	
	public void take(Player player) {
		pickUp(this);
	}
	
	public void pickUp(Item item) {
		
	}
	
	public boolean interact(Player player, Entity e, int dir) {
		return false;
	}

	public void init(Level level) {
		this.level = level;
	}
}