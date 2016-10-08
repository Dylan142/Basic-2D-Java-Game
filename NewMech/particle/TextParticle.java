package com.AGameStudio.NewMech.particle;

import com.AGameStudio.NewMech.gfx.Font;
import com.AGameStudio.NewMech.gfx.Screen;

public class TextParticle extends Particle {
	
	private Font font = new Font();
	
	public TextParticle(String msg, int x, int y) {
		this.msg = msg;
		this.x = x;
		this.y = y;
	}
	
	public void tick() {
		time++;
		if(time > 150) setRemoved(true);
	}
	
	public void render(Screen screen) {
		font.render(screen, msg, x, y, true);
	}
}
