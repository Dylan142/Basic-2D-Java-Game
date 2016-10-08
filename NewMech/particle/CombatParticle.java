package com.AGameStudio.NewMech.particle;

import com.AGameStudio.NewMech.gfx.Font;
import com.AGameStudio.NewMech.gfx.Screen;

public class CombatParticle extends Particle {
	
	private double zz, xx, yy;
	private double za, xa, ya;
	private Font font = new Font();
	
	public CombatParticle(String msg, int x, int y) {
		this.msg = msg;
		this.x = x;
		this.y = y;
		zz = 4.0;
		yy = y;
		xx = x;
		xa = random.nextGaussian() * 0.3;
		ya = random.nextGaussian() * 0.2;
		za = random.nextFloat() * 0.7 + 2;
	}
	
	public void tick() {
		time++;
		
		xx += xa;
		yy += ya;
		zz += za;
		if(zz < 0) {
			za *= -0.5;
			xa *= 0.6;
			ya *= 0.6;
		}
		za -= 0.15;
		
		y = (int) yy;
		x = (int) xx;
		if(time > 60) setRemoved(true);
	}
	
	public void render(Screen screen) {
		font.render(screen, msg, x - msg.length(), y - (int) zz, true);
	}
}
