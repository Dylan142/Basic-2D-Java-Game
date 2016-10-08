package com.AGameStudio.NewMech.level;

import com.AGameStudio.NewMech.gfx.Screen;
import com.AGameStudio.NewMech.gfx.Sprite;
import com.AGameStudio.NewMech.level.tile.DirtTile;
import com.AGameStudio.NewMech.level.tile.GrassTile;
import com.AGameStudio.NewMech.level.tile.ImpenetrableTile;
import com.AGameStudio.NewMech.level.tile.StoneTile;
import com.AGameStudio.NewMech.level.tile.VoidTile;
import com.AGameStudio.NewMech.level.tile.WaterTile;
import com.AGameStudio.NewMech.level.tile.WoodTile;
import com.AGameStudio.NewMech.level.tile.ore.BronzeOreTile;
import com.AGameStudio.NewMech.level.tile.ore.GoldOreTile;
import com.AGameStudio.NewMech.level.tile.ore.IronOreTile;

public class Tile {
	
	public byte id;
	public Sprite sprite;
	
	public static Tile voidTile = new VoidTile(Sprite.voidSprite, 0);
	public static Tile impenetrable = new ImpenetrableTile(Sprite.impenetrable, 9);
	public static Tile grass = new GrassTile(Sprite.grass, 1);
	public static Tile dirt = new DirtTile(Sprite.dirt, 2);
	public static Tile water = new WaterTile(Sprite.water, 3);
	public static Tile wood = new WoodTile(Sprite.wood, 4);
	public static Tile stone = new StoneTile(Sprite.stone, 5);
	public static Tile bronzeOre = new BronzeOreTile(Sprite.bronzeOre, 6);
	public static Tile ironOre = new IronOreTile(Sprite.ironOre, 7);
	public static Tile goldOre = new GoldOreTile(Sprite.goldOre, 8);
	
	public Tile(Sprite sprite, int id) {
		this.sprite = sprite;
		this.id = (byte) id;
	}
	
	public boolean solid() {
		return false;
	}
	
	public boolean breakable() {
		return false;
	}
	
	public void render(Screen screen, int x, int y) {
		
	}
	
	public void tick() {
		
	}
}
