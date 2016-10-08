package com.AGameStudio.NewMech.gfx;

public class Sprites {
	
	public static Sprite playerf = new Sprite(SpriteSheet.player, 0, 0, 64, 64);
	
	public static Sprite enemy_up = new Sprite(SpriteSheet.enemy, 1, 0, 32, 32);
	public static Sprite enemy_hor = new Sprite(SpriteSheet.enemy, 2, 0, 32, 32);
	public static Sprite enemy_down = new Sprite(SpriteSheet.enemy, 0, 0, 32, 32);
	
	public static Sprite jackd = new Sprite(SpriteSheet.jackDown, 0, 0, 64, 64);
	
	public static Sprite shopkeep = new Sprite(SpriteSheet.npc, 0, 0, 64, 64);
	public static Sprite man = new Sprite(SpriteSheet.npc, 2, 0, 32, 64);
	public static Sprite squirrel = new Sprite(SpriteSheet.mob, 0, 0, 32, 32);
	public static Sprite trainer = new Sprite(SpriteSheet.npc, 2, 0, 64, 64);
	public static Sprite bandit = new Sprite(SpriteSheet.mob, 4, 0, 32, 64);
	public static Sprite hagar = new Sprite(SpriteSheet.npc, 0, 1, 32, 64);
	
	//Weapon sprites
	public static Sprite sword = new Sprite(SpriteSheet.weapon, 2, 0, 32, 64);
	public static Sprite axe = new Sprite(SpriteSheet.weapon, 3, 0, 32, 64);
	public static Sprite mace = new Sprite(SpriteSheet.weapon, 5, 0, 32, 64);
	
	//Edit sprites wood, stone, bronzeOre, ironOre, goldOre
	public static Sprite voidSprite = new Sprite(32, 32, 0);
	public static Sprite impenetrable = new Sprite(SpriteSheet.terrain, 5, 0, 32, 32);
	public static Sprite grass = new Sprite(SpriteSheet.terrain, 0, 0, 32, 32);
	public static Sprite dirt = new Sprite(SpriteSheet.terrain, 2, 0, 32, 32);
	public static Sprite water = new Sprite(SpriteSheet.terrain, 0, 3, 32, 32);
	public static Sprite water2 = new Sprite(SpriteSheet.terrain, 0, 4, 32, 32);
	public static Sprite water3 = new Sprite(SpriteSheet.terrain, 0, 3, 32, 32);
	public static Sprite wood = new Sprite(32, 32, 0);
	public static Sprite stone = new Sprite(SpriteSheet.terrain, 4, 0, 32, 32);
	public static Sprite bronzeOre = new Sprite(32, 32, 0xffff00ff);
	public static Sprite ironOre = new Sprite(32, 32, 0xff113377);
	public static Sprite goldOre = new Sprite(32, 32, 0xff115522);
	public static Sprite tree = new Sprite(SpriteSheet.terrain, 1, 3, 32, 64);
	
	//Village sprites
	public static Sprite villageWall = new Sprite(SpriteSheet.terrain, 3, 1, 32, 32);
	public static Sprite villageWallTop = new Sprite(SpriteSheet.terrain, 4, 1, 32, 32);
	public static Sprite villageWallBase = new Sprite(SpriteSheet.terrain, 5, 1, 32, 32);
	public static Sprite villageHouse = new Sprite(SpriteSheet.terrain, 3, 2, 32, 64);
	public static Sprite villageHouseCenter = new Sprite(SpriteSheet.terrain, 4, 2, 32, 64);
	public static Sprite villager = new Sprite(SpriteSheet.npc, 0, 0, 64, 64);
	
	public static Sprite health = new Sprite(27, 27, 0xffff2744);
	public static Sprite armor = new Sprite(27, 27, 0xff668bff);
	
	public static Sprite frame_corner = new Sprite(SpriteSheet.font, 0, 2, 8, 8);
	public static Sprite frame_top = new Sprite(SpriteSheet.font, 1, 2, 8, 8);
	public static Sprite frame_side = new Sprite(SpriteSheet.font, 2, 2, 8, 8);
	public static Sprite frame_filler = new Sprite(SpriteSheet.font, 3, 2, 8, 8);
}
