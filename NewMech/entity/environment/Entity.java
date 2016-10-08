package com.AGameStudio.NewMech.Entity.environment;

import java.util.Random;

import com.AGameStudio.NewMech.Entity.mob.Player;
import com.AGameStudio.NewMech.gfx.Screen;
import com.AGameStudio.NewMech.gfx.Sprite;
import com.AGameStudio.NewMech.item.Item;
import com.AGameStudio.NewMech.level.Level;
import com.AGameStudio.NewMech.particle.CombatParticle;

public class Entity {
	
	protected int x, y;
	public int dir = 2;
	protected int time = 0;
	protected Level level;
	protected Sprite sprite;
	protected Random random = new Random();
	private boolean removed = false;
	
	public boolean solid;
	public int id;
	public int health;
	public int maxHealth;
	public double armor;
	public int damage = 0;
	public int hurtTime = 0;
	public boolean swimming;
	protected String name;
	
	public Entity() {
		
	}
	
	public Entity(String name) {
		this.name = name;
	}
	
	public Entity(int x, int y, Sprite sprite) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}
	
	public void init(Level level) {
		this.level = level;
	}
	
	public void setRemoved(boolean bool) {
		removed = bool;
	}
	
	public boolean isRemoved() {
		return removed;
	}
	
	public boolean isSolid() {
		return false;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int i) {
		this.x = i;
	}
	
	public void setY(int i) {
		this.y = i;
	}
	
	public int getWidth() {
		return getSprite().getWidth();
	}
	
	public int getHeight() {
		return getSprite().getHeight();
	}
	
	public Sprite getSprite() {
		return sprite;
	}
	
	public String getName() {
		return name;
	}
	
	public void render(Screen screen) {
		
	}
	
	public void tick() {
		if(time < Integer.MAX_VALUE - 10000) time++;
		else time = 100;
		if(hurtTime > 0) hurtTime--;
		
		if(hurtTime <= 0 && health <= 0) die();
	}
	
	public boolean hurt(int dmg, int dir) {
		if(hurtTime <= 0 && dmg > 0) {
			dmg -= (armor * dmg);
			health -= dmg;
			level.add(new CombatParticle("" + dmg, x, y));
			if(!(this instanceof Player)) {
				if(armor < .7) {
					if(dir == 2) move(0, 10);
					else if(dir == 3) move(-10, 0);
					else if(dir == 0) move(0, -10);
					else if(dir == 1) move(10, 0);			
				}							
			}
			hurtTime = 30;
			return true;
		}
		return false;
	}
	
	public void move(int xa, int ya) {
		
	}
	
	public boolean interact(Player player, Item item, int dir) {
		return false;
	}
	
	public boolean intersects(int x0, int y0, int x1, int y1) {
		return!(x + getWidth() < x0 || y + getHeight() < y0 || x - getWidth() > x1 || y - getHeight() > y1);
	}
	
	public void attack() {
		
	}
	
	public void die() {
		switch(this.getName()) {
		case "enemy":
			level.player.addExp(4);
			break;
		case "squirrel":
			level.player.addExp(2);
			break;
		case "bandit":
			level.player.addExp(5);
			break;
		case "villager":
			level.player.addExp(3);
			break;
		}
		level.player.level();
		setRemoved(true);
	}
}
