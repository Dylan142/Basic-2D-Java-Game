package com.AGameStudio.NewMech.particle;

import java.util.Random;

import com.AGameStudio.NewMech.gfx.Screen;
import com.AGameStudio.NewMech.level.Level;

public abstract class Particle {
	protected Random random = new Random();
	
	protected Level level;
	
	protected String msg;
	protected int time = 0;
	protected boolean removed = false;
	
	protected int x, y;
	
	public Particle() {
		
	}
	
	public void tick() {
		
	}
	
	public abstract void render(Screen screen);
	
	public void setRemoved(boolean removed) {
		this.removed = removed;
	}
	
	public boolean isRemoved() {
		return removed;
	}
	
	public void init(Level level) {
		this.level = level;
	}
}
