package com.AGameStudio.NewMech;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Random;

import javax.swing.JFrame;

import com.AGameStudio.NewMech.Entity.environment.Entity;
import com.AGameStudio.NewMech.Entity.environment.Tree;
import com.AGameStudio.NewMech.Entity.environment.Village;
import com.AGameStudio.NewMech.Entity.environment.VillageHouse;
import com.AGameStudio.NewMech.Entity.environment.VillageWall;
import com.AGameStudio.NewMech.Entity.environment.VillageWallBase;
import com.AGameStudio.NewMech.Entity.environment.VillageWallTop;
import com.AGameStudio.NewMech.Entity.mob.Mob;
import com.AGameStudio.NewMech.Entity.mob.Player;
import com.AGameStudio.NewMech.gfx.Font;
import com.AGameStudio.NewMech.gfx.Screen;
import com.AGameStudio.NewMech.item.Item;
import com.AGameStudio.NewMech.item.WeaponItem;
import com.AGameStudio.NewMech.level.Level;
import com.AGameStudio.NewMech.level.Tile;
import com.AGameStudio.NewMech.quests.Quest;
import com.AGameStudio.NewMech.quests.QuestLog;
import com.AGameStudio.NewMech.screen.Menu;
import com.AGameStudio.NewMech.screen.PauseMenu;
import com.AGameStudio.NewMech.screen.TitleMenu;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	
	public static final int SCALE = 1;
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 700;
	private Dimension dim = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
	private JFrame frame;
	
	private boolean running = false;
	public boolean loadData;
	
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	
	private Random random = new Random();
	private Menu menu;
	private Screen screen;
	private Level level;
	private InputHandler input;
	private Player player;
	
	public Game() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon.png")));
		setMaximumSize(dim);
		setMinimumSize(dim);
		setPreferredSize(dim);
		screen = new Screen(WIDTH, HEIGHT);
		level = new Level("/levels/level1.png");
		input = new InputHandler(this);
		player = new Player(500, 500, input);
	}
	
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	
	public synchronized void start() {
		running = true;
		new Thread(this).start();
	}
	
	public synchronized void stop() {
		running = false;
	}
	
	public void save() {
		String[] data = new String[50000];
		File.clear();
		File.write(compile(data));
	}
	
	public void load() {
		decompile(File.copy());
	}
	
	public String[] compile(String[] data) {
		data[0] = Integer.toString(player.health);
		data[1] = Integer.toString(player.maxHealth);
		data[2] = Integer.toString(player.damage);
		data[3] = Integer.toString(player.dir);
		data[4] = Integer.toString(player.hurtTime);
		data[5] = Integer.toString(player.attackTime);
		data[6] = Integer.toString(player.range);
		data[7] = Byte.toString(player.activeItem.id);
		data[8] = Double.toString(player.armor);
		data[9] = Boolean.toString(player.attacking);
		data[10] = Integer.toString(player.rank);
		data[11] = Integer.toString(player.exp);
		int j = 12;
		data[j++] = Integer.toString(player.inventory.items.size());
		for(int i = 0; i < player.inventory.items.size(); i++) {
			data[j++] = Integer.toString(player.inventory.items.get(i).id);
			data[j++] = Integer.toString(player.inventory.items.get(i).count);
		}
		data[j++] = Boolean.toString(player.menu);
		data[j++] = Boolean.toString(player.moving);
		data[j++] = Boolean.toString(player.swimming);
		data[j++] = Integer.toString(player.getX());
		data[j++] = Integer.toString(player.getY());
		
		data[j++] = Integer.toString(level.log.getStarted().size());
		for(int i = 0; i < level.log.getStarted().size(); i++) {
			data[j++] = level.log.getStarted().get(i).getName();
			data[j++] = Integer.toString(level.log.getStarted().get(i).getProgress());
			data[j++] = Boolean.toString(level.log.getStarted().get(i).rewarded());
		}
		data[j++] = Integer.toString(level.log.getCompleted().size());
		for(int i = 0; i < level.log.getCompleted().size(); i++) {
			data[j++] = level.log.getCompleted().get(i).getName();
			data[j++] = Integer.toString(level.log.getCompleted().get(i).getProgress());
			data[j++] = Boolean.toString(level.log.getCompleted().get(i).rewarded());
		}
		data[j++] = Integer.toString(level.getEntities().size());
		for(int i = 0; i < level.getEntities().size(); i++) {
			Entity e = level.getEntities().get(i);
			if(!(e instanceof Player)) {
				data[j++] = Integer.toString(e.id);
				data[j++] = Integer.toString(e.getX());
				data[j++] = Integer.toString(e.getY());
				data[j++] = Integer.toString(e.health);
				data[j++] = Integer.toString(e.damage);
				data[j++] = Integer.toString(e.maxHealth);
				data[j++] = Double.toString(e.armor);
				data[j++] = Boolean.toString(e.swimming);				
			}
		}
		
		for(int y = 0; y < level.getHeight(); y++) {
			for(int x = 0; x < level.getWidth(); x++) {
				if(y >= 0 || x >= 0 || y < level.getHeight() || x < level.getWidth()) {
					Tile t = level.getTile(x, y);
					data[j++] = Integer.toString(t.id);
					data[j++] = Integer.toString(x);
					data[j++] = Integer.toString(y);
				}
			}
		}
		return data;
	}
	
	public void decompile(String[] data) {
		player.health = Integer.parseInt(data[0]);
		player.maxHealth = Integer.parseInt(data[1]);
		player.damage = Integer.parseInt(data[2]);
		player.dir = Integer.parseInt(data[3]);
		player.hurtTime = Integer.parseInt(data[4]);
		player.attackTime = Integer.parseInt(data[5]);
		player.range = Integer.parseInt(data[6]);
		int active = player.activeItem.id = Byte.parseByte(data[7]);
		player.armor = Double.parseDouble(data[8]);
		player.attacking = Boolean.parseBoolean(data[9]);
		player.rank = Integer.parseInt(data[10]);
		player.exp = Integer.parseInt(data[11]);
		int j = 12;
		int length = Integer.parseInt(data[j++]);
		for(int i = 0; i < length; i++) {
			int id = Byte.parseByte(data[j++]);
			int count = Byte.parseByte(data[j++]);
			if(i != 0) {
				Item item = null;
				switch(id) {
					case 10:
						item = new Item("axe");
						break;
					case 11:
						item = new Item("maxe");
						break;
					case 12:
						item = new Item("sword");
						break;
					default:
						System.err.println("entity item id is invalid!");
						break;
				}
				player.inventory.add(item);
				if(active == id) player.activeItem = (WeaponItem) item;
				player.inventory.items.get(i).count = count;
			}
		}
		player.menu = Boolean.parseBoolean(data[j++]);
		player.moving = Boolean.parseBoolean(data[j++]);
		player.swimming = Boolean.parseBoolean(data[j++]);
		player.setX(Integer.parseInt(data[j++]));
		player.setY(Integer.parseInt(data[j++]));
		
		int l = Integer.parseInt(data[j++]);
		for(int i = 0; i < l; i++) {
			String name = data[j++];
			int progress = Integer.parseInt(data[j++]);
			boolean rewarded = Boolean.parseBoolean(data[j++]);
			level.log.add(new Quest(name), false);
			level.log.getStarted().get(i).setProgress(progress);
			level.log.getStarted().get(i).setRewarded(rewarded);
		}
		int len = Integer.parseInt(data[j++]);
		for(int i = 0; i < len; i++) {
			String name = data[j++];
			int progress = Integer.parseInt(data[j++]);
			boolean rewarded = Boolean.parseBoolean(data[j++]);
			level.log.add(new Quest(name), true);
			level.log.getCompleted().get(i).setProgress(progress);
			level.log.getCompleted().get(i).setRewarded(rewarded);
		}
		level.setQuest(level.log.getStarted().size() > 0 ? level.log.getStarted().get(0) : null);
		int size = Integer.parseInt(data[j++]);
		for(int i = 0; i < size - 1; i++) {
			int id = Integer.parseInt(data[j++]);
			int x = Integer.parseInt(data[j++]);
			int y = Integer.parseInt(data[j++]);
			int health = Integer.parseInt(data[j++]);
			int damage = Integer.parseInt(data[j++]);
			int maxHealth = Integer.parseInt(data[j++]);
			double armor = Double.parseDouble(data[j++]);
			boolean swimming = Boolean.parseBoolean(data[j++]);
			Entity e = null;
			switch(id) {
				case 0:
					e = new Village(x, y, 30, 30, true);
					level.add(e);
					break;
				case 1:
					e = new VillageHouse(x, y);
					level.add(e);
					break;
				case 2:
					e = new VillageWall(x, y);
					level.add(e);
					break;
				case 3:
					e = new Mob("enemy", x, y);
					level.add(e);
					break;
				case 4:
					e = new Mob("gangster jack", x, y);
					level.add(e);
					break;
				case 6:
					e = new Mob("villager", x, y);
					level.add(e);
					break;
				case 7:
					e = new Mob("shopkeep", x, y);
					level.add(e);
					break;
				case 8:
					e = new VillageWallTop(x, y);
					level.add(e);
					break;
				case 9:
					e = new VillageWallBase(x, y);
					level.add(e);
					break;
				case 13:
					e = new Mob("man", x, y);
					level.add(e);
					break;
				case 14:
					e = new Mob("squirrel", x, y);
					level.add(e);
					break;
				case 15:
					e = new Tree(x, y);
					level.add(e);
					break;
				case 16:
					e = new Mob("trainer", x, y);
					level.add(e);
					break;
				case 17:
					e = new Mob("bandit", x, y);
					level.add(e);
					break;
				default:
					System.err.println("entity id is invalid!");
					break;
			}
			e.health = health;
			e.damage = damage;
			e.maxHealth = maxHealth;
			e.armor = armor;
			e.swimming = swimming;
		}
		
		for(int y = 0; y < level.getHeight(); y++) {
			for(int x = 0; x < level.getWidth(); x++) {
				if(y >= 0 || x >= 0 || y < level.getHeight() || x < level.getWidth()) {
					level.setTile(Integer.parseInt(data[j++]), Integer.parseInt(data[j++]), Integer.parseInt(data[j++]));
				}
			}
		}
	}
	
	public void init() {
		level.log = new QuestLog();
		level.font = new Font();
		level.player = player;
		if(loadData == false) {
			level.add(new Mob("enemy", 576, 320));
			level.add(new Mob("enemy", 700, 320));
			level.add(new Mob("enemy", 612, 400));
			level.add(new Village(25, 20, 30, 30, false));
			level.add(new Mob("man", 400, 384));
			level.add(new Mob("trainer", 500, 900));
			for(int i = 0; i < 9; i++) {
				level.add(new Mob("bandit", random.nextInt(400) + 2200, random.nextInt(400) + 2000));
			}
			for(int i = 0; i < 4; i++) {
				level.add(new Mob("squirrel", random.nextInt(200) + 800, random.nextInt(100) + 300));
				level.add(new Mob("squirrel", random.nextInt(400) + 100, random.nextInt(400) + 1000));
			}
			for(int i = 0; i < 10; i++) {
				int x = random.nextInt((level.getWidth() * 32) + 100) - 100;
				int y = random.nextInt((level.getHeight() * 32) + 100) - 100;
				for(int j = 0; j < 4; j++) {
					level.add(new Mob("bandit", x, y));
				}
			}
			for(int i = 0; i < 50; i++) {
				if(random.nextInt(50) == 0) {
					int x = random.nextInt(level.getWidth() * 32 - 50) + 100;
					int y = random.nextInt(level.getHeight() * 32 - 50) + 100;
					for(int j = 0; j < 30; j++) {
						level.add(new Tree(random.nextInt(150) + x, random.nextInt(150) + y));
					}
				}
				level.add(new Tree(random.nextInt(level.getWidth() * 32 - 50) + 100, random.nextInt(level.getHeight() * 32 - 50) + 100));
			}
		}
		level.add(player);
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double nsPerTick = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0;
		int ticks = 0;
		requestFocus();
		setMenu(new TitleMenu(this, input));
		
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / nsPerTick;
			lastTime = now;
			boolean shouldRender = true;
			requestFocus();
			
			while(delta >= 1) {
				tick();
				ticks++;
				delta--;
				shouldRender = true;
			}
			
			try{
				Thread.sleep(2);
			}catch(InterruptedException ex) {
				ex.printStackTrace();
			}
			
			if(shouldRender) {
				render();
				frames++;
				shouldRender = false;
			}
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frame.setTitle("fps: " + ticks + " ticks: " + frames);
				frames = 0;
				ticks = 0;
			}
		}
		stop();
	}
	
	public void tick() {
		if(menu != null) menu.tick();
		else level.tick();
		if(input.exit.clicked) setMenu(new PauseMenu(this, input));
		input.tick();
	}
	
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		screen.clear(0);
		int xo = (player.getX() + player.getWidth() / 2) - screen.width / 2;
		int yo = (player.getY() + player.getHeight() / 2) - screen.height / 2;
		level.render(screen, xo, yo);
		
		if(menu != null) menu.render(screen);
		
		for(int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}
		
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0 , 0, getWidth(), getHeight(), null);
		g.dispose();
		bs.show();
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.add(game);
		game.frame.pack();
		game.frame.setResizable(false);
		game.frame.setVisible(true);
		
		game.start();
	}

}
