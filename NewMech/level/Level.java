package com.AGameStudio.NewMech.level;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import com.AGameStudio.NewMech.Entity.environment.Entity;
import com.AGameStudio.NewMech.Entity.mob.Player;
import com.AGameStudio.NewMech.gfx.Font;
import com.AGameStudio.NewMech.gfx.Screen;
import com.AGameStudio.NewMech.particle.Particle;
import com.AGameStudio.NewMech.quests.Quest;
import com.AGameStudio.NewMech.quests.QuestLog;
import com.AGameStudio.NewMech.screen.InventoryMenu;

public class Level {
	private InventoryMenu menu;
	public Quest quest;
	public QuestLog log;
	public Font font;
	private int timer = 0;
	
	private ArrayList<String> messages = new ArrayList<String>();
	
	private Random random = new Random();
	protected int width, height;
	public int[] tilesInt;
	public int[] tiles;
	protected boolean png = false;
	
	private int x0, x1, y0, y1;
	private int x, y, x2, y2;
	
	public Player player;
	private List<Particle> particles = new ArrayList<Particle>();
	private List<Entity> entities = new ArrayList<Entity>();
	
	private Comparator<Entity> sorter = new Comparator<Entity>() {
		public int compare(Entity e0, Entity e1) {
			if(e0 instanceof Player) return +1;
			return 0;
		}
	};
	
	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		tilesInt = new int[width * height];
		genLevel();
	}
	
	public Level(String path) {
		loadLevel(path);
		png = true;
	}
	
	public void loadLevel(String path) {
		try{
			BufferedImage image = ImageIO.read(Level.class.getResource(path));
			this.width = image.getWidth();
			this.height = image.getHeight();
			tiles = new int[width * height];
			image.getRGB(0, 0, width, height, tiles, 0, width);
		}catch(IOException e) {
			e.printStackTrace();
			System.out.println("Exception! Could not load level file!");
		}
	}
	
	public void setMenu(InventoryMenu menu) {
		this.menu = menu;
	}
	
	public InventoryMenu getMenu() {
		return menu;
	}
	
	public void setOpen(boolean open) {
		menu.setOpen(open);
	}
	
	public void genLevel() {
		
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void addMessage(String msg) {
		if(messages.size() == 5) messages.remove(messages.get(4));
		messages.add(0, msg);
		timer = 60 * 15;
	}
	
	public void render(Screen screen, int xOffset, int yOffset) {
		screen.setOffsets(xOffset, yOffset);
		x0 = xOffset >> 5;
		x1 = (xOffset + screen.width + 32) >> 5;
		y0 = yOffset >> 5;
		y1 = (yOffset + screen.height + 32) >> 5;
		
		for(int x = x0; x < x1; x++) {
			for(int y = y0; y < y1; y++) {
				getTile(x, y).render(screen, x, y);
			}
		}
		
		x0 = xOffset - 96;
		x1 = xOffset + screen.width + 96;
		y0 = yOffset - 96;
		y1 = yOffset + screen.height + 96;
		x = xOffset - screen.width * 4;
		x2 = xOffset + screen.width * 4;
		y = yOffset - screen.height * 4;
		y2 = yOffset + screen.height * 4;
		
		Collections.sort(entities, sorter);
		for(int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			if(within(x0, y0, x1, y1, e)) e.render(screen);
		}
		
		for(int i = 0; i < particles.size(); i++) {
			particles.get(i).render(screen);
		}
		
		if(menu != null) menu.render(screen, xOffset, yOffset);
		if(log != null) log.render(screen);
		if(font != null) renderChat(screen);
	}
	
	public void renderChat(Screen screen) {
		font.renderFrame(screen, "", 10, 75, 80, 85, true);
		if(messages.size() > 0) {
			for(int i = 0; i < messages.size(); i++) {
				String msg = messages.get(i);
				if(msg != null) font.render(screen, msg, 6 * 8, (38 + i) * 16, false);
			}			
		}
	}
	
	public boolean within(int x0, int y0, int x1, int y1, Entity e) {
		if(e.getX() > x0 && e.getX() < x1) {
			if(e.getY() > y0 && e.getY() < y1) return true;
		}
		return false;
	}
	
	public void add(Particle p) {
		p.init(this);
		particles.add(p);
	}
	
	public List<Particle> getParticles() {
		return particles;
	}
	
	public void add(Entity e) {
		e.init(this);
		entities.add(e);
	}
	
	public List<Entity> getEntities() {
		return entities;
	}
	
	public void tick() {
		if(x0 < 0) x0 = 0;
		if(x1 > width << 4) x1 = width << 5;
		if(y0 < 0) y0 = 0;
		if(y1 > height << 4) y1 = height << 5;
		if(x < 0) x = 0;
		if(x2 > width << 4) x2 = width << 5;
		if(y < 0) y = 0;
		if(y2 > height << 4) y2 = height << 5;
		
		for(int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			
			if(e.isRemoved()) {
				if(quest != null){
					if(quest.getType() == e.id) quest.add(1);
				}
				entities.remove(i);
			}
			else if(within(x, y, x2, y2, e)) e.tick();
		}
		
		for(int i = 0; i < particles.size(); i++) {
			if(particles.get(i).isRemoved()) particles.remove(i);
			else particles.get(i).tick();
		}
		
		if(menu != null) menu.tick();
		if(log != null) log.tick();
		if(timer > 0) timer--;
		else if(messages.size() > 0) {
			messages.remove(messages.size() - 1);
			timer = 60 * 4;
		}
		
		for(int i = 0; i < width * height / 400; i++) {
			int xt = random.nextInt(width);
			int yt = random.nextInt(width);
			getTile(xt, yt).tick();
		}
	}
	
	public Tile getTile(int x, int y) {
		if(x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile;
		if(png) {
			if(tiles[x + y * width] == 0) return Tile.voidTile;
			else if(tiles[x + y * width] == 0xff00ff00) return Tile.grass;
			else if(tiles[x + y * width] == 0xff946739) return Tile.dirt;
			else if(tiles[x + y * width] == 0xff949375) return Tile.stone;
			else if(tiles[x + y * width] == 0xff0000ff) return Tile.water;
			else if(tiles[x + y * width] == 0xffff51bb) return Tile.impenetrable;
			
		}else {
			if(tiles[x + y * width] == 0) return Tile.voidTile;
			else if(tiles[x + y * width] == 1) return Tile.grass;
			else if(tiles[x + y * width] == 2) return Tile.dirt;
			else if(tiles[x + y * width] == 5) return Tile.stone;
			else if(tiles[x + y * width] == 3) return Tile.water;
			else if(tiles[x + y * width] == 9) return Tile.impenetrable;
		}
		return Tile.voidTile;
	}
	
	public void setTile(int x, int y, int id) {
		if(x >= 0 || y >= 0 || x < width || y < height) {
			if(id > 9) id = 9;
			tiles[(x >> 4) + (y >> 4) * width] = id;
		}
	}
	
	public void setQuest(Quest quest) {
		if(log.find(quest) == false) {
			log.add(quest, false);
			this.quest = quest;
		}
	}
}
