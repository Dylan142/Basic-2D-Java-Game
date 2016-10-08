package com.AGameStudio.NewMech.Entity.mob;

import java.util.List;

import com.AGameStudio.NewMech.XML;
import com.AGameStudio.NewMech.Entity.environment.Entity;
import com.AGameStudio.NewMech.gfx.AnimatedSprite;
import com.AGameStudio.NewMech.gfx.Screen;
import com.AGameStudio.NewMech.gfx.Sprite;
import com.AGameStudio.NewMech.gfx.SpriteSheet;
import com.AGameStudio.NewMech.item.Item;
import com.AGameStudio.NewMech.level.Tile;
import com.AGameStudio.NewMech.quests.Quest;
import com.AGameStudio.NewMech.util.Physics;

public class Mob extends Entity {
	
	private AnimatedSprite down;
	private AnimatedSprite up;
	private AnimatedSprite left;
	private AnimatedSprite right;
	private AnimatedSprite animation;
	
	public int rank;
	public int exp;
	
	public boolean attacking = false;
	public boolean moving = false;
	public boolean animated = false;
	
	private int xa = 0;
	private int ya = 0;
	private int speed;
	
	public Mob() {
		
	}
	
	public Mob(String name) {
		super(name);
		init();
	}
	
	public Mob(String name, int x, int y) {
		super(name);
		this.x = x;
		this.y = y;
		init();
	}
	
	public void init() {
		switch(name) {
		case "enemy":
			this.id = 3;
			this.sprite = Sprite.enemy_down;
			this.rank = 1;
			this.health = this.maxHealth = 15;
			this.armor = .03;
			this.damage = 3;
			this.speed = 2;
			break;
		case "gangster jack":
			this.id = 4;
			this.sprite = Sprite.jackd;
			this.down = new AnimatedSprite(SpriteSheet.jackDown, 64, 64, 3);
			this.up = new AnimatedSprite(SpriteSheet.jackUp, 64, 64, 3);
			this.left = new AnimatedSprite(SpriteSheet.jackLeft, 64, 64, 3);
			this.right = new AnimatedSprite(SpriteSheet.jackRight, 64, 64, 3);
			this.animation = down;
			this.animated = true;
			this.rank = 9;
			this.health = this.maxHealth = 40;
			this.armor = .2;
			this.damage = 7;
			this.speed = 1;
			break;
		case "villager":
			this.id = 6;
			this.sprite = Sprite.villager;
			this.rank = 0;
			this.health = this.maxHealth = 6;
			this.armor = .01;
			this.speed = 1;
			break;
		case "shopkeep":
			this.id = 7;
			this.sprite = Sprite.shopkeep;
			this.rank = 1;
			this.health = this.maxHealth = 2;
			this.armor = 1;
			this.speed = 1;
			break;
		case "man":
			this.id = 13;
			this.sprite = Sprite.man;
			this.rank = 1;
			this.health = this.maxHealth = 2;
			this.armor = 1;
			this.speed = 1;
			break;
		case "squirrel":
			this.id = 14;
			this.sprite = Sprite.squirrel;
			this.down = new AnimatedSprite(SpriteSheet.squirrelDown, 32, 32, 3);
			this.up = new AnimatedSprite(SpriteSheet.squirrelUp, 32, 32, 3);
			this.left = new AnimatedSprite(SpriteSheet.squirrelLeft, 32, 32, 3);
			this.right = new AnimatedSprite(SpriteSheet.squirrelRight, 32, 32, 3);
			this.animation = this.down;
			this.animated = true;
			this.rank = 1;
			this.health = this.maxHealth = 3;
			this.armor = 0;
			this.damage = 1;
			this.speed = 1;
			break;
		case "trainer":
			this.id = 16;
			this.sprite = Sprite.trainer;
			this.rank = 13;
			this.health = this.maxHealth = 46;
			this.armor = .5;
			this.damage = 12;
			this.speed = 2;
			break;
		case "bandit":
			this.id = 17;
			this.sprite = Sprite.bandit;
			this.down = new AnimatedSprite(SpriteSheet.banditDown, 32, 64, 3);
			this.up = new AnimatedSprite(SpriteSheet.banditUp, 32, 64, 3);
			this.left = new AnimatedSprite(SpriteSheet.banditLeft, 32, 64, 3);
			this.right = new AnimatedSprite(SpriteSheet.banditRight, 32, 64, 3);
			this.animation = this.down;
			this.animated = true;
			this.rank = random.nextInt(2) + 1;
			this.health = this.maxHealth = random.nextInt(3) + 11;
			this.armor = .1;
			this.damage = 4;
			this.speed = 1;
			break;
		case "Hagar The Feared":
			this.id = 18;
			this.sprite = Sprite.hagar;
			this.rank = 40;
			this.health = this.maxHealth = 175;
			this.armor = .56;
			this.damage = 30;
			this.speed = 6;
			break;
		default:
			System.err.println("entity name is invalid! " + name);
			break;
		}
	}
	
	public boolean interact(Player player, Item item, int dir) {
		switch(name) {
		case "man":
			getManMsg(player);
			break;
		case "trainer":
			getTrainerMsg(player);
			break;
		}
		return true;
	}
	
	public void getTrainerMsg(Player player) {
		//quest 1
		if(level.log.hasStarted("trainer1")) {
			Quest quest = new Quest("trainer1");
			if(level.log.get(quest).isComplete() && !level.log.get(quest).rewarded()) {
				player.exp += level.log.get(quest).getExperience();
				level.log.get(quest).setRewarded(true);
				level.addMessage(XML.read("//entity[@id='16']/quest[@id='1']/option[@id='3']", "text"));
			}
			else if(!level.log.get(quest).isComplete()) level.addMessage(XML.read("//entity[@id='16']/quest[@id='1']/option[@id='2']", "text"));
			else level.addMessage(XML.read("//entity[@id='16']/quest[@id='1']/option[@id='3']", "text"));
		}
		else if(level.log.startable(new Quest("trainer1"))) {
			level.setQuest(new Quest("trainer1"));
			level.addMessage(XML.read("//entity[@id='16']/quest[@id='1']/option[@id='1']", "text"));
		}
		//quest 2
		
		else if(level.log.hasStarted("trainer2")) {
			Quest quest = new Quest("trainer2");
			if(level.log.get(quest).isComplete() && !level.log.get(quest).rewarded()) {
				player.exp += level.log.get(quest).getExperience();
				level.log.get(quest).setRewarded(true);
				level.addMessage(XML.read("//entity[@id='16']/quest[@id='2']/option[@id='3']", "text"));
			}
			else if(!level.log.get(quest).isComplete()) level.addMessage(XML.read("//entity[@id='16']/quest[@id='2']/option[@id='2']", "text"));
			else level.addMessage(XML.read("//entity[@id='16']/quest[@id='2']/option[@id='3']", "text"));
		}
		else if(level.log.startable(new Quest("trainer2"))) {
			level.setQuest(new Quest("trainer2"));
			level.addMessage(XML.read("//entity[@id='16']/quest[@id='2']/option[@id='2']", "text"));
			}
		//quest 3
		
		else if(level.log.hasStarted("trainer3")) {
			Quest quest = new Quest("trainer3");
			if(level.log.get(quest).isComplete() && !level.log.get(quest).rewarded()) {
				player.exp += level.log.get(quest).getExperience();
				player.inventory.add(level.log.get(quest).getReward());
				level.log.get(quest).setRewarded(true);
				level.addMessage(XML.read("//entity[@id='16']/quest[@id='3']/option[@id='3']", "text"));
			}
			else if(!level.log.get(quest).isComplete()) level.addMessage(XML.read("//entity[@id='16']/quest[@id='2']/option[@id='3']", "text"));
			else level.addMessage(XML.read("//entity[@id='16']/quest[@id='3']/option[@id='3']", "text"));
		}
		
		else if(level.log.startable(new Quest("trainer3"))) {
			level.setQuest(new Quest("trainer3"));
			level.addMessage(XML.read("//entity[@id='16']/quest[@id='3']/option[@id='3']", "text"));
		}
	}
	
	public void getManMsg(Player player) {
		if(level.log.startable(new Quest("starter"))) {
			level.setQuest(new Quest("starter"));
			level.addMessage(XML.read("//entity[@id='13']/quest/option[@id='1']", "text"));
		}	
		else if(level.log.hasStarted("starter")) {
			Quest quest = new Quest("starter");
			if(level.log.get(quest).isComplete() && !level.log.get(quest).rewarded()) {
				if(level.log.get(quest).getReward() != null) player.inventory.add(level.log.get(quest).getReward());
				player.exp += level.log.get(quest).getExperience();
				level.log.get(quest).setRewarded(true);
				level.addMessage(XML.read("//entity[@id='13']/quest/option[@id='2']", "text"));
			}
			else if(!level.log.get(quest).isComplete()) level.addMessage(XML.read("//entity[@id='13']/quest/option[@id='3']", "text"));
			else level.addMessage(XML.read("//entity[@id='13']/quest/option[@id='4']", "text"));
		}
	}
	
	public void move(int xa, int ya) {
		if(xa != 0 && ya != 0) {
			move(xa, 0);
			move(0, ya);
			return;
		}
		
		if(xa < 0) dir = 3;
		if(xa > 0) dir = 1;
		if(ya < 0) dir = 0;
		if(ya > 0) dir = 2;
		
		
		if(!collisions(xa, 0)) x += xa;
		if(!collisions(0, ya)) y += ya;
		
		xa = 0;
		ya = 0;
		
	}
	
	public void render(Screen screen) {
		if(animated) sprite = animation.getSprite();
		screen.renderSprite(x, y, sprite, true, 0);
	}
	
	public void walk(int frequency) {
		if(time % (random.nextInt(frequency) + frequency / 2) == 0) {
			if(random.nextInt(5) < 2) {
				int c = random.nextInt(4);
				switch(c) {
				case 0:
					xa = speed;
					animation = right;
					break;
				case 1:
					ya = speed;
					animation = down;
					break;
				case 2:
					xa = -speed;
					animation = left;
					break;
				case 3:
					ya = -speed;
					animation = up;
					break;
				default:
					System.err.println("entity move dir 'c' value is invalid!");
				}
			}
			else {
				xa = 0;
				ya = 0;
			}
		}
	}
	
	public void tick() {
		super.tick();
		
		if(animated) {
			switch(name) {
			case "squirrel":
				walk(40);
				break;
			case "bandit":
				walk(100);
				break;
			}
			
			if(xa != 0 || ya != 0) {
				move(xa, ya);
				animation.tick();
			}
			else animation.setFrame(0);	
		}
		else {
			switch(name) {
			case "enemy":
				if(!moving) {
					if(time % (random.nextInt(60) + 120) == 0) {
						if(random.nextInt(4) < 2) {
							int c = random.nextInt(4);
							switch(c) {
							case 0:
								xa = speed;
								sprite = Sprite.enemy_hor;
								break;
							case 1:
								ya = speed;
								sprite = Sprite.enemy_down;
								break;
							case 2:
								xa = -speed;
								sprite = Sprite.enemy_hor;
								break;
							case 3:
								ya = -speed;
								sprite = Sprite.enemy_up;
								break;
							default:
								System.err.println("entity move dir 'c' is invalid!");
								break;
							}
							moving = true;
						}
						else {
							xa = 0;
							ya = 0;
						}
					}			
				}
				else if(time % 30 == 0) {
					xa = 0;
					ya = 0;
				}
			break;
			}
			if(xa != 0 || ya != 0) {
				move(xa, ya);
				moving = true;
			}
			else moving = false;
		}
	}
	
	public void addExp(int exp) {
		this.exp += exp;
	}
	
	public void level() {
		if(exp > (rank * rank + Math.ceil(rank * 1.5) + 6) * rank) rank++;
	}
	
	public boolean spawn() {
		int x = random.nextInt(level.getWidth() - 64) + 64;
		int y = random.nextInt(level.getHeight() - 64) + 64;
		if(level.getTile(x >> 5, y >> 5).solid()) return false;
		this.x = x;
		this.y = y;
		return true;
	}
	
	public void attack() {
		
	}
	
	protected boolean collisions(int xa, int ya) {
		int xMin = 0;
		int xMax = 0;
		int yMin = 0;
		int yMax = 0;
		switch(name) {
		case "enemy":
			xMin = 3;
			xMax = 29;
			yMin = 3;
			yMax = 29;
			break;
		case "squirrel":
			xMin = 10;
			xMax = 25;
			yMin = 3;
			yMax = 28;
			break;
		case "bandit":
			xMin = 3;
			xMax = 29;
			yMin = 3;
			yMax = 32;
			break;
		default:
			System.err.println("entity collision name is invalid!");
			break;
		}
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
				if(e.isSolid()) return true;
			}
		}
		return false;
	}
	
	protected boolean collision(int xa, int ya, int x, int y) {
		Tile lastTile = level.getTile((this.x + x) >> 5, (this.y + y) >> 5);
		Tile newTile = level.getTile((this.x + x + xa) >> 5, (this.y + y + ya) >> 5);
		if(!lastTile.equals(newTile) && newTile.solid()) return true;
		return false;
	}
}
