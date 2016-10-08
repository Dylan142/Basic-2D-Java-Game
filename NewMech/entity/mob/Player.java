package com.AGameStudio.NewMech.Entity.mob;

import java.util.List;

import com.AGameStudio.NewMech.InputHandler;
import com.AGameStudio.NewMech.Entity.environment.Entity;
import com.AGameStudio.NewMech.gfx.AnimatedSprite;
import com.AGameStudio.NewMech.gfx.Font2x;
import com.AGameStudio.NewMech.gfx.Screen;
import com.AGameStudio.NewMech.gfx.Sprite;
import com.AGameStudio.NewMech.gfx.SpriteSheet;
import com.AGameStudio.NewMech.item.Inventory;
import com.AGameStudio.NewMech.item.WeaponItem;
import com.AGameStudio.NewMech.level.Tile;
import com.AGameStudio.NewMech.screen.InventoryMenu;
import com.AGameStudio.NewMech.util.Physics;

public class Player extends Mob {
	
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.playerUp, 64, 64, 3);
	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.playerDown, 64, 64, 3);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.playerLeft, 64, 64, 3);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.playerRight, 64, 64, 3);
	private AnimatedSprite animatedSprite = down;
	
	private InputHandler input;
	private Font2x font;
	
	public Inventory inventory = new Inventory();
	public WeaponItem activeItem = new WeaponItem("axe");
	
	public int attackTime;
	public int range;
	
	public boolean menu = false;
	
	public Player(int x, int y, InputHandler input) {
		this.x = x;
		this.y = y;
		this.input = input;
		this.id = 5;
		init();
	}
	
	public void init() {
		this.name = "player";
		this.sprite = Sprite.playerf;
		this.inventory.add(activeItem);
		this.attackTime = 0;
		this.maxHealth = health = 25;
		this.armor = .025;
		this.range = 20;
		font = new Font2x();
	}
	
	public void tick() {
		super.tick();
		if(moving) animatedSprite.tick();
		else animatedSprite.setFrame(0);
		
		if(activeItem != null) range = activeItem.range;
		
		if(activeItem != null) activeItem.tick();
		if(attacking && attackTime < activeItem.animationTime) attackTime++;
		else {
			attacking = false;
			attackTime = 0;
		}
		
		if(armor >= 100) armor = 100;
		
		int xa = 0;
		int ya = 0;
		double speed = 2.8;
		
		if(input.w.pressed) {
			ya -= speed;
			animatedSprite = up;
		}
		else if(input.s.pressed) {
			ya += speed;
			animatedSprite = down;
		}
		if(input.a.pressed) {
			xa -= speed;
			animatedSprite = left;
		}
		else if(input.d.pressed) {
			xa += speed;
			animatedSprite = right;
		}
		
		if(input.space.clicked) {
			if(activeItem.canAttack()) attack();
		}
		
		if(input.e.clicked) {
			if(dir == 0 && interact(x, y - range, x + getWidth(), y));
			else if(dir == 1 && interact(x + getWidth(), y, x + getWidth() + range, y + getHeight()));
			else if(dir == 2 && interact(x, y + getHeight(), x + getWidth(), y + getHeight() + range));
			else if(dir == 3 && interact(x - range, y, x + getWidth(), y + getHeight()));
		}
		
		if(input.i.clicked && !menu) {
			level.setOpen(true);
			menu = true;
		}
		else if(input.i.clicked) {
			level.setOpen(false);
			menu = false;
		}
		
		if(level.getMenu() == null) level.setMenu(new InventoryMenu(input, this, inventory.items));
		
		if(level.getTile(x >> 5, y >> 5) == Tile.water) swimming = true;
		else swimming = false;
		
		if(!collisions(xa, 0) && !collisions(0, ya)) {
			if(xa != 0 || ya != 0) {
				move(xa, ya);
				moving = true;
			}
			else moving = false;			
		}
	}
	
	public void render(Screen screen) {
		sprite = animatedSprite.getSprite();
		
		if(activeItem != null && dir == 0) activeItem.renderIcon(screen, x, y, dir);		
		
		
		screen.renderSprite(x, y, sprite, true, 0);
		if(attacking) activeItem.renderIcon(screen, x + 25, y + 25, dir);
		
		if(activeItem != null && dir != 0) activeItem.renderIcon(screen, x, y, dir);
		
		for(int i = 0; i < health / 2; i++) {
			if(health <= maxHealth * .2 && time % 30 < 15) screen.renderSprite(10 + i * 9, 10, Sprite.health, false, 0);
			else if(health > maxHealth * .2) screen.renderSprite(10 + i * 9, 10, Sprite.health, false, 0);
		}
		
		for(double i = 0; i < armor / 2; i += 0.05) {
			screen.renderSprite((int)(10 + i * 3), 39, Sprite.armor, false, 0);
		}
		
		font.render(screen, "Health", 12, 16, false);
		font.render(screen, "Armor", 12, 45, false);
		font.render(screen, "Rank " + rank, 200, 16, false);
		font.render(screen, "Exp " + exp, 200, 45, false);
	}
	
	public boolean collisions(int xa, int ya) {
		int xMin = 18;
		int xMax = 47;
		int yMin = 31;
		int yMax = 63;
		
		for(int x = xMin; x < xMax; x++) {
			if(collision(xa, ya, x, yMin)) return true;
		}
		for(int x = xMin; x < xMax; x++) {
			if(collision(xa, ya, x, yMax)) return true;
		}
		for(int y = yMin; y < yMax; y++) {
			if(collision(xa, ya, xMin, y)) return true;
		}
		for(int y = yMin; y < yMax; y++) {
			if(collision(xa, ya, xMax, y)) return true;	
		}
		
		List<Entity> entities = Physics.rectangleCollide(x + xa + xMin, y + ya + yMin, xMax - xMin, yMax - yMin, level);
		if(entities.size() > 1) {
			for(int i = 0; i < entities.size(); i++) {
				Entity e = entities.get(i);
				if(!(e instanceof Player)) {
					if(e.damage > 0) {
						hurt(e.damage, e.dir);
						entities.remove(e);
					}
					else if(e.isSolid()) return true;					
				}
			}
		}
		
		return false;
	}
	
	public void attack() {
		if(dir == 0) hurt(x, y - range, getWidth(), range);
		else if(dir == 1) hurt(x + getWidth(), y, range, getHeight());
		else if(dir == 2) hurt(x, y + getHeight(), getWidth(), range);
		else if(dir == 3) hurt(x - range, y, range, getHeight());
		attacking = true;
	}
	
	private boolean hurt(int x0, int y0, int x1, int y1) {
		List<Entity> entities = Physics.rectangleCollide(x0, y0, x1, y1, level);
		for(int i = 0; i < entities.size(); i++) {
			if(entities.get(i) != this) if(entities.get(i).hurt(activeItem.critical() + activeItem.damage, dir)) return true;
		}
		return false;
	}
	
	private boolean interact(int x0, int y0, int x1, int y1) {
		List<Entity> entities = Physics.rectangleCollide(x0, y0, x1 - x0, y1 - y0, level);
		for(int i = 0; i < entities.size(); i++) {
			if(entities.get(i) != this) if(entities.get(i).interact(this, activeItem, dir)) return true;
		}
		return false;
	}
}
