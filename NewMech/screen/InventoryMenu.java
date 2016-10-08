package com.AGameStudio.NewMech.screen;

import java.util.List;

import com.AGameStudio.NewMech.InputHandler;
import com.AGameStudio.NewMech.Entity.mob.Player;
import com.AGameStudio.NewMech.gfx.Font2x;
import com.AGameStudio.NewMech.gfx.Screen;
import com.AGameStudio.NewMech.item.Item;
import com.AGameStudio.NewMech.item.WeaponItem;

public class InventoryMenu {
	private Font2x font = new Font2x();
	private InputHandler input;
	private Player player;
	private int selected = 0;
	private boolean open = true;
	
	private List<? extends Item> items;
	
	public InventoryMenu(InputHandler input, Player player, List<? extends Item> items) {
		this.input = input;
		this.items = items;
		this.player = player;
	}
	
	public void tick() {
		if(input.up.clicked) selected--;
		if(input.down.clicked) selected++;
		if(selected < 0) selected += items.size();
		if(selected >= items.size()) selected -= items.size();
		if(input.enter.clicked) player.activeItem = (WeaponItem) items.get(selected);
	}
	
	public void setOpen(boolean open) {
		this.open = open;
	}
	
	public boolean getOpen() {
		return open;
	}
	
	public void render(Screen screen, int x, int y) {
		if(open) {
			font.renderFrame(screen, "Inventory", 100, 54, 122, 84, false);
			int xo = x + 104 * 16 / 2;
			for(int i = 0; i < items.size(); i++) {
				int yo = y + 57 * 16 / 2 + i * 16;
				Item item = items.get(i);
				font.render(screen, item.getName(), xo, yo, true);
				font.render(screen, " x" + item.count, xo + 8 * 10, yo, true);
			}
			font.render(screen, ">", x + 104 * 16 / 2 - 16, y + 57 * 16 / 2 + (selected * 16), true);			
		}
		else {
			font.renderFrame(screen, "", 100, 81, 103, 84, true);
			font.render(screen, "I", screen.getXOffset() + 101 * 8, screen.getYOffset() + 82 * 8, true);
		}
	}
}
